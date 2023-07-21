package luisrrleal.com.foodapp_v1;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    //Never create more than one instance of DataStore for a given file in the same process.
    UserDataStore userDataStore;
    RxDataStore<Preferences> dataStoreRX;

    EditText name, phone, email, password, confirmPassword;
    ImageView getBack_button;

    FirebaseAuth userAuth;
    FirebaseFirestore document = FirebaseFirestore.getInstance();
    //DocumentReference document = FirebaseFirestore.getInstance().document("appData/users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUserDataStore();
        //FirebaseApp.initializeApp(RegisterActivity.this);
        userAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.userName);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password1);
        confirmPassword = (EditText) findViewById(R.id.password2);
        getBack_button = (ImageView) findViewById(R.id.profileImage);
        backToLogin();
    }

    public void registerUser(View v){
        String userName = name.getText().toString();
        String userPhone = phone.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userConfirmPassword = confirmPassword.getText().toString();
        
        if(userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || userConfirmPassword.isEmpty()){
            Toast.makeText(this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            //Si el email no ha sido registrado permite la creación del usuario
            if(!userDataRepeated(userEmail)){
                //Aquí haría falta poner una validación más compleja para que la contraseña no fuera 123456, o algo así
                if(userPassword.equals(userConfirmPassword)){
                    //Primero guarda el usuario en la parte de Authentication de Firebase
                    saveNewUser(userEmail, userPassword);
                    //Luego guarda la información en la base de datos NoSQL (FireStore)
                    saveData_inFirestore(userEmail, userPassword, userPhone, userName);
                }else{
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }else{
                //Si el email ya existe podráimos poner un textView que diga que el email ya está registrado
                Toast.makeText(this, "El email "+userEmail+" ya está registrado", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //Registra un nuevo usuario en la pestaña Authetication
    public void saveNewUser(String userEmail, String userPassword){
        userAuth.createUserWithEmailAndPassword(userEmail, userPassword)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "createUserWithEmail:success");
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }

    public void setUserDataStore(){
        userDataStore = UserDataStore.getInstance();
        if (userDataStore.getDataStore() == null) {
            dataStoreRX = new RxPreferenceDataStoreBuilder(this, "USER_DATA").build();
        } else {
            dataStoreRX = userDataStore.getDataStore();
        }
        userDataStore.setDataStore(dataStoreRX);
    }

    //Proceso paa guardar los datos en la base de datos, en la collection "users"
    public void saveData_inFirestore(String userEmail, String userPassword, String userPhone, String userName){
        Map<String, Object> user = new HashMap<>();
        user.put("name", userName);
        user.put("phone", userPhone);
        user.put("email", userEmail);
        user.put("password", userPassword);

        String email = (user.get("email").toString());
        String password = user.get("password").toString();
        userDataStore.putStringValue("USER_DATA", email+"/"+password);

        document.collection("users")
        .add(user)
        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(RegisterActivity.this, "Registrado con éxito", Toast.LENGTH_SHORT).show();

            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Nooooo", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    //Método que itera sobre los documentos guardados para comprobar que no se haya registrado un email igual, pero 
    //que yo me acuerde no funciona
    public boolean userDataRepeated(String email){

        //Lo declaré como un array porque así me lo cambió el IDE, yo lo había escrito así: boolean isRepeated = false;
        /* 
         * Chta GPT me dijo esto: 
         * La razón por la que se utiliza un array de booleanos en este caso particular es para poder acceder y modificar la variable dentro 
         * del OnCompleteListener, que es una clase anónima.
         * 
         * Esta solución con un array de booleanos en lugar de una variable booleana directa permite modificar la variable dentro de una clase 
         * anónima, ya que la variable debe ser efectivamente final para ser utilizada dentro del OnCompleteListener.
        */
        final boolean[] isRepeated = {false};

        //Solicita los datos de la colección de "users"
        document.collection("users")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            //El QuerySnapshot básicamente representa los resultados de los decomuentos obtenidos
            //a través de una consulta, entiendo que devuelve un array, aunque en este caso el array.
            //También es importante recordar que como es Java, hay que especificar al ".then" qué dato vamos a recibir,
            //por eso la sintaxis de "new OnCompleteListener<QuerySnapshot>()"

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String emailSaved = document.getString("email");
                        if(emailSaved.equals(email)){
                            isRepeated[0] = true;
                        }
                    }
                } else {
                    System.out.println("Problema iterando en los datos de 'users'");
                }
            }
        });
        return isRepeated[0];
    }
            
    //Para iniciar la activity de Login y matar la actual
    public void backToLogin(){
        getBack_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}