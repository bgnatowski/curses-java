package pl.ormlite.example.Main;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example.Model.Author;
import pl.ormlite.example.Model.Book;
import pl.ormlite.example.Utils.DataCreator;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainForeignFields {
	public static void main(String[] args) throws Exception {
		String databaseUrl = "jdbc:sqlite:bazadanych.db";

		JdbcConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

		TableUtils.dropTable(connectionSource, Book.class, true);
		TableUtils.dropTable(connectionSource, Author.class, true);
		TableUtils.createTableIfNotExists(connectionSource, Book.class);
		TableUtils.createTableIfNotExists(connectionSource, Author.class);

		Author author = DataCreator.author();
		Book book = DataCreator.firstBook();

		Dao<Book, Integer> daoBook = DaoManager.createDao(connectionSource, Book.class);
		Dao<Author, Integer> daoAuthor = DaoManager.createDao(connectionSource, Author.class);

		book.setAuthor(author);
		daoBook.create(book);

		System.out.println("po zapisie: " + book);

		Book book2 = daoBook.queryForId(1);
		System.out.println("Po zapytaniu: " + book2);

		book2.getAuthor().setName("Nieznany");
		daoBook.createOrUpdate(book2);
		book2 = daoBook.queryForId(1);
		System.out.println("Po zmianie autora: " + book2);

		author = daoAuthor.queryForId(1);
		author.getBooks().forEach(e-> {
			e.setTitle("Blablabla");
			try {
				daoBook.createOrUpdate(e);
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
		});
		daoAuthor.refresh(author);

		author.getBooks().forEach(e-> System.out.println("Zmiana tytułu: " + e.getTitle()));


		author.getBooks().add(DataCreator.thirdBook());
		daoAuthor.createOrUpdate(author);

		connectionSource.close();
	}
}
