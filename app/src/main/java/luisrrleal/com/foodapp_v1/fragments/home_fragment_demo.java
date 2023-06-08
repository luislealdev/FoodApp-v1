package luisrrleal.com.foodapp_v1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import luisrrleal.com.foodapp_v1.Domain.Popular_food_data;
import luisrrleal.com.foodapp_v1.MainActivity;
import luisrrleal.com.foodapp_v1.R;

public class home_fragment_demo extends Fragment {

    Popular_food_data foodCards[] = null;
    MainActivity activity = new MainActivity();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView rv = activity.findViewById(R.id.fragmentX_demo);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(activity));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main,container,false);
        return inflater.inflate(R.layout.fragment_home_demo, container, false);
    }

    private void foodCards_setUp(){
        foodCards = new Popular_food_data[]{
          new Popular_food_data("Hot cakes",R.drawable.comida1,50.00),
          new Popular_food_data("Caldo de caldo", R.drawable.comida2,50.00),
          new Popular_food_data("Burritoscon guiso", R.drawable.comida3,60.00),
          new Popular_food_data("Burguir con papas", R.drawable.comida4, 60)
        };
    }

}