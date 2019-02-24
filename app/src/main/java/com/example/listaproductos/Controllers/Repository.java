package com.example.listaproductos.Controllers;

import android.app.Application;
import com.example.listaproductos.Model.Producto;
import com.example.listaproductos.Model.ProductoDAO;
import com.example.listaproductos.Model.ProductoDatabase;

import java.util.List;

public class Repository {

    private ProductoDAO productoDAO;

    public Repository(Application application){
        ProductoDatabase productoDatabase = ProductoDatabase.getInstance(application);
        productoDAO = productoDatabase.productoDAO();
    }

    public void insertProduct(String nombre, String CodigoBarras, int numStock, double precio){
        Producto producto = new Producto();
        producto.setPro_Nombre(nombre);
        producto.setPro_CodigoBarras(CodigoBarras);
        producto.setPro_NumStock(numStock);
        producto.setPro_precio(precio);
        productoDAO.insertProducto(producto);
    }

    public void deleteProduct(int id){
        productoDAO.deleteProducto(id);
    }

    public void updateProduct(Producto producto){
        productoDAO.updateProducto(producto);
    }

    public List<Producto> getProducts(){
        return productoDAO.getProductos();
    }

    public void delalteAll(){
        productoDAO.deleteAll();
    }

}
