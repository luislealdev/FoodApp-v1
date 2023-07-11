package luisrrleal.com.foodapp_v1;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText name, phone, email, password, confirmPassword;
    ImageView getBack_button;
    FirebaseAuth userAuth;
    FirebaseFirestore document = FirebaseFirestore.getInstance();
    //DocumentReference document = FirebaseFirestore.getInstance().document("appData/users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password1);
        confirmPassword = (EditText) findViewById(R.id.password2);
        getBack_button = (ImageView) findViewById(R.id.getBack_image);
        backToLogin();
    }

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
    public void registerUser(View v){
        String userName = name.getText().toString();
        String userPhone = phone.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userConfirmPassword = confirmPassword.getText().toString();

        if(userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || userConfirmPassword.isEmpty()){
            Toast.makeText(this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if(!userDataRepeated(userEmail)){
                //Si el email no ha sido registrado permite la creación del usuario
                if(userPassword.equals(userConfirmPassword)){
                    saveNewUser(userEmail, userPassword);
                    saveData_inFirestore(userEmail, userPassword, userPhone, userName);
                }else{
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }else{
                //Si el email ya existe vamos a poner un textView que diga que el email ya está registrado
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
                            Toast.makeText(RegisterActivity.this, "Authentication succeed.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //Proceso paa guardar los datos en la base de datos, en la colleción "users"
    public void saveData_inFirestore(String userEmail, String userPassword, String userPhone, String userName){
        Map<String, Object> user = new HashMap<>();
        user.put("name", userName);
        user.put("phone", userPhone);
        user.put("email", userEmail);
        user.put("password", userPassword);

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
                });;
    }

    //Método que verifica si ese email no ha sido utilizado
    public boolean userDataRepeated(String email){

        final boolean[] isRepeated = {false};
        //Solicita los datos de la colección de "users"
        document.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //Itera sobre los datos guardados en el documento "users"
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
}