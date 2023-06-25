package luisrrleal.com.foodapp_v1.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import luisrrleal.com.foodapp_v1.Domain.Data_Provider;
import luisrrleal.com.foodapp_v1.R;

public class Popular_food_viewholder extends RecyclerView.ViewHolder {
    TextView card_title = (TextView)itemView.findViewById(R.id.food_title_txt);
    TextView card_price = (TextView)itemView.findViewById(R.id.food_price_txt);
    ImageView card_img = (ImageView) itemView.findViewById(R.id.food_image_view1);

    public Popular_food_viewholder(@NonNull View itemView) {
        super(itemView);
    }

    public void render_card(Data_Provider popular_food_item){
        card_title.setText(popular_food_item.getCardTitle());
        card_price.setText(popular_food_item.getCardPrice());
        card_img.setBackgroundResource(popular_food_item.getCardImgResource());
    }
}