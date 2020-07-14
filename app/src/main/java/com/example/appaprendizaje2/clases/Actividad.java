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
import com.example.appaprendizaje2.modelo.ObjActividad;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

import java.util.ArrayList;

public class Actividad extends AppCompatActivity {

    Button button_buscar, button_agregar, button_eliminar, button_modificar;//PARA LOS BOTONES
    EditText editText_descripcion, editText_nombre;//PARA LOS CUADROS DE TEXTO
    Spinner spinner_tema, spinner_habilidad, spinner_complejidad;//PARA LAS LISTAS DE OPCIONES
    String on = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);

        setTitle("Formulario Actividad");//titulo de la pantalla

        //CONEXION DE VARIBLES CREADAS CON LOS OBJETOS CREADOS EN EL XML
        button_buscar = (Button) findViewById(R.id.btn_buscaractividad);//TOMANDO CONTROL DEL OBJETO XML
        button_agregar = (Button) findViewById(R.id.btn_agregaractividad);//TOMANDO CONTROL DEL OBJETO XML
        button_modificar = (Button) findViewById(R.id.btn_modificaractividad);//TOMANDO CONTROL DEL OBJETO XML
        button_eliminar = (Button) findViewById(R.id.btn_eliminaractividad);//TOMANDO CONTROL DEL OBJETO XML
        editText_descripcion = (EditText) findViewById(R.id.et_descripcionactividad);//TOMANDO CONTROL DEL OBJETO XML
        editText_nombre = (EditText) findViewById(R.id.et_nombreactividad);//TOMANDO CONTROL DEL OBJETO XML
        spinner_tema = (Spinner) findViewById(R.id.sp_temaactividad);//TOMANDO CONTROL DEL OBJETO XML
        spinner_habilidad = (Spinner) findViewById(R.id.sp_habilidadactividad);//TOMANDO CONTROL DEL OBJETO XML
        spinner_complejidad = (Spinner) findViewById(R.id.sp_complejidadactividad);//TOMANDO CONTROL DEL OBJETO XML

        //GUARDAMOS DENTRO DEL SPINNER TODOS LOS DATOS QUE SE OBTIENE DEL ARREGLO
        spinner_tema.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AgregarTemaSpinner()));

        //GUARDAMOS DENTRO DEL SPINNER TODOS LOS DATOS QUE SE OBTIENE DEL ARREGLO
        spinner_habilidad.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AgregarHabilidadSpinner()));

        //GUARDAMOS DENTRO DEL SPINNER TODOS LOS DATOS QUE SE OBTIENE DEL ARREGLO
        spinner_complejidad.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AgregarComplejidadSpinner()));

        button_agregar.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                String des = editText_descripcion.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String nom = editText_nombre.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String tem = (String) spinner_tema.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String hab = (String) spinner_habilidad.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String com = (String) spinner_complejidad.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                int idtem = Integer.parseInt(dataBaseAccess.getId_Tema(tem));//GUARDAMOS EL IDE DEL METODO
                int idhab = Integer.parseInt(dataBaseAccess.getId_Habilidad(hab));//GUARDAMOS EL IDE DEL METODO
                int idcom = Integer.parseInt(dataBaseAccess.getId_Complejidad(com));//GUARDAMOS EL IDE DEL METODO

                String registro = dataBaseAccess.setInsertActividad(des, nom, idtem, idhab, idcom);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (des.isEmpty() || nom.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    if (des.isEmpty()) {

                        editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (nom.isEmpty()) {

                        editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (des.isEmpty() && nom.isEmpty()) {

                        editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

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

                ObjActividad ac = dataBaseAccess.SearchActividad(nom);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (nom.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                } else if (ac.getNombre() != null) {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES

                    editText_descripcion.setText(ac.getDescripcion());//capturo el registro dentro del campo de texto
                    on = ac.getNombre();//se guarda el valor para usarlo dentro del boton modificar
                    String descriptema = dataBaseAccess.getNombretema(ac.getTema());//obtener el string desde el id
                    String descriphabil = dataBaseAccess.getNombreHabilidad(ac.getHabilidad());//obtener el string desde el id
                    String descripcompl = dataBaseAccess.getNombreComplejidad(ac.getComplejidad());//obtener el string desde el id
                    //for para seleccionar la opcion que se guardo en la BD
                    for (int i = 0; i < AgregarTemaSpinner().length; i++){

                        if  (descriptema.equals(AgregarTemaSpinner()[i])) {//ingresa solo si son iguales
                            spinner_tema.setSelection(i);//y selecciona la pisicion con la que entro a la condicion
                        }
                    }

                    for (int i = 0; i < AgregarHabilidadSpinner().length; i++){

                        if  (descriphabil.equals(AgregarHabilidadSpinner()[i])) {//ingresa solo si son iguales
                            spinner_habilidad.setSelection(i);//y selecciona la pisicion con la que entro a la condicion
                        }
                    }

                    for (int i = 0; i < AgregarComplejidadSpinner().length; i++){

                        if  (descripcompl.equals(AgregarComplejidadSpinner()[i])) {//ingresa solo si son iguales
                            spinner_complejidad.setSelection(i);//y selecciona la pisicion con la que entro a la condicion
                        }
                    }

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Actividad Encontrada!!", Toast.LENGTH_LONG);
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                } else {

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Actividad NO encontrada!!", Toast.LENGTH_LONG);
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

                String des = editText_descripcion.getText().toString().trim();
                String nomm = on;//obtengo el valor a buscar y modificar
                String nomn = editText_nombre.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String teman = (String) spinner_tema.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String habin = (String) spinner_habilidad.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String compn = (String) spinner_complejidad.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                int idtema = Integer.parseInt(dataBaseAccess.getId_Tema(teman));//GUARDAMOS EL IDE DEL METODO
                int idhabi = Integer.parseInt(dataBaseAccess.getId_Habilidad(habin));//GUARDAMOS EL IDE DEL METODO
                int idcomp = Integer.parseInt(dataBaseAccess.getId_Complejidad(compn));//GUARDAMOS EL IDE DEL METODO

                String registro = dataBaseAccess.ModificarActividad(des, nomm, nomn, idtema, idhabi, idcomp);//GUARDAMOS EL MENSAJE DE INSERTADO O NO
                on = "";//reinicia el valor

                if (des.isEmpty() || nomn.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    if (des.isEmpty()) {

                        editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (nomn.isEmpty()) {

                        editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (des.isEmpty() && nomn.isEmpty()) {

                        editText_descripcion.setError("Descripcion no Ingresada!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        editText_nombre.setError("Nombre no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

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

                String eliminacion = dataBaseAccess.EliminarActividad(nom);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

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

    }

    public String[] AgregarHabilidadSpinner(){//METODO PARA IR AGREGANDO LOS DATOS DE LA COLUMNA DE LA BASE DE DATOS A UN ARREGLO

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
        // ESPECIFICOS DENTRO DE ESA CLASE
        dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

        ArrayList<String> habi;//CREAMOS EL ARREGLO LLAMADO CARGO
        habi = dataBaseAccess.getHabilidad();//GUARDAMOS DENTRO DEL ARREGLO LO QUE OBTIENE DEL METODO

        String[] habi2 = new String[habi.size()];//CREAMOS UN ARREGLO NORMAL CON EL TAMAÑO DEL ARREGLO LIST

        for(int i = 0; i < habi.size(); i++){//BUCLE PARA IR GUARDANDO LOS DATOS DEL LIST DENTRO DEL ARREGLO NORMAL

            habi2[i] = habi.get(i);//GUARDAMOS LOS DATO DEL LIS EN EL ARRAY NORMAL
            System.out.println(habi2[i]);//IMPRIMIMOS LOS DATOS EN LA CONSOLA

        }//FIN DEL BUCLE

        dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

        return habi2;//RETORNAMOS EL ARREGLO YA CON LOS ROLES GUARDADOS

    }//FIN DE ESTE METODO AGREGARSPINNER

    public String[] AgregarTemaSpinner(){//METODO PARA IR AGREGANDO LOS DATOS DE LA COLUMNA DE LA BASE DE DATOS A UN ARREGLO

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
        // ESPECIFICOS DENTRO DE ESA CLASE
        dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

        ArrayList<String> tem;//CREAMOS EL ARREGLO LLAMADO CARGO
        tem = dataBaseAccess.getTema();//GUARDAMOS DENTRO DEL ARREGLO LO QUE OBTIENE DEL METODO

        String[] tem2 = new String[tem.size()];//CREAMOS UN ARREGLO NORMAL CON EL TAMAÑO DEL ARREGLO LIST

        for(int i = 0; i < tem.size(); i++){//BUCLE PARA IR GUARDANDO LOS DATOS DEL LIST DENTRO DEL ARREGLO NORMAL

            tem2[i] = tem.get(i);//GUARDAMOS LOS DATO DEL LIS EN EL ARRAY NORMAL
            System.out.println(tem2[i]);//IMPRIMIMOS LOS DATOS EN LA CONSOLA

        }//FIN DEL BUCLE

        dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

        return tem2;//RETORNAMOS EL ARREGLO YA CON LOS ROLES GUARDADOS

    }//FIN DE ESTE METODO AGREGARSPINNER

    public String[] AgregarComplejidadSpinner(){//METODO PARA IR AGREGANDO LOS DATOS DE LA COLUMNA DE LA BASE DE DATOS A UN ARREGLO

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
        // ESPECIFICOS DENTRO DE ESA CLASE
        dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

        ArrayList<String> com;//CREAMOS EL ARREGLO LLAMADO CARGO
        com = dataBaseAccess.getComplejidad();//GUARDAMOS DENTRO DEL ARREGLO LO QUE OBTIENE DEL METODO

        String[] com2 = new String[com.size()];//CREAMOS UN ARREGLO NORMAL CON EL TAMAÑO DEL ARREGLO LIST

        for(int i = 0; i < com.size(); i++){//BUCLE PARA IR GUARDANDO LOS DATOS DEL LIST DENTRO DEL ARREGLO NORMAL

            com2[i] = com.get(i);//GUARDAMOS LOS DATO DEL LIS EN EL ARRAY NORMAL
            System.out.println(com2[i]);//IMPRIMIMOS LOS DATOS EN LA CONSOLA

        }//FIN DEL BUCLE

        dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

        return com2;//RETORNAMOS EL ARREGLO YA CON LOS ROLES GUARDADOS

    }//FIN DE ESTE METODO AGREGARSPINNER

    public void Limpiar() {//metodo para limpiar los valores dentro de los objetos

        editText_descripcion.setText("");//limpia el objeto
        editText_nombre.setText("");//limpia el objeto
        spinner_tema.setSelection(0);//limpia el objeto
        spinner_habilidad.setSelection(0);//limpia el objeto
        spinner_complejidad.setSelection(0);//limpia el objeto

    }

}
