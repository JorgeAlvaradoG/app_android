package com.example.appaprendizaje2.clases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;//LIBRERIA PARA INVOCAR ACTIVITYS
import android.os.Bundle;//LIBRERIA FORMATO PARA CARGAR LA APP
import android.view.View;//LIBRERIA PARA DIBUJAR CONTENIDO EN LA PANTALLA
import android.widget.ArrayAdapter;//LIBRERIA PARA ADAPTAR AL TIPO DE ARREGLO
import android.widget.Button;//LIBRERIA BOTON QUE REALIZA UNA ACCION
import android.widget.EditText;//LIBRERIA PARA CAMPO DE TEXTO
import android.widget.Spinner;//LIBRERIA PARA OBJETO SPINNER
import android.widget.TextView;//LIBRERIA PARA CAMPO DE MENSAJE DE TEXTO
import android.widget.Toast;//LIBRERIA PARA NOTIFICACION

import com.example.appaprendizaje2.R;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

import java.util.ArrayList;//LIBRERIA PARA EL TIPO DE ARREGLO DE LISTA

public class Asignacion_Cargo extends AppCompatActivity {//CLASE SECUNDARIA

    TextView txt_registro, txt_usuario;//VARIABLES PARA LOS CAMPOS MENSAJE DE TEXTO
    EditText editText_idacceso, editText_passwordacceso;//VARIABLES PARA LOS CAMPOS DE TEXTO
    Spinner spinnerc;//VARIABLE PARA EL SPINNER
    Button btn_acceder, btn_menuprincipal;//VARIABLE DEL BOTON
    String usuario = "";//VARIABLE PARA GUARDAR EL NOMBRE DE USUARIO

    @Override//SOBRESCRIBIENDO UN METODO YA CREADO
    protected void onCreate(Bundle savedInstanceState) {//METODO PARA LAS INICIALIZACIONES
        super.onCreate(savedInstanceState);//PARA EJECUCION O ANULACION DE LA CLASE
        setContentView(R.layout.activity_asignacion__cargo);//SIRVE PARA CONECTAR SU DISEÑO .XML

        txt_registro = (TextView) findViewById(R.id.txt_registro);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        txt_usuario = (TextView) findViewById(R.id.txt_nusuario);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        editText_idacceso = (EditText) findViewById(R.id.editText_ida);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        editText_passwordacceso = (EditText) findViewById(R.id.editText_passworda);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        spinnerc = (Spinner) findViewById(R.id.spinner_Cargos);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        btn_acceder = (Button) findViewById(R.id.btn_acceso);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        btn_menuprincipal = (Button) findViewById(R.id.btn_regresar);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        final String regis = getIntent().getExtras().getString("registro");//GUARDAMOS EL DATO OBTENIDO
        txt_registro.setText(regis);//INSERTAMOS EL DATO DENTRO DEL CAMPO MENSAJE DE TEXTO

        usuario = getIntent().getExtras().getString("nombreusuario");//OBTENEMOS EL DATO
        txt_usuario.setText("Usuario: " + usuario);//LO INSERTAMOS EN EL CAMPO DE MENSAJE DE TEXTO

        //GUARDAMOS DENTRO DEL SPINNER TODOS LOS DATOS QUE SE OBTIENE DEL METODO AGREGARSPINNER()
        spinnerc.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, agregarspinner()));

        btn_acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//INICIO DE LA ACCION DEL BOTON

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                String idacc = editText_idacceso.getText().toString();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String idusu = getIntent().getExtras().getString("idusuario");//GUARDAMOS LO QUE INGRESE EL USUARIO
                String rolnombre = (String) spinnerc.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String passworda = editText_passwordacceso.getText().toString();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                String idrol = dataBaseAccess.getfkRol(rolnombre);//GUARDAMOS EL IDE ROL DEL METODO GETFKROL

                String registro = dataBaseAccess.setInsertAcceso(idacc, idrol, idusu, passworda);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (idacc.isEmpty() || idrol.isEmpty() || passworda.isEmpty()) {//SI ALGUNO NO TIENE NADA INGRESADO

                    if (idacc.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        editText_idacceso.setError("N° Identificador de Acceso no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (idrol.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        //MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        Toast toast = Toast.makeText(getApplicationContext(), "Dede seleccionar un Cargo!!", Toast.LENGTH_LONG);
                        toast.show();

                    } if (passworda.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        editText_passwordacceso.setError("Password no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (idacc.isEmpty() && idrol.isEmpty() && passworda.isEmpty()) {//SI NO TIENE  NADA EN TODOS

                        editText_idacceso.setError("N° Identificador de Acceso no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        //MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        Toast toast = Toast.makeText(getApplicationContext(), "Dede seleccionar un Cargo!!", Toast.LENGTH_LONG);
                        toast.show();//MOSTRAMOS LA NOTIFICACION
                        editText_passwordacceso.setError("Password no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }

                } else {//SI NO OBTINENE NINGUN MENSAJE EL METODO

                    //MANDA MENSAJE ERROR
                    Toast toast = Toast.makeText(getApplicationContext(), registro, Toast.LENGTH_LONG);
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                }

                dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

            }

        });//FIN DEL BOTON

        btn_menuprincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//INICIO DE LA ACCION DEL BOTON

                Intent intent = new Intent(v.getContext(),  Administrativo.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY ADMINISTRATIVO
                intent.putExtra("tipo", "Administrativo");//GUARDAMOS LA VARIABLE
                intent.putExtra("nombre", usuario);//GUARDAMOS LA VARIABLE
                startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                finish();//Y CERRAMOS EL ACTIVITY ACTUAL

            }

        });//FIN DEL BOTON

    }

    public String[] agregarspinner(){//METODO PARA IR AGREGANDO LOS NOMBRES DE ROLES DE LA BASE DE DATOS A UN ARREGLO

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
        // ESPECIFICOS DENTRO DE ESA CLASE
        dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

        ArrayList<String> cargo;//CREAMOS EL ARREGLO LLAMADO CARGO
        cargo = dataBaseAccess.getRoles();//GUARDAMOS DENTRO DEL ARREGLO LO QUE OBTIENE DEL METODO GETROLES

        String[] cargos = new String[cargo.size()];//CREAMOS UN ARREGLO NORMAL CON EL TAMAÑO DEL ARREGLO LIST

        for(int i = 0; i < cargo.size(); i++){//BUCLE PARA IR GUARDANDO LOS DATOS DEL LIST DENTRO DEL ARREGLO NORMAL

            cargos[i] = cargo.get(i);//GUARDAMOS LOS DATO DEL LIS EN EL ARRAY NORMAL
            System.out.println(cargos[i]);//IMPRIMIMOS LOS DATOS EN LA CONSOLA

        }//FIN DEL BUCLE

        dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

        return cargos;//RETORNAMOS EL ARREGLO YA CON LOS ROLES GUARDADOS

    }//FIN DE ESTE METODO AGREGARSPINNER

}