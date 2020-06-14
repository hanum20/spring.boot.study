package me.hanum;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("holoman")
public class HolomanProperties {
    private String name;
    private int howlong;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowlong() {
        return howlong;
    }

    public void setHowlong(int howlong) {
        this.howlong = howlong;
    }
}
