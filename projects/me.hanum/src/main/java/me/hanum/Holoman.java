package me.hanum;

public class Holoman {
    String name;

    int howlong;

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

    @Override
    public String toString() {
        return "Holoman{" +
                "name='" + name + '\'' +
                ", howlong=" + howlong +
                '}';
    }
}
