package pl.ormlite.example.Model;

import pl.ormlite.example.Utils.UnitaryNames;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import static pl.ormlite.example.Utils.UnitaryNames.*;

public class WinPQModelObj extends BaseModelObj{
	private Map<UnitaryNames, Integer> columnsNames = new TreeMap<>();
	private Map<UnitaryNames, Double> records = new TreeMap<>();
	private LocalDateTime localDateTime;
	private Character flags;

	public WinPQModelObj(Map<UnitaryNames, Double> records, LocalDateTime localDateTime){
		init();
		this.records = records;
		this.localDateTime = localDateTime;
	}

	public WinPQModelObj() {
		init();
	}

	public WinPQModelObj(LocalDateTime localDateTime) {
		init();
		this.localDateTime = localDateTime;
	}

	private void init(){
		records.put(U12_avg, 240.213);
		records.put(U12_max, 241.542);
		records.put(U12_min, 239.113);

		columnsNames.put(Date, 1);
		columnsNames.put(Time, 2);
		columnsNames.put(Flag, 3);
		columnsNames.put(U12_avg, 4);
		columnsNames.put(U12_max, 5);
		columnsNames.put(U12_min, 6);
		localDateTime = LocalDateTime.now();
		flags = 'x';
	}

	public Map<UnitaryNames, Integer> getColumnsNames() {
		return columnsNames;
	}

	public void setColumnsNames(Map<UnitaryNames, Integer> columnsNames) {
		this.columnsNames = columnsNames;
	}

	public Map<UnitaryNames, Double> getRecords() {
		return records;
	}

	public void setRecords(Map<UnitaryNames, Double> records) {
		this.records = records;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public Character getFlags() {
		return flags;
	}

	public void setFlags(Character flags) {
		this.flags = flags;
	}
}
