package com.example.appaprendizaje2.modelo;

public class ObjEje {

    private int id;
    private String descripcion;

    public ObjEje() {
    }

    public ObjEje(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
