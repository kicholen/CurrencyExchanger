package currency.database.user.dao.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import currency.database.user.dao.UserDao;
import currency.database.user.model.User;

public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate;
	
	public UserDaoImpl(DataSourceTransactionManager transactionManager) {
		super();
		DataSource dataSource = transactionManager.getDataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		createTableIfNeeded();
	}
	
	@Override
	public int insertUser(User user) {
		String sql = "Insert into USER(ID, LOGIN, PASSWORD) values(?,?,?)";
		Object[] params = new Object[] { user.getId(), user.getLogin(), user.getPassword() };
		int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR };
		
		return jdbcTemplate.update(sql, params, types);
	}

	@Override
	public int updateUser(User user) {
		String sql = "update USER (LOGIN, PASSWORD) set(?,?) where ID = ?";
		Object[] params = new Object[]{ user.getLogin(), user.getPassword(), user.getId() };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		
		return jdbcTemplate.update(sql, params, types);
	}

	@Override
	public void deleteUser(int key) {
		String sql = "delete from USER where ID = ?";
		Object[] params = new Object[]{ key };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public User selectUser(int key) {
		String sql = "select ID, LOGIN, PASSWORD from USER where ID = ?";
		Object[] params = new Object[]{ key };
		User user = new User();
		
		jdbcTemplate.query(sql, params, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setLogin(rs.getString("LOGIN"));
				user.setPassword(rs.getString("PASSWORD"));
			}
			
		});
		
		return user;
	}
	
	@Override
	public void authenticate(String login, String password, ActionListener listener) {
		String sql = "select LOGIN, PASSWORD from USER where LOGIN = ?";
		Object[] params = new Object[]{ login };
		User user = new User();
		
		jdbcTemplate.query(sql, params, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setLogin(rs.getString("LOGIN"));
				user.setPassword(rs.getString("PASSWORD"));
				
				if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
					listener.actionPerformed(new ActionEvent("", 1, ""));
				}
			}
			
		});
	}
	
	private void createTableIfNeeded() {
		String sql = "Create table if not exists USER(ID int NOT NULL AUTO_INCREMENT, LOGIN VARCHAR(255) NOT NULL, PASSWORD VARCHAR(255) NOT NULL, PRIMARY KEY (ID))";
		jdbcTemplate.execute(sql);
	}
	
	private void dropTable() {
		String sql = "Drop table if exists USER";
		jdbcTemplate.execute(sql);
	}
}
