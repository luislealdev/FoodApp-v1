package luisrrleal.com.foodapp_v1.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import luisrrleal.com.foodapp_v1.Domain.Data_Provider;
import luisrrleal.com.foodapp_v1.InfoFoodActivity;
import luisrrleal.com.foodapp_v1.R;
import luisrrleal.com.foodapp_v1.fragments.Home_Fragment;

public class Popular_food_adapter2 extends RecyclerView.Adapter<Popular_food_viewholder2> {
    List<Data_Provider> popular_food_list;

    public Popular_food_adapter2(List<Data_Provider> popular_food_list) {
        this.popular_food_list = popular_food_list;
    }

    @NonNull
    @Override
    public Popular_food_viewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        Popular_food_viewholder2 res_layout;
        res_layout  = new Popular_food_viewholder2(li.inflate(R.layout.food_info_layout_vertical, parent, false));
        return res_layout;
    }

    @Override
    public void onBindViewHolder(@NonNull Popular_food_viewholder2 holder, int position) {
        holder.render_card(popular_food_list.get(position));
        int indexCard = holder.getAdapterPosition();
        displayInfoActivity(holder, indexCard);
    }

    public void displayInfoActivity(Popular_food_viewholder2 holder, int indexCard){
        holder.food_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), InfoFoodActivity.class);
                i.putExtra("sentTitle", popular_food_list.get(indexCard).getCardTitle());
                i.putExtra("sentPrice", popular_food_list.get(indexCard).getCardPrice());
                i.putExtra("sentImg", popular_food_list.get(indexCard).getCardImgResource2());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popular_food_list.size();
    }

    public void updateCardsBySearch(List<Data_Provider> cardsAux){
        this.popular_food_list = cardsAux;
        notifyDataSetChanged();
    }
}
