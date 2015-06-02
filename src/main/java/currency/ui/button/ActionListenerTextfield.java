package currency.ui.button;

import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ActionListenerTextfield extends JTextField {
	private ActionListener actionListener;

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void init() {
        this.addActionListener(actionListener);
    }
}
