package luisrrleal.com.foodapp_v1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import luisrrleal.com.foodapp_v1.R;
public class Register_Fragment extends Fragment {

    public Register_Fragment() {
        // Required empty public constructor
    }

    public static Register_Fragment newInstance() {
        Register_Fragment fragment = new Register_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_, container, false);
    }
}