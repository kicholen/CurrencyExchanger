package currency.ui.button;

import java.awt.event.ActionEvent;

import currency.task.MainTaskExecutor;

public class RefreshButtonActionListener extends ListTableActionListener {
	protected MainTaskExecutor taskExecutor;
	
    public void setTaskExecutor(MainTaskExecutor mainTaskExecutor) {
        this.taskExecutor = mainTaskExecutor;
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		taskExecutor.refreshCurrency(list);
	}
	
}
