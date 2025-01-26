package mvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;

public class Model extends Observable{
    HashSet<Book> books;

    public Model(HashSet<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        for (Book b : books) {
            if (book.getAuthorName().equals(b.getAuthorName()) && book.getAuthorSurname().equals(b.getAuthorSurname())
                    && book.getTitle().equals(b.getTitle()) && book.getYear().equals(b.getYear())) {
                return;
            }
        }
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }


    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
}
