package currency.event;

import org.springframework.context.ApplicationEvent;

public class CurrencyUpdateEvent extends ApplicationEvent {
	private String from;
	private String to;
	private float value;
	
	public CurrencyUpdateEvent(Object source, String from, String to, float value) {
		super(source);
		this.from = from;
		this.to = to;
		this.value = value;
	}

	@Override
	public String toString() {
		return from + " " + to + " " + value;
	}
	
	public float getValue() {
		return value;
	}
	
	public String getFrom() {
		return from;
	}
	
	public String getTo() {
		return to;
	}
}
