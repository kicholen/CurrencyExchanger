package currency.ui.button;

import java.awt.event.ActionEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import currency.model.CurrencyModel;

public class OpenGraphButtonActionListener extends ListTableActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();

        if (selectedRow == -1 || selectedRow == table.getRowCount() || table.isEditing()) {
            return;
        }
        
        CurrencyModel model = (CurrencyModel)list.get(selectedRow);
        
		String[] contextPaths = new String[] { "app-graph.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(contextPaths);
		
		DrawGraphButtonActionListener drawButton = (DrawGraphButtonActionListener)context.getBean("drawGraphButtonActionListener");
		drawButton.setFrom(model.getFrom());
		drawButton.setTo(model.getTo());
	}

}
