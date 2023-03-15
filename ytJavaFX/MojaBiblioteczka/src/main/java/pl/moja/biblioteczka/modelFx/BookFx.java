package pl.moja.biblioteczka.modelFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class BookFx {
	private IntegerProperty id = new SimpleIntegerProperty();
	private ObjectProperty<CategoryFx> categoryFxObjectProperty = new SimpleObjectProperty<>();
	private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>();
	private SimpleStringProperty title = new SimpleStringProperty();
	private SimpleStringProperty description = new SimpleStringProperty();
	private ObjectProperty<LocalDate> releaseDate = new SimpleObjectProperty<>();
	private SimpleStringProperty isbn = new SimpleStringProperty();
	private DoubleProperty rating = new SimpleDoubleProperty();
	private ObjectProperty<LocalDate> addedDate = new SimpleObjectProperty<>(LocalDate.now());

	public int getId() {
		return id.get();
	}
	public IntegerProperty idProperty() {
		return id;
	}
	public void setId(int id) {
		this.id.set(id);
	}
	public CategoryFx getCategoryFxObjectProperty() {
		return categoryFxObjectProperty.get();
	}
	public ObjectProperty<CategoryFx> categoryFxProperty() {
		return categoryFxObjectProperty;
	}
	public void setCategoryFx(CategoryFx categoryFxObjectProperty) {
		this.categoryFxObjectProperty.set(categoryFxObjectProperty);
	}
	public AuthorFx getAuthorFxObjectProperty() {
		return authorFxObjectProperty.get();
	}
	public ObjectProperty<AuthorFx> authorFxProperty() {
		return authorFxObjectProperty;
	}
	public void setAuthorFx(AuthorFx authorFxObjectProperty) {
		this.authorFxObjectProperty.set(authorFxObjectProperty);
	}
	public String getTitle() {
		return title.get();
	}
	public SimpleStringProperty titleProperty() {
		return title;
	}
	public void setTitle(String title) {
		this.title.set(title);
	}
	public String getDescription() {
		return description.get();
	}
	public SimpleStringProperty descriptionProperty() {
		return description;
	}
	public void setDescription(String description) {
		this.description.set(description);
	}

	public LocalDate getReleaseDate() {
		return releaseDate.get();
	}

	public ObjectProperty<LocalDate> releaseDateProperty() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate.set(releaseDate);
	}

	public void setAddedDate(LocalDate addedDate) {
		this.addedDate.set(addedDate);
	}

	public String getIsbn() {
		return isbn.get();
	}
	public SimpleStringProperty isbnProperty() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}

	public double getRating() {
		return rating.get();
	}

	public DoubleProperty ratingProperty() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating.set(rating);
	}

	public LocalDate getAddedDate() {
		return addedDate.get();
	}

	public ObjectProperty<LocalDate> addedDateProperty() {
		return addedDate;
	}

	@Override
	public String toString() {
		return "BookFx{" +
				"id=" + id.get() +
				", categoryFxObjectProperty=" + categoryFxObjectProperty.get() +
				", authorFxObjectProperty=" + authorFxObjectProperty.get() +
				", title=" + title.get() +
				", description=" + description.get() +
				", releaseDate=" + releaseDate.get() +
				", isbn=" + isbn.get() +
				", rating=" + rating.get() +
				", addedDate=" + addedDate.get() +
				'}';
	}

}
