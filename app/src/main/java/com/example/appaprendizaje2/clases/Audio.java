package com.example.appaprendizaje2.clases;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appaprendizaje2.R;
import com.example.appaprendizaje2.modelo.ObjAudio;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

import java.io.IOException;

public class Audio extends AppCompatActivity {

    Button button_detener, button_ubicacion, button_buscar, button_agregar, button_eliminar, button_modificar;//PARA LOS BOTONES
    EditText editText_ubicacion, editText_nombre, editText_descripcion;//PARA LOS CUADROS DE TEXTO
    private static final int READ_REQUEST_CODE = 42;//tipo de variable
    private static final String TAG= "Audio";//tipo de activity donde esta
    MediaPlayer mp;//inicamos el metodo de reproduccion
    String on = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        setTitle("Formulario Audio");//titulo de la pantalla

        //CONEXION DE VARIBLES CREADAS CON LOS OBJETOS CREADOS EN EL XML
        button_ubicacion = (Button) findViewById(R.id.btn_ubicacionaudio);//TOMANDO CONTROL DEL OBJETO XML
        button_agregar = (Button) findViewById(R.id.btn_agregaraudio);//TOMANDO CONTROL DEL OBJETO XML
        button_buscar = (Button) findViewById(R.id.btn_buscaraudio);
        button_modificar = (Button) findViewById(R.id.btn_modificaraudio);//TOMANDO CONTROL DEL OBJETO XML
        button_eliminar = (Button) findViewById(R.id.btn_eliminaraudio);//TOMANDO CONTROL DEL OBJETO XML
        editText_nombre = (EditText) findViewById(R.id.et_nombreaudio);//TOMANDO CONTROL DEL OBJETO XML
        editText_descripcion = (EditText) findViewById(R.id.et_descripcionaudio);//TOMANDO CONTROL DEL OBJETO XML
        editText_ubicacion = (EditText) findViewById(R.id.et_ubicacionaudio);//TOMANDO CONTROL DEL OBJETO XML
        button_detener = (Button) findViewById(R.id.btn_deteneraudio);//TOMANDO CONTROL DEL OBJETO XML

        button_agregar.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                String nom = editText_nombre.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String des = editText_descripcion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String path = editText_ubicacion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                String registro = dataBaseAccess.setInsertAudio(nom, des, path);//GUARDAMOS EL MENSAJE DE INSERTADO O NO
                if (nom.isEmpty() || des.isEmpty() || path.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    if (nom.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (des.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (path.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        editText_ubicacion.setError("Ubicacion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (nom.isEmpty() && des.isEmpty() && path.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        editText_ubicacion.setError("Ubicacion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }

                } else {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

//                    Detener();
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

                String nom = editText_nombre.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                ObjAudio au = dataBaseAccess.SearchAudio(nom);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (nom.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                } else if (au.getNombre() != null) {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

//                    Detener();
                    System.out.println("EL AUDIO: "+au.getNombre());
                    on = au.getNombre();//se guarda el valor para usarlo dentro del boton modificar

                    editText_descripcion.setText(au.getDescripcion());
                    editText_ubicacion.setText(au.getUbicacion());
//                    Reproduccion(au.getUbicacion());

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Audio Encontrado!!", Toast.LENGTH_LONG);
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                } else {

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Audio NO encontrado!!", Toast.LENGTH_LONG);
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

                String nomm = on;//obtengo el valor a buscar y modificar
                String nomn = editText_nombre.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String descn = editText_descripcion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String ubin = editText_ubicacion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                String registro = dataBaseAccess.ModificarAudio(nomm, nomn, descn, ubin);//GUARDAMOS EL MENSAJE DE INSERTADO O NO
                on = "";//reinicia el valor

                if (nomn.isEmpty() || descn.isEmpty() || ubin.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    if (nomn.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (descn.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (ubin.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        editText_ubicacion.setError("Ubicacion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (nomn.isEmpty() && descn.isEmpty() && ubin.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                        editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        editText_ubicacion.setError("Ubicacion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }

                } else {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

//                    Detener();
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

                String nom = editText_nombre.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                String eliminacion = dataBaseAccess.EliminarAudio(nom);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (nom.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                } else {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

//                    Detener();
                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), eliminacion, Toast.LENGTH_LONG);
                    Limpiar();//limpia los objetos
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                }

                dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

            }

        });

        button_detener.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

//                Detener();

            }

        });

        button_ubicacion.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

//                Detener();
                editText_ubicacion.setText("");//limpia el objeto
                performFileSearch();//manda a llamar el metodo para obtener la ruta

            }

        });

    }

    public void performFileSearch() {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);//inicamos la accion que hara el intent

        intent.addCategory(Intent.CATEGORY_OPENABLE);//para abrir el gestor de archivos del movil a usar

        intent.setType("audio/*");//le indicamos el tipo de archivo a buscar

        startActivityForResult(intent, READ_REQUEST_CODE);//inicamos el petodo de guardato de estado del activity
    }

    @Override//Sobreescritura
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {//Metodo para obtener la ruta de la imagen

        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {//solo si obtine en mismo dato en las dos condiciones

            Uri uri = null;//creamos un nuevo tipo de ruta uri
            if (resultData != null) {//solo si el parametro del metodo es diferente a null
                uri = resultData.getData();//guarda el resultado del parametro en uri
                String ruta = uri.toString();//guardamos la ruta ya de tipo string en la variable ruta
                Log.i(TAG, "Uri: " + uri.toString());//para poder mostarlo en consola la ruta uri
                editText_ubicacion.setText(ruta);//muestra la ruta dentro del campo de texto

                //Reproduccion(ruta);//conectamos el metodo con la ruta obtenida

            }
        }
    }

    public void Reproduccion(String ruta){//metodo para reproducior audio

        mp = new MediaPlayer();//inicializamos un nuevo contenedor de audio
        try {
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);//indicamos el tipos de reproduccion
            mp.setDataSource(getApplicationContext(), Uri.parse(ruta));//le insertamos la ruta de tipo uri
            mp.prepare();//preparamos el contenedor para la reproduccion
        } catch (RuntimeException | IOException e) {//en caso de error
            e.printStackTrace();//obtenga el error
            System.out.println(e);//lo imprima en consola
        }
        mp.start();//iniciamos el audio

    }

    /*public void Detener(){//metodo para detener audio

        if (mp != null) {//solo si es diferente de null

            mp.stop();// detiene completamente el audio
            mp.release();//lo destruye

        }

    }*/

    public void Limpiar() {//metodo para limpiar los valores dentro de los objetos

        editText_nombre.setText("");//limpia el objeto
        editText_descripcion.setText("");//limpia el objeto
        editText_ubicacion.setText("");//limpia el objeto

    }

}
