package luisrrleal.com.foodapp_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import luisrrleal.com.foodapp_v1.fragments.Cart_Fragment;
import luisrrleal.com.foodapp_v1.fragments.Help_Fragment;
import luisrrleal.com.foodapp_v1.fragments.Home_Fragment;
import luisrrleal.com.foodapp_v1.fragments.Login_Fragment;
import luisrrleal.com.foodapp_v1.fragments.Profile_Fragment;

public class MainActivity extends AppCompatActivity {


    Home_Fragment home_fragment;
    Login_Fragment login_fragment;
    Profile_Fragment profile_fragment;
    Cart_Fragment cart_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TO SEE HOME PAGE
        setContentView(R.layout.activity_main);
        //TO SEE LOGIN (AND REGISTER)
        //setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //LOAD FRAGMENTS DEPENDING ON WHAT MENU OPTION ARE
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.nav_home){
            //Send user as parameter
            //loadFragment(Home_Fragment.newInstance(user));
            //For testing
            loadFragment(Home_Fragment.newInstance());
        } else if(item.getItemId()==R.id.nav_cart){
            loadFragment(Cart_Fragment.newInstance());
        }else if(item.getItemId()==R.id.nav_profile){
            loadFragment(Profile_Fragment.newInstance());
        }else if(item.getItemId()==R.id.nav_help){
            loadFragment(Help_Fragment.newInstance());
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadFragment(Fragment new_fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //transaction.replace(fragmentContainer.getId(), new_fragment);
        transaction.replace(R.id.home_fragmentcontainer, new_fragment);
        transaction.commit();
    }
}