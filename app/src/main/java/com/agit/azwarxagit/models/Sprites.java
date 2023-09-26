package com.agit.azwarxagit.models;

import java.io.Serializable;

public class Sprites implements Serializable {
    private Other other;

    public Sprites(Other other) {
        this.other = other;
    }

    public Other getOther() {
        return other;
    }

    public void setOther(Other other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Sprites{" +
                "other=" + other +
                '}';
    }
}
