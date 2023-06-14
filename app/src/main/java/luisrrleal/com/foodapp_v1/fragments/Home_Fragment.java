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
import luisrrleal.com.foodapp_v1.Adapter.Sections_adapter;
import luisrrleal.com.foodapp_v1.Domain.Sections;
import luisrrleal.com.foodapp_v1.MainActivity;
import luisrrleal.com.foodapp_v1.R;
import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Domain.Popular_food;

public class Home_Fragment extends Fragment{
    private RecyclerView recyclerView_food;
    private RecyclerView recyclerView_sections;

    ArrayList<Sections> sections = new ArrayList<>();
    ArrayList<Popular_food> cards = new ArrayList<>();

    public Home_Fragment() {

    }
    public static Home_Fragment newInstance() {
        Home_Fragment fragment = new Home_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fill_cards_info();
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

    public void fill_section_info(){
        sections.add(new Sections(R.drawable.food, "comida"));
        sections.add(new Sections(R.drawable.drinks, "bebidas"));
        sections.add(new Sections(R.drawable.snacks, "snacks"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Get the recycelrView of sections and render it in the fragment
        recyclerView_sections = (RecyclerView) getView().findViewById(R.id.recyclerView_sections_id);
        LinearLayoutManager rv_layoutManager1 = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_sections.setLayoutManager(rv_layoutManager1);
        recyclerView_sections.setAdapter(new Sections_adapter(sections));

        //Get the recycelrView of popular food and render it in the fragment
        recyclerView_food = (RecyclerView) getView().findViewById(R.id.recyclerView_food_id);
        LinearLayoutManager rv_layoutManager2 = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_food.setLayoutManager(rv_layoutManager2);
        recyclerView_food.setAdapter(new Popular_food_adapter(cards));
    }
}