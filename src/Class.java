import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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
		className = "n/a";

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
				else {
					if (line.contains("Matches 1 - 50 out of")) {
						String count = line.substring(line.indexOf('f') + 1, line.indexOf('<') - 1);
		        		count = count.replaceAll(" ","");
		        		
		        		//System.out.println(count);
		        		resultCount = Integer.parseInt(count);
		        		System.out.println(resultCount);
		        		
		        		
		        		
		        		//for (int i = 0; i < resultCount / 50; i++) {
		        			
		        		//}
		        		
		        		
		        		
		        		done = true;
		        	}
	        	}
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
     // Generate result page URLs
    	//for (int i = 0; i < pageCount; i++) {
    	//	if (i == 0)
    	//		resultPageURLs.add(urlString);
    	//	else {
    			// edit urlString
    	//		resultPageURLs.add(urlString.replace("p=1", "p=" + (i + 1)));
    			// add urlString
    	//	}
    	//}
    	
    	//System.out.println("");
    	
    	
    	
    	// Fetch URLs from all results pages
    	//for (int i = 0; i < resultPageURLs.size(); i++) {
    		//url = new URL(resultPageURLs.get(i));
	        //is = url.openStream();  // throws an IOException
	        //br = new BufferedReader(new InputStreamReader(is));
	        
	        
	        	
        
        
        
        
        
        /*
        while ((line = br.readLine()) != null) {
        	if (line.contains("legacy-container")) {
        		while (!(line = br.readLine()).contains("</table>")) {
        			if (line.contains("href")) {
        				String suburl = line.substring(line.indexOf('=') + 3, line.indexOf('>') - 1);
        				urls.add("http://www.freepatentsonline.com/" + suburl);
        				
        				System.out.println(urls.size() + ": " + urls.get(urls.size() - 1));
        			}
        		}
        	}
        }*/
	}
	
	public Object[] getData() {
		Object[] data = new Object[3];
		
		data[0] = classID;
		data[1] = className;
		data[2] = resultCount;
		
		return data;
	}
	
	
}