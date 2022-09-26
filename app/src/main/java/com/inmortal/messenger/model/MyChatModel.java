package com.inmortal.messenger.model;

public class MyChatModel {
    String text_character;
    String text_name;
    String text_mb;
    Boolean isChecked;


    public String getText_character() {
        return text_character;
    }

    public void setText_character(String text_character) {
        this.text_character = text_character;
    }

    public String getText_name() {
        return text_name;
    }

    public void setText_name(String text_name) {
        this.text_name = text_name;
    }

    public String getText_mb() {
        return text_mb;
    }

    public void setText_mb(String text_mb) {
        this.text_mb = text_mb;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }


    public MyChatModel(String text_character, String text_name, String text_mb, Boolean isChecked) {
        this.text_character = text_character;
        this.text_name = text_name;
        this.text_mb = text_mb;
        this.isChecked = isChecked;
    }
}
