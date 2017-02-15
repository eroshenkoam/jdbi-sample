package io.qameta.samples;

import io.qameta.samples.jdbi.Column;

public class User2 {

    private int id;

    @Column("name")
    private String fullName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


}
