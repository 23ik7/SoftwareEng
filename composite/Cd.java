package composite;

public class Cd extends Item{

    double price;

    public Cd(String name, double price) {
        super(name);
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public Item findByName(String name) {
        if (this.name.equals(name)) {
            return this;
        }
        return null;
    }

    @Override
    public boolean changePrice(double price) {
        if (price < 0) {
            return false;
        }
        this.price = price;
        return true;
    }

}
