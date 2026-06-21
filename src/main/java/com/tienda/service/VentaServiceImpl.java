package com.tienda.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tienda.dto.DetalleVentaDTO;
import com.tienda.dto.VentaDTO;
import com.tienda.entity.Cliente;
import com.tienda.entity.DetalleVenta;
import com.tienda.entity.MetodoPago;
import com.tienda.entity.Producto;
import com.tienda.entity.Venta;
import com.tienda.repository.ClienteRepository;
import com.tienda.repository.DetalleVentaRepository;
import com.tienda.repository.MetodoPagoRepository;
import com.tienda.repository.ProductoRepository;
import com.tienda.repository.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository repoVenta;

    @Autowired
    private DetalleVentaRepository repoDetalleVenta;

    @Autowired
    private ProductoRepository repoProducto;

    @Autowired
    private ClienteRepository repoCliente;

    @Autowired
    private MetodoPagoRepository repoMetodoPago;

    @Override
    public List<Venta> listar() {
        return repoVenta.findAll();
    }

    @Override
    public Venta buscar(Integer id) {
        return repoVenta.findById(id).orElse(null);
    }

    @Override
    public void guardar(Venta venta) {
        repoVenta.save(venta);
    }

    @Override
    public void eliminar(Integer id) {
        repoVenta.deleteById(id);
    }

    @Override
    public void guardarDetalle(DetalleVenta detalle) {
        repoDetalleVenta.save(detalle);
    }

    @Override
    public List<DetalleVenta> listarDetalles() {
        return repoDetalleVenta.findAll();
    }

    @Override
    @Transactional
    public Venta procesarVenta(VentaDTO dto) {

        // 1. Validar que haya detalles
        if (dto.getDetalles() == null || dto.getDetalles().isEmpty()) {
            throw new RuntimeException("Debe agregar al menos un producto a la venta");
        }

        // 2. Crear la entidad Venta
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());

        // Cliente (puede ser null para venta anónima)
        if (dto.getIdCliente() != null) {
            Cliente cliente = repoCliente.findById(dto.getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            venta.setCliente(cliente);
        }

        // Método de pago (obligatorio)
        MetodoPago metodoPago = repoMetodoPago.findById(dto.getIdMetodoPago())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        venta.setMetodoPago(metodoPago);

        // 3. Procesar cada detalle
        BigDecimal total = BigDecimal.ZERO;

        for (DetalleVentaDTO d : dto.getDetalles()) {

            // Buscar producto
            Producto producto = repoProducto.findById(d.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // Validar stock
            if (producto.getStock() < d.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + producto.getNombre()
                        + ". Stock disponible: " + producto.getStock());
            }

            // Descontar stock
            producto.setStock(producto.getStock() - d.getCantidad());
            repoProducto.save(producto);

            // Crear detalle
            DetalleVenta detalle = new DetalleVenta();
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioVenta(producto.getPrecioVenta());  // precio congelado
            BigDecimal subtotal = producto.getPrecioVenta()
                    .multiply(BigDecimal.valueOf(d.getCantidad()));
            detalle.setSubtotal(subtotal);

            // Agregar detalle a la venta
            venta.agregarDetalle(detalle);

            // Acumular total
            total = total.add(subtotal);
        }

        // 4. Asignar total y guardar
        venta.setTotal(total);
        return repoVenta.save(venta);
    }

	@Override
	@Transactional (readOnly = true)
	public Page<Venta> listarPaginado(Pageable pageable) {
		
		return repoVenta.findAll(pageable);
	}

	@Override
	@Transactional
	public void anularVenta(Integer idVenta) {
		Venta venta = repoVenta.findById(idVenta)
	            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
	    
	    if ("ANULADA".equals(venta.getEstado())) {
	        throw new RuntimeException("La venta ya está anulada");
	    }
	    
	    // Reintegrar stock de todos los productos
	    for (DetalleVenta detalle : venta.getDetalles()) {
	        Producto producto = detalle.getProducto();
	        producto.setStock(producto.getStock() + detalle.getCantidad());
	        repoProducto.save(producto);
	    }
	    
	    // Marcar venta como anulada
	    venta.setEstado("ANULADA");
	    repoVenta.save(venta);
		
	}
}