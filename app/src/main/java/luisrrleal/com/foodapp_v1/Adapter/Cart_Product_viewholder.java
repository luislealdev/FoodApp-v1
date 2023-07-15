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
//se llamará con cada uno de los items del arraylist de Data_Provider que definimos en el adapter, se encargará de tomar cada uno de esos datos y hacer su renderizado.

//Básicamente representa un elemento individual de un RV, por lo que es se llamará a sus métodos las mismas cantidad de veces de la longitud de nustro ArrayList de Data_Provider.

//Proporciona esta clase una manera de acceder y actualizar eficientemente los datos del elemento individual

//El RV necesita del VH para reciclar automáticamente las vistas fuera de pantalla para reutilizarlas cuanod se necesiten mostrar nuevos elementos que se vuelvan visibles.

//El VH hace un inflate de un elemento específico,¿-- a su vez, se guardan las referencias a las vistas cerce de ese elemento para su posterior uso--?

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
    public int getIndexCard(){
        //return itemView
        return 0;
    }
}