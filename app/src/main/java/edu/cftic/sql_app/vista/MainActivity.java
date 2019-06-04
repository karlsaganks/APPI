package edu.cftic.sql_app.vista;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.cftic.sql_app.R;
import edu.cftic.sql_app.dao.BaseDatosCochesPersona;
import edu.cftic.sql_app.dto.Coche;
import edu.cftic.sql_app.dto.Persona;

public class MainActivity extends AppCompatActivity {

    private Button bCargar;
    private Button bBuscar;
    private Button oxid, oxmod;

    private RecyclerView recView;
    private ArrayList<Coche> datos;
    private AdaptadorCoche adaptador;

    BaseDatosCochesPersona baseDatosCochesPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File dbPath = getDatabasePath("MiDB.db");

        //creo el objeto de la base de datos
        baseDatosCochesPersona = new BaseDatosCochesPersona(this, "MiDB.db", null, 1);

        bCargar = (Button) findViewById(R.id.btncargar);
        bBuscar = (Button) findViewById(R.id.btnbuscar);
        oxid = (Button) findViewById(R.id.oid);
        oxmod = (Button) findViewById(R.id.omod);

        bCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCargar();
            }
        });

        bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBuscar();
            }
        });

        bBuscar.setEnabled(dbPath.exists());

        oxid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOrdenId();
            }
        });

        oxmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOrdenMod();
            }
        });

    }

    private void doOrdenId() {
        Log.i("APP", "Orden por ID");
        Collections.sort(datos, new Comparator<Coche>() {
            @Override
            public int compare(Coche o1, Coche o2) {
                return  new Integer (o1.getId()).compareTo(o2.getId());
            }
        });
        adaptador.notifyDataSetChanged();

    }

    private void doOrdenMod() {
        Log.i("APP", "Orden por Modelo");
        Collections.sort(datos, new Comparator<Coche>() {
            @Override
            public int compare(Coche o1, Coche o2) {
                return  new Integer (o1.getModelo().compareTo(o2.getModelo()));
            }
        });
        adaptador.notifyDataSetChanged();
    }

    private void doCargar(){

        Persona persona1 = new Persona(1, "Juan");
        Persona persona2 = new Persona(2, "Conchi");
        Persona persona3 = new Persona(3, "Manolo");

        //inserto las personas
        baseDatosCochesPersona.insertarPersona(persona1);
        baseDatosCochesPersona.insertarPersona(persona2);
        baseDatosCochesPersona.insertarPersona(persona3);

        Coche coche1 = new Coche("Ferrari", persona2);
        Coche coche2 = new Coche("Renault", persona2);
        Coche coche3 = new Coche("Fiat", persona1);

        //inserto los coches
        baseDatosCochesPersona.insertarCoche(coche1);
        baseDatosCochesPersona.insertarCoche(coche2);
        baseDatosCochesPersona.insertarCoche(coche3);
        baseDatosCochesPersona.insertarCoche( new Coche("BMW", persona1));
        baseDatosCochesPersona.insertarCoche( new Coche("Mercedes", persona2));
        baseDatosCochesPersona.insertarCoche( new Coche("Romeo", persona3));
        baseDatosCochesPersona.insertarCoche( new Coche("Ford", persona1));

        bBuscar.setEnabled(true);
    }

    private void doBuscar(){
        //consulto los coches de la persona
        Persona p = new Persona();
        p = baseDatosCochesPersona.buscarPersona("Conchi");

        List<Coche> listacoches = baseDatosCochesPersona.buscarCochesPersona(p);

        Log.d(getClass().getCanonicalName(), "Los coches de " + p.getNombre() + " son :");
        for (Coche coche: listacoches)
        {
            Log.d(getClass().getCanonicalName(), coche.getModelo());
        }

        listacoches = baseDatosCochesPersona.buscarCoches();
        Log.d(getClass().getCanonicalName(), "Los coches son :");
        for (Coche coche: listacoches)
        {
            Log.d(getClass().getCanonicalName(), coche.toString());
        }

        datos = (ArrayList<Coche>) baseDatosCochesPersona.buscarCoches();
        recView = (RecyclerView) findViewById(R.id.tvcoches);
        adaptador = new AdaptadorCoche(datos);
        recView.setAdapter(adaptador);
        recView.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recView.addItemDecoration( new DividerItemDecoration( this, DividerItemDecoration.VERTICAL));
        recView.setHasFixedSize(true);
        adivinaKhace((ViewGroup) findViewById(R.id.root));
    }

    private void adivinaKhace(
        @NonNull ViewGroup vista_raiz) {
        int tresmasdos = 0;
        ViewGroup chorizo = null;
        View piscina = null;
        List<ViewGroup> lista_contactos = new ArrayList<ViewGroup>();
        lista_contactos.add(vista_raiz);
        for (int i = 0; i<lista_contactos.size(); i++){
            chorizo = lista_contactos.get(i);
            Log.i("APP", chorizo.getClass().getCanonicalName());
            tresmasdos = chorizo.getChildCount();
            for(int j = 0; j<tresmasdos; j++){
                piscina = chorizo.getChildAt(j);
                if(piscina instanceof ViewGroup){
                    lista_contactos.add((ViewGroup)piscina);
                } else {
                    Log.i("APP", piscina.getClass().getCanonicalName());
                }
            }
        }

    }

}
