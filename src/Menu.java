import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Vector;
import java.awt.event.ItemEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JFrame {
	private Project project;
	
	private JPanel mainPanel;
	
	private JTabbedPane tabbedPane;
	
	private ResultsPanel resultsPanel;
	private TermsPanel termsPanel;
	private ClassesPanel classesPanel;
	private ProjectsPanel projectsPanel;
	private JTable table;

	
	
	/**
	 * Create the frame.
	 */
	public Menu() {
		project = new Project();
		
		
		
		// Variables
		String[] patentListColumnNames = {"#","Title", "Abstract"};
				
		Object[][] patentListData = {
				{"001", "One", "The number 1"},
				{"002", "Two", "The number 2"},
				{"003", "Three", "The number 3"}
		};
		
		
		
		
		//project.addClass("a63b31/11");
		//project.addClass("002", "Two", 2);
		//project.addClass("003", "Three", 3);
		//project.addClass("004", "Four", 4);
		//project.addClass("005", "Five", 5);
		
		mainPanel = new JPanel();
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		resultsPanel = new ResultsPanel();
		termsPanel = new TermsPanel();
		classesPanel = new ClassesPanel();
		projectsPanel = new ProjectsPanel();
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 361);
		
		
		// Borders and layouts
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new CardLayout(0, 0));
		
		tabbedPane.addTab("Results", null, resultsPanel, null);
		tabbedPane.addTab("Terms", null, termsPanel, null);
		tabbedPane.addTab("Classes", null, classesPanel, null);
		tabbedPane.addTab("Projects", null, projectsPanel, null);
		
		mainPanel.add(tabbedPane, "name_421621166267166");
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		

		//patentListData = {};//project.getPatentData();
		
		JTable patentListTable = new JTable(patentListData, patentListColumnNames);
		patentListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Clicked Menu");
			}
		});
		JScrollPane patentListPane = new JScrollPane(patentListTable);
		
		
		panel.add(patentListPane);
		
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		setContentPane(mainPanel);
		
		
	}
	
	
	
	private class ResultsPanel extends JPanel {
		// Variables
		Vector<String> tableHeaders = new Vector<String>();
		
		
		
		String[] patentListColumnNamesMaster = {"Title", "Abstract", "Inventors", "Application Number", "Publication Date", "Filing Date", "Assignee", "Primary Class", "Other Classes", "International Classes", "ECLA Classes", "Field of Search", "Primary Examiner", "Assistant Examiner", "Attorney, Agent, or Firm", "Claims", "Description"};
		JCheckBox[] tableHeaderMenuItems = new JCheckBox[patentListColumnNamesMaster.length];
		
		Object[][] patentListData;
		
		
		// Components
		JTable patentListTable;
		JScrollPane patentListPane;

		// JPanels
		//JPanel addClassPanel = new JPanel();

		/**
		 * Constructor
		 */
		public ResultsPanel() {
			
			tableHeaders.add("#");
			tableHeaders.add("Title");
			tableHeaders.add("Abstract");
			tableHeaders.add("Claims");
			tableHeaders.add("Description");
			tableHeaders.add("Inventors");
			
			for (int i = 0; i < patentListColumnNamesMaster.length; i++) {
				JCheckBox item = new JCheckBox(patentListColumnNamesMaster[i]);
				for (int i2 = 0; i2 < tableHeaders.size(); i2++) {
					if (patentListColumnNamesMaster[i].equals(tableHeaders.elementAt(i2))) {
						item.setSelected(true);
					}
				}
				item.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//if (e.getSource().toString() == "Title") {
						if (item.isSelected()) {
							tableHeaders.add(item.getText());
							//update();
						}
						else {
							for (int i = 0; i < tableHeaders.size(); i++) {
								if (tableHeaders.elementAt(i).equals(item.getText())) {
									tableHeaders.remove(i);
									i = tableHeaders.size();
									//update();
								}
							}
						}
						
						
						
							//boolean found = false;
							//for (int i = 0; i < patentListColumnNames.length; i++) {
							//	if (patentListColumnNames[i].equals("Title")) {
							//		tableHeaders.remove(i);
							//		found = true;
							//		update();
							//	}
							//}
							//if (!found) {
							//	tableHeaders.add("Title");
							//	update();
							//}
							
						
						//}
					}
				});
				tableHeaderMenuItems[i] = item;
			}
			
			System.out.println("!!!!! " + tableHeaderMenuItems.length);
			
			
			
			
			
			//patentListColumnNames = new String[tableHeaders.size()];
			
			//for (int i = 0; i < tableHeaders.size(); i++) {
			//	patentListColumnNames[i] = tableHeaders.elementAt(i);
			//}
			
			patentListData = project.getPatentData(tableHeaders);
			
			//patentListTable = new JTable(patentListData, patentListColumnNames);
			patentListPane = new JScrollPane(patentListTable);
			
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

			 
			//classListTable.setFillsViewportHeight(true);


			add(patentListPane);
		}
		
		public void update() {
			removeAll();
			
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
			        //int r = table.rowAtPoint(e.getPoint());
			        //if (r >= 0 && r < table.getRowCount()) {
			        //    table.setRowSelectionInterval(r, r);
			        //} else {
			        //    table.clearSelection();
			        //}

			        //int rowindex = table.getSelectedRow();
			        //if (rowindex < 0)
			        //    return;
			        //if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
			            System.out.println("Click");
			        	//JPopupMenu popup = createYourPopUp();
			            //popup.show(e.getComponent(), e.getX(), e.getY());
			        //}
			            
			            
			    }
			});
			
			//this.revalidate();
			this.repaint();
		}
	}
	
	
	private class TermsPanel extends JPanel {
		
		/**
		 * Constructor
		 */
		public TermsPanel() {
			//setLayout(new BoxLayout(termsPanel, BoxLayout.X_AXIS));
		}
	}
	
	private class ClassesPanel extends JPanel {
		// Variables
		String[] classListColumnNames = {"Class ID", "Class Name", "# of patents"};
		
		Object[][] classListData;
		
		
		// Components
		JTextField txtfldNewClassID;
		JTextField txtfldNewClassName;
		JTable classListTable;
		JScrollPane classListPane;
		JButton btnAddClass;
		JButton btnDiscover;
		
		// JPanels
		JPanel addClassPanel = new JPanel();

		/**
		 * Constructor
		 */
		public ClassesPanel() {
			classListData = project.getClassData();
			
			txtfldNewClassID = new JTextField();
			txtfldNewClassName = new JTextField();
			classListTable = new JTable(classListData, classListColumnNames);
			classListPane = new JScrollPane(classListTable);
			btnAddClass = new JButton("Add Class");
			btnDiscover = new JButton("Discover");
			
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			txtfldNewClassID.setColumns(10);
			txtfldNewClassName.setColumns(10);
			
			
			
			
			 
			//classListTable.setFillsViewportHeight(true);
			
			btnAddClass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!txtfldNewClassID.getText().replaceAll("\\s", "").equals("")) {
						project.addClass(txtfldNewClassID.getText(), txtfldNewClassName.getText());
						update();
						resultsPanel.update();
					}
				}
			});
			
			addClassPanel.add(txtfldNewClassID);
			addClassPanel.add(txtfldNewClassName);
			addClassPanel.add(btnAddClass);
			addClassPanel.add(btnDiscover);

			add(classListPane);
			add(addClassPanel);
		}
		
		public void update() {
			removeAll();
			
			classListData = project.getClassData();
			classListTable = new JTable(classListData, classListColumnNames);
			classListPane = new JScrollPane(classListTable);
			add(classListPane);
			add(addClassPanel);
			
			//this.revalidate();
			this.repaint();
		}
	}
	
	private class ProjectsPanel extends JPanel {
		JButton btnNewProject = new JButton("New Project");
		
		/**
		 * Constructor
		 */
		public ProjectsPanel() {
			btnNewProject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					project = new Project();
				}
			});
			
			add(btnNewProject);
		}
	}
}