package currency.database.record.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import currency.database.record.dao.RecordDao;
import currency.database.record.model.Record;

public class RecordDaoImpl implements RecordDao {
	private JdbcTemplate jdbcTemplate;
	
	public RecordDaoImpl(DataSourceTransactionManager transactionManager) {
		super();
		DataSource dataSource = transactionManager.getDataSource();
		System.out.println("asd");
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		createTableIfNeeded();
	}
	
	@Override
	public int insertRecord(Record record) {
		String sql = "Insert into RECORD (ID, VALUE, TIMESTAMP) values(?,?)";
		Object[] params = new Object[] { record.getId(), record.getValue(), record.getTimestamp() };
		int[] types = new int[] { Types.INTEGER, Types.FLOAT, Types.LONGNVARCHAR };
		
		return jdbcTemplate.update(sql, params, types);
	}

	@Override
	public int updateRecord(Record record) {
		String sql = "update RECORD (VALUE, TIMESTAMP) set(?,?) where ID = ?";
		Object[] params = new Object[]{ record.getValue(), record.getTimestamp(), record.getId() };
		int[] types = new int[] { Types.FLOAT, Types.LONGNVARCHAR, Types.INTEGER };
		
		return jdbcTemplate.update(sql, params, types);
	}

	@Override
	public void deleteRecord(int key) {
		String sql = "delete from RECORD where ID = ?";
		Object[] params = new Object[]{ key };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public Record selectRecord(int key) {
		String sql = "select ID, VALUE, TIMESTAMP from RECORD where ID = ?";
		Object[] params = new Object[]{ key };
		Record record = new Record();
		
		jdbcTemplate.query(sql, params, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				record.setValue(rs.getFloat("VALUE"));
				record.setTimestamp(rs.getLong("TIMESTAMP"));
			}
			
		});
		
		return record;
	}
	
	private void createTableIfNeeded() {
		String sql = "Create table if not exists RECORD(ID int NOT NULL AUTO_INCREMENT, VALUE float NOT NULL, TIMESTAMP longtext NOT NULL, PRIMARY KEY (ID))";
		jdbcTemplate.execute(sql);
	}
	
	private void dropTable() {
		String sql = "Drop table if exists RECORD";
		jdbcTemplate.execute(sql);
	}
}
