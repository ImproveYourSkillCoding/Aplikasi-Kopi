package com.belajarkodecoding.aplikasicoffee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdapterItem extends FirebaseRecyclerAdapter<Item,AdapterItem.viewholder>
{

    public AdapterItem(@NonNull FirebaseRecyclerOptions<Item> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull final Item model) {
        holder.nama.setText(model.getNama());
        holder.harga.setText(model.getHarga());
        GlideApp.with(holder.item_photo.getContext()).load(model.getUrl()).into(holder.item_photo);

                holder.item_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppCompatActivity activity=(AppCompatActivity)view.getContext();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.home,
                                new DetailItem(model.getNama(),model.getHarga(),model.getUrl())).addToBackStack(null).commit();
                    }
                });
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kopi,parent,false);
        return new viewholder(view);
    }

    public class viewholder extends RecyclerView.ViewHolder{

        ImageView item_photo;
        TextView nama, harga;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            item_photo=itemView.findViewById(R.id.img_item_photo);
            nama=itemView.findViewById(R.id.tv_judul);
            harga=itemView.findViewById(R.id.tv_harga);

        }
    }

}
