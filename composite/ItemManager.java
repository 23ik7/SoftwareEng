package composite;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * Class for managing items.
 *
 */

public class ItemManager {
	private Item root;

	public ItemManager() {
		this.root = new ItemList("root");
	}

	/**
	 * Reads the xml data from the input stream or throws an Exception if anything
	 * goes wrong (e.g., the xml code is invalid or some price attribute cannot be converted to type double).
	 * Items are assumed to have a unique name.
	 * The input stream is allowed to have empty lists.
	 * This is the only method where xml is handled in this assignment. No other method in this class should contain (or call)
	 * xml-specific code.
	 */
	public void readXml(InputStream xmlData) throws Exception{
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlData);
			Element rootElement = document.getDocumentElement();
			this.root = parseElement(rootElement);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	/**
	 * Returns an {@code Optional} instance representing the price of the item (cd,
	 * book, or list) with the given name; the {@code Optional} is empty, if
	 * no such item exists
	 */
	public Optional<Double> getPrice(String item){
		Item res = root.findByName(item);
		if (res != null) {
			return Optional.of(res.getPrice());
		}
		return Optional.empty();
	}

	/**
	 * removes the item with the given name, unless it's the root
	 * returns true if the operation succeeded (item found and removed)
	 * returns false if item is the root, or it is not found, or it cannot be removed due to some other error
	 */
	public boolean removeItem(String item) {
		if ("root".equals(item)) {
			return false;
		}
		return root.removeChild(item);
	}

	/**
	 * changes the price of the item with the given name;
	 * returns false if item is not found or it is a list, or if the price is smaller than or equal to 0,
	 * returns false if the price could not be changed for any other reason;
	 * returns true if the price was changed successfully (i.e., the item had a different price before);
	 */
	public boolean changePrice(String item, double price) {
		Item res = root.findByName(item);
		if (res == null) {
			return false;
		}
		return res.changePrice(price);
	}

	private Item parseElement(Element element) {
		String name = element.getAttribute("name");

		// If the element is a list
		if ("list".equals(element.getTagName())) {
			ItemList list = new ItemList(name);

			// Process child nodes
			NodeList children = element.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node node = children.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element childElement = (Element) node;
					list.add(parseElement(childElement));
				}
			}
			return list;
		}

		// If the element is a single item (book or cd)
		double price = Double.parseDouble(element.getAttribute("price"));
		if ("book".equals(element.getTagName())) {
			return new Book(name, price, element.getAttribute("isbn"));
		} else if ("cd".equals(element.getTagName())) {
			return new Cd(name, price);
		}

		throw new IllegalArgumentException("Unknown element type: " + element.getTagName());
	}


}