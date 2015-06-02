package currency;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {
	
    public void launch() {
        String[] loginPaths = new String[] { "app-login.xml" };
        new ClassPathXmlApplicationContext(loginPaths);
    }
}
