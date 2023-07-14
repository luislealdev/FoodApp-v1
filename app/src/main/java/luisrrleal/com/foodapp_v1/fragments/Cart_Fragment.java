package luisrrleal.com.foodapp_v1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Interfaces.RV_Item_ClickListener;
import luisrrleal.com.foodapp_v1.Adapter.Cart_Product_adapter;
import luisrrleal.com.foodapp_v1.Domain.Data_Provider;
import luisrrleal.com.foodapp_v1.R;

public class Cart_Fragment extends Fragment{
    ArrayList<Data_Provider> cards = new ArrayList<>();
    private RecyclerView recyclerView_products_added;

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

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView_products_added = (RecyclerView) getView().findViewById(R.id.recyclerView_orders);
        //Un RecyclerView no determina por sí solo como serán mostrados sus items, pesta parte de la lógica
        //esta separada de dicho framework, la rsponsabilidad de ello es de un LayoutManager, dicha clase provee funciones
        //como: scrolling, reciclar los items y las vistas, además de optmizar el performance o el uso de memoria.
        recyclerView_products_added.setLayoutManager(rv_layoutManager3);
        Cart_Product_adapter cards_adapater = new Cart_Product_adapter(cards);

        //En este línea asignamos una referencia del listado al cual le estaremos modificando su estado 
        //(en este caso borrando los items)

        //No hay que olvidar que la instancia de uan interfaz podemos inicilizarla con cualquier otra instnacia de 
        //algunz clase que la implemente
        cards_adapater.set_RV_itemClickListener(cards_adapater);
        recyclerView_products_added.setAdapter(cards_adapater);
    }
    
    //Este método sólo es temporal, nos ayuda a simular la visualización de 2 items agregados en el carrito
    public void fill_cards_info(){
    cards.add(new Data_Provider(
                "Hot cakes", "50.00","",3,R.drawable.comida1
        ));
        cards.add(new Data_Provider(
                "Burritos de guiso", "60.00","",4,R.drawable.comida3
        ));
    }
}