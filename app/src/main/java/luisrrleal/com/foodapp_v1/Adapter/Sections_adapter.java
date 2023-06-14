package luisrrleal.com.foodapp_v1.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Domain.Sections;
import luisrrleal.com.foodapp_v1.R;

public class Sections_adapter extends RecyclerView.Adapter<Sections_viewholder> {

    ArrayList<Sections> sectionsCards;

    public Sections_adapter(ArrayList<Sections> sectionsCards) {
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
    }

    @Override
    public int getItemCount() {
        return sectionsCards.size();
    }
}
