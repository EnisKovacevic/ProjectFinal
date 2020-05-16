package com.example.project3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VinylAdapter extends RecyclerView.Adapter<VinylAdapter.MyViewHolder> {

    private Context mContext;
    private List<Vinyl> vinylList;


    public VinylAdapter(List<Vinyl> vinylList, Context mContext) {
        this.mContext = mContext;
        this.vinylList = vinylList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.vinyl_card, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Vinyl vinyl = vinylList.get(position);
        holder.title.setText(vinyl.getName());
        holder.songtitle.setText(vinyl.getSongtitle());
        holder.price.setText("$" + vinyl.getPrices());

        holder.thumbnail.setImageResource(vinylList.get(position).getThumbnail());

        Glide.with(mContext).load(vinyl.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });

        holder.DeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vinylList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }


        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("Title",vinylList.get(position).getName());
                intent.putExtra("Description",vinylList.get(position).getSongtitle());
                intent.putExtra("Thumbnail",vinylList.get(position).getThumbnail());
                mContext.startActivity(intent);
            }
        });
    }

    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    public void remove(int position) {

    }

    @Override
    public int getItemCount() {
        return vinylList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView songtitle;
        TextView price;
        ImageView thumbnail;
        ImageView overflow;
        ImageView DeleteImage;
        CardView cardView ;
        TextView ime;

        public MyViewHolder(View view) {
            super(view);
            ime = (TextView) view.findViewById(R.id.name);

            title = (TextView) view.findViewById(R.id.title);
            songtitle = (TextView) view.findViewById(R.id.songtitle);
            price = (TextView) view.findViewById(R.id.price);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            cardView = (CardView) view.findViewById(R.id.card_view);
            DeleteImage = (ImageView) view.findViewById(R.id.image_delete);
        }
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.addtocard:
                    Toast.makeText(mContext, "Add To Cart", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.favorite:
                    Toast.makeText(mContext, "Add to favorite", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }


}