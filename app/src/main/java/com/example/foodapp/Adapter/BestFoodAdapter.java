package com.example.foodapp.Adapter;
import jp.wasabeef.glide.transformations.BlurTransformation;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.renderscript.RenderScript;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodapp.Domain.Foods;
import com.example.foodapp.R;

import java.util.ArrayList;
public class BestFoodAdapter extends RecyclerView.Adapter<BestFoodAdapter.ViewHolder> {
    private ArrayList<Foods> items = new ArrayList<>();

    private Context context;

    public BestFoodAdapter(ArrayList<Foods> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_food, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Foods food = items.get(position);
        holder.titleTxt.setText(food.getTitle());
        holder.priceTxt.setText("Ar" + food.getPrice());
        holder.timeTxt.setText(food.getTimeValue() + "min");
        holder.starTxt.setText(String.valueOf(food.getStar()));

        float radius = 10f;
        // Résolution du premier problème
        Drawable windowBackground = ((Activity) holder.itemView.getContext()).getWindow().getDecorView().getBackground();

// Résolution du deuxième problème
        int roundingRadius = Math.round(radius); // Conversion du rayon float en int
        Glide.with(context)
                .load(food.getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(roundingRadius), new BlurTransformation(roundingRadius))
                .into(holder.pic);


        holder.relativeLayout.setBackground(windowBackground);
        holder.relativeLayout.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        holder.relativeLayout.setClipToOutline(true);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, priceTxt, starTxt, timeTxt;
        ImageView pic;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            starTxt = itemView.findViewById(R.id.starTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            pic = itemView.findViewById(R.id.img);
            relativeLayout = itemView.findViewById(R.id.blueView);
        }
    }
}
