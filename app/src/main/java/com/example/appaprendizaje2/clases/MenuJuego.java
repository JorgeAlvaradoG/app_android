package com.example.appaprendizaje2.clases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appaprendizaje2.R;

public class MenuJuego extends AppCompatActivity {

    Button button_nivel0, button_nivel1, button_nivel2, button_nivel3;//VARIABLE PARA EL BOTON
    String usuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_juego);

        usuario = getIntent().getExtras().getString("idUsu");//OBTENEMOS EL ID DEL USUARIO

        button_nivel0 = (Button) findViewById(R.id.btn_nivel0);//CONEXION CON EL OBJETO XML
        button_nivel1 = (Button) findViewById(R.id.btn_nivel1);//CONEXION CON EL OBJETO XML
        button_nivel2 = (Button) findViewById(R.id.btn_nivel2);//CONEXION CON EL OBJETO XML
        button_nivel3 = (Button) findViewById(R.id.btn_nivel3);//CONEXION CON EL OBJETO XML

        button_nivel0.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO
            @Override
            public void onClick(View v) {//METODO DE LA ACCION DEL BOTON

                Intent intent = new Intent(v.getContext(), Aprendizaje.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY ADMINISTRATIVO
                startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                finish();//Y CERRAMOS EL ACTIVITY ACTUAL

            }
        });//FIN DEL BOTON ACCESO

        button_nivel1.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO
            @Override
            public void onClick(View v) {//METODO DE LA ACCION DEL BOTON

                Intent intent = new Intent(v.getContext(), Atencion.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY ADMINISTRATIVO
                intent.putExtra("idUsu", usuario);//GUARDAMOS LA VARIABLE CON EL NOMBRE QUE LE DIMOS "NOMBRE" EL DATO DE LA VARIABLE "U"
                startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                finish();//Y CERRAMOS EL ACTIVITY ACTUAL

            }
        });//FIN DEL BOTON ACCESO

        button_nivel2.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO
            @Override
            public void onClick(View v) {//METODO DE LA ACCION DEL BOTON

                Intent intent = new Intent(v.getContext(), Percepcion.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY ADMINISTRATIVO
                intent.putExtra("idUsu", usuario);//GUARDAMOS LA VARIABLE CON EL NOMBRE QUE LE DIMOS "NOMBRE" EL DATO DE LA VARIABLE "U"
                startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                finish();//Y CERRAMOS EL ACTIVITY ACTUAL

            }
        });//FIN DEL BOTON ACCESO

        button_nivel3.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO
            @Override
            public void onClick(View v) {//METODO DE LA ACCION DEL BOTON

                Intent intent = new Intent(v.getContext(), Memoria.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY ADMINISTRATIVO
                intent.putExtra("idUsu", usuario);//GUARDAMOS LA VARIABLE CON EL NOMBRE QUE LE DIMOS "NOMBRE" EL DATO DE LA VARIABLE "U"
                startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO
                finish();//Y CERRAMOS EL ACTIVITY ACTUAL

            }
        });//FIN DEL BOTON ACCESO

    }
}
