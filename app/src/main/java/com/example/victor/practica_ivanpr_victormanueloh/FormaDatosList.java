package com.example.victor.practica_ivanpr_victormanueloh;

/**
 * Created by IvPR on 22/11/2015.
 */
public class FormaDatosList {
    private String title;
    private String description;
    private int icon;

    public FormaDatosList(String t, String d, int img){
        title=t;
        description=d;
        icon=img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIcon(){
        return icon;
    }

    public void setIcon(int icon){
        this.icon=icon;
    }
}
