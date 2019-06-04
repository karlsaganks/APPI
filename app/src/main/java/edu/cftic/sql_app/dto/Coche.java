package edu.cftic.sql_app.dto;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by vale on 1/06/16.
 */
public class Coche {

    private int id;
    private String modelo;
    private Persona persona;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Coche (int id, String modelo)
    {
        this.id = id;
        this.modelo = modelo;
    }

    public Coche (String modelo)
    {
        this.modelo = modelo;
    }

    public Persona getPersona() {
        return persona;
    }

    public Coche () {}

    public Coche (String modelo, Persona persona)
    {
        this.modelo = modelo;
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                '}';
    }

}
