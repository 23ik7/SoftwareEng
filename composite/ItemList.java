package composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemList extends Item{
    private List<Item> children = new ArrayList<>();

    public ItemList(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        double sum = 0;
        for (Item child : children) {
            sum += child.getPrice();
        }
        return sum;
    }

    public void add(Item item) {
        children.add(item);
    }

    @Override
    public Item findByName(String name) {
        if(this.name.equals(name)) {
            return this;
        }
        for (Item child : children) {
            Item found = child.findByName(name);
            if(found != null) {
                return found;
            }
        }
        return null;
    }

    public boolean removeChild(String name) {
        for (Iterator<Item> itemIterator = children.iterator(); itemIterator.hasNext();) {
            Item item = itemIterator.next();

            if (item.getName().equals(name)) {
                itemIterator.remove();
                return true;
            }
            if (item.removeChild(name)) {
                return true;
            }
        }
        return false;
    }


}
