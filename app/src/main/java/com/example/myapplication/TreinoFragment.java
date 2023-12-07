package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TreinoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TreinoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TreinoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TreinoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TreinoFragment newInstance(String param1, String param2) {
        TreinoFragment fragment = new TreinoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_treino, container, false);
    }

    public void exerciseLibraryBtn(View view) {
        String ExerciseLib = "Biblioteca de vídeos";
        Intent i = new Intent(getActivity(), VideoFilesActivity.class);
        i.putExtra("ExerciseLibrary", ExerciseLib);
        startActivity(i);
        //Intent i = new Intent(getActivity(), ExerciseLibraryActivity.class);
        //startActivity(i);
    }

    public void desafioTreino(View view) {
        Intent i = new Intent(getActivity(), DesafioTreinoActivity.class);
        startActivity(i);
    }

    public void persoTreino(View view) {
        try{
        Intent i = new Intent(getActivity(), TreinoPersonalizadoActivity.class);
        startActivity(i);
    }catch(Exception e){
            Log.d("CREATION", "Erro: " + e);
        }
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button exerciseLibraryButton = view.findViewById(R.id.exerciseLibrary);
        Button desafioTreinoButton = view.findViewById(R.id.DesafioTreino);
        Button treinoPersonalizado = view.findViewById(R.id.TreinoPersonalizado);
        exerciseLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseLibraryBtn(v);
            }
        });

        desafioTreinoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desafioTreino(v);
            }
        });
        treinoPersonalizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persoTreino(v);
            }
        });
    }
}