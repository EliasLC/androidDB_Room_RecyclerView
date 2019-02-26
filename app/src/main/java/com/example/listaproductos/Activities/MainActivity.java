package com.example.listaproductos.Activities;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.listaproductos.Controllers.ProductViewModel;
import com.example.listaproductos.Controllers.Repository;
import com.example.listaproductos.Model.Producto;
import com.example.listaproductos.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Application mAplication;
    private List<Producto> mListaProductos;
    private int mAddActivity;
    private ProductViewModel mProductViewModel;


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

       mProductViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

       mProductViewModel.getAllProducts().observe(this, new Observer<List<Producto>>() {
           @Override
           public void onChanged(@Nullable List<Producto> productos) {
               mAdapter.setProductos(productos);
           }
       });

       new CleanAsync().execute();
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
               // new RecyclerAsync().execute();
            Producto producto = data.getParcelableExtra("EXTRA_REPLY");
            mProductViewModel.insert(producto);
            Toast.makeText(this, "Insercion Exitosa", Toast.LENGTH_SHORT).show();
        }

    }

    private class CleanAsync extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            Repository repository = new Repository(mAplication);
            repository.delalteAll();
            return null;
        }
    }

}
