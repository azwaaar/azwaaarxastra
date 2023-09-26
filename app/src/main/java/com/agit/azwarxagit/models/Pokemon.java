package com.agit.azwarxagit.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "pokemon")
public class Pokemon implements Serializable {

    @PrimaryKey()
    private Integer id;
    private String name;
    private String url;

    public Pokemon() {

    }

    public Pokemon(Integer id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
