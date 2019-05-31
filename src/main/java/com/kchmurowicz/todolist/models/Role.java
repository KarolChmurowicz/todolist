package com.kchmurowicz.todolist.models;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");
    public final String string;

     Role(String string){
        this.string=string;
    }

    public String getString() {
        return string;
    }
}
