package currency.database.record.model;

import java.io.Serializable;

public class Record implements Serializable {
	private int id;
	private float value;
	private long timestamp;
	
	public Record() {
		
	}
	
	public Record(int id, float value, long timestamp) {
		this.id = id;
		this.value = value;
		this.timestamp = timestamp;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Record [value=" + value + ", timestamp=" + timestamp + "]";
	}
}
