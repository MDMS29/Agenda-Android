package com.example.agenda;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.agenda.db.DbHelper;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.puntos);
        toolbar.setOnMenuItemClickListener(item-> {
            if(item.getItemId() == R.id.nuevoMen){
                startActivity(new Intent(MainActivity.this, NuevoActivity.class));
                return true;
            }else{
                return super.onOptionsItemSelected(item);
            }
        });

        DbHelper dbHelper = new DbHelper(MainActivity.this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            Toast.makeText(MainActivity.this, "Base creada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toolbar toolbar = findViewById(R.id.puntos);
        return false;
    }
}