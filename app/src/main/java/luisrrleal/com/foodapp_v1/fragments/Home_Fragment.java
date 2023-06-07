package luisrrleal.com.foodapp_v1.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import luisrrleal.com.foodapp_v1.MainActivity;
import luisrrleal.com.foodapp_v1.R;
import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Domain.Popular_food;

public class Home_Fragment extends Fragment{
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView_food;

    ArrayList<Popular_food> cards = new ArrayList<>();

    //TODO: ADD METHOD TO RECIEVE AN ARGUMENT (NAME)
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
        recyclerView_setUp();
    }

    private void recyclerView_setUp(){

        cards.add(new Popular_food(
                "Hot cakes",
                "3 Hot cakes hechos con huevo, leche, harina de trigo y mantequilla, " +
                        "servidos con miel de maple o mermelada de fresa.",
                "comida1.jpg",
                50.00
        ));

        cards.add(new Popular_food(
                "Caldo de caldo",
                "Un caldo de carne de res y jitomate con chile guajillo, con pedazitos " +
                        "de carne de res y trozos de papa. Servido con 3 tortillas y un vaso de agua de sabor",
                "comida2.jpg",
                50.00
        ));

        cards.add(new Popular_food(
                "Burritos de guiso",
                "Tres burritos de un guiso de preferencia.",
                "comida3.jpg",
                60.00
        ));

        cards.add(new Popular_food(
                "Burguir con papas",
                "Hamburguesa de care de res, servida con papas fritas en gajos y aompañadas de una " +
                        "bebida de sabor.",
                "comida4.jpg",
                60.00
        ));

        //recyclerView_food = root
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_main,container,false);

        //LinearLayout ly = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView_food = v.findViewById(R.id.recyclerView_food_id);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}