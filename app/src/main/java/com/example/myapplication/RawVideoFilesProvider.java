package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RawVideoFilesProvider {

    public static List<MediaFiles> getRawVideoFiles(Context context) {
        List<MediaFiles> videoFilesList = new ArrayList<>();
        Resources resources = context.getResources();
        String packageName = context.getPackageName();

        // Lista de nomes dos arquivos de vídeo na pasta 'raw'
        String[] rawVideoNames = {"cstestecorte", "csteste1corte"};

        for (String rawVideoName : rawVideoNames) {
            int resId = resources.getIdentifier(rawVideoName, "raw", packageName);
            if (resId != 0) {
                String videoTitle = rawVideoName; // Use o nome do arquivo como título, você pode personalizar isso
                String videoDisplayName = rawVideoName;
                String videoPath = "android.resource://" + packageName + "/" + resId;

                MediaFiles mediaFiles = new MediaFiles("", videoTitle, videoDisplayName, videoPath);
                videoFilesList.add(mediaFiles);
            } else {
                Log.e("RawVideoFilesProvider", "Resource not found for: " + rawVideoName);
            }
        }

        return videoFilesList;
    }
}
