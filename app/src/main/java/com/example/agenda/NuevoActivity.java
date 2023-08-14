package com.example.agenda;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    EditText etNombre, etTelefono, etCorreo;
    Button btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        btnGuardar = findViewById(R.id.btnGuardar);
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);

        btnGuardar.setOnClickListener(v -> {
            DbContactos dbContactos = new DbContactos(NuevoActivity.this);
            long id = dbContactos.insertarContacto(etNombre.getText().toString(), etTelefono.getText().toString(), etCorreo.getText().toString());
            if(id>0){
                Toast.makeText(NuevoActivity.this, "¡Usuario creado con exíto!", Toast.LENGTH_SHORT).show();
                limpiar();
            }else{
                Toast.makeText(NuevoActivity.this, "¡Error al crear usuario!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void limpiar(){
        etNombre.setText("");
        etTelefono.setText("");
        etCorreo.setText("");
    }
}