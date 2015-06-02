package currency.model;

public class CurrencyModel {
	private String from;
	private String to;
	private float value;
	
	public CurrencyModel() {
		this.from = "PLN";
		this.to = "EUR";
		this.value = 0;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	public float getValue() {
		return value;
	}
}
