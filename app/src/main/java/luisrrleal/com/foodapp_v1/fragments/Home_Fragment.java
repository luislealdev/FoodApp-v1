package luisrrleal.com.foodapp_v1.fragments;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;

import luisrrleal.com.foodapp_v1.Adapter.Popular_food_adapter;
import luisrrleal.com.foodapp_v1.Adapter.Popular_food_adapter2;
import luisrrleal.com.foodapp_v1.Adapter.Sections_adapter;
import luisrrleal.com.foodapp_v1.Domain.Data_Provider;
import luisrrleal.com.foodapp_v1.R;
import luisrrleal.com.foodapp_v1.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class Home_Fragment extends Fragment {
    FragmentHomeBinding binding;
    FirebaseStorage storage;
    StorageReference foodStorage;
    FirebaseFirestore firestore;
    CollectionReference foodCollection;

    private EditText search_bar;
    private RecyclerView recyclerView_food;
    private RecyclerView recyclerView_sections;
    ArrayList<Data_Provider> sections = new ArrayList<>();
    ArrayList<Data_Provider> cards = new ArrayList<>();

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
        firestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        foodCollection = firestore.collection("food/mealsID/mealsData");
        foodStorage = storage.getReference("food_images/meals");
        /*foodStorage.child("hot_cakes.jpg").getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // File data is returned as bytes. You can log the length of the file for verification.
                Log.d("FirebaseStorage", "File Size: " + bytes.length + " bytes");

                // If you want to convert the bytes to a Bitmap, you can do something like this:
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                Log.d("FirebaseStorage", "Bitmap Width: " + bitmap.getWidth());
                Log.d("FirebaseStorage", "Bitmap Height: " + bitmap.getHeight());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("FirebaseStorage", "EROORRRRR " + e.getMessage());
            }
        });*/

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
        foodCollection
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            for (QueryDocumentSnapshot foodInfo : querySnapshot) {
                                String foodTitle = foodInfo.getString("name");
                                String foodPrice = foodInfo.getString("price");
                                String foodTitleAdapted = adaptFoodTitle(foodTitle);
                                Bitmap foodImage = getImageFromStorage(foodTitleAdapted);

                                cards.add(new Data_Provider(foodTitle, foodPrice, foodImage));
                            }
                        } else {
                            System.out.println("Error getting the food collection");
                        }
                    }
                });;
    }

    public Bitmap getImageFromStorage(String foodURL){
        final Bitmap[] imgResource = {null};
        foodStorage.child(foodURL).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
               imgResource[0] = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                System.out.println("Error con las imágenes del Storage");
            }
        });
        return imgResource[0];
    }

    public String adaptFoodTitle(String foodTitle){
        String adapted = "";
        for (int i = 0; i <foodTitle.length(); i++) {
            if(foodTitle.charAt(i) != ' '){
                adapted += foodTitle.charAt(i);
            }else{
                adapted += "_";
            }
        }
        return adapted.toLowerCase()+".jpg";
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
        search_bar = (EditText)getView().findViewById(R.id.search_editText);
        set_Search_bar();

        recyclerView_sections = (RecyclerView) getView().findViewById(R.id.recyclerView_sections_id);
        //recyclerView_sections = binding.recyclerViewSectionsId; --> Esta línea es para usar el binding en vez de la sintaxis de arriba
        LinearLayoutManager rv_layoutManager1 = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_sections.setLayoutManager(rv_layoutManager1);
        recyclerView_sections.setAdapter(new Sections_adapter(sections));

        recyclerView_food = (RecyclerView) getView().findViewById(R.id.recyclerView_food_id2);
        LinearLayoutManager rv_layoutManager3 = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView_food.setLayoutManager(rv_layoutManager3);
        recyclerView_food.setAdapter(new Popular_food_adapter2(cards));
    }
}