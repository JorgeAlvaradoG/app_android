package com.example.appaprendizaje2.clases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;//LIBRERIA PARA INVOCAR ACTIVITYS
import android.os.Bundle;//LIBRERIA FORMATO PARA CARGAR LA APP
import android.view.Menu;
import android.view.View;//LIBRERIA PARA DIBUJAR CONTENIDO EN LA PANTALLA
import android.widget.Button;//LIBRERIA BOTON QUE REALIZA UNA ACCION
import android.widget.EditText;//LIBRERIA PARA CAMPO DE TEXTO
import android.widget.Toast;//LIBRERIA PARA NOTIFICACION EN LA PANTALLA

import com.example.appaprendizaje2.R;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

public class MainActivity extends AppCompatActivity {//CLASE MAIN PRINCIPAL

    EditText editTextusuario, editTextpassword;//VARIABLES PARA LOS CAMPOS DE TEXTO
    Button btn_acceso;//VARIABLE PARA EL BOTON

    @Override//SOBRESCRIBIENDO UN METODO YA CREADO
    protected void onCreate(Bundle savedInstanceState) {//METODO PARA LAS INICIALIZACIONES
        super.onCreate(savedInstanceState);//PARA EJECUCION O ANULACION DE LA CLASE
        setContentView(R.layout.activity_main);//SIRVE PARA CONECTAR SU DISEÑO .XML

        editTextusuario = (EditText) findViewById(R.id.edtxt_nusuario);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        editTextpassword = (EditText) findViewById(R.id.edtxt_pusuario);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        btn_acceso = (Button) findViewById(R.id.btn_login);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        btn_acceso.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO
            @Override
            public void onClick(View v) {//METODO DE LA ACCION DEL BOTON

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                String u = editTextusuario.getText().toString();//GUARDAMOS DENTRO DE UN STRING LO QUE SE INGRESE DENTRO DEL CAMPO DE TEXTO
                String p = editTextpassword.getText().toString();//GUARDAMOS DENTRO DE UN STRING LO QUE SE INGRESE DENTRO DEL CAMPO DE TEXTO

                String usuario = dataBaseAccess.getFkUsu(u);//OBTENEMOS EL ID DEL USUARIO SI ES QUE EXISTE DENTRO DE LA TABLA
                System.out.println("USUARIO: "+usuario+", PASSWORD:"+p+u);
                String acceso = dataBaseAccess.getAcceso(usuario, p);//OBTIENE EL ID DEL ROL QUE ENCUENTRE Y CONFIRMA QUE SEA CORRECTO
                // EL ID Y CONTRASEÑA DEL USUARIO A INGRESAR
                System.out.println("ACCESO: "+acceso);
                String rol = dataBaseAccess.getRol(acceso);//OBTENEMOS EL NOMBRE DEL ROL QUE TIENE ASIGNADO
                // EL USUARIO PARA DARLE LAS PRIORIDADES DE ACCESO
                System.out.println("ROL: "+rol);
                if (u.isEmpty() || p.isEmpty()) {//COMPARAMOS SI NO SE OBTUVO ALGO DENTRO DE ALGUNO DE LOS CAMPOS DE TEXTOS

                    if (u.isEmpty() && !p.isEmpty()) {//DESPUES SI NO SE OBTUVO NADA DENTRO DEL PRIMER CAMPOR PERO SÍ DEL SEGUNDO

                        editTextusuario.setError("Usuario no Ingresado!");//MOSTRAR MENSAJE DE ERROR DENTRO DEL CAMPO DE TEXTO

                    } else if (p.isEmpty() && !u.isEmpty()) {//DESPUES SI NO SE OBTUVO NADA DENTRO DEL PRIMER CAMPO PERO SÍ DEL SEGUNDO

                        editTextpassword.setError("Password no Ingresado!");//MOSTRAR MENSAJE DE ERROR DENTRO DEL CAMPO DE TEXTO

                    } else {//SI NO ES NINGUNA DE LAS ANTERIORES CONDICIONES INGRESA EN ESTA PARA MOSTRAR EL MENSAJE DE ERROR EN LOS DOS

                        editTextusuario.setError("Usuario no Ingresado!");//MOSTRAR MENSAJE DE ERROR DENTRO DEL CAMPO DE TEXTO
                        editTextpassword.setError("Password no Ingresado!");//MOSTRAR MENSAJE DE ERROR DENTRO DEL CAMPO DE TEXTO

                    }

                } else if (acceso.equals("")){//SI LO OBTENIDO DENTRO DEL METODO  DE GETACCESO ES NADA

                    //MANDAMOS UNA NOTIFICACION EN LA PANTALLA DE DATOS INCORRECTOS
                    Toast toast3 = Toast.makeText(getApplicationContext(), "Usuario y/o Contraseña Incorrecta!!", Toast.LENGTH_LONG);
                    toast3.show();//LO MOSTRAMOS

                } else if (rol.equals("Administrativo")) {//SI LO OBTENIDO DEL METODO GETROL ES IGUAL A ADMINISTRATIVO

                    Intent intent = new Intent(v.getContext(), Administrativo.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY ADMINISTRATIVO
                    intent.putExtra("tipo", rol);//GUARDAMOS LA VARIABLE CON EL NOMBRE QUE LE DIMOS "TIPO" EL DATO DE LA VARIABLE "ROL"
                    intent.putExtra("nombre", u);//GUARDAMOS LA VARIABLE CON EL NOMBRE QUE LE DIMOS "NOMBRE" EL DATO DE LA VARIABLE "U"
                    intent.putExtra("idUsu", usuario);//GUARDAMOS LA VARIABLE CON EL NOMBRE QUE LE DIMOS "NOMBRE" EL DATO DE LA VARIABLE "U"
                    startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                    finish();//Y CERRAMOS EL ACTIVITY ACTUAL

                } else {//EN CASO DE QUE NO SE APLIQUE LAS ANTERIORES CONDICIONES

                    Intent intent = new Intent(v.getContext(), MenuJuego.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY BIENVENIDA
                    intent.putExtra("tipo", rol);//GUARDAMOS LA VARIABLE CON EL NOMBRE QUE LE DIMOS "TIPO" EL DATO DE LA VARIABLE "ROL"
                    intent.putExtra("nombre", u);//GUARDAMOS LA VARIABLE CON EL NOMBRE QUE LE DIMOS "NOMBRE" EL DATO DE LA VARIABLE "U"
                    intent.putExtra("idUsu", usuario);//GUARDAMOS LA VARIABLE CON EL NOMBRE QUE LE DIMOS "NOMBRE" EL DATO DE LA VARIABLE "U"
                    startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                    finish();//Y CERRAMOS EL ACTIVITY ACTUAL

                }

                dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

            }
        });//FIN DEL BOTON ACCESO

    }//FIN DEL METODO ONCREATE

}//FIN DE LA CLASE (ACTIVITY) MAIN
