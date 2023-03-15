package pl.moja.biblioteczka.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.moja.biblioteczka.database.dao.AuthorDao;
import pl.moja.biblioteczka.database.dao.BookDao;
import pl.moja.biblioteczka.database.models.Author;
import pl.moja.biblioteczka.database.models.Book;
import pl.moja.biblioteczka.utils.converters.ConverterAuthor;
import pl.moja.biblioteczka.utils.exceptions.ApplicationException;

import java.sql.SQLException;

public class AuthorModel {
	private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>(new AuthorFx());
	private ObjectProperty<AuthorFx> authorFxObjectPropertyEdit = new SimpleObjectProperty<>(new AuthorFx());
	private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
	
	public void init() throws ApplicationException {
		BookModel.fillObservableAuthorList(authorFxObservableList);
	}
	
	public void saveAuthorInDataBase() throws ApplicationException {
		saveOrUpdate(getAuthorFxObjectProperty());
	}
	
	public void saveAuthorEditInDataBase() throws ApplicationException {
		saveOrUpdate(getAuthorFxObjectPropertyEdit());
	}
	public void deleteAuthorInDataBase() throws ApplicationException, SQLException {
		AuthorDao authorDao = new AuthorDao();
		authorDao.deleteById(Author.class, getAuthorFxObjectPropertyEdit().getId());

		BookDao bookDao = new BookDao();
		bookDao.deleteByColumnName(Book.AUTHOR_ID, getAuthorFxObjectPropertyEdit().getId());
		init();
	}
	
	private void saveOrUpdate(AuthorFx authorFxObjectProperty) throws ApplicationException {
		AuthorDao authorDao = new AuthorDao();
		Author author = ConverterAuthor.convertAuthorFxToAuthor(authorFxObjectProperty);
		authorDao.creatOrUpdate(author);

		init();
	}
	
	public AuthorFx getAuthorFxObjectProperty() {
		return authorFxObjectProperty.get();
	}
	
	public ObjectProperty<AuthorFx> authorFxObjectPropertyProperty() {
		return authorFxObjectProperty;
	}
	
	public ObservableList<AuthorFx> getAuthorFxObservableList() {
		return authorFxObservableList;
	}
	
	public ObjectProperty<AuthorFx> authorFxObjectProperty() {
		return authorFxObjectProperty;
	}
	
	public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
		this.authorFxObservableList = authorFxObservableList;
	}
	
	public void setAuthorFxObjectProperty(AuthorFx authorFxObjectProperty) {
		this.authorFxObjectProperty.set(authorFxObjectProperty);
	}
	
	public AuthorFx getAuthorFxObjectPropertyEdit() {
		return authorFxObjectPropertyEdit.get();
	}
	
	public ObjectProperty<AuthorFx> authorFxObjectPropertyEditProperty() {
		return authorFxObjectPropertyEdit;
	}
	
	public void setAuthorFxObjectPropertyEdit(AuthorFx authorFxObjectPropertyEdit) {
		this.authorFxObjectPropertyEdit.set(authorFxObjectPropertyEdit);
	}
}
