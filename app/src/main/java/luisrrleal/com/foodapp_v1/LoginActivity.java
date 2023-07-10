package luisrrleal.com.foodapp_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText password, email;
    TextView create_account;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        login = (Button) findViewById(R.id.login);
        create_account = (TextView) findViewById(R.id.create_account);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
        //loadFragment(Register_Fragment.newInstance());
    }

    public void createUser(){

    }
    /*private void loadFragment(Fragment new_fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //transaction.replace(fragmentContainer.getId(), new_fragment);
        transaction.replace(R.id.fragmentContainerView, new_fragment);
        transaction.commit();
    }*/

}