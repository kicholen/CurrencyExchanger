package currency.event;

import java.util.List;

import javax.swing.JTable;
import org.springframework.context.ApplicationListener;

import currency.database.record.dao.RecordDao;
import currency.database.record.model.Record;
import currency.model.CurrencyModel;

public class CurrencyEventHandler implements ApplicationListener<CurrencyUpdateEvent> {
	protected JTable table;
    protected List<CurrencyModel> list;
	private RecordDao recordDao;
    private int magicalHack;
    
    public CurrencyEventHandler() {
    	magicalHack = 1;
    }
    
    public void setList(List<CurrencyModel> list) {
        this.list = list;
    }

    public void setTable(JTable itemTable) {
        this.table = itemTable;
    }
    
    public void setRecordDao(RecordDao recordDao) {
    	this.recordDao = recordDao;
    }
    
	@Override
	public void onApplicationEvent(CurrencyUpdateEvent event) {
		saveRecordToDatabase(event.getValue(), event.getFrom(), event.getTo());
		updateList(event);
		table.setSize(table.getWidth() + getMagicalHack(), table.getHeight());
		table.invalidate();
	}
	
	private void updateList(CurrencyUpdateEvent event) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFrom() == event.getFrom() && list.get(i).getTo() == event.getTo()) {
				list.get(i).setValue(event.getValue());
			}
		}
	}
	
	private void saveRecordToDatabase(float value, String from, String to) {
		Record record = new Record();
		record.setTimestamp(System.currentTimeMillis());
		record.setValue(value);
		record.setFrom(from);
		record.setTo(to);
		recordDao.insertRecord(record);
	}
	
	private int getMagicalHack() {
		magicalHack = -magicalHack;
		return magicalHack;
	}

}
