package model.entities;

import model.entities.enums.Lifestyle;
import model.entities.enums.Sex;
/**
 *  <h1>User class</h1>
 * User class represents the relative entity to the application business logic.
 * It is the user of the application which actually uses it in order to calculate his/
 * her diet efficiency to stay healthy.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class User extends Entity{
    /*
        Id of the user
     */
    private Integer id;
    /*
      Name of the user
   */
    private String name;
    /*
      Surname of the user
   */
    private String surname;
    /*
      Username of the user
   */
    private String username;
    /*
      Password of the user
   */
    private String password;
    /*
      Email of the user
   */
    private String email;
    /*
      Sex of the user
   */
    private Sex sex;
    /*
      Age of the user
   */
    private int age;
    /*
      Height of the user
   */
    private double height;
    /*
      Weight of the user
   */
    private double weight;
    /*
      Lifestyle of the user
   */
    private Lifestyle lifestyle;

    /*
        No-arguments constructor
    */
    public User(){}

    /*
           <h1>User.Builder class</h1>
          Builder class to create instances of the User class.
          One of the GoF patterns.
    */
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

    /**
      *  Overriden toString method from the superclass Object.
      *  @return String The string representation of the instance.
     */
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

    /**
     * Overriden equals method from the superclass Object.
     * @param o The relative Nutrients instance to compare with
     * @return boolean Whether objects are equivalent.
     */
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

    /**
     * Overridden hashCode method from the superclass Object.
     * @return int The hash code produced for the current instance
     */
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
