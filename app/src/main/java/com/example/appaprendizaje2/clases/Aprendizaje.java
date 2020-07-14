package com.example.appaprendizaje2.clases;

// Librerias a usar
import androidx.appcompat.app.AppCompatActivity; // para la herencia al objeto inicializacion

import android.content.Context; // para contenedor de las vistas
import android.graphics.Canvas; // para creacion del canvas o lienzo
import android.graphics.Color; // para cambio de colores
import android.graphics.Paint; // para creacion del pincel o trazo
import android.graphics.Path; // para el guardado de las coordenadas del trazo
import android.media.AudioManager;
import android.media.MediaPlayer; // para la reproduccion de los sonidos
import android.net.Uri;
import android.os.Bundle; // para la incializacion del metodo principal
import android.view.MotionEvent; // para la deteccion del touch que realiza el usuario
import android.view.View; // para la creacion de la vista
import android.widget.ImageButton;
import android.widget.LinearLayout; // para la creacion de la division de la ventana

import com.example.appaprendizaje2.R;
import com.example.appaprendizaje2.modelo.ObjAudio;
import com.example.appaprendizaje2.modelo.ObjImagen;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

import java.io.IOException;

public class Aprendizaje extends AppCompatActivity { // Clase Principal con

    private ElCanvas dibujo; // atributo de la clase canvas
    public ImageButton button; // atributo del objeto button
    int n = 1; // atributo del tipo entero
    MediaPlayer mp; // atributo para el sonido
    ObjAudio oau;// PARA OBTENER LOS METODOS SETTERS Y GETTERS DE LA CLASE
    ObjImagen oim;// PARA OBTENER LOS METODOS SETTERS Y GETTERS DE LA CLASE

    @Override // indica que estamos sobreescribiendo una funcion ya creada
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprendizaje); // guardando dentro de view el aprendizaje

        setTitle("Nivel 0 de Aprendizaje");//titulo de la pantalla

        //inicializacion de una subventana
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout1);
        dibujo = new ElCanvas(this); // inicializamos la clase canvas
        layout.addView(dibujo); // insertamos la clase canvas dentro de la subventana

        button = (ImageButton) findViewById(R.id.btn); // inicializacion del botton
        button.setOnClickListener(reini); // metemos en el boton la accion a realizar despues

    }

    public void mp3img(){ // funcion para cambiar el sonido despues de cada clic al boton

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
        // ESPECIFICOS DENTRO DE ESA CLASE
        dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

        switch (n) { // si n es...
            case 1: // igual a 0 entra y..

                oau = dataBaseAccess.SearchUnAudio(n);//MANDAMOS A LLAMAR EL METODO DE BUSCAR AUDIO
                Reproduccion(oau.getUbicacion());//METEMOS LA RUTA DEL AUDIO PARA BUSCARLA Y REPRODUCIRLA
                oim = dataBaseAccess.SearchUnaImagen(n);//obtengo la ruta dE LA IMAGEN
                button.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro

                break;
            case 2: // igual a 1 entra y..

                oau = dataBaseAccess.SearchUnAudio(n);//MANDAMOS A LLAMAR EL METODO DE BUSCAR AUDIO
                Reproduccion(oau.getUbicacion());//METEMOS LA RUTA DEL AUDIO PARA BUSCARLA Y REPRODUCIRLA
                oim = dataBaseAccess.SearchUnaImagen(n);//obtengo la ruta dE LA IMAGEN
                button.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro

                break;
            case 3: // igual a 2 entra y..

                oau = dataBaseAccess.SearchUnAudio(n);//MANDAMOS A LLAMAR EL METODO DE BUSCAR AUDIO
                Reproduccion(oau.getUbicacion());//METEMOS LA RUTA DEL AUDIO PARA BUSCARLA Y REPRODUCIRLA
                oim = dataBaseAccess.SearchUnaImagen(n);//obtengo la ruta dE LA IMAGEN
                button.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro

                break;
            case 4: // igual a 3 entra y..

                oau = dataBaseAccess.SearchUnAudio(n);//MANDAMOS A LLAMAR EL METODO DE BUSCAR AUDIO
                Reproduccion(oau.getUbicacion());//METEMOS LA RUTA DEL AUDIO PARA BUSCARLA Y REPRODUCIRLA
                oim = dataBaseAccess.SearchUnaImagen(n);//obtengo la ruta dE LA IMAGEN
                button.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro

                break;
            case 5: // igual a 4 entra y..

                oau = dataBaseAccess.SearchUnAudio(n);//MANDAMOS A LLAMAR EL METODO DE BUSCAR AUDIO
                Reproduccion(oau.getUbicacion());//METEMOS LA RUTA DEL AUDIO PARA BUSCARLA Y REPRODUCIRLA
                oim = dataBaseAccess.SearchUnaImagen(n);//obtengo la ruta dE LA IMAGEN
                button.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro

                break;
            case 6: // igual a 5 entra y..

                oau = dataBaseAccess.SearchUnAudio(n);//MANDAMOS A LLAMAR EL METODO DE BUSCAR AUDIO
                Reproduccion(oau.getUbicacion());//METEMOS LA RUTA DEL AUDIO PARA BUSCARLA Y REPRODUCIRLA
                oim = dataBaseAccess.SearchUnaImagen(n);//obtengo la ruta dE LA IMAGEN
                button.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro

                break;
            case 7: // igual a 6 entra y..

                oau = dataBaseAccess.SearchUnAudio(n);//MANDAMOS A LLAMAR EL METODO DE BUSCAR AUDIO
                Reproduccion(oau.getUbicacion());//METEMOS LA RUTA DEL AUDIO PARA BUSCARLA Y REPRODUCIRLA
                oim = dataBaseAccess.SearchUnaImagen(n);//obtengo la ruta dE LA IMAGEN
                button.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro

                break;
            case 8: // igual a 7 entra y..

                oau = dataBaseAccess.SearchUnAudio(n);//MANDAMOS A LLAMAR EL METODO DE BUSCAR AUDIO
                Reproduccion(oau.getUbicacion());//METEMOS LA RUTA DEL AUDIO PARA BUSCARLA Y REPRODUCIRLA
                oim = dataBaseAccess.SearchUnaImagen(n);//obtengo la ruta dE LA IMAGEN
                button.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro

                break;
            case 9: // igual a 8 entra y..

                oau = dataBaseAccess.SearchUnAudio(n);//MANDAMOS A LLAMAR EL METODO DE BUSCAR AUDIO
                Reproduccion(oau.getUbicacion());//METEMOS LA RUTA DEL AUDIO PARA BUSCARLA Y REPRODUCIRLA
                oim = dataBaseAccess.SearchUnaImagen(n);//obtengo la ruta dE LA IMAGEN
                button.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro

                break;
            case 10: // igual a 9 entra y..

                oau = dataBaseAccess.SearchUnAudio(n);//MANDAMOS A LLAMAR EL METODO DE BUSCAR AUDIO
                Reproduccion(oau.getUbicacion());//METEMOS LA RUTA DEL AUDIO PARA BUSCARLA Y REPRODUCIRLA
                oim = dataBaseAccess.SearchUnaImagen(n);//obtengo la ruta dE LA IMAGEN
                button.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro

                break;
            case 11: // igual a 10 entra y..

                oau = dataBaseAccess.SearchUnAudio(n);//MANDAMOS A LLAMAR EL METODO DE BUSCAR AUDIO
                Reproduccion(oau.getUbicacion());//METEMOS LA RUTA DEL AUDIO PARA BUSCARLA Y REPRODUCIRLA
                oim = dataBaseAccess.SearchUnaImagen(n);//obtengo la ruta dE LA IMAGEN
                button.setImageURI(Uri.parse(oim.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro
                n = 0; // asignaos el valor para reiniciar el cambio de sonido

            default:

            dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

        }

    }

    View.OnClickListener reini = new View.OnClickListener() { // creando el metodo que insertamos
        @Override                                             // anteriormente en el button
        public void onClick(View v) { // metodo de accion del boton

            mp3img(); // llamamos a la funcion para el cambio de sonidos
            dibujo.dato(); // llamamos a la funcion que esta en la clase
        }                  // ElCanvas para borrar lo dibujado por el usuario
    };

    public class ElCanvas extends View { // Clase creada en la cual le pusimos de nombre Canvas con herencia de un view

        Paint paint1 = new Paint(); // creacion del segundo lienzo
        float x = 0; // variable para obtener la posicion x
        float y = 0; // variable para obtener la posicion y
        String action = "accion"; // variable para obtener el estatus del movimiento del path
        // inicializamos el Path para poder obtner la ruta del trazo mas adelante
        Path path = new Path(); // creacion del medoto path para guardar la posicion del dedo, mouse o pincel

        public ElCanvas(Context context) { // Creamos el context para poder entendernos con el metodo main principal
            super(context); // la clase con la funcion super para indicar que hace este metodo dentro
        }

        @Override
        protected void onDraw(Canvas canvas) { // define la forma en que se dibuja el componente

            //canvas.drawColor(Color.CYAN); // definimos un color al Lienzo o Canvas

            // inicializamos el metodo paint para definir el color, el tamaño del relleno dentro del trazo, etc
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE); // en especifico da el estilo del trazo
            paint.setStrokeWidth(5); // asigna el tamaño del pincel
            paint.setColor(Color.WHITE); // asigna el color del pincel

            if (action == "down") // para verificar el estatus de la posicion que la ruta guarda
                path.moveTo(x, y); // insertamos las coordenadas detectadas
            if (action == "move") // para verificar el estatus de la posicion que la ruta guarda
                path.lineTo(x, y); // insertamos las coordenadas detectadas

            paint1.setTextSize(50); // insertamos el tamalo del texto a dibujar
            paint1.setColor(Color.WHITE); // insertamos el color del trazo
            canvas.drawText("Hola!, Traza el numero de arriba", getWidth()/28, 100, paint1); // indicamos el texto y su coordenada

            canvas.drawPath(path, paint); //para empezar a pintar dependiendo la posicion del path
            System.out.println("Path: "+path);

        }

        public void dato(){ // funcion para borrar lo
            // dibujado por el usuario
            path.reset();// funcion para reiniciar la posicion del trazo
            invalidate(); // funcion para repintar el nuevo trazo

            n++; // variable para aumentar el numero de clic del boton

            x = 0; // reinicio de la deteccion de la posicion del dedo en x
            y = 0; // reinicio de la deteccion de la posicion del dedo en y

        }

        public boolean onTouchEvent(MotionEvent e) { // metodo para detectar si se
            // esta tocando el usuario la pantalla
            x = e.getX(); // detecta la posicion del dedo en x
            y = e.getY(); // detecta la posicion del dedo en y

            if (e.getAction() == MotionEvent.ACTION_DOWN) // detecta hacia que direccion va trazando el dedo
                action = "down"; // cambiamos la palabra dentro del string
            if (e.getAction() == MotionEvent.ACTION_MOVE) // detecta si se mueve el dedo dentro del canvas o vista
                action = "move"; // cambiamos la palabra dentro del string

            invalidate(); // sirve para poder repintar cada segundo

            return true; // enviamos el true cuando detecte el movimiento del dedo, boligrafo, mouse, etc...
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

}