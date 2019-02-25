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
    private int mAddActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAplication = this.getApplication();
        mListaProductos = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this);
        mAddActivity = 1;

        mRecyclerView = findViewById(R.id.main_recyclerProductos);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new RecyclerAdapter(mListaProductos,mAplication);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        new RecyclerAsync().execute();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(RecyclerAdapter.getUpdateAdapter().getUpdateStatus()==1){
            new UpdateAsync().execute();
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
                startActivityForResult(intent, mAddActivity);
                return true;

            default: return false;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        /*AddActivityResult*/
        if(requestCode == mAddActivity && resultCode == RESULT_OK){
                new RecyclerAsync().execute();
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
        }

    }

    private class UpdateAsync extends AsyncTask<Void, Void, Producto>{

        @Override
        protected Producto doInBackground(Void... voids) {
            Repository repository = new Repository(mAplication);
            return repository.getProduct(RecyclerAdapter.getUpdateAdapter().getUpdateItemId());
        }

        @Override
        protected void onPostExecute(Producto result){

            if(result!= null){
                int index = RecyclerAdapter.getUpdateAdapter().getUpdateItemIndex();
                mListaProductos.get(index).setPro_Nombre(result.getPro_Nombre());
                mListaProductos.get(index).setPro_CodigoBarras(result.getPro_CodigoBarras());
                mListaProductos.get(index).setPro_Fecha(result.getPro_Fecha());
                mListaProductos.get(index).setPro_precio(result.getPro_precio());
                mListaProductos.get(index).setPro_NumStock(result.getPro_NumStock());
                mAdapter.notifyItemChanged(index);
                RecyclerAdapter.getUpdateAdapter().setAdapter();
            }
        }

    }

}
