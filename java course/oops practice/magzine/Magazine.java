
public class Magazine {
    private String title;
    private String publisher;
    private int issueNumber;
    private int publicationYear;
 
    public Magazine(String var1, String var2, int var3, int var4) {
       this.setTitle(var1);
       this.setPublisher(var2);
       this.setIssueNumber(var3);
       this.setPublicationYear(var4);
    }
 
    public Magazine(Magazine var1) {
       this.setTitle(var1.getTitle());
       this.setPublisher(var1.getPublisher());
       this.setIssueNumber(var1.getIssueNumber());
       this.setPublicationYear(var1.getPublicationYear());
    }
 
    public String getTitle() {
       return this.title;
    }
 
    public void setTitle(String var1) {
       if (var1 != null && !var1.isBlank()) {
          this.title = var1;
       } else {
          throw new IllegalArgumentException("Title cannot be null or blank.");
       }
    }
 
    public String getPublisher() {
       return this.publisher;
    }
 
    public void setPublisher(String var1) {
       if (var1 != null && !var1.isBlank()) {
          this.publisher = var1;
       } else {
          throw new IllegalArgumentException("Publisher cannot be null or blank.");
       }
    }
 
    public int getIssueNumber() {
       return this.issueNumber;
    }
 
    public void setIssueNumber(int var1) {
       if (var1 <= 0) {
          throw new IllegalArgumentException("Issue number must be greater than 0.");
       } else {
          this.issueNumber = var1;
       }
    }
 
    public int getPublicationYear() {
       return this.publicationYear;
    }
 
    public void setPublicationYear(int var1) {
       if (var1 <= 0) {
          throw new IllegalArgumentException("Publication year must be greater than 0.");
       } else {
          this.publicationYear = var1;
       }
    }
 }
 