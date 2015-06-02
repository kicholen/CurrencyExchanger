package currency.task;

import org.springframework.core.task.TaskExecutor;

public class MainTaskExecutor {
    private TaskExecutor taskExecutor;

    public MainTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void refreshCurrency() {
        //for(int i = 0; i < 5; i++) {
            taskExecutor.execute(new UpdateCurrencyTask("EUR", "USD"));
        //}
    }
}