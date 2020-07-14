package com.example.appaprendizaje2.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appaprendizaje2.modelo.ObjActividad;
import com.example.appaprendizaje2.modelo.ObjAudio;
import com.example.appaprendizaje2.modelo.ObjComplejidad;
import com.example.appaprendizaje2.modelo.ObjDetalleActividad;
import com.example.appaprendizaje2.modelo.ObjEje;
import com.example.appaprendizaje2.modelo.ObjHabilidad;
import com.example.appaprendizaje2.modelo.ObjImagen;
import com.example.appaprendizaje2.modelo.ObjResultadoActividad;
import com.example.appaprendizaje2.modelo.ObjTema;

import java.util.ArrayList;

public class DataBaseAccess {//CLASE

    private SQLiteOpenHelper openHelper;//VARIABLE PARA ACCEDER O ABRIR
    private SQLiteDatabase db;//VARIABLE PARA DAR O ASIGNAR NOMBRE
    private static DataBaseAccess instance;//VARIABLE PARA INSTANCIAR LA BD

    private DataBaseAccess(Context context){//CONSTRUCTOR

        this.openHelper = new DataBaseConexion(context);//GUARDAMOS LOS RECURSOS DE LA CLASE

    }

    public static DataBaseAccess getInstance(Context context){//METODO PARA OBTENER LA INSTANCIAZION

        if (instance == null){//SI ES IGUAL A NULL

            instance = new DataBaseAccess(context);//CREAMOS LA INSTANCIACION DE LA CLASE ACTUAL

        }

        return instance;//Y LA RETORNAMOS

    }

    public void open(){//METODO PARA ABRIR LA BD

        this.db = openHelper.getWritableDatabase();//GUARDAMOS LA BASE DE DATOS ABIERTA

    }

    public void close(){//METODO PARA CERRAR LA BD

        if (db != null){//SI LO GUARDADO EN DB ES DIFERENTE DE NULL ENTONCES

            this.db.close();//CERRAMOS LO QUE HAY DENTRO

        }

    }

    public String getFkUsu(String nombre){//METODO PARA OBTENER EL IDUSUARIO

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Usuarios where Nombre = '" + nombre + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while(c.moveToNext()){//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String usuaro1 = c.getString(0);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(usuaro1);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String getAcceso(String idusuario, String password){//METODO PARA OBTENER EL IDROL Y SABER SI SÍ EXISTE EL USUARIO

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Acceso where fkUsu = '" + idusuario + "' and login = '" + password + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while(c.moveToNext()){//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String acceso2 = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(acceso2);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String getRol(String rol){//METODO PARA OBTENER EL NOMBRE DEL ROL

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Roles where idRol = '" + rol + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while(c.moveToNext()){//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String rol1 = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(rol1);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String setInsertUsuario(String id, String nombre, long tel, String correo){//METODO PARA INSERTAR UN NUEVO USUARIO

        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("idUsu", id);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Nombre", nombre);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("telefono", tel);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Correo", correo);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("Usuarios", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        return c + "";//RETORNAMOS LO QUE SE GUARDO

    }

    public ArrayList<String> getRoles(){//METODO PARA OBTENER TODOS LOS ROLES QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ArrayList<String> roles1 = new ArrayList<>();//CREAMOS EL ARRAYLIST
        String r;//CREAMOS LA VARIABLE PARA GUARDAS LOS DATOS

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Roles", new String[]{});

        while(c.moveToNext()){//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            r = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            roles1.add(r);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return roles1;//RETORNAMOS LO QUE SE GUARDO

    }

    public String getfkRol(String nombrerol){//METODO PARA OBTENER EL ID ROL

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Roles where NomRol = '" + nombrerol + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while(c.moveToNext()){//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String rol1 = c.getString(0);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(rol1);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String setInsertAcceso(String idacceso, String idrol, String idusuario, String login){//METODO PARA
                                                                                    //AGREGAR UN NUEVO ACCESO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("idAcceso", idacceso);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("fkRol", idrol);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("fkUsu", idusuario);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("login", login);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("Acceso", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Exitoso!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, ID ya registrado, porfavor Ingrese otro";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String setInsertCargoNuevo(String idcargo, String nombrecargo){//METODO PARA INSERTAR UN NUEVO CARGO

        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("idRol", idcargo);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("NomRol", nombrecargo);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("Roles", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        return c + "";//RETORNAMOS LO QUE SE GUARDO

    }


    //UNIENDO LAS DE EDUCATIVO
    public String setInsertEje(int id, String descripcion) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("idEje", id);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("descripcion", descripcion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("Eje", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Exitoso!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, ID ya registrado, porfavor Ingrese otro";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public ObjEje SearchEje(int id) {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ObjEje ej = new ObjEje();//creamos un objeto para guardar los campos que se encuentren

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Eje where idEje = " + id, new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            ej.setId(Integer.parseInt(c.getString(0)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ej.setDescripcion(c.getString(1));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA

        }

        return ej;//RETORNAMOS LO QUE SE GUARDO

    }

    public String ModificarEje(int idm, int idn, String descripcion) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("idEje", idn);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("descripcion", descripcion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.update("Eje", valores, "idEje = " + idm,null);//GUARDAMOS EL NUMERO DE MODIFICADO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Modificado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Modificado";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String EliminarEje(int id) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        c = db.delete("Eje", "idEje ='" + id + "'",null);//ELIMINA EL DATO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Eliminado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Eliminar";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public ArrayList<String> getEjes() {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ArrayList<String> eje = new ArrayList<>();//CREAMOS EL ARRAYLIST
        String r;//CREAMOS LA VARIABLE PARA GUARDAS LOS DATOS

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Eje", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            r = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            eje.add(r);//LO GUARDAMOS DENTRO DEL ARRAY LOS DATO

        }

        return eje;//RETORNAMOS LO QUE SE GUARDO

    }

    public String getId_Eje(String ejen) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Eje where descripcion = '" + ejen + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String eje = c.getString(0);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(eje);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String setInsertTema(String descripcion, int ideje) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("descripcion", descripcion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Eje_idEje", ideje);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("Tema", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Exitoso!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, Descripcion ya registrada, porfavor Ingrese otro";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public ObjTema SearchTema(String descripcion) {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ObjTema te = new ObjTema();//creamos un objeto para guardar los campos que se encuentren

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Tema where descripcion = '" + descripcion + "'", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            te.setDescripcion(c.getString(1));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            te.setEje_tema(Integer.parseInt(c.getString(2)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA

        }

        return te;//RETORNAMOS LO QUE SE GUARDO

    }

    public String getDescripcionEje(int eje) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Eje where idEje = " + eje, new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String eje1 = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(eje1);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String ModificarTema(String descripcionm, String descripcionn, int idje) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("descripcion", descripcionn);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Eje_idEje", idje);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.update("Tema", valores, "descripcion = '" + descripcionm + "'",null);//GUARDAMOS EL NUMERO DE MODIFICADO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Modificado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Modificado";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String EliminarTema(String descripcion) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        c = db.delete("Tema", "descripcion='" + descripcion + "'",null);//ELIMINA EL DATO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Eliminado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Eliminar";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String setInsertComplejidad(int id, String descripcion) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("idComplejidad", id);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("descripcion", descripcion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("Complejidad", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Exitoso!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, ID ya registrado, porfavor Ingrese otro";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public ObjComplejidad SearchComplejidad(int id) {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ObjComplejidad com = new ObjComplejidad();//creamos un objeto para guardar los campos que se encuentren

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Complejidad where idComplejidad = " + id, new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            com.setId(Integer.parseInt(c.getString(0)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            com.setDescripcion(c.getString(1));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA

        }

        return com;//RETORNAMOS LO QUE SE GUARDO

    }

    public String ModificarComplejidad(int idm, int idn, String descripcion) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("idComplejidad", idn);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("descripcion", descripcion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.update("Complejidad", valores, "idComplejidad = " + idm,null);//GUARDAMOS EL NUMERO DE MODIFICADO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Modificado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Modificado";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String EliminarComplejidad(int id) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        c = db.delete("Complejidad", "idComplejidad ='" + id + "'",null);//ELIMINA EL DATO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Eliminado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Eliminar";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String setInsertHabilidad(int id, String descripcion) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("idHabilidad", id);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("descripcion", descripcion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("Habilidad", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Exitoso!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, ID ya registrado, porfavor Ingrese otro";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public ObjHabilidad SearchHabilidad(int id) {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ObjHabilidad hab = new ObjHabilidad();//creamos un objeto para guardar los campos que se encuentren

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Habilidad where idHabilidad = " + id, new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            hab.setId(Integer.parseInt(c.getString(0)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            hab.setDescripcion(c.getString(1));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA

        }

        return hab;//RETORNAMOS LO QUE SE GUARDO

    }

    public String ModificarHabilidad(int idm, int idn, String descripcion) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("idHabilidad", idn);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("descripcion", descripcion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.update("Habilidad", valores, "idHabilidad = " + idm,null);//GUARDAMOS EL NUMERO DE MODIFICADO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Modificado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Modificado";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String EliminarHabilidad(int id) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        c = db.delete("Habilidad", "idHabilidad ='" + id + "'",null);//ELIMINA EL DATO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Eliminado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Eliminar";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String getId_Tema(String tema) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Tema where descripcion = '" + tema + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String tem = c.getString(0);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(tem);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String getId_Habilidad(String hab) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Habilidad where descripcion = '" + hab + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String habi = c.getString(0);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(habi);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String getId_Complejidad(String com) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Complejidad where descripcion = '" + com + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String comp = c.getString(0);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(comp);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String setInsertActividad(String descripcion, String nombre, int idtema, int idhabilidad, int idcomplejidad) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("descripcion", descripcion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("nombre", nombre);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Tema_idTema", idtema);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Habilidad_idHabilidad", idhabilidad);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Complejidad_idComplejidad", idcomplejidad);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("Actividad", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Exitoso!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, Nombre ya registrado, porfavor Ingrese otro";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public ArrayList<String> getTema() {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ArrayList<String> tem = new ArrayList<>();//CREAMOS EL ARRAYLIST
        String r;//CREAMOS LA VARIABLE PARA GUARDAS LOS DATOS

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Tema", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            r = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            tem.add(r);//LO GUARDAMOS DENTRO DEL ARRAY LOS DATO

        }

        return tem;//RETORNAMOS LO QUE SE GUARDO

    }

    public ArrayList<String> getHabilidad() {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ArrayList<String> hab = new ArrayList<>();//CREAMOS EL ARRAYLIST
        String r;//CREAMOS LA VARIABLE PARA GUARDAS LOS DATOS

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Habilidad", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            r = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            hab.add(r);//LO GUARDAMOS DENTRO DEL ARRAY LOS DATO

        }

        return hab;//RETORNAMOS LO QUE SE GUARDO

    }

    public ArrayList<String> getComplejidad() {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ArrayList<String> com = new ArrayList<>();//CREAMOS EL ARRAYLIST
        String r;//CREAMOS LA VARIABLE PARA GUARDAS LOS DATOS

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Complejidad", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            r = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            com.add(r);//LO GUARDAMOS DENTRO DEL ARRAY LOS DATO

        }

        return com;//RETORNAMOS LO QUE SE GUARDO

    }

    public ObjActividad SearchActividad(String nombre) {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ObjActividad ac = new ObjActividad();//creamos un objeto para guardar los campos que se encuentren

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Actividad where nombre = '" + nombre + "'", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            ac.setDescripcion(c.getString(1));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ac.setNombre(c.getString(2));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ac.setTema(Integer.parseInt(c.getString(3)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ac.setHabilidad(Integer.parseInt(c.getString(4)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ac.setComplejidad(Integer.parseInt(c.getString(5)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA

        }

        return ac;//RETORNAMOS LO QUE SE GUARDO

    }

    public String getNombretema(int tema) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Tema where idTema = " + tema, new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String tema1 = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(tema1);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String getNombreHabilidad(int habilidad) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Habilidad where idHabilidad = " + habilidad, new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String habilidad1 = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(habilidad1);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String getNombreComplejidad(int complejidad) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Complejidad where idComplejidad = " + complejidad, new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String complejidad1 = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(complejidad1);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String ModificarActividad(String des, String nomm, String nomn, int idtema, int idhabi, int idcomp) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("descripcion", des);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("nombre", nomn);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Tema_idTema", idtema);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Habilidad_idHabilidad", idhabi);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Complejidad_idComplejidad", idcomp);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.update("Actividad", valores, "nombre = '" + nomm + "'",null);//GUARDAMOS EL NUMERO DE MODIFICADO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Modificado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Modificado";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String EliminarActividad(String nombre) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        c = db.delete("Actividad", "nombre='" + nombre + "'",null);//ELIMINA EL DATO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Eliminado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Eliminar";//MENSAJE DE ERROR AL REGISTRAR

        }

    }


    public String getId_Actividad(String acti) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Actividad where nombre = '" + acti + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String act = c.getString(0);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(act);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String getId_Usuario(String usua) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Usuarios where Nombre = '" + usua + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String usu = c.getString(0);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(usu);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String setInsertResultadoActividad(int id, int idact, String idusu, int clasificacion, String nivel, String numint, String numpre, String acert, int idcom) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("idResultadoActividad", id);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Actividad_idActividad", idact);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Usuario_idUsuario", idusu);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Clasificacion_idClasificacion", clasificacion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("nivelDeComplejidad", nivel);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("numeroDeIntentosPredominante", numint);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("numeroDePreguntasEjecutadas", numpre);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("porcentajeDeAcertividad", acert);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Complejidad_idComplejidad", idcom);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("ResultadoActividad", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Exitoso!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, ID ya registrado, porfavor Ingrese otro";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String setInsertNuevoResultadoActividad(int idact, String idusu, int clasificacion, String nivel, String numint, String numpre, String acert, int idcom) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("Actividad_idActividad", idact);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Usuario_idUsuario", idusu);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Clasificacion_idClasificacion", clasificacion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("nivelDeComplejidad", nivel);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("numeroDeIntentosPredominante", numint);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("numeroDePreguntasEjecutadas", numpre);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("porcentajeDeAcertividad", acert);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Complejidad_idComplejidad", idcom);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("ResultadoActividad", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Exitoso!! ";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, ID ya registrado, porfavor Ingrese otro ";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public ArrayList<String> getActividad() {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ArrayList<String> act = new ArrayList<>();//CREAMOS EL ARRAYLIST
        String r;//CREAMOS LA VARIABLE PARA GUARDAS LOS DATOS

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Actividad", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            r = c.getString(2);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            act.add(r);//LO GUARDAMOS DENTRO DEL ARRAY LOS DATO

        }

        return act;//RETORNAMOS LO QUE SE GUARDO

    }

    public ArrayList<String> getUsuario() {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ArrayList<String> usu = new ArrayList<>();//CREAMOS EL ARRAYLIST
        String r;//CREAMOS LA VARIABLE PARA GUARDAS LOS DATOS

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Usuarios", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            r = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            usu.add(r);//LO GUARDAMOS DENTRO DEL ARRAY LOS DATO

        }

        return usu;//RETORNAMOS LO QUE SE GUARDO

    }

    public ObjResultadoActividad SearchResultadoActividad(int id) {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ObjResultadoActividad ra = new ObjResultadoActividad();//creamos un objeto para guardar los campos que se encuentren

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from ResultadoActividad where idResultadoActividad = " + id, new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            ra.setId(Integer.parseInt(c.getString(0)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ra.setActividad(Integer.parseInt(c.getString(1)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ra.setUsuario(c.getString(2));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ra.setClasificacion(Integer.parseInt(c.getString(3)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ra.setNivel(c.getString(4));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ra.setIntentos(c.getString(5));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ra.setPreguntas(c.getString(6));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ra.setAcertividad(c.getString(7));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ra.setComplejidad(Integer.parseInt(c.getString(8)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA

        }

        return ra;//RETORNAMOS LO QUE SE GUARDO

    }

    public ObjResultadoActividad SearchUltimoResultadoActividad() {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ObjResultadoActividad ra = new ObjResultadoActividad();//creamos un objeto para guardar los campos que se encuentren

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("SELECT * FROM ResultadoActividad WHERE idResultadoActividad = (SELECT MAX(idResultadoActividad) FROM ResultadoActividad)", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            ra.setId(Integer.parseInt(c.getString(0)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            ra.setClasificacion(Integer.parseInt(c.getString(3)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA

        }

        return ra;//RETORNAMOS LO QUE SE GUARDO

    }

    public String getNombreActividad(int actividad) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Actividad where idActividad = " + actividad, new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String act = c.getString(2);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(act);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String getNombreUsuario(String usuario) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Usuarios where idUsu = '" + usuario + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String usu = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(usu);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String ModificarResultadoActividad(int idm, int idn, int idacti, String idusu, int clas, String nivel, String intentos, String preguntas, String acertividad, int idcomp) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("idResultadoActividad", idn);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Actividad_idActividad", idacti);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Usuario_idUsuario", idusu);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Clasificacion_idClasificacion", clas);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("nivelDeComplejidad", nivel);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("numeroDeIntentosPredominante", intentos);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("numeroDePreguntasEjecutadas", preguntas);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("porcentajeDeAcertividad", acertividad);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Complejidad_idComplejidad", idcomp);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.update("ResultadoActividad", valores, "idResultadoActividad = " + idm,null);//GUARDAMOS EL NUMERO DE MODIFICADO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Modificado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Modificado";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String ModificarNuevoResultadoActividad(int idm, String intentos, String porcentaje) {//METODO PARA
                                                                              //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("numeroDeIntentosPredominante", intentos);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("porcentajeDeAcertividad", porcentaje);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.update("ResultadoActividad", valores, "idResultadoActividad = " + idm,null);//GUARDAMOS EL NUMERO DE MODIFICADO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Modificado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Modificado";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String EliminarResultadoActividad(int id) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        c = db.delete("ResultadoActividad", "idResultadoActividad = " + id,null);//ELIMINA EL DATO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Eliminado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Eliminar";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String setInsertImagen(String nombre, String descripcion, String ubicacion) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("nombre", nombre);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("descripcion", descripcion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("ubicacion", ubicacion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("Imagen", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Exitoso!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, Nombre ya registrado, porfavor Ingrese otro";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public ObjImagen SearchImagen(String nombre) {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ObjImagen im = new ObjImagen();//creamos un objeto para guardar los campos que se encuentren

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Imagen where nombre = '" + nombre + "'", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            im.setNombre(c.getString(1));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            im.setDescripcion(c.getString(2));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            im.setUbicacion(c.getString(3));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA

        }

        return im;//RETORNAMOS LO QUE SE GUARDO

    }

    public ObjImagen SearchUnaImagen(int idImagenes) {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ObjImagen im = new ObjImagen();//creamos un objeto para guardar los campos que se encuentren

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Imagen where idImagenes = " + idImagenes, new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            im.setId(Integer.parseInt(c.getString(0)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            im.setUbicacion(c.getString(3));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA

        }

        return im;//RETORNAMOS LO QUE SE GUARDO

    }

    public String ModificarImagen(String nombrem, String nombren, String desc, String ubi) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("nombre", nombren);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("descripcion", desc);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("ubicacion", ubi);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.update("Imagen", valores, "nombre='" + nombrem + "'",null);//GUARDAMOS EL NUMERO DE MODIFICADO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Modificado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Modificado";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String EliminarImagen(String nombre) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        c = db.delete("Imagen", "nombre='" + nombre + "'",null);//ELIMINA EL DATO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Eliminado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Eliminar";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String setInsertAudio(String nombre, String descripcion, String ubicacion) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("nombre", nombre);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("descripcion", descripcion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("ubicacion", ubicacion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("Audio", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Exitoso!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, Nombre ya registrado, porfavor Ingrese otro";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public ObjAudio SearchAudio(String nombre) {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ObjAudio au = new ObjAudio();//creamos un objeto para guardar los campos que se encuentren

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Audio where nombre = '" + nombre + "'", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            au.setNombre(c.getString(1));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            au.setDescripcion(c.getString(2));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            au.setUbicacion(c.getString(3));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA

        }

        return au;//RETORNAMOS LO QUE SE GUARDO

    }

    public ObjAudio SearchUnAudio(int idAudio) {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ObjAudio au = new ObjAudio();//creamos un objeto para guardar los campos que se encuentren

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Audio where idAudio = " + idAudio, new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            au.setId(Integer.parseInt(c.getString(0)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            au.setUbicacion(c.getString(3));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA

        }

        return au;//RETORNAMOS LO QUE SE GUARDO

    }

    public String ModificarAudio(String nombrem, String nombren, String desc, String ubi) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("nombre", nombren);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("descripcion", desc);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("ubicacion", ubi);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.update("Audio", valores, "nombre='" + nombrem + "'",null);//GUARDAMOS EL NUMERO DE MODIFICADO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Modificado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Modificado";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String EliminarAudio(String nombre) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        c = db.delete("Audio", "nombre='" + nombre + "'",null);//ELIMINA EL DATO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Eliminado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Eliminar";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String setInsertDetalleActividad(String descripcion, int idimagen, int idaudio, int idactividad) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("descripcion", descripcion);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Imagen_idImagenes", idimagen);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Audio_idAudio", idaudio);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Actividad_idActividad", idactividad);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.insert("DetalleActividad", null, valores);//GUARDAMOS EL NUMERO DE INSERT Y TAMBIEN INSERTAMOS EN LA TABLA

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Exitoso!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, Descripcion ya registrado, porfavor Ingrese otro";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public ArrayList<String> getImagen() {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ArrayList<String> img = new ArrayList<>();//CREAMOS EL ARRAYLIST
        String r;//CREAMOS LA VARIABLE PARA GUARDAS LOS DATOS

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Imagen", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            r = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            img.add(r);//LO GUARDAMOS DENTRO DEL ARRAY LOS DATO

        }

        return img;//RETORNAMOS LO QUE SE GUARDO

    }

    public ArrayList<String> getAudio() {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ArrayList<String> aud = new ArrayList<>();//CREAMOS EL ARRAYLIST
        String r;//CREAMOS LA VARIABLE PARA GUARDAS LOS DATOS

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Audio", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            r = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            aud.add(r);//LO GUARDAMOS DENTRO DEL ARRAY LOS DATO

        }

        return aud;//RETORNAMOS LO QUE SE GUARDO

    }

    public String getId_Imagen(String img) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Imagen where nombre = '" + img + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String img1 = c.getString(0);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(img1);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String getId_Audio(String aud) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Audio where nombre = '" + aud + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String aud1 = c.getString(0);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(aud1);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public ObjDetalleActividad SearchDetalleActividad(String descripcion) {//METODO PARA OBTENER TODOS LOS DATOS QUE EXISTEN

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL
        ObjDetalleActividad da = new ObjDetalleActividad();//creamos un objeto para guardar los campos que se encuentren

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from DetalleActividad where descripcion = '" + descripcion + "'", new String[]{});

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            da.setDescripcion(c.getString(1));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            da.setImagen(Integer.parseInt(c.getString(2)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            da.setAudio(Integer.parseInt(c.getString(3)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            da.setActividad(Integer.parseInt(c.getString(4)));//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA

        }

        return da;//RETORNAMOS LO QUE SE GUARDO

    }

    public String getNombreImagen(int img) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Imagen where idImagenes = " + img, new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String imagen = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(imagen);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String getNombreAudio(int audio) {//METODO PARA OBTENER EL ID

        Cursor c = null;//CREAMOS EL CURSOR INICIALIZADO EN NULL

        //GUARDAMOS DENTRO DEL CURSOS LO SELECCIONADO DE LA TABLA
        c = db.rawQuery("select * from Audio where idAudio = " + audio, new String[]{});
        StringBuffer buffer = new StringBuffer();//CREAMOS EL BUFFER DONDE VAMOS A GUARDAR LA INFORMAION

        while (c.moveToNext()) {//NOS MOVEREMOS DENTRO DE LO SELECCIONADO HASTA QUE NO ALLA NADA

            String aud = c.getString(1);//OBTENEMOS LOS DATOS DE LA POSICION DE LA COLUMNA
            buffer.append(aud);//LO GUARDAMOS DENTRO DEL BUFFER EL DATO

        }

        return buffer.toString();//RETORNAMOS LO QUE SE GUARDO

    }

    public String ModificarDetalleActividad(String desm, String desn, int idimagen, int idaudio, int idactividad) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        valores.put("descripcion", desn);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Imagen_idImagenes", idimagen);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Audio_idAudio", idaudio);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        valores.put("Actividad_idActividad", idactividad);//GUARDAMOS EL DATO INDICANDO EL NOMBRE DE LA COLUMNA Y EL DATO A GUARDAR
        c = db.update("DetalleActividad", valores, "descripcion = '" + desm + "'",null);//GUARDAMOS EL NUMERO DE MODIFICADO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Modificado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Modificado";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

    public String EliminarDetalleActividad(String des) {//METODO PARA
        //AGREGAR UN NUEVO DATO
        long c;//PARA GUARDAR EL CONTEO DE LOS INSERT REALIZADOS
        ContentValues valores = new ContentValues();//CREAMOS LA CLASE QUE NOS AYUDA AGUARDAR LOS DATOS EN LA TABLA QUE QUEREMOS

        c = db.delete("DetalleActividad", "descripcion='" + des + "'",null);//ELIMINA EL DATO

        if (c != -1) {//EN CASO DE QUE NO OBTENGA UN REGISTRO

            return "Registro Eliminado Exitosamente!!";//RETORNAMOS LO QUE SE GUARDO

        } else {

            return "Error!!, No se pudo Eliminar";//MENSAJE DE ERROR AL REGISTRAR

        }

    }

}
