package luisrrleal.com.foodapp_v1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

import java.util.ArrayList;

import luisrrleal.com.foodapp_v1.Domain.Popular_food;
import luisrrleal.com.foodapp_v1.R;

public class Popular_food_adapter extends RecyclerView.Adapter<Popular_food_adapter.ViewHolder> {

    ArrayList<Popular_food> cards;
    Context context;

    public Popular_food_adapter(ArrayList<Popular_food> cards) {
        this.cards = cards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        * Inflate a new view hierarchy from the specified xml resource. Throws InflateException if there is an error.
        *
        Params:
        * resource – ID for an XML layout resource to load (e.g., R.layout.main_page)
        * root – Optional view to be the parent of the generated hierarchy (if attachToRoot is true),
        * or else simply an object that provides a set of LayoutParams values for root of the returned
        * hierarchy (if attachToRoot is false.)
        * attachToRoot – Whether the inflated hierarchy should be attached to the root parameter? If
        * false, root is only used to create the correct subclass of LayoutParams for the root view in the XML.
        *
        Returns:
        * The root View of the inflated hierarchy. If root was supplied and attachToRoot is true,
        * this is root; otherwise it is the root of the inflated XML file.
        * */

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_info_layout, parent, false);
        //context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.food_title.setText(cards.get(position).getTitle());
        holder.food_price.setText(cards.get(position).getPrice()+"");
        int food_img_id = holder.itemView.getContext().getResources().getIdentifier(
                cards.get(position).getPicUrl(),
                "drawable",
                holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(food_img_id)
                .into(holder.food_img);
        //      .transform(new GranularRoundedCorners(30,30,30,30))
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView food_title, food_price;
        ImageView food_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            food_title = itemView.findViewById(R.id.food_title_txt);
            food_price = itemView.findViewById(R.id.food_price_txt);
            food_img = itemView.findViewById(R.id.food_image_view);
        }
    }
}
