package currency.ui.button;

import java.awt.event.ActionEvent;

import currency.model.CurrencyModel;

public class AddNewButtonActionListener extends ListTableActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		list.add(new CurrencyModel());
        table.revalidate();
	}

}
