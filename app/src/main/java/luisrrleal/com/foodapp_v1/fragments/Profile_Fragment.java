package luisrrleal.com.foodapp_v1.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import luisrrleal.com.foodapp_v1.Adapter.Sections_adapter;
import luisrrleal.com.foodapp_v1.LoginActivity;
import luisrrleal.com.foodapp_v1.MainActivity;
import luisrrleal.com.foodapp_v1.R;

public class Profile_Fragment extends Fragment {
    int contador = 0;

    //Para comunicarse con el servicio de Authentication
    FirebaseAuth auth;
    FirebaseUser user;

    //Para comunicarse con el servicio de FireStore
    FirebaseFirestore firestore;
    CollectionReference collectionUser;

    Button logout;
    TextView userEmail, userName;
    ImageView profileImage;

    public Profile_Fragment() {
        // Required empty public constructor
    }

    public static Profile_Fragment newInstance() {
        Profile_Fragment fragment = new Profile_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //En la documentación oficial inicilizaban estos objetos en el método onCreate
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        collectionUser = firestore.collection("users");
        user = auth.getCurrentUser();
    }

    public void checkUserSign(){
        if(user != null){
            //Si hay un inicio de sesión 
            getUserDataFromFirestore();
        }else{
            //Si no hay un inicio de sesión se regresa al Login, pero hay que revisar en que condiciones la Authenticación que se 
            //creó deja de ser valida, porque me pasó que salinedo de la aplicación y sin cerrar la ventana,
            //me pedía un inicio de sesión otra vez
            Intent i = new Intent(this.getContext(), LoginActivity.class);
            startActivity(i);
        }
    }

    //Esto también es una consulta que tomé de la documentación, con la diferencia de el where para hacer el flitrado. 
    //El propósito del método es buscar entre todos los usarios registrados en la colección, y agarrar los datos del que 
    //coincidan su email registrado, luego pasamos esos datos para que se visualicen en el Fragment. 

    //Tengo un conflicto con este método porque no proporciona una funcionalidad tan potente a la app, en cambio sí itera entre 
    //todos los usuarios registrados para encontrar una coincidencia, no creo que sea muy óptimo, sobre todo cuando la colección
    //tenga muchos registros. Además, creo que lo más apropiado sería buscar por un ID, pero como el ID generado en Authentication
    //es diferente al del registro del usuario en FireStore, no se cómo se haría algo así.
    public void getUserDataFromFirestore(){
        collectionUser.whereEqualTo("email", user.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //Este objeto representa el documento que obtuvo
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null) {
                                for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {
                                    System.out.println("Pasé aquí "+contador++);
                                    String collectoin_userName = documentSnapshot.getString("name");
                                    String collection_userEmail = documentSnapshot.getString("email");
                                    userName.setText(collectoin_userName);
                                    userEmail.setText(collection_userEmail);
                                    //Todavía no sé en que formato se guardará la imágen
                                    //String userImage = documentSnapshot.getString("image");

                                }
                            }
                        } else {
                            Toast.makeText(getContext(), "Problemas al acceder al usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void logoutUser(View v){
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        logout = (Button) getView().findViewById(R.id.logout);
        userEmail = (TextView) getView().findViewById(R.id.userEmail);
        userName = (TextView) getView().findViewById(R.id.userName);
        profileImage = (ImageView) getView().findViewById(R.id.profileImage);
        checkUserSign();
    }
}