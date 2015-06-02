package currency.task;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.core.task.TaskExecutor;

import currency.event.CurrencyEventPublisher;
import currency.model.CurrencyModel;

public class MainTaskExecutor {
    private TaskExecutor taskExecutor;
    private CurrencyEventPublisher eventPublisher;
    private List<CurrencyModel> currencyModelList;
    
    public MainTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
    
    public void setCurrencyModelList(List<CurrencyModel> currencyModelList) {
    	this.currencyModelList = currencyModelList;
    }
    
    public void init() {
    	new Timer().schedule(new TimerTask() {
    		public void run()  {
    			refreshCurrency(currencyModelList);
    		}
    	}, 5000, 60000);
    }
    
    public void setEventPublisher(CurrencyEventPublisher eventPublisher) {
    	this.eventPublisher = eventPublisher;
    }

	public void refreshCurrency(List<CurrencyModel> list) {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: " + list.size());
    	for (int i = list.size() - 1; i >= 0; i--) {
    		taskExecutor.execute(new UpdateCurrencyTask(list.get(i).getFrom(), list.get(i).getTo(), eventPublisher));
    	}
    }
}