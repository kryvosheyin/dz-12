package org.example;

public class Woman extends Person {
    private String originalLastName;

    public Woman(String firstName, String lastNAme, int age, boolean married) {
        super(firstName, lastNAme, age, married);
        this.originalLastName = lastNAme;
    }

    public Woman(String firstName, String lastName, int age, boolean married, Person partner) {
        super(firstName, lastName, age, married);
        this.registerPartner(partner);
        this.originalLastName = lastName;
        this.lastName = partner.lastName;
    }

    public String resetLastName() {
        return originalLastName;
    }

    public void setOriginalLastName(String name) {
        this.originalLastName = name;
    }

    public String getOriginalLastName() {
        return this.originalLastName;
    }

    @Override
    public void printFavoriteThings() {
        System.out.println("As a Woman I like to dance and draw");
    }

    @Override
    public void registerPartnership(Person spouse) {
        if (this.canGetMarried(spouse)) {
            registerPartner(spouse);
            this.originalLastName = this.lastName;
            this.lastName = spouse.lastName;
        } else {
            System.out.println("You or the other person is already married");
        }
    }

    @Override
    public String toString() {
        return "Woman{" +
                "originalLastName='" + originalLastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastNAme='" + lastName + '\'' +
                ", age=" + age +
                ", originalAge=" + originalAge +
                ", married=" + married +
                ", partner=" + partner +
                '}';
    }

    @Override
    public void deregisterPartnership() {
        if (this.married) {
            this.married = false;
            this.lastName = originalLastName;
            this.partner.married = false;
            this.partner.partner = null;
            this.partner = null;
        } else {
            System.out.println("You are not married, and can deregister the marriage");
        }
    }

    public void printHumanProperties() {
        super.printHumanProperties();
        System.out.println("Old last name: " + this.getOriginalLastName());
    }
}
