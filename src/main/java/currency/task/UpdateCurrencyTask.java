package currency.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateCurrencyTask implements Runnable {
    private String from;
	private String to;
	private double result;

    public UpdateCurrencyTask(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public void run() {
    	try {
            URL url = new URL("http://quote.yahoo.com/d/quotes.csv?f=l1&s=" + from + to + "=X");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            if (line.length() > 0) {
                System.out.println(Double.parseDouble(line));
            }
            reader.close();
        } 
    	catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
