package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ConfigFragment extends Fragment {
    private List<Timer> timers = new ArrayList<>();
    private List<TimerTask> timerTasks = new ArrayList<>();
    private  List<String> nomes = new ArrayList<>();
    private ArrayList<Integer> horas = new ArrayList<>();
    private Button add_remedios,remove_remedios;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private NumberPicker numberPickerNumber;
    private EditText nome_remedio;
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
        View view = inflater.inflate(R.layout.fragment_config, container, false);
        add_remedios = view.findViewById(R.id.button_remedios);
        remove_remedios = view.findViewById(R.id.button_remove_remedios);
        numberPickerNumber = view.findViewById(R.id.numberpicker);
        nome_remedio = view.findViewById(R.id.nome_remedio);
        numberPickerNumber.setMaxValue(24);
        numberPickerNumber.setMinValue(1);
        add_remedios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valorNumberPicker = numberPickerNumber.getValue();
                horas.add(valorNumberPicker);
                String texto = nome_remedio.getText().toString();
                nomes.add(texto);
                Log.d("CREATION", texto);
            }
        });
        remove_remedios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<nomes.size();i++){
                    String texto = nome_remedio.getText().toString();
                    Log.d("CREATION","Nome: " + texto);
                    Log.d("CREATION","Nome: " + nomes.get(i));
                    try{
                        if(nomes.contains(texto)){
                            nomes.remove(i);
                            if(!timers.isEmpty()){
                                if(timers.size() >= i){
                                    timers.remove(i);
                                }
                            }
                        }
                    }
                    catch(Exception e){
                        Log.d("CREATION","Erro: " + e);
                    }

                }
                for(String nome : nomes) {
                    Log.d("CREATION","Nomes: " + nome);
                }
            }
        });

        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        /*Button waterNotificationBtn = view.findViewById(R.id.water_notification);
        waterNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        showNotification();
                    }
                };
                // 30 segundos entre cada notificação
                timer.schedule(timerTask, 0, 30000);
            }
        });

        Button waterNotificationBtnCancel = view.findViewById(R.id.water_notification_cancel);
        waterNotificationBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                } else {
                    Toast.makeText(requireActivity(),"Notificações Já Desativadas.",Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        Switch s = view.findViewById(R.id.switch_permission);
        Switch s_remedios = view.findViewById(R.id.switch_permission_remedios);



        // Recupera o estado do Switch das preferências compartilhadas
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        boolean switchState = preferences.getBoolean("switch_state", false);



        s.setChecked(switchState);
        s_remedios.setChecked(switchState);

        s_remedios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nomes.size() != 0) {
                    for (int i = 0; i < nomes.size(); i++) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("switch_state", s_remedios.isChecked());
                        editor.apply();
                        if( s_remedios.isChecked()){
                        final int index = i;
                        Timer timer = new Timer();
                        timers.add(timer);
                        TimerTask timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                notification_remedio(nomes.get(index), "Notificação - Hora de tomar o remédio " + nomes.get(index) + "!",
                                        "Não se esqueça de tomar seu remédio " + nomes.get(index) + " a cada " + horas.get(index) + " horas!", index);
                            }
                        };
                        int delay = (horas.get(i) * 10000); // Converte horas para milissegundos
                        Log.d("CREATION", "Delay:" + delay);
                        timer.schedule(timerTask, delay, delay);
                    }
                        else {
                            if (timers.size() != 0) {
                                for(Timer aux : timers) {
                                    aux.cancel();
                                }
                            }
                        }
                    }
                }
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int texto : horas){
                    Log.d("CREATION", "Numero: "+texto);
                }
                // Salva o estado do Switch nas preferências compartilhadas
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch_state", s.isChecked());
                editor.apply();

                if (s.isChecked()) {
                    timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            showNotification();
                        }
                    };
                    // 30 segundos entre cada notificação
                    timer.schedule(timerTask, 0, 30000);
                } else {
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                }
            }
        });

    }



    public void showNotification() {
        //Intent intent = new Intent(getContext(), indexActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), channelID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Aguinha Já! Hidrate-se")
                .setContentText("Sua Saúde Importa!!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("É hora de se hidratar. Não importa o quão ocupado você esteja, tire um momento agora para" +
                                " beber um copo de água. Sua saúde e bem-estar agradecem. A água é a fonte da vida, vital para a " +
                                "digestão, circulação, absorção de nutrientes e manutenção da temperatura corporal adequada."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                //.setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelID);
            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelID, "description", importance);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        notificationManager.notify(0, builder.build());
    }

    public void notification_remedio(String Title, String Content, String text,int id){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), channelID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(Title)
                .setContentText(Content)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                //.setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelID);
            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelID, "description", importance);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        notificationManager.notify(id, builder.build());
    }
}