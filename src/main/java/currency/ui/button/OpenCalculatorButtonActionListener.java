package currency.ui.button;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import currency.model.CurrencyModel;

public class OpenCalculatorButtonActionListener extends ListTableActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();

        if (selectedRow == -1 || selectedRow == table.getRowCount() || table.isEditing()) {
            return;
        }
        
        CurrencyModel model = (CurrencyModel)list.get(selectedRow);
        
		String[] contextPaths = new String[] { "app-calculator.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(contextPaths);
		
		CalculateButtonActionListener conversionButton = (CalculateButtonActionListener)context.getBean("calculateButtonActionListener");
		conversionButton.setConversionRate(model.getValue());
		
		JTextField textFrom = (JTextField)context.getBean("infoTextfield");
		textFrom.setText("Convert " + model.getFrom() + " to " + model.getTo() + " by " + model.getValue());
	}

}
