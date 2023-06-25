package luisrrleal.com.foodapp_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import luisrrleal.com.foodapp_v1.fragments.Cart_Fragment;
import luisrrleal.com.foodapp_v1.fragments.Help_Fragment;
import luisrrleal.com.foodapp_v1.fragments.Home_Fragment;
import luisrrleal.com.foodapp_v1.fragments.Login_Fragment;
import luisrrleal.com.foodapp_v1.fragments.Profile_Fragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mainNav;

    Home_Fragment home_fragment = new Home_Fragment();
    Cart_Fragment cart_fragment = new Cart_Fragment();
    Profile_Fragment profile_fragment = new Profile_Fragment();
    Help_Fragment help_fragment = new Help_Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainNav = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_x, home_fragment).commit();
        mainNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_x, home_fragment).commit();
                    //loadFragment(Home_Fragment.newInstance());
                } else if(item.getItemId()==R.id.nav_cart){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_x, cart_fragment).commit();
                    //loadFragment(Cart_Fragment.newInstance());
                }else if(item.getItemId()==R.id.nav_profile){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_x, profile_fragment).commit();
                    //loadFragment(Profile_Fragment.newInstance());
                }else if(item.getItemId()==R.id.nav_help){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_x, help_fragment).commit();
                    //loadFragment(Help_Fragment.newInstance());
                }

                return false;
            }
        });
    }

}