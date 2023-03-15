package pl.moja.biblioteczka;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
	private static final List<String> dateFormatPatterns = new ArrayList<>();

	static {
		dateFormatPatterns.add("d.MM.yyyy");
		dateFormatPatterns.add("d-MM-yyyy");
		dateFormatPatterns.add("d/MM/yyyy");
		dateFormatPatterns.add("yyyy/MM/d");
	}

	public static LocalDate parseDate(String stringDate) {
//		var ref = new Object() {
//			LocalDate parsedDate;
//		};
//		dateFormatPatterns.stream()
//				.anyMatch(pattern -> {
//					DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
//					ref.parsedDate = LocalDate.parse(stringDate, dtf);
//					return true;
//				});
//
//		return ref.parsedDate;

		AtomicReference<LocalDate> parsedDate = new AtomicReference<>();
		dateFormatPatterns.stream()
				.anyMatch(pattern -> {
					try {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
						LocalDate parse = LocalDate.parse(stringDate, formatter);
						parsedDate.set(parse);
						return true;
					} catch (DateTimeParseException e) {
						return false;
					}
				});
		return parsedDate.get();
	}



	public static void main(String[] args) {
//		MyLibraryApp.main(args);
		LocalDate date = LocalDate.of(2000,11,2);
		LocalTime time = LocalTime.of(22,10,50);
		LocalDateTime dateTime = LocalDateTime.of(date, time);

		System.out.println(dateTime.toLocalTime());
		System.out.println(dateTime.toLocalDate());

		List<String> allDataToString = new ArrayList<>();
		allDataToString.add("2000-02-12");
		allDataToString.add("20:20:10");
		allDataToString.add("x");
		allDataToString.add("23.123");
		System.out.println(allDataToString.toString());

	}
}
