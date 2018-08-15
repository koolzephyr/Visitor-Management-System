package com.model;

/**
 * Created by anons on 5/2/16.
 */
public class Person {
    private final int pId;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String address;
    private final String contact;
    private final String email;
    private final String type;

    public Person(PersonBuilder builder){
        this.pId=builder.pId;
        this.firstName=builder.firstName;
        this.middleName=builder.middleName;
        this.lastName=builder.lastName;
        this.username=builder.username;
        this.password=builder.password;
        this.address=builder.address;
        this.contact=builder.contact;
        this.email=builder.email;
        this.type=builder.type;
    }

    public int getpId() {
        return pId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static class PersonBuilder{
        private final int pId;
        private final String firstName;
        private String middleName;
        private final String lastName;
        private final String username;
        private final String password;
        private String address;
        private final String contact;
        private String email;
        private final String type;

        public PersonBuilder(int pId, String firstName, String lastName,
                             String username, String password, String contact, String type) {
            this.pId=pId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.username= username;
            this.password = password;
            this.contact = contact;
            this.type = type;
        }

        public PersonBuilder middleName(String middleName){
            this.middleName=middleName;
            return this;
        }

        public PersonBuilder address(String address){
            this.address=address;
            return this;
        }

        public PersonBuilder email(String email){
            this.email=email;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }

    public static Person getPerson(int pId, String firstName, String middleName, String lastName, String username,
                                   String password, String address, String contact, String email, String type){

        return new PersonBuilder(pId,firstName,lastName,username,password,contact,type)
                            .middleName(middleName)
                            .address(address)
                            .email(email)
                            .build();
    }

    @Override
    public String toString() {
        return "Person{" +
                "pId=" + pId +
                ", username='" + username + '\'' +
                ", contact='" + contact + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
