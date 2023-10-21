package com.example.myapplication;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Timer;
import java.util.TimerTask;

public class ConfigFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private final String channelID = "waterNotificationChannel";
    private Timer timer = null;

    public ConfigFragment() {
        // Required empty public constructor
    }

    public static ConfigFragment newInstance(String param1, String param2) {
        ConfigFragment fragment = new ConfigFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        Switch s = view.findViewById(R.id.switch_permission);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        boolean switchState = preferences.getBoolean("switch_state", false);

        s.setChecked(switchState);

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Salve o estado do Switch nas preferências compartilhadas
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch_state", s.isChecked());
                editor.apply();

                if (s.isChecked()) {
                    // Inicie o serviço para gerar notificações quando o switch estiver ativado
                    Intent serviceIntent = new Intent(requireContext(), NotificationService.class);
                    serviceIntent.setAction("start_notification");
                    requireContext().startService(serviceIntent);
                } else {
                    // Pare o serviço se o switch estiver desativado
                    Intent serviceIntent = new Intent(requireContext(), NotificationService.class);
                    serviceIntent.setAction("stop_notification");
                    requireContext().startService(serviceIntent);
                }
            }
        });
    }
}