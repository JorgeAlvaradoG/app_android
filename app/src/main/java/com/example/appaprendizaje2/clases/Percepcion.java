package com.example.appaprendizaje2.clases;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appaprendizaje2.R;
import com.example.appaprendizaje2.modelo.ObjAudio;
import com.example.appaprendizaje2.modelo.ObjImagen;
import com.example.appaprendizaje2.modelo.ObjResultadoActividad;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

import java.io.IOException;
import java.util.Random;

public class Percepcion extends AppCompatActivity {

    ImageButton imageButton_iniciar, imageButton_opc1, imageButton_opc2, imageButton_opc3; // atributo del objeto button
    MediaPlayer mp; // atributo para el sonido
    ObjAudio oau;//inicio la clase para el uso de sus getters y setters
    ObjImagen oim;//inicio la clase para el uso de sus getters y setters
    TextView txt1, txt2, txt3, txtmarcador, txtcronometro;//variables para las etiquetas de texto
    String resultado_actividad = "", usuario = "";//varaibles string
    ImageView imageView_percepcion_finish;//variable para la etiqueta de imagen
    int n = 1, iniciar = 0, opc1 = 0, opc2 = 0, opc3 = 0, imgcorrecta = 0, h = 0, puntos = 0, error = 0, intentos = 1, porcentaje = 0; // atributo del tipo entero
    float tStart = 0, elapsedSeconds = 0;//variables para obtener el tiempo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percepcion);

        setTitle("Nivel 2 de Percepcion");//titulo de la pantalla

        usuario = getIntent().getExtras().getString("idUsu");//OBTENEMOS EL ID DEL USUARIO
        imageButton_iniciar = (ImageButton) findViewById(R.id.imgbtn_iniciar2); // inicializacion del botton
        imageButton_opc1 = (ImageButton) findViewById(R.id.imgbtn_opc12); // inicializacion del botton
        imageButton_opc2 = (ImageButton) findViewById(R.id.imgbtn_opc22); // inicializacion del botton
        imageButton_opc3 = (ImageButton) findViewById(R.id.imgbtn_opc32); // inicializacion del botton
        txt1 = (TextView) findViewById(R.id.text_opc12);// inicializacion texto
        txt2 = (TextView) findViewById(R.id.text_opc22);// inicializacion texto
        txt3 = (TextView) findViewById(R.id.text_opc32);// inicializacion texto
        txtmarcador = (TextView) findViewById(R.id.text_marcado2);// inicializacion texto
        txtcronometro = (TextView) findViewById(R.id.text_cronometro2);// inicializacion texto
        imageView_percepcion_finish = (ImageView) findViewById(R.id.imgv_percepcion2);// inicializacion etiqueta para la imagen
        DesactivarBTN();

        imageButton_iniciar.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO
            @Override//sobreescritura
            public void onClick(View v) {//METODO DE LA ACCION DEL BOTON

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                Random r = new Random();//metodo para generacion de numeros aleatorio
                iniciar = (int) (r.nextDouble() * (10 - 1 + 1) + 1);//guardaos el numero generado

                h = (int) (r.nextDouble() * (3 - 1 + 1) + 1);//guardaos el numero generado

                if (h == 1) {//en caso que sea 1
                    opc1 = iniciar;//guardamos el valor
                    imgcorrecta = 14+iniciar;
                } else {//en caso de que no sea 1
                    opc1 = (int) (r.nextDouble() * (10 - 1 + 1) + 1);//guardaos el numero generado
                    if (opc1 == iniciar) {//solo si es igual
                        opc1 = (int) (r.nextDouble() * (10 - 1 + 1) + 1);//guardaos el numero generado
                    }
                }
                if (h == 2) {//en caso que sea 2
                    opc2 = iniciar;//guardamos el valor
                    imgcorrecta = 14+iniciar;
                } else {//en caso de que no sea 2
                    opc2 = (int) (r.nextDouble() * (10 - 1 + 1) + 1);//guardaos el numero generado
                    if (opc2 == iniciar) {//solo si es igual
                        opc2 = (int) (r.nextDouble() * (10 - 1 + 1) + 1);//guardaos el numero generado
                    }
                }
                if (h == 3) {//en caso que sea 3
                    opc3 = iniciar;//guardamos el valor
                    imgcorrecta = 14+iniciar;
                } else {//en caso de que no sea 3
                    opc3 = (int) (r.nextDouble() * (10 - 1 + 1) + 1);//guardaos el numero generado
                    if (opc3 == iniciar) {//solo si es igual
                        opc3 = (int) (r.nextDouble() * (10 - 1 + 1) + 1);//guardaos el numero generado
                    }
                }

                if (n <= 10) {//solo si es menor o igual a 10
                    if (tStart == 0){//condicion para obtener el tiempo solo inicial
                        tStart = SystemClock.elapsedRealtime();//entrado obtien el tiempo actual
                    }

                    imageButton_opc1.setEnabled(true);//HABILITAMOS EL BOTON
                    imageButton_opc2.setEnabled(true);//HABILITAMOS EL BOTON
                    imageButton_opc3.setEnabled(true);//HABILITAMOS EL BOTON
                    imageButton_iniciar.setEnabled(false);//DESHABILITAMOS EL BOTON
                    txtmarcador.setText("");//INDICAMOS QUE NO INSERTE NINGUN TEXTO
                    txtcronometro.setText("");//INDICAMOS QUE NO INSERTE NINGUN TEXTO

                    txt1.setText("OPCION 1");//INSERTAMOS EL SIGUIENTE TEXTO
                    txt2.setText("OPCION 2");//INSERTAMOS EL SIGUIENTE TEXTO
                    txt3.setText("OPCION 3");//INSERTAMOS EL SIGUIENTE TEXTO

                    /*System.out.println("android.resource://"+getPackageName()+"/"+R.drawable.continuar);//ejemplo de continuar
                    imageButton_iniciar.setImageURI(Uri.parse("android.resource://com.example.appaprendizaje2/2131099752"));//continuar*/
                    oim = dataBaseAccess.SearchUnaImagen(13);//obtengo la ruta de continuar
                    imageButton_iniciar.setImageURI(Uri.parse(oim.getUbicacion()));//muestra la imagen continuar
                    oim = dataBaseAccess.SearchUnaImagen(opc1);//MANDAMOS A LLAMAR LA RUTA DE LA IMAGEN
                    imageButton_opc1.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro
                    oim = dataBaseAccess.SearchUnaImagen(opc2);//MANDAMOS A LLAMAR LA RUTA DE LA IMAGEN
                    imageButton_opc2.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro
                    oim = dataBaseAccess.SearchUnaImagen(opc3);//MANDAMOS A LLAMAR LA RUTA DE LA IMAGEN
                    imageButton_opc3.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro
                    oim = dataBaseAccess.SearchUnaImagen(imgcorrecta);//obtengo la ruta de finish
                    imageView_percepcion_finish.setImageURI(Uri.parse(oim.getUbicacion()));//muestra la imagen finish
                    n++;//AUMENTAMOS LA VARIABLE PARA QUE SOLO TENGA 10 PREGUNTAS
                } else {
                    long tEnd = SystemClock.elapsedRealtime();//obtenemos el tiempo actual
                    float tDelta = tEnd - tStart;//restamos el tiempo final por el inicial
                    elapsedSeconds = tDelta / 1000;//dividimos para sacar los segundos
                    if (elapsedSeconds >= 60) {//entramos para pasarlo a minutos solo si es iguai o mayor a 60
                        elapsedSeconds = elapsedSeconds / 60;//convertimos a minutos
                        txtcronometro.setText("Tiempo: " + (String.format("%.2f", elapsedSeconds)) + " minutos");//lo mostramos
                    } else {//en caso de que sea menor a 60
                        txtcronometro.setText("Tiempo: " + (String.format("%.2f", elapsedSeconds)) + " segundos");//lo mostramos
                    }
                    DesactivarBTN();

                    txt1.setText("");//INDICAMOS QUE NO INSERTE NINGUN TEXTO
                    txt2.setText("");//INDICAMOS QUE NO INSERTE NINGUN TEXTO
                    txt3.setText("");//INDICAMOS QUE NO INSERTE NINGUN TEXTO

                    oau = dataBaseAccess.SearchUnAudio(11);//MANDAMOS A LLAMAR EL METODO DE BUSCAR AUDIO
                    Reproduccion(oau.getUbicacion());//METEMOS LA RUTA DEL AUDIO PARA BUSCARLA Y REPRODUCIRLA

                    Toast toast = Toast.makeText(getApplicationContext(), "Aciertos:" + puntos + ", Errores: " + error, Toast.LENGTH_LONG);//RESULTADO
                    toast.show();//MOSTRAMOS LA NOTIFICACION
                    oim = dataBaseAccess.SearchUnaImagen(11);//obtengo la ruta de reset
                    imageButton_iniciar.setImageURI(Uri.parse(oim.getUbicacion()));//muestra la imagen reset
                    imageButton_opc1.setImageURI(null);//para que no muestre nada
                    imageButton_opc2.setImageURI(null);//para que no muestre nada
                    imageButton_opc3.setImageURI(null);//para que no muestre nada
                    oim = dataBaseAccess.SearchUnaImagen(14);//obtengo la ruta de finish
                    imageView_percepcion_finish.setImageURI(Uri.parse(oim.getUbicacion()));//muestra la imagen finish
                    txtmarcador.setText("Aciertos:" + puntos + ", Errores: " + error);//MUESTRA EL RESULTADO EN UN TEXT

                    porcentaje = 10 * puntos;//CALCULA EL PORCENTAJE DE ACIERTOS

                    if (intentos == 1) {//INGRESA SOLO SI ES UNO PARA AGREGAR NUEVO REGISTRO
                        ObjResultadoActividad clasificacion = dataBaseAccess.SearchUltimoResultadoActividad();//OBTENEMOS LOS DATOS AL LLAMAR EL METODO
                        //INSERTAMOS CON EL SIGUIENTE METODO LOS DATOS DENTRO DE LA TABLA
                        resultado_actividad = dataBaseAccess.setInsertNuevoResultadoActividad(2, usuario, clasificacion.getClasificacion()+1,
                                "Nivel 2", intentos+"", "10", porcentaje + " %", 2);//CADA PARAMETRO LO INSERTAMOS
                        Toast toast1 = Toast.makeText(getApplicationContext(), resultado_actividad, Toast.LENGTH_LONG);//MOSTRAMOS NOTIFICACION CONFIRMANDO EL REGISTRO
                        toast1.show();//MOSTRAMOS LA NOTIFICACION
                        intentos++;//AUMENTAMOS LA VARIABLE PARA REGISTRAR LAS VECES QUE LO INTENTO EN ESTA SESION
                    } else {//EN CASO DE QUE NO SEA IGUAL A 1

                        ObjResultadoActividad idactual = dataBaseAccess.SearchUltimoResultadoActividad();//GUARDA LOS DATOS DE LA BUSQUEDA
                        //MODIFICA EL REGISTRO CREADO ACTIALMENTE, SOLO EN CIERTOS PARAMETROS
                        String resultado_intentos = dataBaseAccess.ModificarNuevoResultadoActividad(idactual.getId(), intentos+"", porcentaje + " %");
                        Toast toast1 = Toast.makeText(getApplicationContext(), resultado_intentos, Toast.LENGTH_LONG);//MUESTRA NOTIFICACION DE RESULTADO
                        toast1.show();//MOSTRAMOS LA NOTIFICACION
                        intentos++;//AUMENTA LOS INTENTOS
                    }

                    n = 1;//REINICIA LOS VALORES
                    puntos = 0;//REINICIA LOS VALORES
                    error = 0;//REINICIA LOS VALORES
                    tStart = 0;//REINICIA LOS VALORES
                    elapsedSeconds = 0;//REINICIA LOS VALORES
                }

                dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

            }
        });//FIN DEL BOTON ACCESO

        imageButton_opc1.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO
            @Override
            public void onClick(View v) {//METODO DE LA ACCION DEL BOTON

                imageButton_iniciar.setEnabled(true);//HABILITAMOS EL BOTON

                if (iniciar == opc1) {
                    DesactivarBTN();
                    puntos++;//AUMENTAMOS LOS ACIERTOS
                    Toast toast = Toast.makeText(getApplicationContext(), "Correcto!!", Toast.LENGTH_SHORT);//NOTIFICACION
                    toast.show();//MOSTRAMOS LA NOTIFICACION
                } else {
                    DesactivarBTN();
                    error++;//AUMENTAMOS LOS ERRORES
                    Toast toast = Toast.makeText(getApplicationContext(), "Error, El numero correcto es: " + (iniciar-1), Toast.LENGTH_SHORT);//NOTIFICACION
                    toast.show();//MOSTRAMOS LA NOTIFICACION
                }

            }
        });//FIN DEL BOTON ACCESO

        imageButton_opc2.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO
            @Override
            public void onClick(View v) {//METODO DE LA ACCION DEL BOTON

                imageButton_iniciar.setEnabled(true);//HABILITAMOS EL BOTON

                if (iniciar == opc2) {
                    DesactivarBTN();
                    puntos++;//AUMENTAMOS LOS ACIERTOS
                    Toast toast = Toast.makeText(getApplicationContext(), "Correcto!!", Toast.LENGTH_SHORT);//NOTIFICACION
                    toast.show();//MOSTRAMOS LA NOTIFICACION
                } else {
                    DesactivarBTN();
                    error++;//AUMENTAMOS LOS ERRORES
                    Toast toast = Toast.makeText(getApplicationContext(), "Error, El numero correcto es: " + (iniciar-1), Toast.LENGTH_SHORT);//NOTIFICACION
                    toast.show();//MOSTRAMOS LA NOTIFICACION
                }

            }
        });//FIN DEL BOTON ACCESO

        imageButton_opc3.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO
            @Override
            public void onClick(View v) {//METODO DE LA ACCION DEL BOTON

                imageButton_iniciar.setEnabled(true);//HABILITAMOS EL BOTON

                if (iniciar == opc3) {
                    DesactivarBTN();
                    puntos++;//AUMENTAMOS LOS ACIERTOS
                    Toast toast = Toast.makeText(getApplicationContext(), "Correcto!!", Toast.LENGTH_SHORT);//NOTIFICACION
                    toast.show();//MOSTRAMOS LA NOTIFICACION
                } else {
                    DesactivarBTN();
                    error++;//AUMENTAMOS LOS ERRORES
                    Toast toast = Toast.makeText(getApplicationContext(), "Error, El numero correcto es: " + (iniciar-1), Toast.LENGTH_SHORT);//NOTIFICACION
                    toast.show();//MOSTRAMOS LA NOTIFICACION
                }

            }
        });//FIN DEL BOTON ACCESO

    }

    public void DesactivarBTN(){

        imageButton_opc1.setEnabled(false);//DESHABILITAMOS EL BOTON
        imageButton_opc2.setEnabled(false);//DESHABILITAMOS EL BOTON
        imageButton_opc3.setEnabled(false);//DESHABILITAMOS EL BOTON

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

}
