package http;

public class Quote {
    String author;
    String quote;

    public Quote(String author, String quote) {
        this.author = author;
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return author + "-" + quote;
    }

    public String jsonString() {
        return "{\"author\":"+this.author+",\"quote\":"+this.quote+"}";
    }
}
