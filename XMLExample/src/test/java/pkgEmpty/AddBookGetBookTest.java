package pkgEmpty;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pkgLibrary.Book;
import pkgLibrary.BookException;
import pkgLibrary.Catalog;

public class AddBookGetBookTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetBook() throws BookException {
		Catalog cat = ReadXMLFile();
		cat.GetBook("bk000");
		//"Book not found" b/c not in xml file
	}
	
	@Test
	public void testAddBook() throws BookException {
		Catalog cat = ReadXMLFile();
		Book b = new Book("000");
		cat.AddBook(0, b);
	}
	
	

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

}
