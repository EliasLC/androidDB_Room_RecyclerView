package com.example.listaproductos.Activities;

import android.app.Application;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listaproductos.Controllers.Repository;
import com.example.listaproductos.Model.Producto;
import com.example.listaproductos.R;

public class UpdateProducts extends AppCompatActivity {

    private Producto mProducto;
    private Application mApplication;
    private int mIndexProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);
        mApplication = this.getApplication();

        mProducto = getIntent().getParcelableExtra("product");
        mIndexProducto = getIntent().getIntExtra("index", 0);

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

        updateProduct();
        backToHome();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Toast.makeText(this,"Cerrado", Toast.LENGTH_SHORT).show();
    }


    private void updateProduct(){
        final Button btnUpdate = findViewById(R.id.update_btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnUpdate.setActivated(false);
                Button btnCerrar = findViewById(R.id.update_btnCancel);
                btnCerrar.setActivated(false);
                new UpdateAsync().execute();
            }
        });
    }


    private void backToHome(){
        Button btnCerrar = findViewById(R.id.update_btnCancel);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class UpdateAsync extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Repository repository = new Repository(mApplication);

            EditText etName = findViewById(R.id.update_etName);
            EditText etCode = findViewById(R.id.update_etCode);
            EditText etStock = findViewById(R.id.update_etStock);
            EditText etPrice = findViewById(R.id.update_etPrice);
            EditText etDate = findViewById(R.id.update_Date);

            mProducto.setPro_Nombre(etName.getText().toString());
            mProducto.setPro_CodigoBarras(etCode.getText().toString());
            mProducto.setPro_NumStock(Integer.valueOf(etStock.getText().toString()));
            mProducto.setPro_precio(Double.valueOf(etPrice.getText().toString()));
            mProducto.setPro_Fecha(etDate.getText().toString());

            return repository.updateProduct(mProducto);
        }

        @Override
        protected void onPostExecute(Boolean result){
            String text = "No se logro la modificacion :C" ;
            if(result){
                text = "Modificacion exitosa";
                RecyclerAdapter.getUpdateAdapter().setUpdateStatus(1);
                RecyclerAdapter.getUpdateAdapter().setUpdateItemIndex(mIndexProducto);
                RecyclerAdapter.getUpdateAdapter().setUpdateItemId(mProducto.getPro_Id());
            }
            Button btnUpdate = findViewById(R.id.update_btnUpdate);
            btnUpdate.setActivated(true);
            Button btnCerrar = findViewById(R.id.update_btnCancel);
            btnCerrar.setActivated(true);
            Toast.makeText(mApplication.getApplicationContext(),text, Toast.LENGTH_SHORT).show();
        }
    }


}
