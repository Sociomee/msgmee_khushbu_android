package com.inmortal.messenger.model;

 public class LanguageModel {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

     private String id;
     private String name;
     private String iconURL;


     public LanguageModel(String id, String name, String iconURL)
     {
         this.id = id;
         this.name = name;
         this.iconURL = iconURL;
     }
}
