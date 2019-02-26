package com.example.listaproductos.Controllers;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.Toast;

import com.example.listaproductos.Model.Producto;
import com.example.listaproductos.Model.ProductoDAO;
import com.example.listaproductos.Model.ProductoDatabase;

import java.net.PortUnreachableException;
import java.util.List;

public class Repository {

    private ProductoDAO productoDAO;
    private LiveData<List<Producto>> mAllProducts;
    private Application mApplication;

    public Repository(Application application){
        ProductoDatabase productoDatabase = ProductoDatabase.getInstance(application);
        productoDAO = productoDatabase.productoDAO();
        mAllProducts = productoDAO.getProductos();
        this.mApplication = application;
    }

    public boolean insertProduct(Producto producto){
        new InsertProducto(productoDAO, producto).execute();
        return true;
    }

    public Producto getProduct(int id){
        return  productoDAO.getProducto(id);
    }

    public boolean deleteProduct(int id){
        new DeleteProducto(productoDAO,id).execute();
        return true;
    }

    public boolean updateProduct(Producto producto){
        new UpdateProducto(productoDAO, producto).execute();
        return true;
    }

    public LiveData<List<Producto>> getAllProducts(){
        return productoDAO.getProductos();
    }

    public void delalteAll(){
        productoDAO.deleteAll();
    }

    /*Insertar Producto*/
    private static class InsertProducto extends AsyncTask<Void,Void,Void> {

        private Producto producto;
        private ProductoDAO productoDAO;

        public InsertProducto(ProductoDAO productoDAO, Producto producto){
            this.producto = producto;
            this.productoDAO = productoDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productoDAO.insertProducto(producto);
            return null;
        }
    }

    /*Modificar pRODUCTO*/
    private static class UpdateProducto extends AsyncTask<Void,Void,Void> {

        private Producto producto;
        private ProductoDAO productoDAO;

        public UpdateProducto(ProductoDAO productoDAO, Producto producto){
            this.producto = producto;
            this.productoDAO = productoDAO;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            productoDAO.updateProducto(producto);
            return null;
        }
    }

    /*Eliminar Producto*/
    private static class DeleteProducto extends AsyncTask<Void,Void,Void> {

        private ProductoDAO productoDAO;
        private int productoId;

        public DeleteProducto (ProductoDAO productoDAO, int productoId){
            this.productoDAO = productoDAO;
            this.productoId = productoId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productoDAO.deleteProducto(productoId);
            return null;
        }

    }

}
