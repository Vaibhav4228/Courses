public class Main {
    public Main() {
    }
 
    public static void main(String[] var0) {
       Book var1 = new Book("To Kill a Mockingbird", "Harper Lee", 4.27, 15.99);
       Book var2 = new Book("1984", "George Orwell", 4.17, 12.99);
       Store var3 = new Store();
       var3.addBook(var1);
       var3.addBook(var2);
       Book var4 = var3.getBook(0);
       System.out.println(var4.getTitle());
       Book var5 = new Book("Moby-Dick", "Herman Melville", 3.5, 14.99);
       var3.setBook(0, var5);
       var4 = var3.getBook(0);
       System.out.println(var4.getTitle());
       System.out.println(var3.contains(var2));
       var3.sellBook("1984");
       System.out.println(var3.contains(var2));
    }
 }
 