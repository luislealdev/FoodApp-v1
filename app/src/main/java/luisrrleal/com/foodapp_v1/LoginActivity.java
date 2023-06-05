package luisrrleal.com.foodapp_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import luisrrleal.com.foodapp_v1.fragments.Login_Fragment;
import luisrrleal.com.foodapp_v1.fragments.Register_Fragment;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadFragment(Register_Fragment.newInstance());

    }

    private void loadFragment(Fragment new_fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //transaction.replace(fragmentContainer.getId(), new_fragment);
        transaction.replace(R.id.fragmentContainerView, new_fragment);
        transaction.commit();
    }

}