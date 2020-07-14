package com.example.appaprendizaje2.clases;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;//LIBRERIA PARA INVOCAR ACTIVITYS
import android.os.Bundle;//LIBRERIA FORMATO PARA CARGAR LA APP
import android.view.View;//LIBRERIA PARA DIBUJAR CONTENIDO EN LA PANTALLA
import android.widget.Button;//LIBRERIA BOTON QUE REALIZA UNA ACCION
import android.widget.EditText;//LIBRERIA PARA CAMPO DE TEXTO
import android.widget.Toast;//LIBRERIA PARA NOTIFICACION EN LA PANTALLA

import com.example.appaprendizaje2.R;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

public class AgregarCargo extends AppCompatActivity {//CLASE SECUNDARIA

    EditText editText_idrol1, editText_nombrerol1;//VARIABLES PARA LOS CAMPOS DE TEXTO
    String usuario = "";//VARIABLE PARA GUARDAR NOMBRE DE USUARIO
    Button button_menuprincipal, button_agregar;//VARIABLES PARA LOS BOTONES

    @Override//SOBRESCRIBIENDO UN METODO YA CREADO
    protected void onCreate(Bundle savedInstanceState) {//METODO PARA LAS INICIALIZACIONES
        super.onCreate(savedInstanceState);//PARA EJECUCION O ANULACION DE LA CLASE
        setContentView(R.layout.activity_agregar_cargo);//SIRVE PARA CONECTAR SU DISEÑO .XML

        editText_idrol1 = (EditText) findViewById(R.id.editText_idrol);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        editText_nombrerol1 = (EditText) findViewById(R.id.editText_nombrerol);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        button_menuprincipal = (Button) findViewById(R.id.btn_salir);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        button_agregar = (Button) findViewById(R.id.btn_agregar);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        usuario = getIntent().getExtras().getString("nombreadmin");//GUARDAMOS EL DATO DE LA VARIABLE

        button_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//INICIO DEL METODO ACCION DEL BOTON

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                String idrol = editText_idrol1.getText().toString();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String nomrol = editText_nombrerol1.getText().toString();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                String registro = dataBaseAccess.setInsertCargoNuevo(idrol, nomrol);//OBTENEMOS EL MENSAJE SI FUE O NO REGISTRADO EL ROL

                if (idrol.isEmpty() || nomrol.isEmpty()) {//SI ALGUNO NO TIENE NADA INGRESADO

                    if (idrol.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        editText_idrol1.setError("Dede ingresar un Identificador de Cargo!!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (nomrol.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        editText_nombrerol1.setError("Dede ingresar un Cargo Nuevo!!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (idrol.isEmpty() && nomrol.isEmpty()) {//SI NO TIENE  NADA INGRESADO EN TODOS

                        editText_idrol1.setError("Dede ingresar un Identificador de Cargo!!");//MANDA MENSAJE ERROR
                        editText_nombrerol1.setError("Dede ingresar un Cargo Nuevo!!");//MANDA MENSAJE ERROR

                    }

                } else if (registro.equals("") || registro.equals(null)){//SI NO OBTINENE NINGUN MENSAJE DEL METODO

                    //MANDA NOTIFICACION EN LA PANTALLA DEL ERROR
                    Toast toast = Toast.makeText(getApplicationContext(), "No se pudo Registrar el Nuevo Cargo!!", Toast.LENGTH_LONG);
                    toast.show();//MUESTRA EL MENSAJE

                } else {//SI NO OCURRE NINGUNA DE LAS ANTERIORES

                    //MANDA NOTIFICACION EN LA PANTALLA EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), registro + " Registro Exitoso!!", Toast.LENGTH_LONG);
                    toast.show();//MUESTRA EL MENSAJE

                }

                dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

            }

        });//FINAL DEL METODO DEL BOTON

        button_menuprincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//INICIO DEL METODO ACCION DEL BOTON

                Intent intent = new Intent(v.getContext(),  Administrativo.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY ADMINISTRATIVO
                intent.putExtra("tipo", "Administrativo");//GUARDAMOS LA VARIABLE
                intent.putExtra("nombre", usuario);//GUARDAMOS LA VARIABLE
                startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                finish();//Y CERRAMOS EL ACTIVITY ACTUAL

            }

        });//FINAL DEL METODO DEL BOTON

    }
}