package com.example.listaproductos.Activities;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listaproductos.Model.Producto;
import com.example.listaproductos.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.AdapterViewHolder> {

    private List<Producto> mProductos;
    private Application mAplication;

    public static class AdapterViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName,tvPrice, tvStock;
        public ImageView ivMenu;


        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.card_tvName);
            tvPrice = itemView.findViewById(R.id.card_tvPrice);
            tvStock = itemView.findViewById(R.id.card_tvStock);
            ivMenu = itemView.findViewById(R.id.card_popup_menu);
        }

    }

    public RecyclerAdapter(List<Producto> productos, Application application){
        this.mProductos = productos;
        this.mAplication = application;
    }


    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_card, viewGroup,false);
        AdapterViewHolder evh = new AdapterViewHolder(view);
        return evh;
    }


    //Obtener los datos de la lista y pasarlos a cada cardview
    @Override
    public void onBindViewHolder(@NonNull final AdapterViewHolder adapterViewHolder, int i) {
        Producto producto = mProductos.get(i);
        adapterViewHolder.tvName.setText(producto.getPro_Nombre());
        String aux = adapterViewHolder.tvStock.getText().toString();
        adapterViewHolder.tvStock.setText(aux+" "+String.valueOf(producto.getPro_NumStock()));
        aux = adapterViewHolder.tvPrice.getText().toString();
        adapterViewHolder.tvPrice.setText(aux+" "+String.valueOf(producto.getPro_precio()));

        //Agregar menu a imageview
        adapterViewHolder.ivMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(mAplication.getApplicationContext(), adapterViewHolder.ivMenu);
                popupMenu.inflate(R.menu.cardview_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch(item.getItemId()){

                            case R.id.cvm_update:
                                Toast.makeText(mAplication.getApplicationContext(), "Modificar", Toast.LENGTH_SHORT).show();
                                return true;


                            case R.id.cvm_delete:
                                Toast.makeText(mAplication.getApplicationContext(), "Eliminar", Toast.LENGTH_SHORT).show();
                                return true;

                            default: return false;
                        }

                    }
                });//END SETONCLICK

                popupMenu.show();
            }
        });//END

    }


    //Numero de elementos en la lista
    @Override
    public int getItemCount() {
        return mProductos.size();
    }



}
