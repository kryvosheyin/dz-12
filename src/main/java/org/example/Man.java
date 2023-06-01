package org.example;

public class Man extends Person {
    public Man(String firstName, String lastNAme, int age, boolean married) {
        super(firstName, lastNAme, age, married);
    }

    @Override
    public boolean isRetired() {
        return this.age >=65;
    }

    public void listenMusic() {
        System.out.println("🎵🎶♬");
    }


    @Override
    public String toString() {
        return "Man{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", originalAge=" + originalAge +
                ", married=" + married +
                '}';
    }

    @Override
    public void printFavoriteThings() {
        System.out.println("As a Man I like beer, squash and bikes");
    }
}
