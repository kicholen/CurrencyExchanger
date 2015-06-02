package currency.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import currency.model.CurrencyModel;

public class TableModel extends AbstractTableModel {
	List<String> columnNameList;
	List<CurrencyModel> currencyModelList;
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 2) {
			return false;
		}
		else {
			return true;
		}
    }
	
	public String getColumnName(int column) {
        return columnNameList.get(column);
    }
	
	public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }
	
	public void setCurrencyModelList(List<CurrencyModel> currencyModelList) {
		this.currencyModelList = currencyModelList;
	}
	
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			currencyModelList.get(rowIndex).setFrom((String)value);
		}
		else if (columnIndex == 1) {
			currencyModelList.get(rowIndex).setTo((String)value);
		}
		else {
			System.out.println(columnIndex);
			currencyModelList.get(rowIndex).setValue((float)value);
		}
	}
	
	@Override
	public int getColumnCount() {
		return columnNameList.size();
	}

	@Override
	public int getRowCount() {
		return currencyModelList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return currencyModelList.get(rowIndex).getFrom();
		}
		else if (columnIndex == 1) {
			return currencyModelList.get(rowIndex).getTo();
		}
		else {
			return currencyModelList.get(rowIndex).getValue();
		}
	}

}
