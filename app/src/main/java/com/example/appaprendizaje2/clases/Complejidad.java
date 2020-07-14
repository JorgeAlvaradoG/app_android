package com.example.appaprendizaje2.clases;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appaprendizaje2.R;
import com.example.appaprendizaje2.modelo.ObjComplejidad;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

public class Complejidad extends AppCompatActivity {

    Button button_buscar, button_agregar, button_eliminar, button_modificar;//PARA LOS BOTONES
    EditText editText_id, editText_descripcion;//PARA LOS CUADROS DE TEXTO
    int id = 0, idb = 0, idn = 0, ide = 0, di = 0;//variable de tipo entero

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complejidad);

        setTitle("Formulario Complejidad");//titulo de la pantalla

        //CONEXION DE VARIBLES CREADAS CON LOS OBJETOS CREADOS EN EL XML
        button_buscar = (Button) findViewById(R.id.btn_buscarcomplejidad);//TOMANDO CONTROL DEL OBJETO XML
        button_agregar = (Button) findViewById(R.id.btn_agregarcomplejidad);//TOMANDO CONTROL DEL OBJETO XML
        button_modificar = (Button) findViewById(R.id.btn_modificarcomplejidad);//TOMANDO CONTROL DEL OBJETO XML
        button_eliminar = (Button) findViewById(R.id.btn_eliminarcomplejidad);//TOMANDO CONTROL DEL OBJETO XML
        editText_id = (EditText) findViewById(R.id.et_idcomplejidad);//TOMANDO CONTROL DEL OBJETO XML
        editText_descripcion = (EditText) findViewById(R.id.et_descripcioncomplejidad);//TOMANDO CONTROL DEL OBJETO XML

        button_agregar.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                try {//para el error de formato

                    id = Integer.parseInt(editText_id.getText().toString().trim());//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                } catch (NumberFormatException e) {//en caso de marcar el error

                    editText_id.setError("ID no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                }
                String des = editText_descripcion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                String registro = dataBaseAccess.setInsertComplejidad(id, des);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (des.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                } else {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), registro, Toast.LENGTH_LONG);
                    Limpiar();//limpia los objetos
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                }

                dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

            }

        });

        button_buscar.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                try {//para el error de formato

                    idb = Integer.parseInt(editText_id.getText().toString().trim());//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                } catch (NumberFormatException e) {//en caso de marcar el error

                    editText_id.setError("ID no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                }

                ObjComplejidad com = dataBaseAccess.SearchComplejidad(idb);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (com.getId() != 0) {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

                    System.out.println("EN COMPLEJIDAD: "+com.getId());
                    di = com.getId();//se guarda el valor para usarlo dentro del boton modificar

                    editText_descripcion.setText(com.getDescripcion());

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Complejidad Encontrada!!", Toast.LENGTH_LONG);
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                } else {

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Complejidad NO encontrada!!", Toast.LENGTH_LONG);
                    Limpiar();//limpia los objetos
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                }

                dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

            }

        });

        button_modificar.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                int idm = di;//obtengo el valor a buscar y modificar
                try {//para el error de formato

                    idn = Integer.parseInt(editText_id.getText().toString().trim());//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                } catch (NumberFormatException e) {//en caso de marcar el error

                    editText_id.setError("ID no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                }

                String descripcion = editText_descripcion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                System.out.println("dsafghjgfdsfdgds:"+descripcion);
                String registro = dataBaseAccess.ModificarComplejidad(idm, idn, descripcion);//GUARDAMOS EL MENSAJE DE INSERTADO O NO
                di = 0;//reinicia el valor

                if (descripcion.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                } else {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), registro, Toast.LENGTH_LONG);
                    Limpiar();//limpia los objetos
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                }

                dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

            }

        });

        button_eliminar.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                try {//para el error de formato

                    ide = Integer.parseInt(editText_id.getText().toString().trim());//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                } catch (NumberFormatException e) {//en caso de marcar el error

                    editText_id.setError("ID no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                }

                String eliminacion = dataBaseAccess.EliminarComplejidad(ide);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                //MANDA MENSAJE EXITOSO
                Toast toast = Toast.makeText(getApplicationContext(), eliminacion, Toast.LENGTH_LONG);
                Limpiar();//limpia los objetos
                toast.show();//MOSTRAMOS LA NOTIFICACION

                dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

            }

        });

    }

    public void Limpiar() {//metodo para limpiar los valores dentro de los objetos

        editText_id.setText("");//limpia el objeto
        editText_descripcion.setText("");//limpia el objeto

    }

}
