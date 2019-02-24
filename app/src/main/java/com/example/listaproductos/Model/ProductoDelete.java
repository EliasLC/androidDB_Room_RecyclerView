package com.example.listaproductos.Model;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.listaproductos.Controllers.Repository;

public class ProductoDelete extends AsyncTask<Void, Void, Boolean> {

    private int idDel;
    private Application mApplication;

    public ProductoDelete(int id, Application application){
        this.idDel = id;
        this.mApplication = application;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Repository repository = new Repository(mApplication);
        return repository.deleteProduct(idDel);

    }

    @Override
    protected void onPostExecute(Boolean result){
        String text = "Eliminacion exitosa";
        if(!result){
            text =  "No se pudo realizar la eliminacion";
        }
        Toast.makeText(mApplication.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
