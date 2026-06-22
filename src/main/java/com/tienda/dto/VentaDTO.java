package com.tienda.dto;

import java.util.ArrayList;
import java.util.List;

public class VentaDTO {

    private Integer idCliente;           // puede ser null (venta anónima)
    private Integer idMetodoPago;
    private List<DetalleVentaDTO> detalles = new ArrayList<>();

    public VentaDTO() {
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public List<DetalleVentaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaDTO> detalles) {
        this.detalles = detalles;
    }
}