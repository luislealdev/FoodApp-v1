package luisrrleal.com.foodapp_v1.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import luisrrleal.com.foodapp_v1.Domain.Popular_food;
import luisrrleal.com.foodapp_v1.R;

public class Popular_food_adapter extends RecyclerView.Adapter<Popular_food_viewholder> {
    ArrayList<Popular_food> popular_food_list;

    public Popular_food_adapter(ArrayList<Popular_food> popular_food_list) {
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
    }

    @Override
    public int getItemCount() {
        return popular_food_list.size();
    }
}
