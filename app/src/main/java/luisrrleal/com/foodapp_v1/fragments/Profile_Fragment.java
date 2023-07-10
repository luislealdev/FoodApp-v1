package luisrrleal.com.foodapp_v1.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import luisrrleal.com.foodapp_v1.LoginActivity;
import luisrrleal.com.foodapp_v1.MainActivity;
import luisrrleal.com.foodapp_v1.R;

public class Profile_Fragment extends Fragment {
    FirebaseAuth auth;
    FirebaseUser user;
    Button logout;
    TextView userEmail;
    public Profile_Fragment() {
        // Required empty public constructor
    }

    public static Profile_Fragment newInstance() {
        Profile_Fragment fragment = new Profile_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user  = auth.getCurrentUser();
        logout = getView().findViewById(R.id.logout);
        userEmail = getView().findViewById(R.id.userEmail);
        checkUserSign();
    }

    public void checkUserSign(){
        if(user != null){
            //Hay un inicio de sesión
            userEmail.setText(user.getEmail());
        }else{
            //No hay un inicio de sesión
            Intent i = new Intent(this.getContext(), LoginActivity.class);
            startActivity(i);
            //Terminar la main activity
        }
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
}