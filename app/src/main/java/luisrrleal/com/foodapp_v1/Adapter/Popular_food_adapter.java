package luisrrleal.com.foodapp_v1.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Domain.Data_Provider;
import luisrrleal.com.foodapp_v1.InfoFoodActivity;
import luisrrleal.com.foodapp_v1.R;

public class Popular_food_adapter extends RecyclerView.Adapter<Popular_food_viewholder> {
    ArrayList<Data_Provider> popular_food_list;
    public Popular_food_adapter(ArrayList<Data_Provider> popular_food_list) {
        this.popular_food_list = popular_food_list;
    }

    @NonNull
    @Override
    public Popular_food_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        Popular_food_viewholder res_layout;
        res_layout  = new Popular_food_viewholder(li.inflate(R.layout.food_info_layout, parent, false));
        return res_layout;
    }

    @Override
    public void onBindViewHolder(@NonNull Popular_food_viewholder holder, int position) {
        holder.render_card(popular_food_list.get(position));
        int indexCard = holder.getAdapterPosition();
        setClickEvent(holder, indexCard);
    }

    public void setClickEvent(Popular_food_viewholder holder, int indexCard){
        holder.food_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), InfoFoodActivity.class);
                i.putExtra("sentTitle", popular_food_list.get(indexCard).getCardTitle());
                i.putExtra("sentPrice", popular_food_list.get(indexCard).getCardPrice());
                i.putExtra("sentImg", popular_food_list.get(indexCard).getCardImgResource());

                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popular_food_list.size();
    }
}
