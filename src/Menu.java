import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
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

public class Menu extends JFrame {
	private Project project;
	
	private JPanel mainPanel;
	
	private JTabbedPane tabbedPane;
	
	private ResultsPanel resultsPanel;
	private TermsPanel termsPanel;
	private ClassesPanel classesPanel;
	private ProjectsPanel projectsPanel;

	
	
	/**
	 * Create the frame.
	 */
	public Menu() {
		project = new Project();
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		setContentPane(mainPanel);
		
		
	}
	
	
	
	private class ResultsPanel extends JPanel {
		
		/**
		 * Constructor
		 */
		public ResultsPanel() {
			
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
					boolean found = false;
					for (int i = 0; i < project.getClasses().size(); i++) {
						if (txtfldNewClassID.getText().equals(project.getClasses().elementAt(i).getData()[0])) {
							found = true;
							System.out.println("Found");
						}
					}
					
					if (!found) {
						project.addClass(txtfldNewClassID.getText(), txtfldNewClassName.getText());
						update();
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