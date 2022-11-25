package file_management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import table.Table;
import circuit_element.Circuit_Element;

public class File_management {
		
	public void saving(String filename, Table t) {
		try {
			FileOutputStream f = new FileOutputStream(new File(filename));
			ObjectOutputStream o = new ObjectOutputStream(f);
			for(int sor = 0; sor< 20; sor++) {
				for(int oszlop = 0; oszlop< 20; oszlop++) {
					o.writeObject(t.getPlayfield()[sor][oszlop]);
				}
			}
			o.close();
			f.close();
		}
		catch(Exception e){
		}
	}
	public Circuit_Element[][] loading(String filename) throws Exception{
		FileInputStream fi;
		Circuit_Element[][] input = new Circuit_Element[20][20];
		ObjectInputStream oi;
		try {
			fi = new FileInputStream(new File(filename));
			oi = new ObjectInputStream(fi);
			for(int sor = 0; sor< 20; sor++) {
				for(int oszlop = 0; oszlop< 20; oszlop++) {
					input[sor][oszlop] = new Circuit_Element(((Circuit_Element) oi.readObject()).getCondition());
				}
			}
			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return input;
	}	
}