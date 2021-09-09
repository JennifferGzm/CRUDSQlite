package com.itca.crud_sqlite;

import android.content.ContentValues;
import android.content.Entity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.itca.crud_sqlite.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(  this,  "administracion.db",  null, 1);
    //final SQLiteDatabase db = admin.getwritableDatabase();

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    String codigo, descrpcion, presio;


    private EditText et_codigo, et_demostracion, et_presio;
    private Button btnAlta, btnconsulta1, btnconsulta2, btneliminar, btnactualizar, btnnuevo, btnsalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        /*NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);*/

        et_codigo = findViewById(R.id.et_codigo);
        et_demostracion = findViewById(R.id.et_demostracion);
        et_presio = findViewById(R.id.et_presio);
        btnAlta = findViewById(R.id.btnAlta);
        btnconsulta1 = findViewById(R.id.btnconsulta1);
        btnconsulta2 = findViewById(R.id.btnconsulta2);
        btneliminar = findViewById(R.id.btneliminar);
        btnactualizar = findViewById(R.id.btnactualizar);
        btnnuevo = findViewById(R.id.btnnuevo);
        btnsalir = findViewById(R.id.btnsalir);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        SQLiteDatabase bd = admin.getWritableDatabase();
        switch (view.getId()) {


            case R.id.btnAlta:
                Toast.makeText(this, "Has hecho clic en el boton Alta", Toast.LENGTH_SHORT).show();
                String codigo = et_codigo.getText().toString();
                String descripcion = et_demostracion.getText().toString();
                String presio = et_presio.getText().toString();
                ContentValues registro =new ContentValues();
                registro.put ("codigo", codigo);
                registro.put("descripcion", descripcion);
                registro.put("presio", presio);

                if(codigo.isEmpty()){
                    et_codigo.setError("Campo obligatorio");
                }
                else if (descripcion.isEmpty()){
                    et_demostracion.setError("Campo obligatorio");
                }
                else if (presio.isEmpty()){
                    et_presio.setError("Campo obligatorio");
                }
                else {
                    bd.insert("articulos", null, registro);
                    bd.close();
                    et_codigo.setText(null);
                    et_demostracion.setText(null);
                    et_presio.setText(null);
                    Toast.makeText(this, "Registro guardado correctamente", Toast.LENGTH_SHORT).show();
                }
                break;

            /*case R.id.btnconsulta1:
                //Toast.makeText(this, "Has hecho clic en el boton consulta 1", Toast.LENGTH_SHORT).show();
                codigo = et_codigo.getText().toString();
                if(codigo.isEmpty()){
                    et_codigo.setError("Campo obligatorio.");
                }else{
                    Cursor file = bd.rawQuery("select descripcion, presio from articulos where codigo="+codigo, null);
                    if (file.moveToFirst()){
                        et_demostracion.setText(file.getString(0));
                        et_presio.setText(file.getString(1));
                    }else {
                        Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT).show();
                    }
                }
                break;


         /*   case R.id.btnconsulta2:
                Toast.makeText(this, "Has hecho clic en el boton consulta 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btneliminar:
                Toast.makeText(this, "Has hecho clic en el boton eliminar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnactualizar:
                Toast.makeText(this, "Has hecho clic en el boton actualizar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnnuevo:
                Toast.makeText(this, "Has hecho clic en el boton nuevo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnsalir:
                Toast.makeText(this, "Has hecho clic en el boton salir", Toast.LENGTH_SHORT).show();
                break;*/
            default:
                //break;
        }
    }

    /*@Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
}