import java.util.Vector;

public class Project {
	private Vector<Class> classes = new Vector<Class>();
	
	
	/**
	 * Constructor
	 */
	public Project() {
		
	}
	
	public Vector<Class> getClasses() {
		return classes;
	}
	
	public void addClass(String classID, String className) {
		
		classes.add(new Class(classID, className));
	}
	
	public Object[][] getClassData() {
		Object[][] classData = new Object[classes.size()][3];
		System.out.println(classes.size());
		for (int i = 0; i < classes.size(); i++) {
			classData[i] = classes.elementAt(i).getData();
		}
		
		return classData;
	}
	
	public int getClassCount() {
		return classes.size();
	}
}
