package com.kchmurowicz.todolist.models;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    public final String string;

    private Role(String string){
        this.string=string;
    }


}
