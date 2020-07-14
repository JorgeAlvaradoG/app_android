package com.example.appaprendizaje2.clases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;//FORMATO DE CARGA
import android.view.View;
import android.widget.Button;//OBJETO BOTON
import android.widget.RadioButton;

import com.example.appaprendizaje2.R;

public class Otros extends AppCompatActivity {

    Button button_comenzar;//PARA LOS BOTONES
    RadioButton radioButton_eje, radioButton_tema, radioButton_complejidad, radioButton_habilidad,
            radioButton_actividad, radioButton_resultadoactividad, radioButton_imagen, radioButton_audio, radioButton_detalleactividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otros);

        //CONEXION DE VARIBLES CREADAS CON LOS OBJETOS CREADOS EN EL XML
        button_comenzar = (Button) findViewById(R.id.btn_comenzar);//TOMANDO CONTROL DEL OBJETO XML
        radioButton_eje = (RadioButton) findViewById(R.id.rb_eje);//TOMANDO CONTROL DEL OBJETO XML
        radioButton_tema = (RadioButton) findViewById(R.id.rb_tema);//TOMANDO CONTROL DEL OBJETO XML
        radioButton_complejidad = (RadioButton) findViewById(R.id.rb_complejidad);//TOMANDO CONTROL DEL OBJETO XML
        radioButton_habilidad = (RadioButton) findViewById(R.id.rb_habilidad);//TOMANDO CONTROL DEL OBJETO XML
        radioButton_actividad = (RadioButton) findViewById(R.id.rb_actividad);//TOMANDO CONTROL DEL OBJETO XML
        radioButton_resultadoactividad = (RadioButton) findViewById(R.id.rb_resultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        radioButton_imagen = (RadioButton) findViewById(R.id.rb_imagen);//TOMANDO CONTROL DEL OBJETO XML
        radioButton_audio = (RadioButton) findViewById(R.id.rb_audio);//TOMANDO CONTROL DEL OBJETO XML
        radioButton_detalleactividad = (RadioButton) findViewById(R.id.rb_detalleactividad);//TOMANDO CONTROL DEL OBJETO XML

        button_comenzar.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

                if (radioButton_eje.isChecked()) {

                    Intent intent = new Intent(v.getContext(), Eje.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY
                    startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO

                } else if (radioButton_tema.isChecked()) {

                    Intent intent = new Intent(v.getContext(), Tema.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY
                    startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO

                } else if (radioButton_complejidad.isChecked()) {

                    Intent intent = new Intent(v.getContext(), Complejidad.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY
                    startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO

                } else if (radioButton_habilidad.isChecked()) {

                    Intent intent = new Intent(v.getContext(), Habilidad.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY
                    startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO

                } else if (radioButton_actividad.isChecked()) {

                    Intent intent = new Intent(v.getContext(), Actividad.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY
                    startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO

                } else if (radioButton_resultadoactividad.isChecked()) {

                    Intent intent = new Intent(v.getContext(), ResultadoActividad.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY
                    startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO

                } else if (radioButton_imagen.isChecked()) {

                    Intent intent = new Intent(v.getContext(), Imagen.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY
                    startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO

                } else if (radioButton_audio.isChecked()) {

                    Intent intent = new Intent(v.getContext(), Audio.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY
                    startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO

                } else if (radioButton_detalleactividad.isChecked()) {

                    Intent intent = new Intent(v.getContext(), DetalleActividad.class);//INICIAMOS LA FUNCION INSTANT ASIGNANDO EL ACTIVITY
                    startActivityForResult(intent, 0);//ARRANCAMOS EL ACTIVITY NUEVO

                }

            }

        });

    }
}
