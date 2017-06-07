import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class Class {
	private String classID;
	private String className;
	private int resultCount;
	private String searchURL;
	Vector<String> patentURLs = new Vector<String>();
	
	/**
	 * Constructor
	 */
	public Class(String classID, String className) {
		this.classID = classID;
		this.className = className;

		searchURL = "http://www.freepatentsonline.com/result.html?p=1&srch=xprtsrch&query_txt=" + classID.replaceAll("/", "%2F") + "&uspat=on&usapp=on&eupat=on&pct=on&date_range=all&stemming=on&sort=relevance&search=Search";	
		
		URL url;
		InputStream is = null;
		BufferedReader br = null;
	    String line;
	    
		try {
			url = new URL(searchURL);
			is = url.openStream();  // throws an IOException
			br = new BufferedReader(new InputStreamReader(is));
			
			boolean done = false;
	        while (!done) {
				line = br.readLine();
				if (line == null)
					done = true;
				else if (line.contains("Matches 1 - 50 out of")) {
					String count = line.substring(line.indexOf('f') + 1, line.indexOf('<') - 1);
	        		count = count.replaceAll(" ","");
	        		
	        		//System.out.println(count);
	        		resultCount = Integer.parseInt(count);
	        		System.out.println(resultCount);
	        		
	        		Vector<String> resultPageURLs = new Vector<String>();
	        		
	        		// Generate resultPageURLs
	        		for (int i = 0; i < resultCount / 50 + resultCount % 50; i++) {
	        			if (i == 0)
	        	    		resultPageURLs.add(searchURL);
	        	    		else {
	        	    			//edit urlString
	        	    			resultPageURLs.add(searchURL.replace("p=1", "p=" + (i + 1)));
	        	    			//add urlString
	        	    		}
	        		}
		        		
		        		
	        		// Fetch URLs from all results pages
	            	for (int i = 0; i < resultPageURLs.size(); i++) {
	            		url = new URL(resultPageURLs.get(i));
	        	        is = url.openStream();  // throws an IOException
	        	        br = new BufferedReader(new InputStreamReader(is));
	        	        
	        	        boolean done2 = false;
	        	        while (!done2) {
	        	        	line = br.readLine();
	        	        	if (line == null) {
	        	        		done2 = true;
	        	        	}
	        	        	else if (line.contains("legacy-container")) {
	        	        		while (!(line = br.readLine()).contains("</table>")) {
	        	        			if (line.contains("href")) {
	        	        				String suburl = line.substring(line.indexOf('=') + 3, line.indexOf('>') - 1);
	        	        				
	        	        				patentURLs.add("http://www.freepatentsonline.com/" + suburl);
	        	        				done2 = true;
	        	        				System.out.println(patentURLs.size() + ": " + patentURLs.get(patentURLs.size() - 1));
	        	        			}
	        	        		}
	        	        	}
	        	        }
	            	}
		        	
		        	done = true;
	        	}
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object[] getData() {
		Object[] data = new Object[3];
		
		data[0] = classID;
		data[1] = className;
		data[2] = resultCount;
		
		return data;
	}
	
	
}