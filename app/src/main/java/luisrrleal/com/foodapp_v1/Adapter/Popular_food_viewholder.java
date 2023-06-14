package luisrrleal.com.foodapp_v1.Adapter;

//import static android.;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import luisrrleal.com.foodapp_v1.Domain.Popular_food;
import luisrrleal.com.foodapp_v1.R;

public class Popular_food_viewholder extends RecyclerView.ViewHolder {

    TextView card_title = (TextView)itemView.findViewById(R.id.food_title_txt);
    TextView card_price = (TextView)itemView.findViewById(R.id.food_price_txt);
    RelativeLayout card_img = (RelativeLayout) itemView.findViewById(R.id.card_id);
    //ImageView card_img = (ImageView) itemView.findViewById(R.id.food_image_view);

    public Popular_food_viewholder(@NonNull View itemView) {
        super(itemView);
    }

    public void render_card(Popular_food popular_food_item){
        card_title.setText(popular_food_item.getTitle());
        card_price.setText(popular_food_item.getPrice());
        card_img.setBackgroundResource(popular_food_item.getPicUrl());
    }
}