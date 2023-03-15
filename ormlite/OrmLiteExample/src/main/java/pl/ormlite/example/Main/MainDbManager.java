package pl.ormlite.example.Main;

import pl.ormlite.example.Dao.WinPQDataDao;
import pl.ormlite.example.Model.WinPQModelObj;
import pl.ormlite.example.Model.WinPQData;
import pl.ormlite.example.Utils.ConverterWinPQData;
import pl.ormlite.example.Utils.DbManager;
import pl.ormlite.example.Utils.UnitaryNames;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import static pl.ormlite.example.Utils.UnitaryNames.*;

public class MainDbManager {
	public static void main(String[] args) throws SQLException {
		DbManager.initDatabase();

		// PIERWSZA
		WinPQModelObj winPQModelObj = new WinPQModelObj(LocalDateTime.of(2022, 9, 12, 19,34));
		WinPQDataDao dao = new WinPQDataDao(DbManager.getConnectionSource());
		dao.createOrUpdate(ConverterWinPQData.convertToDbObject(winPQModelObj));

//		StringBuilder builder = new StringBuilder();
//		builder.append("UPDATE MODELWIN SET U12_MAX = 222.231 WHERE ");
//		builder.append("DATE = '" + winPQModelObj.getLocalDateTime().toLocalDate() + "' ");
//		builder.append("AND TIME = '" + winPQModelObj.getLocalDateTime().toLocalTime() + "';");
//		dao.getDao(WinPQData.class).executeRaw(builder.toString());


//		// DRUGA
//		Map<UnitaryNames, Double> records = new TreeMap<>();
//		records.put(U12_avg, 255.453);
//		records.put(U12_max, 260.453);
//		records.put(U12_min, 220.453);
//		WinPQModelObj winPQModelObj2 = new WinPQModelObj(records, LocalDateTime.of(2022, 9, 12, 20,35));
//		dao.createOrUpdate(ConverterWinPQData.convertToDbObject(winPQModelObj2));
//		builder = new StringBuilder();
//		builder.append("UPDATE MODELWIN SET U12_MAX = 240.345 WHERE ");
//		builder.append("DATE = '" + winPQModelObj2.getLocalDateTime().toLocalDate() + "' ");
//		builder.append("AND TIME = '" + winPQModelObj2.getLocalDateTime().toLocalTime() + "';");
//		dao.getDao(WinPQData.class).executeRaw(builder.toString());

//




		DbManager.closeConnectionSource();
	}
}
