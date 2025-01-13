// Source code is decompiled from a .class file using FernFlower decompiler.
import java.util.ArrayList;

public class Store {
   private ArrayList<Book> books = new ArrayList();

   public Store() {
   }

   public Book getBook(int var1) {
      return new Book((Book)this.books.get(var1));
   }

   public void setBook(int var1, Book var2) {
      this.books.set(var1, new Book(var2));
   }

   public void addBook(Book var1) {
      this.books.add(new Book(var1));
   }

   public boolean contains(Book var1) {
      return this.books.contains(var1);
   }

   public void sellBook(String var1) {
      for(int var2 = 0; var2 < this.books.size(); ++var2) {
         if (((Book)this.books.get(var2)).getTitle().equals(var1)) {
            this.books.remove(var2);
         }
      }

   }
}
