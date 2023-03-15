package pl.moja.biblioteczka.modelFx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AuthorFx {
	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty surname = new SimpleStringProperty();
	private SimpleStringProperty nameAndSurname  = new SimpleStringProperty();
	
	public String getName() {
		return name.get();
	}
	
	public SimpleStringProperty nameProperty() {
		return name;
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getSurname() {
		return surname.get();
	}
	
	public SimpleStringProperty surnameProperty() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname.set(surname);
	}
	
	public int getId() {
		return id.get();
	}
	
	public SimpleIntegerProperty idProperty() {
		return id;
	}
	
	public void setId(int id) {
		this.id.set(id);
	}
	
	public String getNameAndSurname() {
		return nameAndSurname.get();
	}
	
	public SimpleStringProperty nameAndSurnameProperty() {
		return nameAndSurname;
	}
	
	public void setNameAndSurname() {
		this.nameAndSurname.set(getName() + " " + getSurname());
	}

	@Override
	public String toString() {
		return nameAndSurname.get();
	}
}
