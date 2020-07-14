package com.example.appaprendizaje2.clases;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appaprendizaje2.R;
import com.example.appaprendizaje2.modelo.ObjImagen;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

public class Imagen extends AppCompatActivity {

    private ImageView mImageView;
    Button button_ubicacion, button_buscar, button_agregar, button_eliminar, button_modificar;//PARA LOS BOTONES
    EditText editText_ubicacion, editText_nombre, editText_descripcion;//PARA LOS CUADROS DE TEXTO
    private static final int READ_REQUEST_CODE = 42;//tipo de variable
    private static final String TAG= "Imagen";//tipo de activity donde esta
    String on = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        setTitle("Formulario Imagen");//titulo de la pantalla

        //CONEXION DE VARIBLES CREADAS CON LOS OBJETOS CREADOS EN EL XML
        mImageView = (ImageView) findViewById(R.id.imageView);

        button_ubicacion = (Button) findViewById(R.id.btn_ubicacionimagen);//TOMANDO CONTROL DEL OBJETO XML
        button_agregar = (Button) findViewById(R.id.btn_agregarimagen);//TOMANDO CONTROL DEL OBJETO XML
        button_buscar = (Button) findViewById(R.id.btn_buscarimagen);
        button_modificar = (Button) findViewById(R.id.btn_modificarimagen);//TOMANDO CONTROL DEL OBJETO XML
        button_eliminar = (Button) findViewById(R.id.btn_eliminarimagen);//TOMANDO CONTROL DEL OBJETO XML
        editText_nombre = (EditText) findViewById(R.id.et_nombreimagen);//TOMANDO CONTROL DEL OBJETO XML
        editText_descripcion = (EditText) findViewById(R.id.et_descripcionimagen);//TOMANDO CONTROL DEL OBJETO XML
        editText_ubicacion = (EditText) findViewById(R.id.et_ubicacionimagen);//TOMANDO CONTROL DEL OBJETO XML

        button_agregar.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                String nom = editText_nombre.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String des = editText_descripcion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String path = editText_ubicacion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                String registro = dataBaseAccess.setInsertImagen(nom, des, path);//GUARDAMOS EL MENSAJE DE INSERTADO O NO
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

                ObjImagen im = dataBaseAccess.SearchImagen(nom);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (nom.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                } else if (im.getNombre() != null) {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

                    System.out.println("LA IMAGEN: "+im.getNombre());
                    on = im.getNombre();//se guarda el valor para usarlo dentro del boton modificar

                    editText_descripcion.setText(im.getDescripcion());
                    editText_ubicacion.setText(im.getUbicacion());
                    mImageView.setImageURI(Uri.parse(im.getUbicacion()));//insertamos la ruta ya parseada tipo uri dentro del imageview

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Imagen Encontrada!!", Toast.LENGTH_LONG);
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                } else {

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Imagen NO encontrada!!", Toast.LENGTH_LONG);
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

                String registro = dataBaseAccess.ModificarImagen(nomm, nomn, descn, ubin);//GUARDAMOS EL MENSAJE DE INSERTADO O NO
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

                String eliminacion = dataBaseAccess.EliminarImagen(nom);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (nom.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                } else {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), eliminacion, Toast.LENGTH_LONG);
                    Limpiar();//limpia los objetos
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                }

                dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

            }

        });

        button_ubicacion.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

                editText_ubicacion.setText("");//limpia el objeto
                mImageView.setImageURI(null);//insertamos la ruta
                performFileSearch();//manda a llamar el metodo para obtener la ruta

            }

        });

    }

    public void performFileSearch() {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);//inicamos la accion que hara el intent

        intent.addCategory(Intent.CATEGORY_OPENABLE);//para abrir el gestor de archivos del movil a usar

        intent.setType("image/*");//le indicamos el tipo de archivo a buscar

        startActivityForResult(intent, READ_REQUEST_CODE);//inicamos el petodo de guardato de estado del activity
    }

    @Override//Sobreescritura
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {//Metodo para obtener la ruta de la imagen

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {//solo si obtine en mismo dato en las dos condiciones

            Uri uri = null;//creamos un nuevo tipo de ruta uri
            if (resultData != null) {//solo si el parametro del metodo es diferente a null
                uri = resultData.getData();//guarda el resultado del parametro en uri
                String ruta = uri.toString();//guardamos la ruta ya de tipo string en la variable ruta
                Log.i(TAG, "Uri: " + uri.toString());//para poder mostarlo en consola la ruta uri
                editText_ubicacion.setText(ruta);//muestra la ruta dentro del campo de texto
                mImageView.setImageURI(Uri.parse(ruta));//insertamos la ruta ya parseada tipo uri dentro del imageview
            }
        }
    }

    public void Limpiar() {//metodo para limpiar los valores dentro de los objetos

        editText_nombre.setText("");//limpia el objeto
        editText_descripcion.setText("");//limpia el objeto
        editText_ubicacion.setText("");//limpia el objeto
        mImageView.setImageURI(null);//insertamos la ruta

    }

}
