package com.agit.azwarxagit.models;

import java.io.Serializable;

public class Abilities implements Serializable {
    private Ability ability;
    private String is_hidden;
    private String slot;

    public Abilities(Ability ability, String is_hidden, String slot) {
        this.ability = ability;
        this.is_hidden = is_hidden;
        this.slot = slot;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public String getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(String is_hidden) {
        this.is_hidden = is_hidden;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return "Abilitys{" +
                "ability=" + ability +
                ", is_hidden='" + is_hidden + '\'' +
                ", slot='" + slot + '\'' +
                '}';
    }
}
