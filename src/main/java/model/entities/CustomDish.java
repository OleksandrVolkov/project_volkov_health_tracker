package model.entities;

public class CustomDish extends Dish {
    protected int userId;
    public CustomDish(){}

    public CustomDish(String name, Nutrients nutrients, int userId, int dishTypeId){
        super(name, nutrients, dishTypeId);
        this.userId = userId;
    }
    public CustomDish(String name){
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CustomDish that = (CustomDish) o;

        return userId == that.userId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + userId;
        return result;
    }

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


