package com.example.scannerqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class RegistrarProducto extends AppCompatActivity {

    private FirebaseAuth Auth; //se crea objeto para cerrar sewsion en Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_producto);

        Auth = FirebaseAuth.getInstance();
    }

    //METODO PARA MOSTRAIR Y OCULTAR LAS OPCIONES DEL MENU
    //A FUERZA DEBE LLAMARCE "onCreateOptionsMenu"
    public boolean onCreateOptionsMenu(Menu menu){
        //(ruta R, carpeta MENU, nombre del archivo xml, parametro que lo recive)
        getMenuInflater().inflate(R.menu.menuoverflow, menu);
        return true;
    }


    //METODO PARA ASIGNAR LAS FUNCIONES A LAS OPCIONES DEL MENU
    //A FUERZA DEBE LLAMARCE "onOptionItemSelected"     (se ponen los parametros "MenuItem" es un objeto) "item" puede ser cualquier nombre
    public boolean onOptionsItemSelected(MenuItem item) {
        //se crea una variable que diga cual opcion (item) se seleciono
        int id = item.getItemId();

        if (id == R.id.home) {
            startActivity(new Intent(RegistrarProducto.this, Bienvenido.class));
        } else {
            if (id == R.id.buscar) {
                startActivity(new Intent(RegistrarProducto.this, ModificarProducto.class));
            } else {
                if (id == R.id.registrar) {
                    startActivity(new Intent(RegistrarProducto.this, RegistrarProducto.class));
                } else {
                    if (id == R.id.salir) {
                        //ahora si para cerrar sesion se pone el objeto
                        Auth.signOut();

                        //Esto ahce que nos regrese a la pantalla de iniciar sesión
                        startActivity(new Intent(RegistrarProducto.this, MainActivity.class));
                        finish();
                    }

                }
            }//FIN if
        }
        return super.onOptionsItemSelected(item);
    }
}