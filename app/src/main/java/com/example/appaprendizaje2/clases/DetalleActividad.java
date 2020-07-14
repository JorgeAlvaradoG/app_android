package com.example.appaprendizaje2.clases;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appaprendizaje2.R;
import com.example.appaprendizaje2.modelo.ObjDetalleActividad;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

import java.util.ArrayList;

public class DetalleActividad extends AppCompatActivity {

    Button button_buscar, button_agregar, button_eliminar, button_modificar;//PARA LOS BOTONES
    EditText editText_descripcion;//PARA LOS CUADROS DE TEXTO
    Spinner spinner_imagen, spinner_audio, spinner_actividad;//PARA LAS LISTAS DE OPCIONES
    String on = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_actividad);

        setTitle("Formulario Detalle de Actividad");//titulo de la pantalla

        //CONEXION DE VARIBLES CREADAS CON LOS OBJETOS CREADOS EN EL XML
        button_buscar = (Button) findViewById(R.id.btn_buscarDA);//TOMANDO CONTROL DEL OBJETO XML
        button_agregar = (Button) findViewById(R.id.btn_agregarDA);//TOMANDO CONTROL DEL OBJETO XML
        button_modificar = (Button) findViewById(R.id.btn_modificarDA);//TOMANDO CONTROL DEL OBJETO XML
        button_eliminar = (Button) findViewById(R.id.btn_eliminarDA);//TOMANDO CONTROL DEL OBJETO XML
        editText_descripcion = (EditText) findViewById(R.id.et_descripcionDA);//TOMANDO CONTROL DEL OBJETO XML
        spinner_imagen = (Spinner) findViewById(R.id.sp_imagenDA);//TOMANDO CONTROL DEL OBJETO XML
        spinner_audio = (Spinner) findViewById(R.id.sp_audioDA);//TOMANDO CONTROL DEL OBJETO XML
        spinner_actividad = (Spinner) findViewById(R.id.sp_actividadDA);//TOMANDO CONTROL DEL OBJETO XML

        //GUARDAMOS DENTRO DEL SPINNER TODOS LOS DATOS QUE SE OBTIENE DEL ARREGLO
        spinner_imagen.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AgregarImagenSpinner()));

        //GUARDAMOS DENTRO DEL SPINNER TODOS LOS DATOS QUE SE OBTIENE DEL ARREGLO
        spinner_audio.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AgregarAudioSpinner()));

        //GUARDAMOS DENTRO DEL SPINNER TODOS LOS DATOS QUE SE OBTIENE DEL ARREGLO
        spinner_actividad.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AgregarActividadSpinner()));

        button_agregar.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                String des = editText_descripcion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String img = (String) spinner_imagen.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String aud = (String) spinner_audio.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String act = (String) spinner_actividad.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                int idimg = Integer.parseInt(dataBaseAccess.getId_Imagen(img));//GUARDAMOS EL IDE DEL METODO
                int idaud = Integer.parseInt(dataBaseAccess.getId_Audio(aud));//GUARDAMOS EL IDE DEL METODO
                int idact = Integer.parseInt(dataBaseAccess.getId_Actividad(act));//GUARDAMOS EL IDE DEL METODO

                String registro = dataBaseAccess.setInsertDetalleActividad(des, idimg, idaud, idact);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (des.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

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

                String des = editText_descripcion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                ObjDetalleActividad da = dataBaseAccess.SearchDetalleActividad(des);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (des.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                } else if (da.getDescripcion() != null) {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

                    on = da.getDescripcion();//se guarda el valor para usarlo dentro del boton modificar
                    String nombreimagen = dataBaseAccess.getNombreImagen(da.getImagen());//obtener el string desde el id
                    String nombreaudio = dataBaseAccess.getNombreAudio(da.getAudio());//obtener el string desde el id
                    String nombreactividad = dataBaseAccess.getNombreActividad(da.getActividad());//obtener el string desde el id
                    //for para seleccionar la opcion que se guardo en la BD
                    for (int i = 0; i < AgregarImagenSpinner().length; i++){

                        if  (nombreimagen.equals(AgregarImagenSpinner()[i])) {//ingresa solo si son iguales
                            spinner_imagen.setSelection(i);//y selecciona la pisicion con la que entro a la condicion
                        }
                    }

                    for (int i = 0; i < AgregarAudioSpinner().length; i++){

                        if  (nombreaudio.equals(AgregarAudioSpinner()[i])) {//ingresa solo si son iguales
                            spinner_audio.setSelection(i);//y selecciona la pisicion con la que entro a la condicion
                        }
                    }

                    for (int i = 0; i < AgregarActividadSpinner().length; i++){

                        if  (nombreactividad.equals(AgregarActividadSpinner()[i])) {//ingresa solo si son iguales
                            spinner_actividad.setSelection(i);//y selecciona la pisicion con la que entro a la condicion
                        }
                    }

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Detalle de Actividad Encontrada!!", Toast.LENGTH_LONG);
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                } else {

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Detalle de Actividad NO encontrada!!", Toast.LENGTH_LONG);
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

                String desm = on;//obtengo el valor a buscar y modificar
                String desn = editText_descripcion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String imgn = (String) spinner_imagen.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String audn = (String) spinner_audio.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String actn = (String) spinner_actividad.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                int idimagen = Integer.parseInt(dataBaseAccess.getId_Imagen(imgn));//GUARDAMOS EL IDE DEL METODO
                int idaudio = Integer.parseInt(dataBaseAccess.getId_Audio(audn));//GUARDAMOS EL IDE DEL METODO
                int idactividad = Integer.parseInt(dataBaseAccess.getId_Actividad(actn));//GUARDAMOS EL IDE DEL METODO

                String registro = dataBaseAccess.ModificarDetalleActividad(desm, desn, idimagen, idaudio, idactividad);//GUARDAMOS EL MENSAJE DE INSERTADO O NO
                on = "";//reinicia el valor

                if (desn.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

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

                String des = editText_descripcion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                String eliminacion = dataBaseAccess.EliminarDetalleActividad(des);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (des.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                } else {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), eliminacion, Toast.LENGTH_LONG);
                    Limpiar();//limpia los objetos
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                }

                dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

            }

        });

    }

    public String[] AgregarImagenSpinner(){//METODO PARA IR AGREGANDO LOS DATOS DE LA COLUMNA DE LA BASE DE DATOS A UN ARREGLO

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
        // ESPECIFICOS DENTRO DE ESA CLASE
        dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

        ArrayList<String> img;//CREAMOS EL ARREGLO LLAMADO CARGO
        img = dataBaseAccess.getImagen();//GUARDAMOS DENTRO DEL ARREGLO LO QUE OBTIENE DEL METODO

        String[] img2 = new String[img.size()];//CREAMOS UN ARREGLO NORMAL CON EL TAMAÑO DEL ARREGLO LIST

        for(int i = 0; i < img.size(); i++){//BUCLE PARA IR GUARDANDO LOS DATOS DEL LIST DENTRO DEL ARREGLO NORMAL

            img2[i] = img.get(i);//GUARDAMOS LOS DATO DEL LIS EN EL ARRAY NORMAL
            System.out.println(img2[i]);//IMPRIMIMOS LOS DATOS EN LA CONSOLA

        }//FIN DEL BUCLE

        dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

        return img2;//RETORNAMOS EL ARREGLO YA CON LOS ROLES GUARDADOS

    }//FIN DE ESTE METODO AGREGARSPINNER

    public String[] AgregarAudioSpinner(){//METODO PARA IR AGREGANDO LOS DATOS DE LA COLUMNA DE LA BASE DE DATOS A UN ARREGLO

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
        // ESPECIFICOS DENTRO DE ESA CLASE
        dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

        ArrayList<String> aud;//CREAMOS EL ARREGLO LLAMADO CARGO
        aud = dataBaseAccess.getAudio();//GUARDAMOS DENTRO DEL ARREGLO LO QUE OBTIENE DEL METODO

        String[] aud2 = new String[aud.size()];//CREAMOS UN ARREGLO NORMAL CON EL TAMAÑO DEL ARREGLO LIST

        for(int i = 0; i < aud.size(); i++){//BUCLE PARA IR GUARDANDO LOS DATOS DEL LIST DENTRO DEL ARREGLO NORMAL

            aud2[i] = aud.get(i);//GUARDAMOS LOS DATO DEL LIS EN EL ARRAY NORMAL
            System.out.println(aud2[i]);//IMPRIMIMOS LOS DATOS EN LA CONSOLA

        }//FIN DEL BUCLE

        dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

        return aud2;//RETORNAMOS EL ARREGLO YA CON LOS ROLES GUARDADOS

    }//FIN DE ESTE METODO AGREGARSPINNER

    public String[] AgregarActividadSpinner(){//METODO PARA IR AGREGANDO LOS DATOS DE LA COLUMNA DE LA BASE DE DATOS A UN ARREGLO

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
        // ESPECIFICOS DENTRO DE ESA CLASE
        dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

        ArrayList<String> act;//CREAMOS EL ARREGLO LLAMADO CARGO
        act = dataBaseAccess.getActividad();//GUARDAMOS DENTRO DEL ARREGLO LO QUE OBTIENE DEL METODO

        String[] act2 = new String[act.size()];//CREAMOS UN ARREGLO NORMAL CON EL TAMAÑO DEL ARREGLO LIST

        for(int i = 0; i < act.size(); i++){//BUCLE PARA IR GUARDANDO LOS DATOS DEL LIST DENTRO DEL ARREGLO NORMAL

            act2[i] = act.get(i);//GUARDAMOS LOS DATO DEL LIS EN EL ARRAY NORMAL
            System.out.println(act2[i]);//IMPRIMIMOS LOS DATOS EN LA CONSOLA

        }//FIN DEL BUCLE

        dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

        return act2;//RETORNAMOS EL ARREGLO YA CON LOS ROLES GUARDADOS

    }//FIN DE ESTE METODO AGREGARSPINNER

    public void Limpiar() {//metodo para limpiar los valores dentro de los objetos

        editText_descripcion.setText("");//limpia el objeto
        spinner_imagen.setSelection(0);//limpia el objeto
        spinner_audio.setSelection(0);//limpia el objeto
        spinner_actividad.setSelection(0);//limpia el objeto

    }

}
