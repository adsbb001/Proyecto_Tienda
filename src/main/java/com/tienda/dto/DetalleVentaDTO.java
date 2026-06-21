package com.tienda.dto;

public class DetalleVentaDTO {

    private Integer idProducto;
    private Integer cantidad;

    public DetalleVentaDTO() {
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}