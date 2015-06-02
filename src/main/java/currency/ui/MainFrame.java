package currency.ui;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {
	
    private Dimension _dim;

	public void init() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        _dim = _dim == null ? new Dimension(600, 400) : _dim;
        setSize(_dim);
        setVisible(true);
        setState(Frame.NORMAL);
    }
    
    @Override
    public void setSize(Dimension dim) {
    	_dim = dim;
    	super.setSize(dim);
    }
}