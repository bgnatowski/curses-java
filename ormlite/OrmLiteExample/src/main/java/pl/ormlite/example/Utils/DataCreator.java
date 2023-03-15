package pl.ormlite.example.Utils;

import pl.ormlite.example.Model.Author;
import pl.ormlite.example.Model.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataCreator {
	public static Book firstBook(){
		Book book = new Book();
		book.setTitle("Władca pierścieni");
		book.setIsbn("1111");
		book.setAddedDate(new Date());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String dateInString = "2014/11/11";
		Date date = null;
		try {
			date = sdf.parse(dateInString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		book.setReleaseDate(date);
		book.setRating("1");
		book.setBorrowed(true);
		book.setPrice(33.99);

		return book;
	}

	public static Book secondBook(){

		Book book2 = new Book();
		book2.setTitle("Hobbit");
		book2.setIsbn("222222");
		book2.setAddedDate(new Date());
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		String dateInString2 = " 2014/02/13";
		Date date2 = null;
		try {
			date2 = sdf2.parse(dateInString2);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		book2.setReleaseDate(date2);
		book2.setRating("1");
		book2.setBorrowed(true);
		book2.setPrice(22.22);

		return book2;
	}

	public static Book thirdBook(){
		Book book3 = new Book();
		book3.setTitle("Szybcy i wściekli");
		book3.setIsbn("333333");
		book3.setAddedDate(new Date());
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd");
		String dateInString3 = " 2005/06/11";
		Date date3 = null;
		try {
			date3 = sdf3.parse(dateInString3);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		book3.setReleaseDate(date3);
		book3.setRating("1");
		book3.setBorrowed(true);
		book3.setPrice(33.33);

		return book3;
	}

	public static Author author() {
		Author author = new Author();
		author.setName("Tolkien");
		return author;
	}
}
