package luisrrleal.com.foodapp_v1.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import luisrrleal.com.foodapp_v1.Adapter.Popular_food_adapter;
import luisrrleal.com.foodapp_v1.Adapter.Popular_food_adapter2;
import luisrrleal.com.foodapp_v1.Adapter.Sections_adapter;
import luisrrleal.com.foodapp_v1.Domain.Data_Provider;
import luisrrleal.com.foodapp_v1.R;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Home_Fragment extends Fragment{
    private EditText search_bar;
    private RecyclerView recyclerView_food;
    private RecyclerView recyclerView_sections;

    ArrayList<Data_Provider> sections = new ArrayList<>();
    ArrayList<Data_Provider> cards = new ArrayList<>();

    //Esta arrayList lo ocupamos para guardar las cards filtradas por el buscador
    List<Data_Provider> cardsAux = new ArrayList<Data_Provider>();
    Popular_food_adapter2 vertical_cards = new Popular_food_adapter2(cards);

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
        //searchItems();
        fill_cards_info();
        fill_section_info();
    }

    public void set_Search_bar(){
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String valueTyped = s.toString().toLowerCase();
                cardsAux = cards.stream()
                        .filter(cardItem -> cardItem.getCardTitle().toLowerCase().contains(valueTyped))
                        .collect(Collectors.toList());
                vertical_cards.updateCardsBySearch(cardsAux);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void fill_cards_info(){
        cards.add(new Data_Provider(
                "Hot cakes", "50.00",R.drawable.comida1
        ));
        cards.add(new Data_Provider(
                "Caldo de caldo","50.00",R.drawable.comida2
        ));
        cards.add(new Data_Provider(
                "Burritos de guiso","60.00",R.drawable.comida3
        ));
        cards.add(new Data_Provider(
                "Burguir con papas", "60.00",R.drawable.comida4
        ));
    }

    public void fill_section_info(){
        sections.add(new Data_Provider("Comida",R.drawable.food));
        sections.add(new Data_Provider("Bebidas",R.drawable.drinks));
        sections.add(new Data_Provider("Snacks",R.drawable.snacks));
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
        /*recyclerView_food = (RecyclerView) getView().findViewById(R.id.recyclerView_food_id);
        LinearLayoutManager rv_layoutManager2 = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_food.setLayoutManager(rv_layoutManager2);
        recyclerView_food.setAdapter(new Popular_food_adapter(cards));*/

        //Get the recycelrView of popular food and render it in the fragment
        search_bar = (EditText)getView().findViewById(R.id.search_editText);
        set_Search_bar();
        recyclerView_food = (RecyclerView) getView().findViewById(R.id.recyclerView_food_id2);
        LinearLayoutManager rv_layoutManager3 = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView_food.setLayoutManager(rv_layoutManager3);
        recyclerView_food.setAdapter(vertical_cards);
    }
}