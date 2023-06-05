package luisrrleal.com.foodapp_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TO SEE HOME PAGE
        setContentView(R.layout.activity_main);
        //TO SEE LOGIN (AND REGISTER)
        //setContentView(R.layout.activity_login);
    }
}