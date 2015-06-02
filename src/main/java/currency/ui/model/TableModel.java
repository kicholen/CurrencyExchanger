package currency.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
	List<String> itemList;
	List<String> columnNameList;
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
	
	public String getColumnName(int column) {
        return columnNameList.get(column);
    }
	
	public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }
	
	public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }
	
	public void setValueAt(String value, int rowIndex, int columnIndex) {
		itemList.set(rowIndex, value);
	}
	
	@Override
	public int getColumnCount() {
		return columnNameList.size();
	}

	@Override
	public int getRowCount() {
		return itemList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return itemList.get(rowIndex);
	}

}
