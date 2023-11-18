package com.example.myapplication;

public class DataDesafioTreino {
    String title;
    String sub;
    String duration;
    String level;
    String muscleGroups;
    String restTime;
    int image;

    public DataDesafioTreino(String title, String sub, String duration, String level, String muscleGroups, String restTime, int image) {
        this.title = title;
        this.sub = sub;
        this.duration = duration;
        this.level = level;
        this.muscleGroups = muscleGroups;
        this.restTime = restTime;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(String muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public String getRestTime() {
        return restTime;
    }

    public void setRestTime(String title) {
        this.restTime = restTime;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
