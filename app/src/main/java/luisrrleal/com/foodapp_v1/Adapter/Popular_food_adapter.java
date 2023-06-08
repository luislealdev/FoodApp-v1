package luisrrleal.com.foodapp_v1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Domain.Popular_food_data;
import luisrrleal.com.foodapp_v1.MainActivity;
import luisrrleal.com.foodapp_v1.R;

public class Popular_food_adapter extends RecyclerView.Adapter<Popular_food_adapter.ViewHolder> {

    Popular_food_data[] food_cards;
    Context context;
    public Popular_food_adapter(Popular_food_data[] food_cards, MainActivity activity) {
        this.food_cards = food_cards;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.food_info_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Popular_food_data popular_food_data = food_cards[position];
        holder.food_title.setText(popular_food_data.getTitle());
        holder.food_price.setText(popular_food_data.getPrice()+"");
        holder.food_img.setImageResource(popular_food_data.getPicUrl());
        //This code supposted to work with the last code
        /*int food_img_id = holder.itemView.getResources().getIdentifier(
                food_cards.get(position).getPicUrl(),
                "drawable",
                holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(food_img_id)
                .transform(new GranularRoundedCorners(30,30,30,30))
                .into(holder.food_img);*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, popular_food_data.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return food_cards.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView food_title, food_price;
        ImageView food_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            food_title = itemView.findViewById(R.id.food_title_txt);
            food_price = itemView.findViewById(R.id.food_price_txt);
            food_img = itemView.findViewById(R.id.card_id);
        }
    }
}
