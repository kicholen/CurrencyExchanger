package currency.ui.button;

import java.awt.event.ActionEvent;

public class DeleteButtonActionListener extends ListTableActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();

        if (selectedRow == -1 || selectedRow == table.getRowCount() || table.isEditing()) {
            return;
        }

        list.remove(selectedRow);
        table.revalidate();
	}

}
