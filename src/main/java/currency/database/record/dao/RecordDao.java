package currency.database.record.dao;

import java.util.List;

import currency.database.record.model.Record;

public interface RecordDao {

	int insertRecord(Record record);
	
	int updateRecord(Record record);
			
	void deleteRecord(int key);
	
	Record selectRecord(int key);

	List<Record> findAll();

	List<Record> findRecords(String from, String to);
}