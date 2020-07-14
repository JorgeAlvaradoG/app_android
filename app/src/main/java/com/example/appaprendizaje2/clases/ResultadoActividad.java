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
import com.example.appaprendizaje2.modelo.ObjResultadoActividad;
import com.example.appaprendizaje2.sqlite.DataBaseAccess;

import java.util.ArrayList;

public class ResultadoActividad extends AppCompatActivity {

    Button button_buscar, button_agregar, button_eliminar, button_modificar;//PARA LOS BOTONES
    EditText editText_id, editText_clasificacion, editText_nivelcomplejidad, editText_numintentos,
            editText_numpreguntas, editText_acertividad;//PARA LOS CUADROS DE TEXTO
    Spinner spinner_actividad, spinner_usuario, spinner_complejidad;//PARA LAS LISTAS DE OPCIONES
    int id = 0, clasificacion = 0, bid = 0, on = 0, ide = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_actividad);

        setTitle("Formulario Resultado de Actividades");//titulo de la pantalla

        //CONEXION DE VARIBLES CREADAS CON LOS OBJETOS CREADOS EN EL XML
        button_buscar = (Button) findViewById(R.id.btn_buscaridresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        button_agregar = (Button) findViewById(R.id.btn_agregarresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        button_modificar = (Button) findViewById(R.id.btn_modificarresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        button_eliminar = (Button) findViewById(R.id.btn_eliminarresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        editText_id = (EditText) findViewById(R.id.et_idresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        spinner_actividad = (Spinner) findViewById(R.id.sp_actividadresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        spinner_usuario = (Spinner) findViewById(R.id.sp_usuarioresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        editText_clasificacion = (EditText) findViewById(R.id.et_clasificacionresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        editText_nivelcomplejidad = (EditText) findViewById(R.id.et_nivelresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        editText_numintentos = (EditText) findViewById(R.id.et_intentosresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        editText_numpreguntas = (EditText) findViewById(R.id.et_preguntasresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        editText_acertividad = (EditText) findViewById(R.id.et_acertividadresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML
        spinner_complejidad = (Spinner) findViewById(R.id.sp_complejidadresultadoactividad);//TOMANDO CONTROL DEL OBJETO XML

        //GUARDAMOS DENTRO DEL SPINNER TODOS LOS DATOS QUE SE OBTIENE DEL ARREGLO
        spinner_actividad.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AgregarActividadSpinner()));

        //GUARDAMOS DENTRO DEL SPINNER TODOS LOS DATOS QUE SE OBTIENE DEL ARREGLO
        spinner_usuario.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AgregarUsuarioSpinner()));

        //GUARDAMOS DENTRO DEL SPINNER TODOS LOS DATOS QUE SE OBTIENE DEL ARREGLO
        spinner_complejidad.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AgregarComplejidadSpinner()));

        button_agregar.setOnClickListener(new View.OnClickListener() {//INICIA EL METODO ACCION DENTRO DEL BOTON INDICADO

            @Override//SOBREESCRITURA
            public void onClick(View v) {

                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
                // ESPECIFICOS DENTRO DE ESA CLASE
                dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

                try {//para el error de formato

                    id = Integer.parseInt(editText_id.getText().toString().trim());//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                } catch (NumberFormatException e) {//en caso de marcar el error

                    editText_id.setError("ID no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                }
                String act = (String) spinner_actividad.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String usu = (String) spinner_usuario.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                try {//para el error de formato

                    clasificacion = Integer.parseInt(editText_clasificacion.getText().toString().trim());//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                } catch (NumberFormatException e) {//en caso de marcar el error

                    editText_clasificacion.setError("N° Clasificacion no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                }

                String nivel = editText_nivelcomplejidad.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String numint = editText_numintentos.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String numpre = editText_numpreguntas.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String acert = editText_acertividad.getText().toString().trim();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String comple = (String) spinner_complejidad.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                int idact = Integer.parseInt(dataBaseAccess.getId_Actividad(act));//GUARDAMOS EL IDE DEL METODO
                String idusu = dataBaseAccess.getId_Usuario(usu);//GUARDAMOS EL IDE DEL METODO
                int idcom = Integer.parseInt(dataBaseAccess.getId_Complejidad(comple));//GUARDAMOS EL IDE DEL METODO

                String registro = dataBaseAccess.setInsertResultadoActividad(id, idact, idusu, clasificacion, nivel, numint, numpre, acert, idcom);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (nivel.isEmpty() || numint.isEmpty() || numpre.isEmpty() || acert.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    if (nivel.isEmpty()) {

                        editText_nivelcomplejidad.setError("Nivel de Complejidad no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (numint.isEmpty()) {

                        editText_numintentos.setError("N° Intentos no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (numpre.isEmpty()) {

                        editText_numpreguntas.setError("N° Preguntas no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (acert.isEmpty()) {

                        editText_acertividad.setError("% Acertividad no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (nivel.isEmpty() && numint.isEmpty() && numpre.isEmpty() && acert.isEmpty()) {

                        editText_nivelcomplejidad.setError("Nivel de Complejidad no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        editText_numintentos.setError("N° Intentos no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        editText_numpreguntas.setError("N° Preguntas no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        editText_acertividad.setError("% Acertividad no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

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

                try {//para el error de formato

                    bid = Integer.parseInt(editText_id.getText().toString().trim());//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                } catch (NumberFormatException e) {//en caso de marcar el error

                    editText_id.setError("ID no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                }
                ObjResultadoActividad ra = dataBaseAccess.SearchResultadoActividad(bid);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                if (ra.getId() != 0) {//SI NO SE CUMPLE NINGUNAS DE LAS ANTERIORES CONDICIONES
                    System.out.println("el id: "+ra.getId());
                    editText_id.setText(ra.getId() +"");//capturo el registro dentro del campo de texto
                    on = ra.getId();//se guarda el valor para usarlo dentro del boton modificar
                    String nombractividad = dataBaseAccess.getNombreActividad(ra.getActividad());//obtener el string desde el id
                    String nombreusuario = dataBaseAccess.getNombreUsuario(ra.getUsuario());//obtener el string desde el id
                    String descripcompl = dataBaseAccess.getNombreComplejidad(ra.getComplejidad());//obtener el string desde el id
                    //for para seleccionar la opcion que se guardo en la BD
                    for (int i = 0; i < AgregarActividadSpinner().length; i++){

                        if  (nombractividad.equals(AgregarActividadSpinner()[i])) {//ingresa solo si son iguales
                            spinner_actividad.setSelection(i);//y selecciona la pisicion con la que entro a la condicion
                        }
                    }

                    for (int i = 0; i < AgregarUsuarioSpinner().length; i++){

                        if  (nombreusuario.equals(AgregarUsuarioSpinner()[i])) {//ingresa solo si son iguales
                            spinner_usuario.setSelection(i);//y selecciona la pisicion con la que entro a la condicion
                        }
                    }

                    editText_clasificacion.setText(ra.getClasificacion()+"");//capturo el registro dentro del campo de texto
                    editText_nivelcomplejidad.setText(ra.getNivel());//capturo el registro dentro del campo de texto
                    editText_numintentos.setText(ra.getIntentos()+"");//capturo el registro dentro del campo de texto
                    editText_numpreguntas.setText(ra.getPreguntas()+"");//capturo el registro dentro del campo de texto
                    editText_acertividad.setText(ra.getAcertividad());//capturo el registro dentro del campo de texto

                    for (int i = 0; i < AgregarComplejidadSpinner().length; i++){

                        if  (descripcompl.equals(AgregarComplejidadSpinner()[i])) {//ingresa solo si son iguales
                            spinner_complejidad.setSelection(i);//y selecciona la pisicion con la que entro a la condicion
                        }
                    }

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Resultado de Actividad Encontrada!!", Toast.LENGTH_LONG);
                    toast.show();//MOSTRAMOS LA NOTIFICACION

                } else {

                    //MANDA MENSAJE EXITOSO
                    Toast toast = Toast.makeText(getApplicationContext(), "Resultado de Actividad NO encontrada!!", Toast.LENGTH_LONG);
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

                int idm = on;//obtengo el valor a buscar y modificar
                int idn = Integer.parseInt(editText_id.getText().toString());//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String actin = (String) spinner_actividad.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String usuan = (String) spinner_usuario.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                int clas = Integer.parseInt(editText_clasificacion.getText().toString());//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String nivel = editText_nivelcomplejidad.getText().toString();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String intentos = editText_numintentos.getText().toString();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String preguntas = editText_numpreguntas.getText().toString();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String acertividad = editText_acertividad.getText().toString();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO
                String compn = (String) spinner_complejidad.getSelectedItem();//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                int idacti = Integer.parseInt(dataBaseAccess.getId_Actividad(actin));//GUARDAMOS EL IDE DEL METODO
                String idusu = dataBaseAccess.getId_Usuario(usuan);//GUARDAMOS EL IDE DEL METODO
                int idcomp = Integer.parseInt(dataBaseAccess.getId_Complejidad(compn));//GUARDAMOS EL IDE DEL METODO

                String registro = dataBaseAccess.ModificarResultadoActividad(idm, idn, idacti, idusu, clas, nivel, intentos, preguntas, acertividad, idcomp);//GUARDAMOS EL MENSAJE DE INSERTADO O NO
                on = 0;//reinicia el valor

                if (nivel.isEmpty() || intentos.isEmpty() || preguntas.isEmpty() || acertividad.isEmpty()) {//SI NO TIENE  NADA INGRESADO

                    if (nivel.isEmpty()) {

                        editText_nivelcomplejidad.setError("Nivel de Complejidad no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (intentos.isEmpty()) {

                        editText_numintentos.setError("N° Intentos no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (preguntas.isEmpty()) {

                        editText_numpreguntas.setError("N° Preguntas no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (acertividad.isEmpty()) {

                        editText_acertividad.setError("% Acertividad no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                    }
                    if (nivel.isEmpty() && intentos.isEmpty() && preguntas.isEmpty() && acertividad.isEmpty()) {

                        editText_nivelcomplejidad.setError("Nivel de Complejidad no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        editText_numintentos.setError("N° Intentos no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        editText_numpreguntas.setError("N° Preguntas no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO
                        editText_acertividad.setError("% Acertividad no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

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

                try {//para el error de formato

                    ide = Integer.parseInt(editText_id.getText().toString().trim());//GUARDAMOS LO QUE INGRESE EL USUARIO DENTRO DEL CAMPO DE TEXTO INDICADO

                } catch (NumberFormatException e) {//en caso de marcar el error

                    editText_id.setError("ID no Ingresado!");//MANDA MENSAJE ERROR SOLO A ESTE CAMPO

                }
                String eliminacion = dataBaseAccess.EliminarResultadoActividad(ide);//GUARDAMOS EL MENSAJE DE INSERTADO O NO

                //MANDA MENSAJE EXITOSO
                Toast toast = Toast.makeText(getApplicationContext(), eliminacion, Toast.LENGTH_LONG);Limpiar();//limpia los objetos
                toast.show();//MOSTRAMOS LA NOTIFICACION

                dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

            }

        });

    }

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

    public String[] AgregarUsuarioSpinner(){//METODO PARA IR AGREGANDO LOS DATOS DE LA COLUMNA DE LA BASE DE DATOS A UN ARREGLO

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());//PARA ACCEDER A LOS RECURSOS
        // ESPECIFICOS DENTRO DE ESA CLASE
        dataBaseAccess.open();//LLAMAMOS AL METODO OPEN PARA ABRIR LA BASE DE DATOS SQLITE

        ArrayList<String> usu;//CREAMOS EL ARREGLO LLAMADO CARGO
        usu = dataBaseAccess.getUsuario();//GUARDAMOS DENTRO DEL ARREGLO LO QUE OBTIENE DEL METODO

        String[] usu2 = new String[usu.size()];//CREAMOS UN ARREGLO NORMAL CON EL TAMAÑO DEL ARREGLO LIST

        for(int i = 0; i < usu.size(); i++){//BUCLE PARA IR GUARDANDO LOS DATOS DEL LIST DENTRO DEL ARREGLO NORMAL

            usu2[i] = usu.get(i);//GUARDAMOS LOS DATO DEL LIS EN EL ARRAY NORMAL
            System.out.println(usu2[i]);//IMPRIMIMOS LOS DATOS EN LA CONSOLA

        }//FIN DEL BUCLE

        dataBaseAccess.close();//CERRAMOS LA BASE DE DATOS

        return usu2;//RETORNAMOS EL ARREGLO YA CON LOS ROLES GUARDADOS

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

        editText_id.setText("");//limpia el objeto
        spinner_actividad.setSelection(0);//limpia el objeto
        spinner_usuario.setSelection(0);//limpia el objeto
        editText_clasificacion.setText("");//limpia el objeto
        editText_nivelcomplejidad.setText("");//limpia el objeto
        editText_numintentos.setText("");//limpia el objeto
        editText_numpreguntas.setText("");//limpia el objeto
        editText_acertividad.setText("");//limpia el objeto
        spinner_complejidad.setSelection(0);//limpia el objeto

    }

}
