package model.entities;

/**
 *  <h1>CustomDish class</h1>
 * CustomDish class represents the csutom dish which user adds and then chooses from the given ones.
 * They are not pre-set and user can add ones.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class CustomDish extends Dish {
    /**
     * User id of the custom dish.
     */
    protected int userId;

    /**
     * Empty constructor of the CustomDish class.
     */
    public CustomDish(){}

    /**
     * Constructor with 4 parameters of the CustomDish class.
     * @param name Name of the custom dish.
     * @param nutrients Nutrients of the custom dish.
     * @param userId User id of the custom dish.
     * @param dishTypeId DishTypeId of the custom dish.
     */
    public CustomDish(String name, Nutrients nutrients, int userId, int dishTypeId){
        super(name, nutrients, dishTypeId);
        this.userId = userId;
    }

    /**
     * Constructor with 1 parameter of the CustomDish class.
     * @param name Name of the custom dish
     */
    public CustomDish(String name){
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Overridden equals method from the superclass Object.
     * @param o The relative CustomDish instance to compare with
     * @return boolean Whether objects are equivalent.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CustomDish that = (CustomDish) o;

        return userId == that.userId;
    }

    /**
     * Overridden hashCode method from the superclass Object.
     * @return int The hash code produced for the current instance
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + userId;
        return result;
    }

    /**
     * Overridden equals method from the superclass Object.
     * @return String The string representation of the instance.
     */
    @Override
    public String toString() {
        return "CustomDish{" +
                "userId=" + userId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", nutrients=" + nutrients +
                ", dishTypeId=" + dishTypeId +
                '}';
    }
}


