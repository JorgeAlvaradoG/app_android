package com.example.appaprendizaje2.modelo;

public class ObjResultadoActividad {

    private int id;
    private int actividad;
    private String usuario;
    private int clasificacion;
    private String nivel;
    private String intentos;
    private String preguntas;
    private String acertividad;
    private int complejidad;

    public ObjResultadoActividad() {
    }

    public ObjResultadoActividad(int id, int actividad, String usuario, int clasificacion, String nivel, String intentos, String preguntas, String acertividad, int complejidad) {
        this.id = id;
        this.actividad = actividad;
        this.usuario = usuario;
        this.clasificacion = clasificacion;
        this.nivel = nivel;
        this.intentos = intentos;
        this.preguntas = preguntas;
        this.acertividad = acertividad;
        this.complejidad = complejidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActividad() {
        return actividad;
    }

    public void setActividad(int actividad) {
        this.actividad = actividad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getIntentos() {
        return intentos;
    }

    public void setIntentos(String intentos) {
        this.intentos = intentos;
    }

    public String getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(String preguntas) {
        this.preguntas = preguntas;
    }

    public String getAcertividad() {
        return acertividad;
    }

    public void setAcertividad(String acertividad) {
        this.acertividad = acertividad;
    }

    public int getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(int complejidad) {
        this.complejidad = complejidad;
    }

}
