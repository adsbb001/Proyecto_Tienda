package com.tienda.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.entity.LoteProducto;
import com.tienda.repository.LoteProductoRepository;

@Service
public class LoteProductoServiceImpl
implements LoteProductoService {

	@Autowired
	private LoteProductoRepository repoLote;

	@Override
	public List<LoteProducto> listar() {
		return repoLote.findAll();
	}

	@Override
	public LoteProducto buscar(Integer id) {
		return repoLote.findById(id).orElse(null);
	}

	@Override
	public void guardar(LoteProducto lote) {
		repoLote.save(lote);
	}

	@Override
	public void eliminar(Integer id) {
		repoLote.deleteById(id);
	}

	@Override
	public List<LoteProducto> buscarLotesAvanzados(int categoria, int marca, int filtroVencimiento) {
		LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaLimite = fechaHoy.plusDays(30);
        return repoLote.buscarLotesAvanzados(categoria, marca, filtroVencimiento, fechaHoy, fechaLimite);
    }

}