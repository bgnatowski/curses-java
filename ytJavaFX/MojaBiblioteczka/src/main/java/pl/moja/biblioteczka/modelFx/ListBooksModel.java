package pl.moja.biblioteczka.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.moja.biblioteczka.database.dao.BookDao;
import pl.moja.biblioteczka.database.dao.CategoryDao;
import pl.moja.biblioteczka.database.models.Book;
import pl.moja.biblioteczka.database.models.Category;
import pl.moja.biblioteczka.utils.converters.ConverterBook;
import pl.moja.biblioteczka.utils.converters.ConverterCategory;
import pl.moja.biblioteczka.utils.exceptions.ApplicationException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static pl.moja.biblioteczka.modelFx.BookModel.fillObservableAuthorList;

public class ListBooksModel {
	private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
	private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
	private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();
	private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>();
	private ObjectProperty<CategoryFx> categoryFxObjectProperty = new SimpleObjectProperty<>();

	private List<BookFx> bookFxList = new ArrayList<>();

	public void init() throws ApplicationException {
		BookDao bookDao = new BookDao();
		List<Book> books = bookDao.queryForAll(Book.class);
		bookFxList.clear();
		books.forEach(book -> {
			bookFxList.add(ConverterBook.convertToBookFx(book));
		});
		bookFxObservableList.setAll(bookFxList);

		initAuthorsList();
		initCategoryList();
	}

	public void filterBooksList(){
		if(getAuthorFxObjectProperty() != null && getCategoryFxObjectProperty() != null){
			filterPredicate(predicateAuthor().and(predicateCategory()));
		} else if (getCategoryFxObjectProperty() != null ){
			filterPredicate(predicateCategory());
		} else if (getAuthorFxObjectProperty() != null ){
			filterPredicate(predicateAuthor());
		} else {
			bookFxObservableList.setAll(bookFxList);
		}
	}

	private void initAuthorsList() throws ApplicationException {
		fillObservableAuthorList(authorFxObservableList);
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


	private Predicate<BookFx> predicateCategory(){
		return bookFx -> bookFx.getCategoryFxObjectProperty().getId() == this.getCategoryFxObjectProperty().getId();
	}

	private Predicate<BookFx> predicateAuthor(){
		return bookFx -> bookFx.getAuthorFxObjectProperty().getId() == this.getAuthorFxObjectProperty().getId();
	}

	private void filterPredicate(Predicate<BookFx> predicate){
		List<BookFx> newList = bookFxList.stream().filter(predicate).collect(Collectors.toList());
		bookFxObservableList.setAll(newList);
	}
	public void deleteBook(BookFx bookFx) throws ApplicationException {
		BookDao bookDao = new BookDao();
		bookDao.deleteById(Book.class, bookFx.getId());
		init();
	}

	public ObservableList<BookFx> getBookFxObservableList() {
		return bookFxObservableList;
	}

	public void setBookFxObservableList(ObservableList<BookFx> bookFxObservableList) {
		this.bookFxObservableList = bookFxObservableList;
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

	public AuthorFx getAuthorFxObjectProperty() {
		return authorFxObjectProperty.get();
	}

	public ObjectProperty<AuthorFx> authorFxObjectPropertyProperty() {
		return authorFxObjectProperty;
	}

	public void setAuthorFxObjectProperty(AuthorFx authorFxObjectProperty) {
		this.authorFxObjectProperty.set(authorFxObjectProperty);
	}

	public CategoryFx getCategoryFxObjectProperty() {
		return categoryFxObjectProperty.get();
	}

	public ObjectProperty<CategoryFx> categoryFxObjectPropertyProperty() {
		return categoryFxObjectProperty;
	}

	public void setCategoryFxObjectProperty(CategoryFx categoryFxObjectProperty) {
		this.categoryFxObjectProperty.set(categoryFxObjectProperty);
	}
}
