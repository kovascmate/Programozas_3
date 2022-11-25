package frame;
import javax.swing.*;

import circuit_element.Circuit_Element;
import file_management.File_management;
import table.Table;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame {
	public Frame() {
		System.out.println("Frame kon");
		t						= new Table();	
		p_buttons				= new JPanel();
		b_refresh 				= new JButton("Refresh");
		b_autofresh 			= new JButton("Autofresh");
		b_stop 					= new JButton("Stop");
		b_quit 					= new JButton("Quit");
		b_save					= new JButton("Save");
		timer 					= new Timer(200,new TickActionListener());
		
		b_quit.addActionListener(new QuitButtonActionListener());
		b_autofresh.addActionListener(new AutoButtonActionListener());
		b_stop.addActionListener(new StopButtonActionListener());
		b_refresh.addActionListener(new RefreshButtonActionListener());
		b_save.addActionListener(new SaveButtonActionListener());
		timer.setInitialDelay(0);
		timer.setRepeats(true);
		System.out.println("Frame end");
	}
	public Frame(Circuit_Element[][] input) {
		System.out.println("Frame kona");
		t						= new Table(input);	
		p_buttons				= new JPanel();
		b_refresh 				= new JButton("Refresh");
		b_autofresh 			= new JButton("Autofresh");
		b_stop 					= new JButton("Stop");
		b_quit 					= new JButton("Quit");
		b_save					= new JButton("Save");
		timer 					= new Timer(200,new TickActionListener());
		
		b_quit.addActionListener(new QuitButtonActionListener());
		b_autofresh.addActionListener(new AutoButtonActionListener());
		b_stop.addActionListener(new StopButtonActionListener());
		b_refresh.addActionListener(new RefreshButtonActionListener());
		b_save.addActionListener(new SaveButtonActionListener());
		timer.setInitialDelay(0);
		timer.setRepeats(true);
	}
	private JFrame fi;
	private Timer timer;
	private JPanel p_buttons;
	private Table t;
	private JButton b_refresh;
	private JButton b_autofresh;
	private JButton b_stop;
	private JButton b_quit;
	private JButton b_save;
	
	public class SaveButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			File_management fm = new File_management();
			fm.saving("savedgame.txt",t);		
		}
	}
	public class TickActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			t.ujkor();
			refresh();
			
		}
	}
	public class RefreshButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			t.ujkor();
			refresh();
		}		
	}
	public class AutoButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			timer.start();
		}
	}
	public class StopButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			timer.stop();
		}	
	}
	public class QuitButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	public void refresh() {
		fi.remove(t.getPanel());
		fi.add(t.getPanel(),BorderLayout.CENTER);		
	}
	
	public void megjelenit(JFrame f) {
		timer.start();
		fi = f;
		f.repaint();
		f.add(t.getPanel(),BorderLayout.CENTER);
		p_buttons.add(b_refresh);
		p_buttons.add(b_autofresh);
		p_buttons.add(b_stop);
		p_buttons.add(b_save);
		p_buttons.add(b_quit);
		f.add(p_buttons,BorderLayout.SOUTH);
		f.validate();
		f.setVisible(true);
	}
}