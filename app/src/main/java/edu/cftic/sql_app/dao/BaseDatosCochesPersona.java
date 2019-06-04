package edu.cftic.sql_app.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import edu.cftic.sql_app.dto.Coche;
import edu.cftic.sql_app.dto.Persona;


public class BaseDatosCochesPersona extends SQLiteOpenHelper {


    private final String sqlCreacionTablaPersona = "CREATE TABLE PERSONA (id INTEGER PRIMARY KEY, nombre TEXT)";

    private final String sqlCreacionTablaCoches = "CREATE TABLE COCHE (id INTEGER PRIMARY KEY AUTOINCREMENT, modelo TEXT, idpersona INTEGER, FOREIGN KEY (idpersona) REFERENCES PERSONA (id))";

    public BaseDatosCochesPersona(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version); //el método padre, llamará a Oncreate o OnUpgrade, segn corresponda
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sqlCreacionTablaPersona);
        db.execSQL(sqlCreacionTablaCoches);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        //En caso de que al constructor le pasemos un número de versión distinto a
        // la base de datos existente, este métdo es invocado. Esto sería necesario
        //cuando modificamos la estrucutura de la base de datos

        //Aquí, deberíamos
        // 1 - Extraer los datos de la vieja versión y copiarlos a la nueva instancia
        // 2 - Crear la nueva versión
        // 3 - Cargar los datos en las tablas de la nueva versión

    }

    private void cerrarBaseDatos (SQLiteDatabase database)
    {
        database.close();
    }

    public void insertarPersona (Persona persona)
    {

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("INSERT INTO PERSONA (id, nombre) VALUES ("+ persona.getId()+" , '"+ persona.getNombre()+"')");
        this.cerrarBaseDatos(database);

    }

    public void insertarCoche (Coche coche)
    {

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("INSERT INTO COCHE (modelo, idpersona) VALUES ('"+coche.getModelo()+"' , "+coche.getPersona().getId()+")");
        this.cerrarBaseDatos(database);
    }

    public Persona buscarPersona (String nombre)
    {
        Persona persona = null;
        int aux_id = -1;
        String nombre_aux = null;

        String consulta = "SELECT id, nombre FROM PERSONA WHERE nombre LIKE '%"+nombre+"%' ;";
        Log.i("APP", consulta);

        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);


        if( cursor != null && cursor.getCount() >0)
        {
            cursor.moveToFirst();

            aux_id = cursor.getInt(0); //la posicion primera, el id
            nombre_aux = cursor.getString(1); //la posicion segunda, el id
            persona = new Persona(aux_id, nombre_aux);

            cursor.close();
        }

        this.cerrarBaseDatos(basedatos);

        return persona;
    }



    public List<Coche> buscarCochesPersona (Persona persona)
    {
        List<Coche> lista_coches = null;
        Coche coche = null;
        int aux_id = -1;
        String modelo = null;


        String consulta = "SELECT modelo FROM COCHE WHERE idpersona ="+persona.getId();

        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);


        if( cursor != null && cursor.getCount() >0)
        {
            cursor.moveToFirst();
            lista_coches = new ArrayList<Coche>(cursor.getCount());

            do
            {

                modelo = cursor.getString(0); //la posicion primera, el id
                coche = new Coche(modelo);
                lista_coches.add(coche);

            }while (cursor.moveToNext());

            cursor.close();
        }

        this.cerrarBaseDatos(basedatos);
        return lista_coches;
    }

    public List<Coche> buscarCoches()
    {
        List<Coche> lista_coches = null;
        Coche coche = null;
        int aux_id = -1;

        String consulta = "SELECT * FROM COCHE ";

        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);

        if( cursor != null && cursor.getCount() >0)
        {
            cursor.moveToFirst();
            lista_coches = new ArrayList<Coche>(cursor.getCount());

            do
            {

                int _id = cursor.getInt(0); //la posicion primera, el id
                String model = cursor.getString(1);
                coche = new Coche(_id, model);
                lista_coches.add(coche);

            }while (cursor.moveToNext());

            cursor.close();
        }

        this.cerrarBaseDatos(basedatos);
        return lista_coches;
    }
}
