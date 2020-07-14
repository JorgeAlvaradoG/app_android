package com.example.appaprendizaje2.sqlite;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseConexion extends SQLiteAssetHelper {//CLASE DE CONEXION A LA BD INSTANCIANDO LA CLASE PRINCIPAL DE SQLITE

    private static final String DATABASE_NAME = "app.db";//CREAMOS UNA VARIABLE UNICA(STATIC) Y QUE NO ADMITE CAMBIOS(FINAL)
                                                            //DE FORMA PRIVADA(PRIVATE) Y GUARDAMOS EL NOMBRE DE LA BD
    private static final int DATABASE_VERSION = 1;//CREAMOS UNA VARIABLE UNICA(STATIC) Y QUE NO ADMITE CAMBIOS(FINAL)
                                                  //DE FORMA PRIVADA(PRIVATE) Y GURADAMOS LA VERSION DE LA BD

    //CONSTRUCTOR
    public DataBaseConexion(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);//SUPERPONEMOS EL CONTEXTO DE ESTA CLASE GUARDANDO LAS DOS VARIABLES
                                                                     //PARA EL USSO EXTERNO DE ELLAS
    }

}
