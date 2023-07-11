package luisrrleal.com.foodapp_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText password, email;
    TextView create_account;
    Button login;
    FirebaseAuth userAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userAuth = FirebaseAuth.getInstance();
        password = (EditText) findViewById(R.id.passwordLogin);
        email = (EditText) findViewById(R.id.userEmail);
        login = (Button) findViewById(R.id.login);
        create_account = (TextView) findViewById(R.id.create_account);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
        //loadFragment(Register_Fragment.newInstance());
    }

    public void login(View v){
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        userAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login exitoso.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(v.getContext(), MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    /*private void loadFragment(Fragment new_fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //transaction.replace(fragmentContainer.getId(), new_fragment);
        transaction.replace(R.id.fragmentContainerView, new_fragment);
        transaction.commit();
    }*/

}