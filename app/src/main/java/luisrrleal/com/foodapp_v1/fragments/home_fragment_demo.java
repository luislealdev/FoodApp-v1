package luisrrleal.com.foodapp_v1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import luisrrleal.com.foodapp_v1.R;

public class home_fragment_demo extends Fragment {

    public home_fragment_demo() {
        // Required empty public constructor
    }


    public static home_fragment_demo newInstance(String param1, String param2) {
        home_fragment_demo fragment = new home_fragment_demo();
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
        return inflater.inflate(R.layout.fragment_home_demo, container, false);

    }
}