package luisrrleal.com.foodapp_v1.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Domain.Data_Provider;
import luisrrleal.com.foodapp_v1.R;

public class Popular_food_adapter2 extends RecyclerView.Adapter<Popular_food_viewholder2> {
    ArrayList<Data_Provider> popular_food_list;

    public Popular_food_adapter2(ArrayList<Data_Provider> popular_food_list) {
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
    }

    @Override
    public int getItemCount() {
        return popular_food_list.size();
    }
}
