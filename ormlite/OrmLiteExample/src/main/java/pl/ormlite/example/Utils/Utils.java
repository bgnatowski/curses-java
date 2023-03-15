package pl.ormlite.example.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Utils {
	public static Date convertToDate(LocalDate localDate){
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate convertToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalTime convertToLocalTime(Date timeAsDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String formattedToString = sdf.format(timeAsDate);
		return LocalTime.parse(formattedToString);
	}

	public static Date convertToTime(LocalTime localTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			return sdf.parse(localTime.toString());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
