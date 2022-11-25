package circuit_element;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.awt.event.*;
import javax.swing.JButton;

public class Circuit_Element extends JButton implements Serializable {
	private static final long serialVersionUID = 1L;
	public enum Condition{
		Empty,
		Electron_head,
		Electron_tail,
		Connector
	}
	public enum Direction{
		Left,
		Right
	}
	
	protected Condition con;
	public Circuit_Element(Condition c) {
		System.out.print(",");
		setLayout(null);
		setPreferredSize(new Dimension(28,28));
		this.setCondition(c);
		this.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {}
	    });
		this.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
	    	if (e.getButton() == MouseEvent.BUTTON3) {
	    		setChange(Circuit_Element.Direction.Right);
	        	}
	        else if(e.getButton() == MouseEvent.BUTTON1){
	        	setChange(Circuit_Element.Direction.Left);
	        	}
	    	}
		});
		
	}
	public Circuit_Element() {
		System.out.print(".");
		setLayout(null);
		setPreferredSize(new Dimension(28,28));
		setBackground(Color.GRAY);
		con = Circuit_Element.Condition.Empty;
		
		this.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {}
	    });
		this.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
	    	if (e.getButton() == MouseEvent.BUTTON3) {
	    		setChange(Circuit_Element.Direction.Right);
	        	}
	        else if(e.getButton() == MouseEvent.BUTTON1){
	        	setChange(Circuit_Element.Direction.Left);
	        	}
	    	}
		});
	}
	public void setChange(Circuit_Element.Direction input) {
			if(this.getCondition() == Circuit_Element.Condition.Empty) {
				if(input == Circuit_Element.Direction.Left) {
					setCondition(Circuit_Element.Condition.Connector);		
				}
				else if(input == Circuit_Element.Direction.Right) {
					setCondition(Circuit_Element.Condition.Electron_head);
				}
			}
			else if(this.getCondition() == Circuit_Element.Condition.Connector) {
				if(input == Circuit_Element.Direction.Left) {
					setCondition(Circuit_Element.Condition.Electron_head);
				}
				else if(input == Circuit_Element.Direction.Right) {
					setCondition(Circuit_Element.Condition.Empty);
				}
			}
			else if(this.getCondition() == Circuit_Element.Condition.Electron_head ||this.getCondition() == Circuit_Element.Condition.Electron_tail) {
				if(input == Circuit_Element.Direction.Right) {
					setCondition(Circuit_Element.Condition.Connector);
				}
			}
		}
	public void setCondition(Condition c) {
			this.con = c;
			switch (c) {
			case Empty:
				setBackground(Color.GRAY);
				break;
			case Electron_head:
				setBackground(Color.MAGENTA);
				break;
			case Electron_tail:
				setBackground(Color.BLUE);
				break;
			case Connector:
				setBackground(Color.YELLOW);
				break;
			}
		}
	public Condition getCondition(){
			return this.con;
		}
}