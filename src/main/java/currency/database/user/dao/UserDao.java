package currency.database.user.dao;

import java.awt.event.ActionListener;

import currency.database.user.model.User;

public interface UserDao {

	int insertUser(User user);
	
	int updateUser(User user);
			
	void deleteUser(int key);
	
	User selectUser(int key);
	
	void authenticate(String login, String password, ActionListener listener);
}
