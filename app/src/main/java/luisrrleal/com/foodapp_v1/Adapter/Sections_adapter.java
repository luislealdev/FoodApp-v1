package luisrrleal.com.foodapp_v1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Domain.Data_Provider;
import luisrrleal.com.foodapp_v1.R;

public class Sections_adapter extends RecyclerView.Adapter<Sections_viewholder> {

    ArrayList<Data_Provider> sectionsCards;

    public Sections_adapter(ArrayList<Data_Provider> sectionsCards) {
        this.sectionsCards = sectionsCards;
    }

    @NonNull
    @Override
    public Sections_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        Sections_viewholder res_layout;
        res_layout  = new Sections_viewholder(li.inflate(R.layout.section_item, parent, false));
        return res_layout;
    }

    @Override
    public void onBindViewHolder(@NonNull Sections_viewholder holder, int position) {
        holder.renderSection(sectionsCards.get(position));
        holder.section_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Evento", Toast.LENGTH_SHORT).show();
                Context context = v.getContext();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sectionsCards.size();
    }
}
