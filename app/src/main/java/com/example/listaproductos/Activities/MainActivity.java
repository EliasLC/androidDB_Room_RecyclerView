package com.example.listaproductos.Activities;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listaproductos.Controllers.Repository;
import com.example.listaproductos.Model.Producto;
import com.example.listaproductos.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Application mAplication;
    private List<Producto> mListaProductos;
    private int mEstadoCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAplication = this.getApplication();
        mListaProductos = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this);
        mEstadoCambio = 0;

        mRecyclerView = findViewById(R.id.main_recyclerProductos);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new RecyclerAdapter(mListaProductos,mAplication);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        new RecyclerAsync().execute();
    }


    @Override
    protected void onResume(){
        super.onResume();
        switch (mEstadoCambio){

            case 1:
                new RecyclerAsync().execute();
                //Toast.makeText(this, "Se agrego un nuevo",Toast.LENGTH_SHORT).show();
                mEstadoCambio = 0;
                break;
        }
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
                Intent intent = new Intent(getApplicationContext(), AddProducts.class );
                mEstadoCambio=1;
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

            if(mListaProductos.size()<productos.size()){

                for(int i = mListaProductos.size(); i<productos.size(); i++){
                    mListaProductos.add(productos.get(i));
                    mAdapter.notifyItemInserted(i);
                }

            }

            /*Producto pro = new Producto();
            pro.setPro_Nombre("Leche");
            pro.setPro_NumStock(23);
            pro.setPro_precio(18);
            Producto pr2 = new Producto();
            pr2.setPro_Nombre("Carlos V");
            pr2.setPro_NumStock(23);
            pr2.setPro_precio(18);
            mListaProductos.add(pro);
            mAdapter.notifyItemInserted(0);
            mListaProductos.add(pr2);
            mAdapter.notifyItemInserted(1);
            */
        }

    }

}
