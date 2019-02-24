package com.example.listaproductos.Activities;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.listaproductos.Controllers.Repository;
import com.example.listaproductos.Model.Producto;
import com.example.listaproductos.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Application mAplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAplication = this.getApplication();

        mLayoutManager = new LinearLayoutManager(this);
        new RecyclerAsync().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.mi_addProducts:
                Intent intent = new Intent(getApplicationContext(), addProductos.class );
                startActivity(intent);
                return true;

            default: return false;
        }
    }



    private class RecyclerAsync extends AsyncTask<Void, Void, List<Producto>> {


        @Override
        protected void onPreExecute(){

        }

        @Override
        protected List<Producto> doInBackground(Void... voids) {
            Repository repo = new Repository(mAplication);
            return repo.getProducts();
        }

        @Override
        protected void onPostExecute(List<Producto> productos){
            Producto pro = new Producto();
            pro.setPro_Nombre("Leche");
            pro.setPro_NumStock(23);
            pro.setPro_precio(18);
            productos.add(pro);
            mRecyclerView = findViewById(R.id.main_recyclerProductos);
            mRecyclerView.setHasFixedSize(true);

            mAdapter = new RecyclerAdapter(productos,mAplication);

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }

    }

}
