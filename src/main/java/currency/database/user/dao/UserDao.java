package currency.database.user.dao;

import currency.database.user.model.User;

public interface UserDao {

	int insertUser(User user);
	
	int updateUser(User user);
			
	void deleteUser(int key);
	
	User selectUser(int key);
}
