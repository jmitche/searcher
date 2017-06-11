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
			//System.out.println("COPYING");
			Vector<String> patentURLs = classes.elementAt(classes.size() - 1).getPatentURLs();
			
			for (int i = 0; i < patentURLs.size() /*10*/; i++) {
				patents.add(new Patent(patentURLs.elementAt(i)));
			}
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
	
	public Object[][] getPatentData() {
		Object[][] patentData = new Object[patents.size()][1];
		System.out.println(patents.size());
		for (int i = 0; i < patents.size(); i++) {
			patentData[i] = patents.elementAt(i).getData();
		}
		
		return patentData;
	}
	
	public int getClassCount() {
		return classes.size();
	}
}
