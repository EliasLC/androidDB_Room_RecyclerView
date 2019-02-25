package com.example.listaproductos.Model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProductoDAO {

    @Insert
    void insertProducto(Producto producto);

    @Query("SELECT * FROM Producto WHERE pro_Id =:Id")
    Producto getProducto(int Id);

    @Query("DELETE FROM Producto WHERE pro_Id =:Id")
    void deleteProducto(int Id);

    @Update
    void updateProducto(Producto producto);

    @Query("SELECT * FROM Producto")
    List<Producto> getProductos();

    @Query("DELETE FROM Producto")
    void deleteAll();
}
