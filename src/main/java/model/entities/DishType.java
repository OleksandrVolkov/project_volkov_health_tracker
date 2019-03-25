package model.entities;

import java.util.List;

/**
 *  <h1>DishType class</h1>
 * DishType class represents the dish type which is used to have a way to describe a type of the
 * relative dish in the application.
 * They are pre-set so there is no way to change it.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class DishType extends Entity{
    /**
     * Id of the dish type
     */
    protected int id;
    /**
     * Name of the dish type
     */
    protected String name;

    /**
     * Constructor of the DishType class
     * @param name
     */
    public DishType(String name){
        this.name = name;
    }


    public int getId() {
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

    /**
     * Overridden equals method from the superclass Object.
     * @return String The string representation of the instance.
     */
    @Override
    public String toString() {
        return "DishType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Overridden equals method from the superclass Object.
     * @param o The relative DishType instance to compare with
     * @return boolean Whether objects are equivalent.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishType dishType = (DishType) o;

        if (id != dishType.id) return false;
        return name.equals(dishType.name);
    }

    /**
     * Overridden hashCode method from the superclass Object.
     * @return int The hash code produced for the current instance
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
