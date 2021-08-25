package model;

import utils.Utility;

import java.util.Comparator;
import java.util.Date;
public class Book implements Comparable<Book> {
    private int bookId;
    private String title;
    private String category;
    private String author;
    private Date date;

    public Book(int bookId, String title, String category, String author, Date date) {
        this.bookId = bookId;
        this.title = title;
        this.category = category;
        this.author = author;
        this.date = date;
    }

    public Book() {
    }
    Utility ut = new Utility();
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        String id = ut.IntegerToString(bookId);
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (id == null){
            if (id!= null)
                return false;
        }
        else if (!id.equals(other.ut.IntegerToString(bookId)))
            return false;
        return true;}

    @Override
    public int hashCode() {
        String id = ut.IntegerToString(bookId);
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : (id.hashCode()));
        return result;
    }
    @Override
    public String toString() {
        return "Book Id:\t " +this.getBookId()+
                " Book title:\t" +this.getTitle()+
                " Book category:\t" +this.getCategory()+
                " Author:\t" +this.getAuthor()+
                " Publication Date: "+this.getDate();
    }

    @Override
    public int compareTo(Book book) {
        int compareIds = book.getBookId();
        return this.bookId - compareIds;
    }
    public static Comparator<Book> bookTitleComparator = new Comparator<Book>() {

        @Override
        public int compare(Book book, Book t1) {
            String category1 = book.getTitle();
            String category2 = book.getTitle();
            return category1.compareTo(category2);
        }
    };
}
