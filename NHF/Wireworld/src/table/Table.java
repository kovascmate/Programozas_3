package table;

import javax.swing.JPanel;

import circuit_element.Circuit_Element;
public class Table{
	public JPanel panel;
	public Circuit_Element[][] playfield ; 
	public Table() {
		System.out.println("Table");
		panel = new JPanel();
			playfield = new Circuit_Element[20][20];
			for(int oszlop = 0; oszlop<20;oszlop++){
				for(int sor = 0; sor<20;sor++) {
					playfield[sor][oszlop] = new Circuit_Element();
				}
			}
		System.out.println("Table kon");
	}
	public Table(Circuit_Element[][] input)
	{
		panel = new JPanel();
		playfield = input;
	}
	
	public Circuit_Element[][] getTable(){
		return this.playfield;
	}
	public void setTable(Circuit_Element[][] uj){
		this.playfield = uj;		
	}
	public void setTableElement(Circuit_Element.Condition elem,int oszlop, int sor) {
		this.playfield[sor][oszlop].setCondition(elem);
	}
	public void ujkor() {
		Circuit_Element.Condition[][] copy= new Circuit_Element.Condition[20][20];
		boolean masodik[][] = new boolean[20][20];
		for(int sor = 0; sor < 20; sor++) {
			for(int oszlop = 0; oszlop <20;oszlop++) {
				masodik[sor][oszlop] = false;
			}
		}
		Circuit_Element[][] playfield = this.getTable();
		
		for(int sor = 0; sor < 20; sor++) {
			for(int oszlop = 0; oszlop <20;oszlop++){
				if(copy[sor][oszlop] ==(Circuit_Element.Condition.Electron_head)) {}
				else if(playfield[sor][oszlop].getCondition()==Circuit_Element.Condition.Connector ) {
					copy[sor][oszlop] =(Circuit_Element.Condition.Connector);
				}
				else if(playfield[sor][oszlop].getCondition()==Circuit_Element.Condition.Empty){
					copy[sor][oszlop] =(Circuit_Element.Condition.Empty);
				}
				else if(playfield[sor][oszlop].getCondition()==Circuit_Element.Condition.Electron_tail) {
					copy[sor][oszlop] =(Circuit_Element.Condition.Connector);
				}
				else if(playfield[sor][oszlop].getCondition()==Circuit_Element.Condition.Electron_head){
					for(int i = -1; i<2;i++) {
						for(int j = -1;j<2;j++) {
							if(sor+i>0 && sor+i<20 && oszlop+j>0 && oszlop+j<20) {
								if(masodik[sor+i][oszlop+j] == true) {
									copy[sor+i][oszlop+j] =(Circuit_Element.Condition.Connector);
								}
								else if(copy[sor+i][oszlop+j] ==(Circuit_Element.Condition.Electron_head)){
									masodik[sor+i][oszlop+j] = true;
								}
								else if(playfield[sor+i][oszlop+j].getCondition()==Circuit_Element.Condition.Connector){
									copy[sor+i][oszlop+j] =(Circuit_Element.Condition.Electron_head);
								}
							}
						}
					}
					copy[sor][oszlop] =(Circuit_Element.Condition.Electron_tail);
				}	
				else if(playfield[sor][oszlop].getCondition()==Circuit_Element.Condition.Connector){
					copy[sor][oszlop] =(Circuit_Element.Condition.Connector);
					}
				}
		}
		for(int sor = 0; sor < 20; sor++) {
			for(int oszlop = 0; oszlop <20;oszlop++) {
				playfield[sor][oszlop].setCondition(copy[sor][oszlop]);
			}
		}
}
	public JPanel getPanel() {
		for(int oszlop = 0; oszlop<20;oszlop++){
			for(int sor = 0; sor<20;sor++) {
				panel.add(playfield[sor][oszlop]);
			}
		}
		return panel;
	}
	public Circuit_Element[][] getPlayfield(){
		return this.playfield;
	}
}
