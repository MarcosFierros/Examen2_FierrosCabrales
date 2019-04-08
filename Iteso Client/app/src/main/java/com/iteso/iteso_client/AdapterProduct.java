package com.iteso.iteso_client;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.iteso_client.beans.ItemProduct;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {

    private ArrayList<ItemProduct> products;
    private Context context;
    private int fragment;

    public AdapterProduct(ArrayList<ItemProduct> products, Context context, int fragment) {
        this.products = products;
        this.context = context;
        this.fragment = fragment;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle;
        TextView mStore;
        TextView mDescription;


        ViewHolder(View v){
            super(v);
            mTitle = v.findViewById(R.id.item_product_title);
            mStore = v.findViewById(R.id.item_product_store);
            mDescription = v.findViewById(R.id.item_product_description);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(products.get(holder.getAdapterPosition()).getTitle());
        holder.mStore.setText(products.get(holder.getAdapterPosition()).getStore());
        holder.mDescription.setText(products.get(holder.getAdapterPosition()).getDescription());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

}
