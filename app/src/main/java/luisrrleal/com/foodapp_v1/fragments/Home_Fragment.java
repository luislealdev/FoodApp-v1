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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Los códigos que se usan para comunicar FireBase y la aplicación son la gran mayoría un copia y pega de la documentación
//oficial de Google, ajustando algunas cosas para que cumpla algunas necesidades específicas 

//Es importante saber que la instancia de FirebaseStorage nos proporciona acceso al storage, donde se pretende almacenar los
//archivos de imágen, en cambio, FirebaseFirestore hace refrencia a otro servicio que es el de almacenamiento en una base de 
//datos tipo noSQL, ahí es donde se escriben de los usuarios que se reigistran, y donde se pretende guardar las órdenes hechas
//

public class Home_Fragment extends Fragment{
    //Consumiendo del Storage de FireBase. 

    //El storage toma servirá para descargar desde nuestro Cloud Storage, el getInstance sirve para retornar ese Storage 
    //que previamente se inicilaiza con una instancia de FirebaseApp, pero no ay necesidad de hacer una instancia de esta clase
    //porque ya sabe que nos referimos a nuestra app sin necesdiad de especificarlo (se logra gracias a las configuraciones del SDK)
    FirebaseStorage storage = FirebaseStorage.getInstance(); 

    //Este objeto no sproporcionará la manera de representar el Storage qu esolicitamos, además de incluir los métodos necesarions
    //para solicitar o setear nuevos datos
    StorageReference storageRef = storage.getReference();
    
    FirebaseFirestore firestore;
    CollectionReference foodReference;

    private EditText search_bar;
    private RecyclerView recyclerView_food;
    private RecyclerView recyclerView_sections;

    ArrayList<Data_Provider> sections = new ArrayList<>();
    ArrayList<Data_Provider> cards = new ArrayList<>();

    //Esta arrayList lo ocupamos para guardar las cards filtradas por el buscador
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
        //Esta ruta debe cambiar dinámicamente de acuerdo con el evento que se genere al hacer click en x section
        foodReference = firestore.collection("food/mealsID/mealsData");
        fill_cards_info();
        fill_section_info();
    }

    //Método en el que se agrega un listener al search_bar, el método implementado requiere que le paemos un tipo de 
    //clase abstracta el cual nos proporciona los métodos para manejar el evento. 
    //Yo lo veo como un equivalente a usar el onChange para actualizar el estado en React, solo que nuestra variable
    //que recibe las cards filtradas, actualiza todo el Adapter que contiene el RecyclerView
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

    //Aquí lo que hice fue copiar y pegar de la documentación de FireBase, el método get obtiene la información y el addOnCompleteListener
    //nos ayuda a manejar peticiones asíncronas. El QuerySnapshot es ese tipo de dato que devuelve FireBase cunado hacemos una 
    //petición de los datos, representa nuestras colleción de comida/meals guardada 
    public void fill_cards_info(){
        foodReference
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            for (QueryDocumentSnapshot foodInfo : querySnapshot) {
                                String foodTitle = foodInfo.getString("name");
                                String foodTitleAdapted = adaptFoodTitle(foodTitle);
                                String foodPrice = foodInfo.getString("price");

                                //NOTA: para que esto funcione, el documento debe tener el mismo nombre que el título de la comida
                                StorageReference imgReference = storageRef.child("food_images/meals/"+foodTitleAdapted);
                                Bitmap foodImage = getImgResourceFromFireBaseStorage(imgReference);
                                cards.add(new Data_Provider(
                                        foodTitle, foodPrice,foodImage
                                ));
                            }
                        } else {
                            //No sé porqué se daría un error, pero si es por coneixón a internet, podemos hacer que se renderize
                            //un componente que lo indique
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });;
    }

    //Este es el método que separa la lógica de consumir del storage, aquí ya tenemos la referencia al archivo en firebase
    //El métood getBytes descarga asincrónicamente un objeto que es la imágen, después lo convierte a un array de bytes, 
    //el parámetro indicará el tamaño máximo de bytes que se peuden almacenar.

    //Los addOnSuccessListener/addOnFailureListener son los métodos para manejar la respuesta de un callback, creo que es el 
    //equivalente a un .then o .catch cuando usamos fetch
    public Bitmap getImgResourceFromFireBaseStorage(StorageReference imgReference){
        final Bitmap[] bitmap = new Bitmap[1];
        imgReference.getBytes(Long.MAX_VALUE)
        .addOnSuccessListener(
            new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    //El código anterior maneja la respuesta como .then, al final nos quedamos con un array de bytes
                    //, para convertirlos a un BitMap como tal, es necesario emplear el método siguiente, al cual le pasamos:
                    //el array de bytes, el índice donde empieza a decodificar, y el índice donde termina a decodificar
                    bitmap[0] = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                }
            }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                
            }
        });
        return bitmap[0];
    }

    //Método para pasar el formato del título del platillo que aparece en la card a lowerCase y con guiones bajos
    //Ese formato es como se planea estén guardados los nombres en jpg, así podremos consumir las imágenes 
    public String adaptFoodTitle(String foodTitle){
        String adapted = "";
        for (int i = 0; i <foodTitle.length(); i++) {
            if(foodTitle.charAt(i) != ' '){
                adapted += foodTitle.charAt(i);
            }else{
                adapted += "_";
            }
        }
        return adapted.toLowerCase();
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
        //Get the recycelrView of sections and render it in the fragment
        recyclerView_sections = (RecyclerView) getView().findViewById(R.id.recyclerView_sections_id);
        LinearLayoutManager rv_layoutManager1 = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_sections.setLayoutManager(rv_layoutManager1);
        recyclerView_sections.setAdapter(new Sections_adapter(sections));

        //Este es el RV que tenía las cards más pequeñas y estaba en horizontal, pero lo comenté tanto en el layout del 
        //xml y aquí porque no pude reoslver el problema del render que no se veía fluido

        //Get the recycelrView of popular food and render it in the fragment
        /*recyclerView_food = (RecyclerView) getView().findViewById(R.id.recyclerView_food_id);
        LinearLayoutManager rv_layoutManager2 = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_food.setLayoutManager(rv_layoutManager2);
        recyclerView_food.setAdapter(new Popular_food_adapter(cards));*/

        //Get the recycelrView of popular food and render it in the fragment
        search_bar = (EditText)getView().findViewById(R.id.search_editText);
        set_Search_bar();
        recyclerView_food = (RecyclerView) getView().findViewById(R.id.recyclerView_food_id2);
        LinearLayoutManager rv_layoutManager3 = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView_food.setLayoutManager(rv_layoutManager3);
        recyclerView_food.setAdapter(new Popular_food_adapter2(cards));
    }
}