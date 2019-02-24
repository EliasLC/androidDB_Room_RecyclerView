package com.example.listaproductos.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listaproductos.Model.Producto;
import com.example.listaproductos.R;

public class UpdateProducts extends AppCompatActivity {

    private Producto mProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);

        mProducto = getIntent().getParcelableExtra("product");

        EditText etName = findViewById(R.id.update_etName);
        EditText etCode = findViewById(R.id.update_etCode);
        EditText etStock = findViewById(R.id.update_etStock);
        EditText etPrice = findViewById(R.id.update_etPrice);
        EditText etDate = findViewById(R.id.update_Date);

        etName.setText(mProducto.getPro_Nombre());
        etCode.setText(mProducto.getPro_CodigoBarras());
        etStock.setText(String.valueOf(mProducto.getPro_NumStock()));
        etPrice.setText(String.valueOf(mProducto.getPro_precio()));
        etDate.setText(mProducto.getPro_Fecha());

    }
}
