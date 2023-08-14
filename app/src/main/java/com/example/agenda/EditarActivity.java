package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.db.DbContactos;
import com.example.agenda.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {
    EditText etNombre, etTelefono, etCorreo;
    Button btnGuardar;
    FloatingActionButton fabEditar;
    Contactos contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etCorreo = findViewById(R.id.etCorreo);
        btnGuardar = findViewById(R.id.btnGuardar);
        fabEditar = findViewById(R.id.fabEditar);

        fabEditar.setVisibility(View.INVISIBLE);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbContactos dbContactos = new DbContactos(EditarActivity.this);
        contacto = dbContactos.verContato(id);

        if (contacto != null) {
            etNombre.setText(contacto.getNombre());
            etTelefono.setText(contacto.getTelefono());
            etCorreo.setText(contacto.getCorreo());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            boolean correcto;
            @Override
            public void onClick(View view) {
                if (!etNombre.getText().toString().equals("") && !etTelefono.getText().toString().equals("") && !etCorreo.getText().toString().equals("")) {
                    correcto = dbContactos.editarContacto(id, etNombre.getText().toString(), etTelefono.getText().toString(), etCorreo.getText().toString());
                    if (correcto) {
                        Toast.makeText(EditarActivity.this, "¡Registro actualizado!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditarActivity.this, VerActivity.class));
                    } else {
                        Toast.makeText(EditarActivity.this, "¡Ha ocurrido un error!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "¡Debe llenar todos los campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
