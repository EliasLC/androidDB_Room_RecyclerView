package com.example.listaproductos.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity
public class Producto implements Parcelable {

    @PrimaryKey(autoGenerate = true)
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
    private double pro_precio;

    @ColumnInfo(name = "pro_Fecha")
    @NonNull
    private String pro_Fecha;


    public Producto(){

    }

    public Producto(Parcel in){
        readFromParcel(in);
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

    public double getPro_precio() {
        return pro_precio;
    }

    public void setPro_precio(double pro_precio) {
        this.pro_precio = pro_precio;
    }

    @NonNull
    public String getPro_Fecha() {
        return pro_Fecha;
    }

    public void setPro_Fecha(@NonNull String pro_Fecha) {
        this.pro_Fecha = pro_Fecha;
    }

    /*================================================
        Parcelable Stuff
    ================================================== */

    @Override
    public int describeContents() {
        return 0;
    }

    //Escribir todos los datos en el objeto al recuperarlo
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pro_Id);
        dest.writeString(pro_Nombre);
        dest.writeString(pro_CodigoBarras);
        dest.writeInt(pro_NumStock);
        dest.writeDouble(pro_precio);
        dest.writeString(pro_Fecha);
    }

    //Leer datos recividos
    private void readFromParcel(Parcel in) {
        pro_Id = in.readInt();
        pro_Nombre = "" +in.readString();
        pro_CodigoBarras = ""+ in.readString();
        pro_NumStock = in.readInt();
        pro_precio = in.readDouble();
        pro_Fecha = ""+ in.readString();
    }

    public static final Parcelable.Creator<Producto> CREATOR =
            new Parcelable.Creator<Producto>(){
                public Producto createFromParcel(Parcel in){
                    return new Producto(in);
                }

                @Override
                public Producto[] newArray(int size) {
                    return new Producto[0];
                }
            };

}
