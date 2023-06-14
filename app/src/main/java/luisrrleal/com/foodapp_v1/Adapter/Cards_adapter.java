package luisrrleal.com.foodapp_v1.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import luisrrleal.com.foodapp_v1.R;

import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Domain.Data_cards_provider;

public class Cards_adapter extends RecyclerView.Adapter<Cards_viewholder> {

    ArrayList<Data_cards_provider> cards ;

    @NonNull
    @Override
    public Cards_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        Cards_viewholder res_layout = null;

        //Necesitamos una forma de validar para saber cuál es el componente gráfico que vamos a mostrar, pero aún no se cómo
        String provider = li.getContext().getClass().getName();
        if(provider.equals("Sections")){
            res_layout = new Cards_viewholder(li.inflate(R.layout.section_item, parent, false));
        }
        if(provider.equals("Sections")){
            res_layout = new Cards_viewholder(li.inflate(R.layout.section_item, parent, false));
        }
        return res_layout;
    }

    @Override
    public void onBindViewHolder(@NonNull Cards_viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
