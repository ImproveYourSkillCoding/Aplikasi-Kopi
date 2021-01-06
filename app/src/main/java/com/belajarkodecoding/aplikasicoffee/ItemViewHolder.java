package com.belajarkodecoding.aplikasicoffee;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    // deklarasi tipe data
    public TextView txtNamaProduk,txtHargaProduk;
    public ImageView imgProduk;
    public CardView cardView;
    public TextView txtNamaProduk_cart,txtHargaProduk_cart, txtJumlahProduk_cart, txtKategori;
    public ImageView imgProduk_cart;
    public CardView cardView_cart;
    public Button btn_cancel;

    public TextView txtTanggalBeli, txtTotalHarga;
    public CardView cardView_riwayat;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        //deklarasi id produk
        txtNamaProduk = (TextView)itemView.findViewById(R.id.tv_judul);
        txtHargaProduk = (TextView)itemView.findViewById(R.id.tv_harga);
        imgProduk = (ImageView)itemView.findViewById(R.id.item_photo);
        cardView = (CardView)itemView.findViewById(R.id.card_view_kopi);

        // deklarasi id fitur keranjang
        txtNamaProduk_cart = (TextView)itemView.findViewById(R.id.tv_nama_cart);
        txtHargaProduk_cart = (TextView)itemView.findViewById(R.id.tv_harga_cart);
        txtJumlahProduk_cart= (TextView)itemView.findViewById(R.id.jumlah_kopi);
        imgProduk_cart = (ImageView)itemView.findViewById(R.id.item_photo_cart);
        cardView_cart = (CardView)itemView.findViewById(R.id.card_view_cart);
        btn_cancel = (Button)itemView.findViewById(R.id.btn_cancel);

        //deklarasi id fitur riwayat
        txtTanggalBeli = (TextView)itemView.findViewById(R.id.tanggalbeli);
        txtTotalHarga = (TextView)itemView.findViewById(R.id.total_harga);
        txtKategori = (TextView)itemView.findViewById(R.id.kategori);
        cardView_riwayat = (CardView)itemView.findViewById(R.id.card_view_riwayat);
    }


    @Override
    public void onClick(View view) {

    }
}
