package com.example.listaproductos.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.listaproductos.Model.Producto;
import com.example.listaproductos.R;

public class UpdateProducts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);

        Producto producto = getIntent().getParcelableExtra("product");

        Toast.makeText(this,producto.getPro_Nombre(),Toast.LENGTH_SHORT).show();

    }
}
