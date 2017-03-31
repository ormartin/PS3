package pkgLibrary;

public class BookException extends Exception {
	private Book b;
	
	public BookException(Book b){
		super();
		this.b = b;
	}
}
