package currency.database.record.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		createTableIfNeeded();
	}
	
	@Override
	public int insertRecord(Record record) {
		String sql = "Insert into RECORD (VALUE, TIMESTAMP, FROM_V, TO_V) values(?,?,?,?)";
		Object[] params = new Object[] { record.getValue(), record.getTimestamp(), record.getFrom(), record.getTo() };
		int[] types = new int[] { Types.FLOAT, Types.LONGNVARCHAR, Types.VARCHAR, Types.VARCHAR };
		
		return jdbcTemplate.update(sql, params, types);
	}

	@Override
	public int updateRecord(Record record) {
		String sql = "update RECORD (VALUE, TIMESTAMP, FROM_V, TO_V) set(?,?,?,?) where ID = ?";
		Object[] params = new Object[]{ record.getValue(), record.getTimestamp(), record.getId() };
		int[] types = new int[] { Types.FLOAT, Types.LONGNVARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR };
		
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
		String sql = "Create table if not exists RECORD(ID int NOT NULL AUTO_INCREMENT, FROM_V varchar(255) NOT NULL, TO_V varchar(255) NOT NULL, VALUE float NOT NULL, TIMESTAMP longtext NOT NULL, PRIMARY KEY (ID))";
		jdbcTemplate.execute(sql);
	}
	
	private void dropTable() {
		String sql = "Drop table if exists RECORD";
		jdbcTemplate.execute(sql);
	}

	@Override
	public List<Record> findAll() {
		String sql = "Select * from RECORD";
		 
		List<Record> records = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Record>(Record.class));
	 
		return records;
	}

	@Override
	public List<Record> findRecords(String from, String to) {
		String sql = "Select * from RECORD where FROM_V = '" + from + "' and TO_V = '" + to + "'";
		
		List<Record> records = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Record>(Record.class));
		
		return records;
	}
}
