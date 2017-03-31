package pkgLibrary;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import pkgMain.XMLReader;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

	@XmlAttribute
	int id;	
	
	@XmlElement(name="book")
	ArrayList<Book> books;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Book> getBooks() {
		return books;
	}
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	//GetBook method
	public Book GetBook (String id) throws BookException{
		//Taken from XMLreader
		Catalog cat = ReadXMLFile();
		try {
			for(Book b : cat.getBooks()){
				if(b.getId().equals(id)){
					return b;
				}
				else{
					throw new BookException(b);
				}
			}
		} catch (BookException x) {
			System.out.println("Book not found");
		}
		return new Book();
	}
	
	//This needed to be created for ReadXMLfile() to work so I just copy and pasted the stuff from XMLReader 
	private Catalog ReadXMLFile() {
		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return cat;
	}
	
	public void AddBook (int id, Book b) throws BookException{
		Catalog cat = ReadXMLFile();
		try {
			if(cat.getBooks().contains(b)){
				throw new BookException(b);
			}
			else{
				cat.getBooks().add(b);
			}
		} catch(BookException x){
			throw x;
		}
	}
		
}
