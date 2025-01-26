package mvc;

public class Book {
    public int book_isbn;
    public String title;
    public String author_name;
    public String author_surname;
    public String year;
    public static int isbn = 1;


    public Book(String title, String author_name, String author_surname, String year) {
        this.title = title;
        this.author_name = author_name;
        this.author_surname = author_surname;
        this.year = year;
        book_isbn = isbn;

        isbn++;
    }

    public int getBook_isbn() {
        return book_isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthorSurname() {
        return author_surname;
    }

    public void setAuthor_surname(String author_surname) {
        this.author_surname = author_surname;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String toString() {
        return  " Isbn: " + book_isbn + ", Title: " +  title + ", Author: " + author_name + " " + author_surname + " (" + year + ")";
    }
}
