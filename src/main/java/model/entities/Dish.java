package model.entities;

public class Dish implements Comparable <Dish>{
    protected int id;
    protected String name;
    protected Nutrients nutrients;
    protected int dishTypeId;

    public Dish(){}

    public Dish(String name, Nutrients nutrients, int dishTypeId){
        this.name = name;
        this.nutrients = nutrients;
        this.dishTypeId = dishTypeId;
    }
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

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nutrients=" + nutrients +
                ", dishTypeId=" + dishTypeId +
                '}';
    }

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
