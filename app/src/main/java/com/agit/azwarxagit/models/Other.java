package com.agit.azwarxagit.models;

import java.io.Serializable;

public class Other implements Serializable {
    private Home home;

    public Other(Home home) {
        this.home = home;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Others{" +
                "home=" + home +
                '}';
    }
}
