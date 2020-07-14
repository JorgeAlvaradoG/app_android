package com.example.appaprendizaje2.clases;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;//LIBRERIA PARA INVOCAR ACTIVITYS
import android.os.Bundle;//LIBRERIA FORMATO PARA CARGAR LA APP
import android.view.View;//LIBRERIA PARA DIBUJAR CONTENIDO EN LA PANTALLA
import android.widget.ImageButton;//LIBRERIA IMAGEN BOTON QUE REALIZA UNA ACCION
import android.widget.TextView;//LIBRERIA PARA CAMPO DE MENSAJE DE TEXTO

import com.example.appaprendizaje2.R;

public class Administrativo extends AppCompatActivity {

    TextView txtadministrativo;//VARIABLE PARA LOS CAMPO MENSAJE DE TEXTO
    ImageButton btnagregaru, btnagregarc, btnsalir, btnotros;//VARIABLES PARA BOTONES IMAGEN
    String usuario = "";//VARIABLE PARA OBTENER NOMBRE DE USUARIO

    @Override//SOBRESCRIBIENDO UN METODO YA CREADO
    protected void onCreate(Bundle savedInstanceState) {//METODO PARA LAS INICIALIZACIONES
        super.onCreate(savedInstanceState);//PARA EJECUCION O ANULACION DE LA CLASE
        setContentView(R.layout.activity_administrativo);//SIRVE PARA CONECTAR SU DISEÑO .XML

        txtadministrativo = (TextView) findViewById(R.id.txt_admin);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        btnagregaru = (ImageButton) findViewById(R.id.imgbtn_agregaru);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        btnagregarc = (ImageButton) findViewById(R.id.imgbtn_agregarc);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        btnsalir = (ImageButton) findViewById(R.id.imgbutton_salir);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        btnotros = (ImageButton) findViewById(R.id.btn_otros);//REALIZA LA CONEXION ENTRE EL
        // OBJETO DENTRO DE XML CON ESTA CLASE

        String dato = getIntent().getExtras().getString("tipo");//GUARDAMOS EL DATO DE LA VARIABLE TIPO
        usuario = getIntent().getExtras().getString("nombre");//GUARDAMOS EL DATO DE LA VARIABLE NOMBRE
        txtadministrativo.setText("Bienvenido " + dato + " " + usuario);//INSERTAMOS LA FRASE DENTRO DEL CAMPO MENSAJE DE TEXTO

        btnagregaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//METODO ACCION DENTRO DEL BOTON INDICADO

                Intent intent = new Intent(v.getContext(), AgregarUsuario.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY ADMINISTRATIVO
                intent.putExtra("nombreadmin", usuario);//GUARDAMOS LA VARIABLE CON EL NOMBRE QUE LE DIMOS "TIPO" EL DATO DE LA VARIABLE "ROL"
                startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                finish();//Y CERRAMOS EL ACTIVITY ACTUAL

            }
        });//FIN DE ESTE BOTON

        btnagregarc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//METODO ACCION DENTRO DEL BOTON INDICADO

                Intent intent = new Intent(v.getContext(), AgregarCargo.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY ADMINISTRATIVO
                intent.putExtra("nombreadmin", usuario);//GUARDAMOS LA VARIABLE CON EL NOMBRE QUE LE DIMOS "TIPO" EL DATO DE LA VARIABLE "ROL"
                startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                finish();//Y CERRAMOS EL ACTIVITY ACTUAL

            }
        });//FIN DE ESTE BOTON

        btnotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//METODO ACCION DENTRO DEL BOTON INDICADO

                Intent intent = new Intent(v.getContext(), Otros.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY ADMINISTRATIVO
                startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                finish();//Y CERRAMOS EL ACTIVITY ACTUAL

            }
        });//FIN DE ESTE BOTON

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//METODO ACCION DENTRO DEL BOTON INDICADO

                System.exit(0);//SALIMOS DE LA APLICACION

            }
        });//FIN DE ESTE BOTON

    }
}