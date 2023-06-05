package luisrrleal.com.foodapp_v1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import luisrrleal.com.foodapp_v1.R;

public class Login_Fragment extends Fragment {
    private TextView create_account;
    private Button login;
    public Login_Fragment() {
        // Required empty public constructor
    }

    public static Login_Fragment newInstance() {
        Login_Fragment fragment = new Login_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        login.setOnClickListener(e->{
            System.out.println("Hello");

            // Create new fragment and transaction
            Fragment newFragment = new Register_Fragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
            transaction.replace(R.id.fragmentContainerView, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();
        });
        create_account = getView().findViewById(R.id.create_account);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}