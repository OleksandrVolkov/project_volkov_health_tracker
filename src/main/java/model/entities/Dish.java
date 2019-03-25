package model.entities;

/**
 *  <h1>Dish class</h1>
 * Dish class represents the dish which user chooses from the given ones.
 * They are pre-set and there is no way to change it.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class Dish extends Entity implements Comparable <Dish>{
    /**
     * Id of the dish
     */
    protected int id;
    /**
     * Name of the dish
     */
    protected String name;
    /**
     * Nutrients of the dish
     */
    protected Nutrients nutrients;
    /**
     * Dish type id of the relative dish type of the current dish
     */
    protected int dishTypeId;

    /**
     * Empty constructor of the Dish class.
     */
    public Dish(){}

    /**
     * Constructor with 2 parameters of the Dish class.
     * @param name Name of the dish.
     * @param nutrients Nutrients of the dish.
     * @param dishTypeId Dish type id of the dish.
     */
    public Dish(String name, Nutrients nutrients, int dishTypeId){
        this.name = name;
        this.nutrients = nutrients;
        this.dishTypeId = dishTypeId;
    }

    /**
     * Constructor with 1 parameter of the Dish class.
     * @param name Name of the dish
     */
    public Dish(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nutrients getNutrients() {
        return nutrients;
    }

    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDishTypeId() {
        return dishTypeId;
    }

    public void setDishTypeId(int dishTypeId) {
        this.dishTypeId = dishTypeId;
    }

    /**
     * Overridden equals method from the superclass Object.
     * @return String The string representation of the instance.
     */
    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nutrients=" + nutrients +
                ", dishTypeId=" + dishTypeId +
                '}';
    }

    /**
     * Overridden equals method from the superclass Object.
     * @param o The relative Dish instance to compare with
     * @return boolean Whether objects are equivalent.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (id != dish.id) return false;
        if (dishTypeId != dish.dishTypeId) return false;
        if (!name.equals(dish.name)) return false;
        return nutrients.equals(dish.nutrients);
    }

    /**
     * Overridden hashCode method from the superclass Object.
     * @return int The hash code produced for the current instance
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + nutrients.hashCode();
        result = 31 * result + dishTypeId;
        return result;
    }


    @Override
    public int compareTo(Dish dish) {
        return dish.getName().compareTo(this.name);
    }
}
