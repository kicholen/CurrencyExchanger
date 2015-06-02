package currency.database.record.dao;

import currency.database.record.model.Record;

public interface RecordDao {

	int insertRecord(Record record);
	
	int updateRecord(Record record);
			
	void deleteRecord(int key);
	
	Record selectRecord(int key);
}