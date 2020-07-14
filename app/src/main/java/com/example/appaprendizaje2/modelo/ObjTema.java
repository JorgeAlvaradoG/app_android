package com.example.appaprendizaje2.modelo;

public class ObjTema {

    private int id;
    private String descripcion;
    private int eje_tema;

    public ObjTema() {
    }

    public ObjTema(int id, String descripcion, int eje_tema) {
        this.id = id;
        this.descripcion = descripcion;
        this.eje_tema = eje_tema;
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

    public int getEje_tema() {
        return eje_tema;
    }

    public void setEje_tema(int eje_tema) {
        this.eje_tema = eje_tema;
    }

}
