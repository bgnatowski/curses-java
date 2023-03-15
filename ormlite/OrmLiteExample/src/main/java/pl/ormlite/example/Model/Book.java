package pl.ormlite.example.Model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "books")
public class Book implements BaseModel{

	public Book() {
	}

	@DatabaseField(columnName = "AUTHOR_ID", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
	private Author author; // author_id

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(columnName = "TITLE", canBeNull = false)
	private String title;

	@DatabaseField(columnName = "DESCRIPTION", dataType = DataType.LONG_STRING)
	private String description;
	@DatabaseField(columnName = "ISBN", unique = true)
	private String isbn;
	@DatabaseField(columnName = "ADDED_DATE")
	private Date addedDate;
	@DatabaseField(columnName = "RELEASE_DATE", dataType = DataType.DATE_STRING, format = "yyyy-MM-DD")
	private Date releaseDate;
	@DatabaseField(columnName = "RATING", width = 1)
	private String rating;
	@DatabaseField(columnName = "BORROWED", defaultValue = "false")
	private boolean borrowed;
	@DatabaseField(columnName = "PRICE")
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public boolean isBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book{" +
				"author=" + author +
				"\nid=" + id +
				"\ntitle='" + title + '\'' +
				"\ndescription='" + description + '\'' +
				"\nisbn='" + isbn + '\'' +
				"\naddedDate=" + addedDate +
				"\nreleaseDate=" + releaseDate +
				"\nrating='" + rating + '\'' +
				"\nborrowed=" + borrowed +
				"\nprice=" + price +
				'}';
	}
}
