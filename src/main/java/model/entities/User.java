package model.entities;

import model.entities.enums.Lifestyle;
import model.entities.enums.Sex;

public class User extends Entity{
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private Sex sex;
    private int age;
    private double height;
    private double weight;
    private Lifestyle lifestyle;


    public User(){}


    public static class Builder {
        private User user;

        public Builder(){
            user = new User();
        }

        public Builder withName(String name){
            user.name = name;
            return this;
        }

        public Builder withSurname(String surname){
            user.surname = surname;
            return this;
        }

        public Builder withAge(int age){
            user.age = age;
            return this;
        }

        public Builder withHeight(double height){
            user.height = height;
            return this;
        }

        public Builder withWeight(double weight){
            user.weight = weight;
            return this;
        }

        public Builder withUsername(String username){
            user.username = username;
            return this;
        }

        public Builder withPassword(String password){
            user.password = password;
            return this;
        }

        public Builder withEmail(String email){
            user.email = email;
            return this;
        }

        public Builder withSex(Sex sex){
            user.sex = sex;
            return this;
        }

        public Builder withId(int id){
            user.id = id;
            return this;
        }

        public Builder withLifestyle(Lifestyle lifestyle){
            user.lifestyle = lifestyle;
            return this;
        }

        public User build(){
            return user;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", lifestyle=" + lifestyle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (Double.compare(user.height, height) != 0) return false;
        if (Double.compare(user.weight, weight) != 0) return false;
        if (!id.equals(user.id)) return false;
        if (!name.equals(user.name)) return false;
        if (!surname.equals(user.surname)) return false;
        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (sex != user.sex) return false;
        return lifestyle == user.lifestyle;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + sex.hashCode();
        result = 31 * result + age;
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + lifestyle.hashCode();
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Lifestyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(Lifestyle lifestyle) {
        this.lifestyle = lifestyle;
    }
}
