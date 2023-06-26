package luisrrleal.com.foodapp_v1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Adapter.Cart_Product_adapter;
import luisrrleal.com.foodapp_v1.Adapter.Popular_food_adapter;
import luisrrleal.com.foodapp_v1.Adapter.Popular_food_adapter2;
import luisrrleal.com.foodapp_v1.Adapter.Sections_adapter;
import luisrrleal.com.foodapp_v1.Domain.Data_Provider;
import luisrrleal.com.foodapp_v1.R;

public class Cart_Fragment extends Fragment {

    ArrayList<Data_Provider> cards = new ArrayList<>();
    private RecyclerView recyclerView_products_added;
    public Cart_Fragment() {
        // Required empty public constructor
    }

    public static Cart_Fragment newInstance() {
        Cart_Fragment fragment = new Cart_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fill_cards_info();
    }

    //Este método sólo es para poder visualizar cómo se vería un item agregado en el carrito, aún no funciona
    public void fill_cards_info(){
        cards.add(new Data_Provider(
                "Hot cakes", "50.00","",3,R.drawable.comida1
        ));
        cards.add(new Data_Provider(
                "Burritos de guiso", "60.00","",4,R.drawable.comida3
        ));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        recyclerView_products_added = (RecyclerView) getView().findViewById(R.id.recyclerView_orders);
        LinearLayoutManager rv_layoutManager3 = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView_products_added.setLayoutManager(rv_layoutManager3);
        recyclerView_products_added.setAdapter(new Cart_Product_adapter(cards));

    }
}