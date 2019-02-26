package com.example.listaproductos.Activities;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listaproductos.Controllers.ProductViewModel;
import com.example.listaproductos.Controllers.Repository;
import com.example.listaproductos.Model.Producto;
import com.example.listaproductos.R;

public class UpdateProducts extends AppCompatActivity {

    private Producto mProducto;
    private Application mApplication;
    private int mIndexProducto;
    private EditText etName, etCode, etStock, etPrice,etDate;
    private ProductViewModel mProductViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);
        mApplication = this.getApplication();
        setTitle("Modificar Informacion");
        mProducto = getIntent().getParcelableExtra("product");
        mIndexProducto = getIntent().getIntExtra("index", 0);

        etName = findViewById(R.id.update_etName);
        etCode = findViewById(R.id.update_etCode);
        etStock = findViewById(R.id.update_etStock);
        etPrice = findViewById(R.id.update_etPrice);
        etDate = findViewById(R.id.update_Date);

        etName.setText(mProducto.getPro_Nombre());
        etCode.setText(mProducto.getPro_CodigoBarras());
        etStock.setText(String.valueOf(mProducto.getPro_NumStock()));
        etPrice.setText(String.valueOf(mProducto.getPro_precio()));
        etDate.setText(mProducto.getPro_Fecha());


        mProductViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        updateProduct();
    }


    private void updateProduct(){
        final Button btnUpdate = findViewById(R.id.update_btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                mProducto.setPro_Nombre(etName.getText().toString());
                mProducto.setPro_CodigoBarras(etCode.getText().toString());
                mProducto.setPro_NumStock(Integer.valueOf(etStock.getText().toString()));
                mProducto.setPro_precio(Double.valueOf(etPrice.getText().toString()));
                mProducto.setPro_Fecha(etDate.getText().toString());
                mProductViewModel.update(mProducto);
                Toast.makeText(mApplication.getApplicationContext(), "Modificacion Exitosa", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
