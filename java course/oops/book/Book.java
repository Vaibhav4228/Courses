package oops.book;

public class Book {
    private String title;
    private String author;
    private double rating;
    private double price;
 
    public Book(String var1, String var2, double var3, double var5) {
       this.title = var1;
       this.author = var2;
       this.rating = var3;
       this.price = var5;
    }
 
    public Book(Book var1) {
       this.title = var1.title;
       this.author = var1.author;
       this.rating = var1.rating;
       this.price = var1.price;
    }
 
    public String getTitle() {
       return this.title;
    }
 }
 