package pl.ormlite.example.Dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import pl.ormlite.example.Model.BaseModel;
import pl.ormlite.example.Model.Book;
import pl.ormlite.example.Utils.DbManager;

import java.sql.SQLException;
import java.util.List;

public abstract class CommonDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
	protected final ConnectionSource connectionSource;

	public CommonDao(ConnectionSource connectionSource) {
		this.connectionSource = connectionSource;
	}

	public <T extends BaseModel, I> void createOrUpdate(BaseModel model){
		try {
			Dao<T, I> dao = getDao((Class<T>) model.getClass());
			dao.createOrUpdate((T) model);
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
	}

	public <T extends BaseModel, I> void refresh(BaseModel model){
		try {
			Dao<T, I> dao = getDao((Class<T>) model.getClass());
			dao.refresh((T) model);
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
	}

	public <T extends BaseModel, I> void delete(BaseModel model){
		try {
			Dao<T, I> dao = getDao((Class<T>) model.getClass());
			dao.delete((T) model);
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
	}

	public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls){
		try {
			Dao<T, I> dao = getDao(cls);
			return dao.queryForAll();
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
		return null;
	}

	public<T extends BaseModel,I> T findById(Class<T>cls,Integer id){
		try {
			Dao<T, I> dao = getDao(cls);
			return dao.queryForId((I) id);
		} catch (SQLException e) {
			LOGGER.warn(e.getCause().getMessage());
		}
		return null;
	}

	public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls){
		try {
			return DaoManager.createDao(connectionSource, cls);
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage());
		}
		return null;
	}

	public <T extends BaseModel, I> QueryBuilder<T, I> getQueryBuilder(Class<T> cls) {
		Dao<T, I> dao = getDao(cls);
		return dao.queryBuilder();
	}
}
