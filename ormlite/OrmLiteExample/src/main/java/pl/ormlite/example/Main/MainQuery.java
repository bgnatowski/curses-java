package pl.ormlite.example.Main;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example.Model.Book;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MainQuery {
	public static void main(String[] args) throws Exception {
		String databaseUrl = "jdbc:sqlite:bazadanych.db";

		JdbcConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

		TableUtils.dropTable(connectionSource, Book.class, true);
//		TableUtils.createTableIfNotExists(connectionSource, Book.class);
		TableUtils.createTable(connectionSource, Book.class);

		Book book = new Book();
		book.setTitle("Władca pierścieni");
		book.setIsbn("1111");
		book.setAddedDate(new Date());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String dateInString = "2014/11/11";
		Date date = sdf.parse(dateInString);

		book.setReleaseDate(date);
		book.setRating("1");
		book.setBorrowed(true);
		book.setPrice(33.99);

		Book book2 = new Book();
		book2.setTitle("Hobbit");
		book2.setIsbn("222222");
		book2.setAddedDate(new Date());
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		String dateInString2 = " 2014/02/13";
		Date date2 = sdf.parse(dateInString2);
		book2.setReleaseDate(date2);
		book2.setRating("1");
		book2.setBorrowed(true);
		book2.setPrice(22.22);

		Book book3 = new Book();
		book3.setTitle("Wiedźmin");
		book3.setIsbn("333333");
		book3.setAddedDate(new Date());
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd");
		String dateInString3 = " 2005/06/11";
		Date date3 = sdf.parse(dateInString3);
		book3.setReleaseDate(date3);
		book3.setRating("1");
		book3.setBorrowed(true);
		book3.setPrice(33.33);

		Dao<Book, Integer> dao = DaoManager.createDao(connectionSource, Book.class);
		dao.create(book);
		dao.create(book2);
		dao.create(book3);

		QueryBuilder<Book, Integer> queryBuilder = dao.queryBuilder();
		queryBuilder.where().eq("TITLE", "Hobbit");
		PreparedQuery<Book> prepare = queryBuilder.prepare();
		List<Book> query = dao.query(prepare);
		query.forEach(e-> System.out.print(e));

		List<Book> query1 = dao.query(dao.queryBuilder().where().eq("TITLE", "Wiedźmin").prepare());
		query1.forEach(e-> System.out.println(e));
		connectionSource.close();


	}
}

