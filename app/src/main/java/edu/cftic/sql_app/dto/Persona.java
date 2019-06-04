package edu.cftic.sql_app.dto;

/**
 * Created by vale on 1/06/16.
 */
public class Persona {

    private int id;
    private String nombre;

    public Persona () {}

    public Persona (String nombre)
    {
        this.nombre = nombre;
    }

    public Persona(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
