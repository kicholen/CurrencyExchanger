package currency.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import currency.event.CurrencyEventPublisher;

public class UpdateCurrencyTask implements Runnable {
	private CurrencyEventPublisher currencyPublisher;
	private String from;
	private String to;
	
    public UpdateCurrencyTask(String from, String to, CurrencyEventPublisher currencyPublisher) {
        this.from = from;
        this.to = to;
        this.currencyPublisher = currencyPublisher;
    }

    public void run() {
    	try {
            URL url = new URL("http://quote.yahoo.com/d/quotes.csv?f=l1&s=" + from + to + "=X");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            if (line.length() > 0) {
                currencyPublisher.publish(from, to, Float.parseFloat(line));
            }
            reader.close();
        } 
    	catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

}
