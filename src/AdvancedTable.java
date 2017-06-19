import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableColumn;

public class AdvancedTable extends JTable {
	Vector<Column> columns;
	//Vector<Row> rows;
	JTable table;
	
	
	
	public AdvancedTable(Vector<String> headerStrings) {
		for (int i = 0; i < headerStrings.size(); i++) {
			Column column = new Column(headerStrings.elementAt(i));
		}
		
		
		
		
		
		
		// Update columnNames from tableHeaders
		String[] patentListColumnNames = new String[tableHeaders.size()];
		for (int i = 0; i < tableHeaders.size(); i++) {
			patentListColumnNames[i] = tableHeaders.elementAt(i);
		}
		
		
		patentListData = project.getPatentData(tableHeaders);
		patentListTable = new JTable(patentListData, patentListColumnNames);
		patentListPane = new JScrollPane(patentListTable);
		add(patentListPane);
		
		patentListTable.getTableHeader().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	if (e.getButton() == 3) {
		    		//JMenuItem itemHide = new JMenuItem("Hide");
			    	//JCheckBox item = new JCheckBox("Title");
			    	JPopupMenu popup = new JPopupMenu();
			    	
			    	for (int i = 0; i < tableHeaderMenuItems.length; i++) {
			    		popup.add(tableHeaderMenuItems[i]);
			    	}
			    	
			    	popup.addPopupMenuListener(new PopupMenuListener() {
						@Override
						public void popupMenuCanceled(PopupMenuEvent e) {
							update();
							
						}

						@Override
						public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
							// TODO Auto-generated method stub
							
						}
			    	});

			    	if (e.isPopupTrigger()) {
			    		popup.show(e.getComponent(), e.getX(), e.getY());
			    		
			    	}
		
			        System.out.println("Right Click");
		    	}
		    	else if (e.getButton() == 1) {
		    		int index = patentListTable.convertColumnIndexToModel(patentListTable.columnAtPoint(e.getPoint()));
			        if (index >= 0) {
			        	project.sortPatentsBy(patentListTable.getColumnName(index));
			            System.out.println("Left Click");
			        	update();
			        }
		    	}
		    }
		});
		
		//this.revalidate();
		this.repaint();
	}
		
		
	}
	
	
	public Vector<String> getActiveColumnHeaderStrings() {
		Vector<String> activeColumnHeaderStrings = new Vector<String>();
		
		for (int i = 0; i < columns.size(); i++) {
			if (columns.elementAt(i).isActive()) {
				activeColumnHeaderStrings.add(columns.elementAt(i).getHeaderString());
			}
		}
		
		return activeColumnHeaderStrings;
		
	}
	
	
	
	
	
	
	
	
	
	public void update(Vector<Object> tableData) {
		
		
		//Vector<String> tableHeaders = getActiveColumnHeaderStrings();
		
		
		
		table = new JTable(getActiveColumnHeaderStrings(), tableData);
		
		// Format JTable columns based on columnData
		
		
		
		
		
		for (int i = 0; i < 3; i++) {
	        column = table.getColumnModel().getColumn(i);
	        if (i == 2) {
	            column.setPreferredWidth(100); //sport column is bigger
	        } else {
	            column.setPreferredWidth(50);
	        }
	    }
	}
	
	
	
	
	
	
	
	
	
	private class Column extends TableColumn {
		private boolean active;
		
		
		
		//public Column(String headerString) {
		//	setHeaderValue(headerString);
		//}
		
		
		
		//public String getHeaderString() {
		//	return getHeaderValue().toString();
		//}
		
		
	}
}
