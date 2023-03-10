package com.example.onlinetestapp.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onlinetestapp.R;
import com.example.onlinetestapp.databinding.FragmentPhoneLoginBinding;

public class phoneLoginFragment extends Fragment {


    private FragmentPhoneLoginBinding binding;

    public phoneLoginFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentPhoneLoginBinding.inflate(inflater,container,false);

       binding.getOtp.setOnClickListener(v -> {

           String number = binding.mobileNumber.getText().toString();
           if (number.length() != 10){
               Toast.makeText(getContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();
               return;
           }
           NavDirections directions = phoneLoginFragmentDirections.actionPhoneLoginFragmentToOTPFragment(number);
           Navigation.findNavController(v).navigate(directions);
       });
       return binding.getRoot();
    }
}