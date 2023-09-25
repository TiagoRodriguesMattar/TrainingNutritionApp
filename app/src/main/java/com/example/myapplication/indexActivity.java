package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.myapplication.databinding.ActivityIndexBinding;
import com.example.myapplication.databinding.ActivityMainBinding;

public class indexActivity extends AppCompatActivity {

    ActivityIndexBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIndexBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new TreinoFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.treino) {
                replaceFragment(new TreinoFragment());
            } else if (item.getItemId() == R.id.nutricao) {
                replaceFragment(new NutricaoFragment());
            } else if (item.getItemId() == R.id.perfil) {
                replaceFragment(new PerfilFragment());
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}