package luisrrleal.com.foodapp_v1.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import luisrrleal.com.foodapp_v1.Adapter.Popular_food_adapter;
import luisrrleal.com.foodapp_v1.MainActivity;
import luisrrleal.com.foodapp_v1.R;
import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Domain.Popular_food;

public class Home_Fragment extends Fragment{

    private RecyclerView recyclerView_food;
    ArrayList<Popular_food> cards = new ArrayList<>();
    MainActivity activity = new MainActivity();

    public static Home_Fragment newInstance() {
        Home_Fragment fragment = new Home_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void food_cards_Setup(){
        fill_cards_info();
        LinearLayoutManager rv_layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false);
        recyclerView_food.setLayoutManager(rv_layoutManager);
        recyclerView_food.setAdapter(new Popular_food_adapter(cards));
    }

    public void fill_cards_info(){
        cards.add(new Popular_food(
                "Hot cakes", "50.00",""
        ));
        cards.add(new Popular_food(
                "Caldo de caldo","50.00",""
        ));
        cards.add(new Popular_food(
                "Burritos de guiso","60.00",""
        ));
        cards.add(new Popular_food(
                "Burguir con papas", "60.00",""
        ));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    /*
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView_food = (RecyclerView) getView().findViewById(R.id.recyclerView_food_id);
        food_cards_Setup();
    }*/
}