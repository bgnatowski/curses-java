package pl.ormlite.example.Dao;

import com.j256.ormlite.support.ConnectionSource;
import pl.ormlite.example.Model.BaseModel;
import pl.ormlite.example.Model.WinPQData;
import pl.ormlite.example.Utils.UnitaryNames;
import pl.ormlite.example.Utils.Utils;

import java.sql.SQLException;
import java.util.Map;

public class WinPQDataDao extends CommonDao {
	private StringBuilder stringBuilder;

	public WinPQDataDao(ConnectionSource connectionSource) {
		super(connectionSource);
	}

//	public List<String[]> queryRaw(Date) throws SQLException {
//		GenericRawResults<String[]> where = getDao(Book.class).queryRaw("UPDA ");
//		return where.getResults();
//	}

	@Override
	public <T extends BaseModel, I> void createOrUpdate(BaseModel model) {
		super.createOrUpdate(model);
		if (model instanceof WinPQData) {
			WinPQData finalInnerModel = (WinPQData) model;
			Map<UnitaryNames, Double> records = finalInnerModel.getRecords();
			records.forEach((columnName, value) -> {
				stringBuilder = new StringBuilder();
				stringBuilder.append("UPDATE " + model.getClass().getSimpleName());
				stringBuilder.append(" SET " + columnName + " = " + value);
				stringBuilder.append(" WHERE DATE = '" + Utils.convertToLocalDate(finalInnerModel.getDate()) + "'");
				stringBuilder.append(" AND TIME = '" + Utils.convertToLocalTime(finalInnerModel.getTime()) + "';");
//			System.out.println(stringBuilder.toString());
				try {
					this.getDao(WinPQData.class).executeRaw(stringBuilder.toString());
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}

			});
		}


	}

//	UPDATE MODELWIN SET U12_MAX = 222.231 WHERE DATE = '2022-09-11' AND TIME = '20:48:34';

}
