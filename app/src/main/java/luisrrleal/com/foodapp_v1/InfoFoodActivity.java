package luisrrleal.com.foodapp_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoFoodActivity extends AppCompatActivity {

    ImageView back_button, food_img;
    TextView food_title, food_price, food_decription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_food);
        back_button = (ImageView) findViewById(R.id.back_button);
        food_img = (ImageView) findViewById(R.id.food_img_id);
        food_title = (TextView) findViewById(R.id.food_title_id);
        food_price = (TextView) findViewById(R.id.food_price_id);
        //food_decription = (TextView) findViewById(R.id.food_description_id);

        setEventsListeners();
        fillFoodInfo();
    }
    public void setEventsListeners(){
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(i);
            }
        });
    }
    public void fillFoodInfo(){
        Intent i = getIntent();
        String food_title_getted = i.getStringExtra("sentTitle");
        String food_price_getted = i.getStringExtra("sentPrice");
        int food_image_getted = i.getIntExtra("sentImg", 0);

        food_title.setText(food_title_getted);
        food_price.setText(food_price_getted);
        food_img.setImageResource(food_image_getted);
    }
}