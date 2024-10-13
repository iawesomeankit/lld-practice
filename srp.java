import java.io.*;
import java.util.*;

class Book{
    private String title;
    private String author;
	Book(String title, String author){
		this.title = title;
		this.author = author;
	}
	public String getTitle(){
		return this.title;
	}
	public String getAuthor(){
		return this.author;
	}
}

class BookFileSaver{
	public void saveToFile(Book book,String fileName){
		System.out.println("Saving " + book.getTitle() + " by " + book.getAuthor() + " to file: " + fileName);
	}
}

public class Solution {
    public static void main(String[] args) {
		Book book = new Book("never ending life","ankit patel");
		BookFileSaver bookfilesaver = new BookFileSaver();
		bookfilesaver.saveToFile(book,"index.html");
	}
}