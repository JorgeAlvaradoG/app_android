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
import com.example.appaprendizaje2.modelo.ObjTema;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

import java.util.ArrayList;

public class Tema extends AppCompatActivity {

    Button button_buscar, button_agregar, button_eliminar, button_modificar;//PARA LOS BOTONES
    EditText editText_descripcion;//PARA LOS CUADROS DE TEXTO
    Spinner spinner_eje;//PARA LAS LISTAS DE OPCIONES
    String on = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);

        setTitle("Formulario Tema");//titulo de la pantalla

        //CONEXION DE VARIBLES CREADAS CON LOS OBJETOS CREADOS EN EL XML
        button_buscar = (Button) findViewById(R.id.btn_buscartema);//TOMANDO CONTROL DEL OBJETO XML
        button_agregar = (Button) findViewById(R.id.btn_agregartema);//TOMANDO CONTROL DEL OBJETO XML
        button_modificar = (Button) findViewById(R.id.btn_modificartema);//TOMANDO CONTROL DEL OBJETO XML
        button_eliminar = (Button) findViewById(R.id.btn_eliminartema);//TOMANDO CONTROL DEL OBJETO XML
        editText_descripcion = (EditText) findViewById(R.id.et_descripciontema);//TOMANDO CONTROL DEL OBJETO XML
        spinner_eje = (Spinner) findViewById(R.id.sp_ejetema);//TOMANDO CONTROL DEL OBJETO XML

        //GUARDAMOS DENTRO DEL SPINNER TODOS LOS DATOS QUE SE OBTIENE DEL ARREGLO
        spinner_eje.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AgregarEjeSpinner()));

        button_agregar.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                String des = editText_descripcion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String ejen = (String) spinner_eje.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                int ideje = Integer.parseInt(dataBaseAccess.getId_Eje(ejen));//GUARDAMOS EL IDE DEL METODO

                String registro = dataBaseAccess.setInsertTema(des, ideje);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

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

                ObjTema te = dataBaseAccess.SearchTema(des);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (des.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                } else if (te.getDescripcion() != null) {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

                    on = te.getDescripcion();//se guarda el valor para usarlo dentro del boton modificar
                    String descripeje = dataBaseAccess.getDescripcionEje(te.getEje_tema());//obtener el string desde el id
                    //for para seleccionar la opcion que se guardo en la BD
                    for (int i = 0; i < AgregarEjeSpinner().length; i++){

                        if  (descripeje.equals(AgregarEjeSpinner()[i])) {//ingresa solo si son iguales
                            spinner_eje.setSelection(i);//y selecciona la pisicion con la que entro a la condicion
                        }
                    }

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Tema Encontrado!!", Toast.LENGTH_LONG);
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                } else {

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Tema NO encontrado!!", Toast.LENGTH_LONG);
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
                String ejen = (String) spinner_eje.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                int ideje = Integer.parseInt(dataBaseAccess.getId_Eje(ejen));//GUARDAMOS EL IDE DEL METODO

                String registro = dataBaseAccess.ModificarTema(desm, desn, ideje);//GUARDAMOS EL MENSAJE DE INSERTADO O NO
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

                String eliminacion = dataBaseAccess.EliminarTema(des);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

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

    public String[] AgregarEjeSpinner(){//METODO PARA IR AGREGANDO LOS DATOS DE LA COLUMNA DE LA BASE DE DATOS A UN ARREGLO

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
        // ESPECIFICOS DENTRO DE ESA CLASE
        dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

        ArrayList<String> ejes;//CREAMOS EL ARREGLO LLAMADO CARGO
        ejes = dataBaseAccess.getEjes();//GUARDAMOS DENTRO DEL ARREGLO LO QUE OBTIENE DEL METODO

        String[] ejes2 = new String[ejes.size()];//CREAMOS UN ARREGLO NORMAL CON EL TAMAÑO DEL ARREGLO LIST

        for(int i = 0; i < ejes.size(); i++){//BUCLE PARA IR GUARDANDO LOS DATOS DEL LIST DENTRO DEL ARREGLO NORMAL

            ejes2[i] = ejes.get(i);//GUARDAMOS LOS DATO DEL LIS EN EL ARRAY NORMAL
            System.out.println(ejes2[i]);//IMPRIMIMOS LOS DATOS EN LA CONSOLA

        }//FIN DEL BUCLE

        dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

        return ejes2;//RETORNAMOS EL ARREGLO YA CON LOS ROLES GUARDADOS

    }//FIN DE ESTE METODO AGREGARSPINNER

    public void Limpiar() {//metodo para limpiar los valores dentro de los objetos

        editText_descripcion.setText("");//limpia el objeto
        spinner_eje.setSelection(0);//limpia el objeto

    }

}
