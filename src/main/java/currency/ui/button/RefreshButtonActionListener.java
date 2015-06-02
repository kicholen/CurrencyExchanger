package currency.ui.button;

import java.awt.event.ActionEvent;

import currency.task.MainTaskExecutor;

public class RefreshButtonActionListener extends ListTableActionListener {
	protected MainTaskExecutor taskExecutor;
	//private UserDao userDao;
	
    public void setTaskExecutor(MainTaskExecutor mainTaskExecutor) {
        this.taskExecutor = mainTaskExecutor;
    }
    
   // public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		taskExecutor.refreshCurrency();
		//downloader.getEuroUsdRate();
		//downloader.start();
		
		/*if (false) {
			User record = new User();
			record.setId(1);
			record.setLogin("root");
			record.setPassword("root");
			
			userDao.insertUser(record);
			System.out.println(record);
		}*/
		
		//list.add("0.0");
        table.revalidate();
	}
	
}
