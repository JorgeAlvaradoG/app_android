package com.example.appaprendizaje2.modelo;

public class ObjDetalleActividad {

    private int id;
    private String descripcion;
    private int imagen;
    private int audio;
    private int actividad;

    public ObjDetalleActividad() {
    }

    public ObjDetalleActividad(int id, String descripcion, int imagen, int audio, int actividad) {
        this.id = id;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.audio = audio;
        this.actividad = actividad;
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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }

    public int getActividad() {
        return actividad;
    }

    public void setActividad(int actividad) {
        this.actividad = actividad;
    }

}
