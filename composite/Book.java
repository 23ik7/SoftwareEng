package composite;

public class Book extends Item{

    double price;
    String isbn;

    public Book(String name, double price, String isbn) {
        super(name);
        this.price = price;
        this.isbn = isbn;
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
