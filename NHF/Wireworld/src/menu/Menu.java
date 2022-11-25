package menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import frame.Frame;
import file_management.File_management;

public class Menu {
	
	private JFrame 	menu;
	private JButton game_name;
	private JButton new_game;
	private JButton saved_game;
	private JButton quit;
	private JPanel 	panel ;
	private File_management fm;
	
	public Menu(){
	 	
		menu 			= new JFrame();
		game_name 		= new JButton("Wireworld");
		new_game 		= new JButton("New game");
		saved_game 		= new JButton("Saved game");
		quit 			= new JButton("Quit");
		panel 			= new JPanel();
		fm				= new File_management();
		
		//Egyéb beállítások
				menu.setResizable(false);
				menu.setSize(700,800);
		
		//ActionListenerek
		game_name.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {}
		    });
		game_name.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	if (e.getButton() == MouseEvent.BUTTON2) {
		    		System.out.println("titkos mikentyű");
		        	}
		    }
		});
		new_game.addActionListener(new New_GameButtonActionListener());
		saved_game.addActionListener(new Saved_GameButtonActionListener());
		quit.addActionListener(new QuitButtonActionListener());
		
		//Csoportosítás
		panel.add(new_game);
		panel.add(saved_game );
		panel.add(quit);
			
		//Framehez adás
		menu.add(game_name,BorderLayout.NORTH);
		menu.add(panel,BorderLayout.CENTER);
		
		menu.setVisible(true);
	}
	//ActionListenerek
	public class Game_NameButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {}
	}
	public class New_GameButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Frame f = new Frame();
			menu.remove(game_name);
			menu.remove(panel);
			f.megjelenit(menu);
		}
		
	}
	public class Saved_GameButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Frame f = new Frame(fm.loading("savedgame.txt"));
				menu.remove(game_name);
				menu.remove(panel);
				f.megjelenit(menu);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("savedgamelistener");
		}
	}
	public class QuitButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("quitlistener");
			System.exit(0);
		}
	}
}
