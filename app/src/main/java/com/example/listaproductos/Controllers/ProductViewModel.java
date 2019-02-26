package com.example.listaproductos.Controllers;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.listaproductos.Model.Producto;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<Producto>> mAllProducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllProducts = mRepository.getAllProducts();
    }

    public LiveData<List<Producto>> getAllProducts(){
        return this.mAllProducts;
    }

    public void insert(Producto producto){
        mRepository.insertProduct(producto);
    }

    public void update(Producto producto){
        mRepository.updateProduct(producto);
    }

    public void delete(int id){
        mRepository.deleteProduct(id);
    }

}
