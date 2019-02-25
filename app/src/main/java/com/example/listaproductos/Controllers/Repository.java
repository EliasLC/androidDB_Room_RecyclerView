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

    public boolean insertProduct(String nombre, String CodigoBarras, String fecha, int numStock, double precio){
        boolean res = true;
        Producto producto = new Producto();
        producto.setPro_Nombre(nombre);
        producto.setPro_CodigoBarras(CodigoBarras);
        producto.setPro_NumStock(numStock);
        producto.setPro_precio(precio);
        producto.setPro_Fecha(fecha);
        try {
            productoDAO.insertProducto(producto);
        } catch(Exception e){
            res=false;
        }
        return true;
    }

    public Producto getProduct(int id){
        return  productoDAO.getProducto(id);
    }

    public boolean deleteProduct(int id){
        boolean res = true;
        try {
            productoDAO.deleteProducto(id);
        } catch (Exception e){
            res = false;
        }
        return res;
    }

    public boolean updateProduct(Producto producto){
        boolean res = true;
        try {
            productoDAO.updateProducto(producto);
        } catch(Exception e){
            res = false;
        }
        return res;
    }

    public List<Producto> getProducts(){
        return productoDAO.getProductos();
    }

    public void delalteAll(){
        productoDAO.deleteAll();
    }

}
