package com.example.appaprendizaje2.clases;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appaprendizaje2.R;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

import android.content.Intent;//LIBRERIA PARA INVOCAR ACTIVITYS
import android.os.Bundle;//LIBRERIA FORMATO PARA CARGAR LA APP
import android.view.View;//LIBRERIA PARA DIBUJAR CONTENIDO EN LA PANTALLA
import android.widget.Button;//LIBRERIA BOTON QUE REALIZA UNA ACCION
import android.widget.EditText;//LIBRERIA PARA CAMPO DE TEXTO
import android.widget.Toast;//LIBRERIA PARA NOTIFICACION EN LA PANTALLA

public class AgregarUsuario extends AppCompatActivity {//CLASE SECUNDARIA

    EditText edit_id, edit_nombre, edit_tel, edit_correo;//VARIABLES PARA LOS CAMPOS DE TEXTO
    Button btn_agregar, btn_menuprincipalu;//VARIABLES PARA LOS BOTONES
    String nombreadmin = "";//VARIABLE PARA OBTENER EL NOMBRE DEL ADMIN
    int tel = 0;//VARIABLE PARA OBTNER EL NUMERO TEL

    @Override//SOBRESCRIBIENDO UN METODO YA CREADO
    protected void onCreate(Bundle savedInstanceState) {//METODO PARA LAS INICIALIZACIONES
        super.onCreate(savedInstanceState);//PARA EJECUCION O ANULACION DE LA CLASE
        setContentView(R.layout.activity_agregar_usuario);//SIRVE PARA CONECTAR SU DISEÑO .XML

        edit_id = (EditText) findViewById(R.id.editText_idu);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        edit_nombre = (EditText) findViewById(R.id.editText_nombreu);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        edit_tel = (EditText) findViewById(R.id.editText_telefonou);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        edit_correo = (EditText) findViewById(R.id.editText_correo);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        btn_agregar = (Button) findViewById(R.id.btn_agregaru);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        btn_menuprincipalu = (Button) findViewById(R.id.btn_regresaru);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        nombreadmin = getIntent().getExtras().getString("nombreadmin");//GUARDAMOS EL DATO DE LA VARIABLE NOMBREADMIN

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//METODO ACCION DEL BOTON ASIGNADO

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                String id = edit_id.getText().toString();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String nombre = edit_nombre.getText().toString();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                try {//TRY PARA OBTENER EL MENSAJE DE UN CONFLICTO DE LA VARIABLE ENTERA

                    tel = Integer.parseInt(edit_tel.getText().toString());//GUARDA EL DATO PARSEANDOLO A ENTERO

                } catch(NumberFormatException e){//SI DETECTA UN ERROR

                    System.out.println("Error: " + e);//MANDA MENSAJE A CONSOLA DEL ERROR
                    edit_tel.setError("N° Telefonico no Ingresado!");//MANDA MENSAJE ERROR AL CAMPO DE TEXTO

                }

                String correo = edit_correo.getText().toString();//GUARDA EL CORREO ELECTRONICO INGRESADO

                String mensaje = dataBaseAccess.setInsertUsuario(id, nombre, tel, correo);//INSERTA Y GUARDA SI SE HA
                // INSERTADO O NO LOS DATOS

                if (id.isEmpty() || nombre.isEmpty() || tel == 0 || correo.isEmpty()) {//SI ALGUNO NO TIENE NADA INGRESADO

                    if (id.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        edit_id.setError("N° Identificador no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (nombre.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        edit_nombre.setError("Nombre de Usuario no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (tel == 0) {//SI NO TIENE  NADA INGRESADO

                        edit_tel.setError("N° Telefonico no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (correo.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        edit_correo.setError("Correo Electronico no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (id.isEmpty() && nombre.isEmpty() && tel == 0 && correo.isEmpty()) {//SI NO TIENE  NADA EN TODOS

                        edit_id.setError("N° Identificador no Ingresado!");//MANDA MENSAJE ERROR
                        edit_nombre.setError("Nombre de Usuario no Ingresado!");//MANDA MENSAJE ERROR
                        edit_tel.setError("N° Telefonico no Ingresado!");//MANDA MENSAJE ERROR
                        edit_correo.setError("Correo Electronico no Ingresado!");//MANDA MENSAJE ERROR

                    }

                } else if (mensaje.equals("") || mensaje.equals(null)){//SI NO OBTINENE NINGUN MENSAJE EL METOD INSERTUSUARIO

                    //MANDA NOTIFICACION EN LA PANTALLA DEL ERROR
                    Toast toast = Toast.makeText(getApplicationContext(), "No se pudo Registrar el Usuario!!", Toast.LENGTH_LONG);
                    toast.show();//MUESTRA EL MENSAJE

                } else {//SI NO OCURRE NINGUNA DE LAS ANTERIORES

                    Intent intent = new Intent(v.getContext(), Asignacion_Cargo.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY ADMINISTRATIVO
                    intent.putExtra("registro", mensaje);//GUARDAMOS LA VARIABLE
                    intent.putExtra("idusuario", id);//GUARDAMOS LA VARIABLE
                    intent.putExtra("nombreusuario", nombre);//GUARDAMOS LA VARIABLE
                    startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                    finish();//Y CERRAMOS EL ACTIVITY ACTUAL

                }

                dataBaseAccess.close();

            }

        });//FINAL DEL METODO DEL BOTON

        btn_menuprincipalu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//METODO ACCION DEL BOTON ASIGNADO

                Intent intent = new Intent(v.getContext(),  Administrativo.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY ADMINISTRATIVO
                intent.putExtra("tipo", "Administrativo");//GUARDAMOS LA VARIABLE
                intent.putExtra("nombreadmin", nombreadmin);//GUARDAMOS LA VARIABLE
                startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                finish();//Y CERRAMOS EL ACTIVITY ACTUAL

            }

        });//FINAL DEL METODO DEL BOTON

    }
}
