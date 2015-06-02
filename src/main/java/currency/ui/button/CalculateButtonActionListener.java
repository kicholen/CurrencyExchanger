package currency.ui.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class CalculateButtonActionListener implements ActionListener {
	private JTextField valueTextfield;
	private JTextField resultTexfield;
	private float conversionRate;
	
	public void setValueTextfield(JTextField valueTextfield) {
		this.valueTextfield = valueTextfield;
	}
	
	public void setResultTextfield(JTextField resultTexfield) {
		this.resultTexfield = resultTexfield;
	}
	
	public void setConversionRate(float conversionRate) {
		this.conversionRate = conversionRate;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		float value = Float.parseFloat(valueTextfield.getText());

        /*if (table.isEditing()) {
            return;
        }*/
		
		String resultText = String.valueOf(value * conversionRate);
        resultTexfield.setText(resultText);
        resultTexfield.revalidate();
	}
}
