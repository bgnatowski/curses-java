package pl.ormlite.example.Main;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example.Model.Book;
import pl.ormlite.example.Utils.Utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class Main {
	public static void main(String[] args) throws Exception {
		String time = "12:05:00";
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date date = sdf.parse(time);

		System.out.println(sdf.format(date));

//		Date time = localDateTime.toLocalTime();


	}
}
