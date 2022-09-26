package com.inmortal.messenger.model;

public class MyChatss_ClearModel {

    String text_char;
    String text_clear_name;
    String text_mb1;
    Boolean isChecked;

    public MyChatss_ClearModel(String text_char, String text_clear_name, String text_mb1,Boolean isChecked) {
        this.text_char = text_char;
        this.text_clear_name = text_clear_name;
        this.text_mb1 = text_mb1;
        this.isChecked = isChecked;
    }



    public String getText_char() {
        return text_char;
    }

    public void setText_char(String text_char) {
        this.text_char = text_char;
    }

    public String getText_clear_name() {
        return text_clear_name;
    }

    public void setText_clear_name(String text_clear_name) {
        this.text_clear_name = text_clear_name;
    }

    public String getText_mb1() {
        return text_mb1;
    }

    public void setText_mb1(String text_mb1) {
        this.text_mb1 = text_mb1;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
