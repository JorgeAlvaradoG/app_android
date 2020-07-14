package com.example.appaprendizaje2.modelo;

public class ObjActividad {

    private int id;
    private String descripcion;
    private String nombre;
    private int tema;
    private int habilidad;
    private int complejidad;

    public ObjActividad() {
    }

    public ObjActividad(int id, String descripcion, String nombre, int tema, int habilidad, int complejidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.tema = tema;
        this.habilidad = habilidad;
        this.complejidad = complejidad;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTema() {
        return tema;
    }

    public void setTema(int tema) {
        this.tema = tema;
    }

    public int getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(int habilidad) {
        this.habilidad = habilidad;
    }

    public int getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(int complejidad) {
        this.complejidad = complejidad;
    }

}
