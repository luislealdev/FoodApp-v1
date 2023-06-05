package luisrrleal.com.foodapp_v1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Domain.Popular_food;

public class Home_Fragment extends Fragment{
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView_food;
    private MainActivity main;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public Home_Fragment() {

    }
    public static Home_Fragment newInstance(String param1, String param2) {
        Home_Fragment fragment = new Home_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        recyclerView_setUp();
    }

    private void recyclerView_setUp(){

        ArrayList<Popular_food> cards = new ArrayList<>();
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
        //main = new ViewModelProvider(this).get(Popular_food.class);

        return inflater.inflate(R.layout.fragment_home_, container, false);
    }
}