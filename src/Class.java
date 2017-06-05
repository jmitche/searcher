
public class Class {
	private String classID;
	private String searchURL;
	private String className;
	private int resultCount;
	
	
	/**
	 * Constructor
	 */
	public Class(String classID) {
		this.classID = classID;
		searchURL = "http://www.freepatentsonline.com/result.html?p=1&srch=xprtsrch&query_txt=" + classID.replaceAll("/", "%2F") + "&uspat=on&usapp=on&eupat=on&pct=on&date_range=all&stemming=on&sort=relevance&search=Search";
		
	}
}