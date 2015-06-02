package currency.ui.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import currency.database.record.dao.RecordDao;
import currency.database.record.model.Record;
import currency.ui.graph.GraphPanel;

public class DrawGraphButtonActionListener implements ActionListener {
	private RecordDao recordDao;
	private GraphPanel graphPanel;
	private String from;
	private String to;
	
	public DrawGraphButtonActionListener() {
		this.from = "PLN";
		this.to = "EUR";
	}
	
	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}
	
	public void setGraphPanel(GraphPanel graphPanel) {
		this.graphPanel = graphPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		List<Record> records = recordDao.findRecords(from, to);
		
		graphPanel.setRecords(records);
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
}
