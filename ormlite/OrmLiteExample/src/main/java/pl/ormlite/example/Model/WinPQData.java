package pl.ormlite.example.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import pl.ormlite.example.Utils.UnitaryNames;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@DatabaseTable(tableName = "WinPQData")
public class WinPQData implements BaseModel{
	@DatabaseField(generatedId = true)
	private long id;
	@DatabaseField(columnName = "DATE",canBeNull = false, format = "yyyy-MM-DD")
	private Date date;
	@DatabaseField(columnName = "TIME",canBeNull = false, format = "HH:mm")
	private Date time;
	@DatabaseField(columnName = "FLAG")
	private Character flag;
	private Map<UnitaryNames, Integer> columnsNames = new TreeMap<>();
	private Map<UnitaryNames, Double> records = new TreeMap<>();
	public WinPQData() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Character getFlag() {
		return flag;
	}

	public void setFlag(Character flag) {
		this.flag = flag;
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
}
