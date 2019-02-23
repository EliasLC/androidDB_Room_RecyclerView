package com.example.listaproductos.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Producto {

    @PrimaryKey
    private int pro_Id;

    @ColumnInfo(name = "pro_Nombre")
    @NonNull
    private String pro_Nombre;

    @ColumnInfo(name = "pro_CodigoBarras")
    @NonNull
    private String pro_CodigoBarras;

    @ColumnInfo(name = "pro_NumStock")
    @NonNull
    private int pro_NumStock;

    @ColumnInfo(name = "pro_Precio")
    @NonNull
    private int pro_precio;

    @ColumnInfo(name = "pro_Fecha")
    @NonNull
    private String pro_Fecha;


    public Producto(){

    }

    public int getPro_Id() {
        return pro_Id;
    }

    public void setPro_Id(int pro_Id) {
        this.pro_Id = pro_Id;
    }

    @NonNull
    public String getPro_Nombre() {
        return pro_Nombre;
    }

    public void setPro_Nombre(@NonNull String pro_Nombre) {
        this.pro_Nombre = pro_Nombre;
    }

    @NonNull
    public String getPro_CodigoBarras() {
        return pro_CodigoBarras;
    }

    public void setPro_CodigoBarras(@NonNull String pro_CodigoBarras) {
        this.pro_CodigoBarras = pro_CodigoBarras;
    }

    public int getPro_NumStock() {
        return pro_NumStock;
    }

    public void setPro_NumStock(int pro_NumStock) {
        this.pro_NumStock = pro_NumStock;
    }

    public int getPro_precio() {
        return pro_precio;
    }

    public void setPro_precio(int pro_precio) {
        this.pro_precio = pro_precio;
    }

    @NonNull
    public String getPro_Fecha() {
        return pro_Fecha;
    }

    public void setPro_Fecha(@NonNull String pro_Fecha) {
        this.pro_Fecha = pro_Fecha;
    }
}
