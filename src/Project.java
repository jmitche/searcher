import java.util.Collections;
import java.util.Vector;

public class Project {
	private Vector<Class> classes = new Vector<Class>();
	private Vector<Patent> patents = new Vector<Patent>();
	
	
	/**
	 * Constructor
	 */
	public Project() {
		
	}
	
	public Vector<Class> getClasses() {
		return classes;
	}
	
	public void addClass(String classID, String className) {
		boolean found = false;
		for (int i = 0; i < classes.size(); i++) {
			if (classID.equals(classes.elementAt(i).getData()[0])) {
				found = true;
				//System.out.println("Found");
			}
		}
		
		if (!found) {
			classes.add(new Class(classID, className));
			System.out.println("COPYING");
			Vector<String> patentURLs = classes.elementAt(classes.size() - 1).getPatentURLs();
			
			
			
			int num = 0;
			int num2 = 0;
			for (int i = 0; i < patentURLs.size() /*10*/; i++) {
				boolean found2 = false;
				for (int i2 = 0; i2 < patents.size(); i2++) {
					if (patentURLs.elementAt(i).equals(patents.elementAt(i2).getURL().toString())) {
						found2 = true;
						num2++;
						System.out.println("DUPE");
					}
				}
				
				if (!found2) {
					System.out.println("NEW");
					num++;
					patents.add(new Patent(patentURLs.elementAt(i)));
				}
			}
			System.out.println("Patents in this class: " + patentURLs.size());
			System.out.println("New Patents: " + num);
			System.out.println("Duplicate Patents: " + num2);
			System.out.println("Patents in Project: " + patents.size());
		}
	}
		
	
	
	public Object[][] getClassData() {
		Object[][] classData = new Object[classes.size()][3];
		//System.out.println(classes.size());
		for (int i = 0; i < classes.size(); i++) {
			classData[i] = classes.elementAt(i).getData();
		}
		
		return classData;
	}
	
	public Object[][] getPatentData(Vector<String> elements) {
		Object[][] patentData = new Object[patents.size()][1];
		System.out.println(patents.size());
		for (int i = 0; i < patents.size(); i++) {
			patentData[i] = patents.elementAt(i).getData(elements);
		}
		
		return patentData;
	}
	
	public int getClassCount() {
		return classes.size();
	}
	
	public void sortPatentsBy(String property) {
		int sorts = 0;
		do {
			sorts = 0;
			for (int i = 0; i < patents.size() - 1; i++) {
				Vector<String> prop1 = new Vector<String>();
				//Vector<String> prop2;
				
				prop1.add(property);
				
				//System.out.println(patents.elementAt(i).getData(prop1)[0].toString());
				
				if (patents.elementAt(i).getData(prop1)[0] != null && patents.elementAt(i + 1).getData(prop1)[0] != null) {
					if (patents.elementAt(i).getData(prop1)[0].toString().compareToIgnoreCase(patents.elementAt(i + 1).getData(prop1)[0].toString()) > 0) {
						Collections.swap(patents, i, i + 1);
						sorts++;
					}
				}
			}
		} while (sorts != 0);
	}
}
