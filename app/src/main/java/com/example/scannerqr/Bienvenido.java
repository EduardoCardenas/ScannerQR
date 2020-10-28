package com.example.scannerqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Bienvenido extends AppCompatActivity {


    private FirebaseAuth Auth; //se crea objeto para cerrar sewsion en Firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        Auth = FirebaseAuth.getInstance();
    }


    //METODO PARA EL BOTON InformacionProducto (que me mande a la pantalla registro)
    //El metodo se llama IrREGISTRO
    public void IrInformacion(View view){
        //se crea objeto Intent para hacer que los botones funciones
        //los parametros se pone (la activity de donde se parte "this", a la que se quiere ir "Registro.class")
        Intent boton_registro = new Intent(this, InformacionProducto.class);
        //vamos a indicar el mMETODO starActivity, en los parametros se pone el nombre del OBJETO intent "boton_registro"
        startActivity(boton_registro);

        //NO olvidar ir al XML y Activar este metodo en el boton (en atributos OnClik
    }//FIN DE METODO


    //METODO PARA EL BOTON REGISTRO (que me mande a la pantalla registro)
    //El metodo se llama IrREGISTRO
    public void IrModificar(View view){
        //se crea objeto Intent para hacer que los botones funciones
        //los parametros se pone (la activity de donde se parte "this", a la que se quiere ir "Registro.class")
        Intent boton_registro = new Intent(this, ModificarProducto.class);
        //vamos a indicar el mMETODO starActivity, en los parametros se pone el nombre del OBJETO intent "boton_registro"
        startActivity(boton_registro);

        //NO olvidar ir al XML y Activar este metodo en el boton (en atributos OnClik
    }//FIN DE METODO


    //METODO PARA EL BOTON REGISTRO (que me mande a la pantalla registro)
    //El metodo se llama IrREGISTRO
    public void IrRegistrar(View view){
        //se crea objeto Intent para hacer que los botones funciones
        //los parametros se pone (la activity de donde se parte "this", a la que se quiere ir "Registro.class")
        Intent boton_registro = new Intent(this, RegistrarProducto.class);
        //vamos a indicar el mMETODO starActivity, en los parametros se pone el nombre del OBJETO intent "boton_registro"
        startActivity(boton_registro);

        //NO olvidar ir al XML y Activar este metodo en el boton (en atributos OnClik
    }//FIN DE METODO


    //METODO PARA EL BOTON REGISTRO (que me mande a la pantalla registro)
    //El metodo se llama IrREGISTRO
    public void IrInicioSesion(View view){
        //se crea objeto Intent para hacer que los botones funciones
        //los parametros se pone (la activity de donde se parte "this", a la que se quiere ir "Registro.class")
        Intent boton_registro = new Intent(this, MainActivity.class);
        //vamos a indicar el mMETODO starActivity, en los parametros se pone el nombre del OBJETO intent "boton_registro"
        startActivity(boton_registro);

        //NO olvidar ir al XML y Activar este metodo en el boton (en atributos OnClik
    }//FIN DE METODO

    //METODO PARA MOSTRAIR Y OCULTAR LAS OPCIONES DEL MENU
    //A FUERZA DEBE LLAMARCE "onCreateOptionsMenu"
    public boolean onCreateOptionsMenu(Menu menu){
        //(ruta R, carpeta MENU, nombre del archivo xml, parametro que lo recive)
        getMenuInflater().inflate(R.menu.menuoverflow, menu);
        return true;
    }


    //METODO PARA ASIGNAR LAS FUNCIONES A LAS OPCIONES DEL MENU
    //A FUERZA DEBE LLAMARCE "onOptionItemSelected"     (se ponen los parametros "MenuItem" es un objeto) "item" puede ser cualquier nombre
    public boolean onOptionsItemSelected(MenuItem item){
        //se crea una variable que diga cual opcion (item) se seleciono
        int id = item.getItemId();

        if(id==R.id.home){
            startActivity(new Intent(Bienvenido.this, Bienvenido.class));
        }else{
            if(id==R.id.buscar){
                startActivity(new Intent(Bienvenido.this, ModificarProducto.class));
            }else {
                if (id == R.id.registrar) {
                    startActivity(new Intent(Bienvenido.this, RegistrarProducto.class));
                } else {
                    if (id == R.id.salir) {
                        //ahora si para cerrar sesion se pone el objeto
                        Auth.signOut();

                        //Esto ahce que nos regrese a la pantalla de iniciar sesión
                        startActivity(new Intent(Bienvenido.this, MainActivity.class));
                        finish();
                    }

                }
            }
        }//FIN if
        return super.onOptionsItemSelected(item);
    }

}