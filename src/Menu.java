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
	private JPanel contentPane;
	private JTextField txtfldNewClassName;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Menu() {
		project = new Project();
		
		
		//String[] classListColumnNames = {"ID", "Name", "# of Patents"};
		//Object[][] classListData = {
		//	    {"001", "One", new Integer(1)},
		//	    {"002", "Two", new Integer(2)},
		//	    {"003", "Three", new Integer(3)},
		//	    {"004", "Four", new Integer(4)},
		//	    {"005", "Five", new Integer(5)}
		//};
		
		
		
		//getContentPane().setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 361);
		
		// Panels
		contentPane = new JPanel();
		
		// Components
		
		
		// Borders and layouts
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout(0, 0));
		
		
		
		
		// Main Panel
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JPanel resultsPanel = new JPanel();
		resultsPanel.setLayout(null);
		JPanel termsPanel = new JPanel();
		
		
		JPanel classesPanel = new JPanel();
		classesPanel.setLayout(new BoxLayout(classesPanel, BoxLayout.X_AXIS));
		
		JTable classListTable = new JTable(classListData, classListColumnNames);
		JScrollPane classListPane = new JScrollPane(classListTable);
		//classesPanel.add(classListTable);
		classesPanel.add(classListPane);
		
		tabbedPane.addTab("Results", null, resultsPanel, null);
		tabbedPane.addTab("Terms", null, termsPanel, null);
		tabbedPane.addTab("Classes", null, classesPanel, null);
		
		setContentPane(contentPane);
		
		
		
		
		
		
		
		
		
		
		
		JPanel addClassPanel = new JPanel();
		classesPanel.add(addClassPanel);
		
		txtfldNewClassName = new JTextField();
		addClassPanel.add(txtfldNewClassName);
		txtfldNewClassName.setColumns(10);
		
		JButton btnAddClass = new JButton("Add Class");
		btnAddClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newClassName = txtfldNewClassName.getText();
				
			}
		});
		addClassPanel.add(btnAddClass);
		
		
		JButton btnDiscover = new JButton("Discover");
		addClassPanel.add(btnDiscover);
		
		contentPane.add(tabbedPane, "name_421621166267166");
		
		JPanel projectsPanel = new JPanel();
		tabbedPane.addTab("Projects", null, projectsPanel, null);
		
		JButton btnNewProject = new JButton("New Project");
		btnNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				project = new Project();
			}
		});
		projectsPanel.add(btnNewProject);
	}
}
