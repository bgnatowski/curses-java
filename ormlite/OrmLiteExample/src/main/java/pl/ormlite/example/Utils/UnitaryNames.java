package pl.ormlite.example.Utils;

import java.util.stream.Stream;

public enum UnitaryNames {
	// Base
	Date("date"),
	Time("time"),
	Flag("Flag"),
	U12_avg("U12_avg"),
	U12_max("U12_max"),
	U12_min("U12_min");
	private final String uniName;

	UnitaryNames(String uniName) {
		this.uniName = uniName;
	}

	public static Stream<UnitaryNames> stream() {
		return Stream.of(UnitaryNames.values());
	}
	@Override
	public String toString() {
		return this.uniName;
	}
}
