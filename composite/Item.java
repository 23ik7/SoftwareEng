package composite;


public abstract class Item {
    protected String name;

    public Item(String name) {
        this.name = name;
    }

    public  String getName() {
        return name;
    }

    public abstract double getPrice();

    public abstract Item findByName(String name);

    public boolean removeChild(String name) {
        return false;
    }

    public boolean changePrice(double price) {
        return false;
    }

}

