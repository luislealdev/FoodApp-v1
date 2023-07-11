package luisrrleal.com.foodapp_v1.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import luisrrleal.com.foodapp_v1.Domain.Data_Provider;
import luisrrleal.com.foodapp_v1.R;

public class Cart_Product_viewholder extends RecyclerView.ViewHolder {

    CardView food_card = (CardView)itemView.findViewById(R.id.food_card_horizontal);
    TextView card_title = (TextView)itemView.findViewById(R.id.cart_title_id);
    TextView card_price = (TextView)itemView.findViewById(R.id.cart_price_id);
    ImageView card_img = (ImageView) itemView.findViewById(R.id.cart_img_id);
    Button card_button = (Button) itemView.findViewById(R.id.delete_cartItem_id);

    public Cart_Product_viewholder(@NonNull View itemView) {
        super(itemView);
    }

    public void render_card(Data_Provider popular_food_item){
        card_title.setText(popular_food_item.getCardTitle());
        card_price.setText(popular_food_item.getCardPrice());
        card_img.setBackgroundResource(popular_food_item.getCardImgResource());
    }
    public  int getIndexCard(){
        //return itemView
        return 0;
    }
}