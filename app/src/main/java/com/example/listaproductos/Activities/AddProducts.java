package com.example.listaproductos.Activities;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listaproductos.Controllers.ProductViewModel;
import com.example.listaproductos.Controllers.Repository;
import com.example.listaproductos.Model.Producto;
import com.example.listaproductos.R;

public class AddProducts extends AppCompatActivity {

    private Application mApplication;
    private ProgressBar mProgressBar;
    private EditText etName, etCode, etDate, etStock, etPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Agregar Producto");
        setContentView(R.layout.activity_add_productos);

        etName = findViewById(R.id.agregar_etNombre);
        etCode = findViewById(R.id.agregar_etCodigoBarras);
        etDate = findViewById(R.id.agregar_etFechaCaducidad);
        etStock = findViewById(R.id.agregar_etNumStock);
        etPrice = findViewById(R.id.agregar_etPrecio);

        mApplication = this.getApplication();
        mProgressBar = findViewById(R.id.agregar_progress);
        addProduct();

    }




    public void addProduct(){

        final Button btnAgregar = findViewById(R.id.agregar_btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Producto producto = new Producto();
                producto.setPro_Nombre(etName.getText().toString());
                producto.setPro_CodigoBarras(etCode.getText().toString());
                producto.setPro_NumStock(Integer.valueOf(etStock.getText().toString()));
                producto.setPro_precio(Double.valueOf(etPrice.getText().toString()));
                producto.setPro_Fecha(etDate.getText().toString());

                Intent resultIntent = new Intent();
                resultIntent.putExtra("EXTRA_REPLY", producto);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }



   /* private class AgregarAsyncTask extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected void onPreExecute(){
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result){

            mProgressBar.setVisibility(View.INVISIBLE);

            String mensaje = "Producto agregado";
            int resultado = RESULT_OK;

            if(!result){
                mensaje = "No se pudo agregar el producto";
                resultado = RESULT_FIRST_USER;
            }
            final Button btnAgregar = findViewById(R.id.agregar_btnAgregar);
            btnAgregar.setActivated(true);

            //Asignar la respuesta para la activitie padre
            Intent resultIntent = new Intent();
            //resultIntent.putExtra("result",respuestaIntent);
            setResult(resultado, resultIntent);

            Toast.makeText(mApplication.getApplicationContext(),mensaje, Toast.LENGTH_SHORT).show();
        }
    }*/

}
