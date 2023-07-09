package luisrrleal.com.foodapp_v1.Adapter;

import luisrrleal.com.foodapp_v1.Interfaces.RV_Item_ClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Domain.Data_Provider;
import luisrrleal.com.foodapp_v1.R;

public class Cart_Product_adapter extends RecyclerView.Adapter<Cart_Product_viewholder> implements RV_Item_ClickListener{
    //Objeto para manejar los click events
    private RV_Item_ClickListener listener;
    ArrayList<Data_Provider> cart_products;
    public Cart_Product_adapter(ArrayList<Data_Provider> cart_products) {
        this.cart_products = cart_products;
    }

    //Contructor para la inicialización del objeto
    public void set_RV_itemClickListener(RV_Item_ClickListener listener) {
        this.listener = listener;
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

        //Agrega el event listener al botón
        holder.card_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    //Toast.makeText(v.getContext(), "ya no soy nuloooo", Toast.LENGTH_SHORT).show();
                    int cartItem_index = holder.getAdapterPosition();
                    listener.rv_itemClick_event(cartItem_index);
                }else{
                    Toast.makeText(v.getContext(), "Soy nuloooo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cart_products.size();
    }

    @Override
    public void rv_itemClick_event(int position, Cart_Product_adapter cards_adapater) {

    }

    @Override
    public void rv_itemClick_event(int position) {
        cart_products.remove(position);
        this.notifyItemRemoved(position);
    }
}
