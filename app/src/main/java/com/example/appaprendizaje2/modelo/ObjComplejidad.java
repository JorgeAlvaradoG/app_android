package com.example.appaprendizaje2.modelo;

public class ObjComplejidad {

    private int id;
    private String descripcion;

    public ObjComplejidad() {
    }

    public ObjComplejidad(int id, String descripcion) {
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
