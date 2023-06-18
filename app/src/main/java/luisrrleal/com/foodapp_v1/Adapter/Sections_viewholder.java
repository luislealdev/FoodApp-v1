package luisrrleal.com.foodapp_v1.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import luisrrleal.com.foodapp_v1.Domain.Sections;
import luisrrleal.com.foodapp_v1.R;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Sections_viewholder extends RecyclerView.ViewHolder {

    CardView section_cv = (CardView)itemView.findViewById(R.id.section_card_id);
    TextView sectionName_tv = (TextView) itemView.findViewById(R.id.sectionName_tv_id);
    ImageView sectionIcon_iv = (ImageView) itemView.findViewById(R.id.sectionIcon_iv_id);
    public Sections_viewholder(@NonNull View itemView) {
        super(itemView);
    }

    public void renderSection(Sections section_item){
        sectionName_tv.setText(section_item.getSectionName());
        sectionIcon_iv.setImageResource(section_item.getSectionIcon());
    }
}
