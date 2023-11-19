package com.dmadev.task7.model;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "firstName")
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2,max=30,message="Name should be between 2 and 30 characters")
    private String firstName;

    @Column(name="lastName")
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2,max=30,message="Name should be between 2 and 30 characters")
    private String lastName;

    @Column(name="email")
    @NotEmpty(message = "Email should be valid")
    private String email;

    @Column(name="password")
    @Size(min=6,max=100,message="Password should be min 6 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    public User(String firstName, String lastName, String email, String password, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public String getGenderDisplayName(){
        return gender.getDisplayName();
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!Objects.equals(firstName, user.firstName)) return false;
        if (!Objects.equals(lastName, user.lastName)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(password, user.password)) return false;
        return gender == user.gender;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }
}
