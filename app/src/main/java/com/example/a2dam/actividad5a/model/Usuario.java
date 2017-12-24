package com.example.a2dam.actividad5a.model;

/**
 * Created by 2dam on 18/12/2017.
 */
public class Usuario {
    private String usuario;
    private String correo;
    private String nombre;
    private String apellidos;
    private String direccion;

    public Usuario(){

    }

    public Usuario(String usuario, String correo, String nombre, String apellidos, String direccion) {
        this.usuario = usuario;
        this.correo = correo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
