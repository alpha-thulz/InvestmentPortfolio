package za.co.tyaphile.investmentportfolio.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

/**
 * Investor Entity, used with JPA to automatically make connections to the Database
 */
@Entity
public class Investor {

    @Id
    private String id;  // Database ID, unique ID generated
    private String name;
    private String address;
    private String contact;
    private int age;

    public Investor() {}

    public Investor(String name, String address, String contact, int age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.age = age;

    }

    public Investor(String id, String name, String address, String contact, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
