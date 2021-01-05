package com.belajarkodecoding.aplikasicoffee;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtNamaProduk,txtHargaProduk;
    public ImageView imgProduk;
    public CardView cardView;
    public TextView txtNamaProduk_cart,txtHargaProduk_cart,txtJumlahProduk_cart;
    public ImageView imgProduk_cart;
    public CardView cardView_cart;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        txtNamaProduk = (TextView)itemView.findViewById(R.id.tv_judul);
        txtHargaProduk = (TextView)itemView.findViewById(R.id.tv_harga);
        imgProduk = (ImageView)itemView.findViewById(R.id.img_item_photo);
        cardView = (CardView)itemView.findViewById(R.id.card_view);

        txtNamaProduk_cart = (TextView)itemView.findViewById(R.id.tv_nama_cart);
        txtHargaProduk_cart = (TextView)itemView.findViewById(R.id.tv_harga_cart);
        txtJumlahProduk_cart= (TextView)itemView.findViewById(R.id.jumlah_item);
        imgProduk_cart = (ImageView)itemView.findViewById(R.id.img_item_photo_cart);
        cardView_cart = (CardView)itemView.findViewById(R.id.card_view2);
    }


    @Override
    public void onClick(View view) {

    }
}
