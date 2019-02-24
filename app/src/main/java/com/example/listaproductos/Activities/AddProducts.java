package com.example.listaproductos.Activities;

import android.app.Application;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listaproductos.Controllers.Repository;
import com.example.listaproductos.R;

public class AddProducts extends AppCompatActivity {

    private Application mApplication;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_productos);
        mApplication = this.getApplication();
        mProgressBar = findViewById(R.id.agregar_progress);
        addProduct();
        backToHome();
    }




    public void addProduct(){

        final Button btnAgregar = findViewById(R.id.agregar_btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnAgregar.setActivated(false);
                final Button btnCancelar = findViewById(R.id.agregar_btnCancelar);
                btnCancelar.setActivated(false);
                new AgregarAsyncTask().execute();
            }
        });
    }

    public void backToHome(){
        Button btnCancelar = findViewById(R.id.agregar_btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }


    private class AgregarAsyncTask extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected void onPreExecute(){
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            Repository repository = new Repository(mApplication);
            EditText etName = findViewById(R.id.agregar_etNombre);
            EditText etCode = findViewById(R.id.agregar_etCodigoBarras);
            EditText etDate = findViewById(R.id.agregar_etFechaCaducidad);
            EditText etStock = findViewById(R.id.agregar_etNumStock);
            EditText etPrice = findViewById(R.id.agregar_etPrecio);

            return repository.insertProduct(
                    etName.getText().toString(),
                    etCode.getText().toString(),
                    etDate.getText().toString(),
                    Integer.valueOf(etStock.getText().toString()),
                    Double.valueOf(etPrice.getText().toString())
            );

        }

        @Override
        protected void onPostExecute(Boolean result){

            mProgressBar.setVisibility(View.INVISIBLE);
            String mensaje = "Producto agregado";

            if(!result){
                mensaje = "No se pudo agregar el producto";
            }
            final Button btnAgregar = findViewById(R.id.agregar_btnAgregar);
            final Button btnCancelar = findViewById(R.id.agregar_btnCancelar);
            btnAgregar.setActivated(true);
            btnCancelar.setActivated(true);
            Toast.makeText(mApplication.getApplicationContext(),mensaje, Toast.LENGTH_SHORT).show();
        }
    }

}
