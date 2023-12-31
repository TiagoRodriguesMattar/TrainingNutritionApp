package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    public void imcDataBtn(View view) {
        Intent i = new Intent(getActivity(), ImcDataActivity.class);
        startActivity(i);
    }

    public void monCalbtn(View view) {
        Intent i = new Intent(getActivity(), MonitoramentoCaloriasActivity.class);
        startActivity(i);
    }

    public void acPesobtn(View view) {
        Intent i = new Intent(getActivity(), AcompanhamentoPesoActivity.class);
        startActivity(i);
    }

    public void acProg(View view) {
        Intent i = new Intent(getActivity(), AcoProgressoActivity.class);
        startActivity(i);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

        Button imcCalculatorBtn = view.findViewById(R.id.imcCalculator);
        Button monitoramentoCalBtn = view.findViewById(R.id.monitoramentoCalorias);
        Button acompanhamentoPeso = view.findViewById(R.id.acompanhamentoPeso);
        Button acompanhamentoProg = view.findViewById(R.id.acompanhamentoProg);
        imcCalculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imcDataBtn(v);
            }
        });

        monitoramentoCalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monCalbtn(v);
            }
        });

        acompanhamentoPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acPesobtn(v);
            }
        });

        acompanhamentoProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acProg(v);
            }
        });
    }
}