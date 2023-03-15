package pl.moja.biblioteczka.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.moja.biblioteczka.database.dao.AuthorDao;
import pl.moja.biblioteczka.database.dao.BookDao;
import pl.moja.biblioteczka.database.dao.CategoryDao;
import pl.moja.biblioteczka.database.models.Author;
import pl.moja.biblioteczka.database.models.Book;
import pl.moja.biblioteczka.database.models.Category;
import pl.moja.biblioteczka.utils.converters.ConverterAuthor;
import pl.moja.biblioteczka.utils.converters.ConverterBook;
import pl.moja.biblioteczka.utils.converters.ConverterCategory;
import pl.moja.biblioteczka.utils.exceptions.ApplicationException;

import java.util.List;

public class BookModel {
	private ObjectProperty<BookFx> bookFxObjectProperty = new SimpleObjectProperty<>(new BookFx());
	private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
	private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();

	public void init() throws ApplicationException {
		initAuthorList();
		initCategoryList();
	}

	private void initCategoryList() throws ApplicationException {
		CategoryDao categoryDao = new CategoryDao();
		List<Category> categoryList = categoryDao.queryForAll(Category.class);
		categoryFxObservableList.clear();
		categoryList.forEach(category -> {
			CategoryFx categoryFx = ConverterCategory.convertToCategoryFx(category);
			categoryFxObservableList.add(categoryFx);
		});
	}

	private void initAuthorList() throws ApplicationException {
		fillObservableAuthorList(authorFxObservableList);
	}

	static void fillObservableAuthorList(ObservableList<AuthorFx> authorFxObservableList) throws ApplicationException {
		AuthorDao authorDao = new AuthorDao();
		List<Author> authorList = authorDao.queryForAll(Author.class);
		authorFxObservableList.clear();
		authorList.forEach(author -> {
			AuthorFx authorFx = ConverterAuthor.convertAuthorToAuthorFx(author);
			authorFxObservableList.add(authorFx);
		});
	}


	public void saveBookInDataBase() throws ApplicationException {
		Book book = ConverterBook.convertToBook(getBookFxObjectProperty());

		CategoryDao categoryDao = new CategoryDao();
		int bookCategoryIdToFind = getBookFxObjectProperty().getCategoryFxObjectProperty().getId();
		Category category = categoryDao.findById(Category.class, bookCategoryIdToFind);

		AuthorDao authorDao = new AuthorDao();
		int bookAuthorIdToFind = getBookFxObjectProperty().getAuthorFxObjectProperty().getId();
		Author author = authorDao.findById(Author.class, bookAuthorIdToFind);

		book.setCategory(category);
		book.setAuthor(author);

		BookDao bookDao = new BookDao();
		bookDao.creatOrUpdate(book);

	}
	public BookFx getBookFxObjectProperty() {
		return bookFxObjectProperty.get();
	}

	public ObjectProperty<BookFx> bookFxObjectPropertyProperty() {
		return bookFxObjectProperty;
	}

	public void setBookFxObjectProperty(BookFx bookFxObjectProperty) {
		this.bookFxObjectProperty.set(bookFxObjectProperty);
	}

	public ObservableList<AuthorFx> getAuthorFxObservableList() {
		return authorFxObservableList;
	}

	public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
		this.authorFxObservableList = authorFxObservableList;
	}
	public ObservableList<CategoryFx> getCategoryFxObservableList() {
		return categoryFxObservableList;
	}

	public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
		this.categoryFxObservableList = categoryFxObservableList;
	}


}
