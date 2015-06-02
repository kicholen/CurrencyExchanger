package currency.ui.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import currency.database.user.dao.UserDao;

public class LoginButtonActionListener implements ActionListener {
	private JTextField loginTextfield;
	private JTextField passwordTextfield;
	private UserDao userDao;
	
	public void setLoginTextfield(JTextField loginTextfield) {
		this.loginTextfield = loginTextfield;
	}
	
	public void setPasswordTextfield(JTextField passwordTextfield) {
		this.passwordTextfield = passwordTextfield;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		userDao.authenticate(loginTextfield.getText(), passwordTextfield.getText(), new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] contextPaths = new String[] { "app-context.xml" };
				new ClassPathXmlApplicationContext(contextPaths);
			}
		});
	}
	
}
