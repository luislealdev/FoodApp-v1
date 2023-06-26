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

public class Cart_Product_adapter extends RecyclerView.Adapter<Cart_Product_viewholder> {
    ArrayList<Data_Provider> cart_products;
    public Cart_Product_adapter(ArrayList<Data_Provider> cart_products) {
        this.cart_products = cart_products;
    }

    @NonNull
    @Override
    public Cart_Product_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        Cart_Product_viewholder res_layout;
        res_layout  = new Cart_Product_viewholder(li.inflate(R.layout.cart_item, parent, false));
        return res_layout;
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_Product_viewholder holder, int position) {
        holder.render_card(cart_products.get(position));
    }

    @Override
    public int getItemCount() {
        return cart_products.size();
    }
}
