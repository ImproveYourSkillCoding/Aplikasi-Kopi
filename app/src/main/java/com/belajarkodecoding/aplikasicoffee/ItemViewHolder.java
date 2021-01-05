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
    public TextView txtNamaProduk_cart,txtHargaProduk_cart, txtJumlahProduk_cart;
    public ImageView imgProduk_cart;
    public CardView cardView_cart;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        //deklarasi variable menu
        txtNamaProduk = (TextView)itemView.findViewById(R.id.tv_judul);
        txtHargaProduk = (TextView)itemView.findViewById(R.id.tv_harga);
        imgProduk = (ImageView)itemView.findViewById(R.id.item_photo);
        cardView = (CardView)itemView.findViewById(R.id.card_view_kopi);

        // deklarasi fitur keranjang
        txtNamaProduk_cart = (TextView)itemView.findViewById(R.id.tv_nama_cart);
        txtHargaProduk_cart = (TextView)itemView.findViewById(R.id.tv_harga_cart);
        txtJumlahProduk_cart= (TextView)itemView.findViewById(R.id.txt_total);
        imgProduk_cart = (ImageView)itemView.findViewById(R.id.item_photo_cart);
        cardView_cart = (CardView)itemView.findViewById(R.id.card_view_cart);
    }


    @Override
    public void onClick(View view) {

    }
}
