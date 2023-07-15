package luisrrleal.com.foodapp_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

import luisrrleal.com.foodapp_v1.fragments.Profile_Fragment;

public class InfoFoodActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore firestore;
    CollectionReference collectionUser;
    //La referencia para que cuando encuentre la órden,
    CollectionReference userOrdersCollection;
    ImageView back_button, food_img;
    TextView food_title, food_price, food_decription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_food);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        collectionUser = firestore.collection("users");
        user  = auth.getCurrentUser();

        back_button = (ImageView) findViewById(R.id.back_button);
        food_img = (ImageView) findViewById(R.id.food_img_id);
        food_title = (TextView) findViewById(R.id.food_title_id);
        food_price = (TextView) findViewById(R.id.food_price_id);
        //food_decription = (TextView) findViewById(R.id.food_description_id);

        setEventsListeners();
        fillFoodInfo();
    }
    public void setEventsListeners(){
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(i);
            }
        });
    }
    public void fillFoodInfo(){
        Intent i = getIntent();
        String food_title_getted = i.getStringExtra("sentTitle");
        String food_price_getted = i.getStringExtra("sentPrice");
        int food_image_getted = i.getIntExtra("sentImg", 0);

        food_title.setText(food_title_getted);
        food_price.setText(food_price_getted);
        food_img.setImageResource(food_image_getted);
    }

    public void saveOrder(View view){
        collectionUser.whereEqualTo("email", user.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null) {
                                /*for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {
                                    String collection_userEmail = documentSnapshot.getString("email");
                                }*/
                                //Agarrar el primer elemento porque es el único documento que existe
                                //(es el usuario con el email del usuario autenticado en ese momento)
                                QueryDocumentSnapshot documentSnapshot = (QueryDocumentSnapshot) querySnapshot.getDocuments().get(0);
                                // Aquí ya tenemos la referencia a las órdenes del usuario, solo falta hacer un push del nuedo dato
                                userOrdersCollection = documentSnapshot.getReference().collection("orders");
                                pushNewOrder();
                            }
                        } else {
                            System.out.println("error para acceder al usuario");
                        }
                    }
                });
    }

    public void pushNewOrder(){
        Map<String, Object> newOrder = new HashMap<>();
        newOrder.put("title", "comida nombre");
        newOrder.put("price", "30");
        newOrder.put("timeStamp", "10am");
        newOrder.put("quantity", 1);
        userOrdersCollection
                .add(newOrder)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(InfoFoodActivity.this, "Órden guardada con éxito", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@androidx.annotation.NonNull Exception e) {
                        Toast.makeText(InfoFoodActivity.this, "No se guardó bien", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}