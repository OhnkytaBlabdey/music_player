package gui;

import java.io.File;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import db.sheet_info;

public class FavLists extends IList {
	public FavLists() {
		// TODO Auto-generated constructor stub
		super();
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					System.out.println(e);
				}
			}
		});
		sheet_info[] infos=GlobalVars.getDBSheets().queryAll();
		for(sheet_info info:infos) {
			addItem(info.path, info.user+"\t-\t"+info.name, null, 30, 30);
		}
	}
}
