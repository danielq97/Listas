package com.example.moviles.listas;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.net.Uri;
import android.content.pm.PackageManager;
public class MainActivity extends AppCompatActivity {


    private EditText et_nombre;
    private EditText et_codigo;
    private Button btn_agregar;
    private ListView lista_estudiantes;
  private Adaptador adaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},11);

        et_nombre = findViewById(R.id.et_nombre);
        et_codigo = findViewById(R.id.et_codigo);
        btn_agregar = findViewById(R.id.btn_agregar);
        lista_estudiantes = findViewById(R.id.lista_estudiantes);
        adaptador = new Adaptador(this);
        lista_estudiantes.setAdapter(adaptador);

        adaptador.agregarEstudiante(new Estudiante("Cristian","3182390323"));


        lista_estudiantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               Estudiante es = (Estudiante) adaptador.getItem(position);
                Toast.makeText(MainActivity.this, es.getNombre(),Toast.LENGTH_SHORT).show();
            }
        });

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = et_nombre.getText().toString();
                String codigo = et_codigo.getText().toString();
                Estudiante new_estudiante = new Estudiante(nombre,codigo);
                adaptador.agregarEstudiante(new_estudiante);
                et_nombre.setText("");
                et_codigo.setText("");
            }
        });

        lista_estudiantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               Estudiante listItem = (Estudiante) lista_estudiantes.getItemAtPosition(position);

               String telefono = listItem.getCodigo();

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + telefono));

                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
