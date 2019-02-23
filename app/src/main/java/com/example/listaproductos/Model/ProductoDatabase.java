package com.example.listaproductos.Model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Producto.class}, version = 1)
public abstract class ProductoDatabase extends RoomDatabase {

    public abstract ProductoDAO productoDAO();

    public static volatile ProductoDatabase instance;

    public static ProductoDatabase getInstance(final Context context){

        if(instance == null){
            synchronized (Producto.class){
                if(instance==null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            ProductoDatabase.class, "Productos_db")
                            .build();
                }
            }
        }

        return instance;
    }

}
