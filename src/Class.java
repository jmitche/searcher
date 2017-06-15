import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
		
		//System.out.println(searchURL);
		
		URL url;
		InputStream is = null;
		BufferedReader br = null;
	    String line;
	    
	    //try {
			//Document doc = Jsoup.connect(searchURL).get();
			
			//System.out.println(doc.toString());
			
			//Elements elements = doc.select("div[class=well well-small]");
			//System.out.println(elements.size());
			//System.out.println(elements.first().toString());
			
			
			
			//Elements elements = doc.select("div[class=legacy-container]");
			//System.out.println("# of elements: " + elements.size());
			//System.out.println(elements.get(0).select("a[href]").attr("abs:href").toString());
			
			
			
			
			
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	    
	    

		try {
			url = new URL(searchURL);
			
			//System.out.println(url.toString());
			
			is = url.openStream();  // throws an IOException
			br = new BufferedReader(new InputStreamReader(is));
			
			// Read page
			boolean done = false;
	        while (!done) {
				line = br.readLine();
				//System.out.println(line);
				if (line == null) {
					done = true;
					//System.out.println("#####");
				}
				else if (line.contains("Matches 1 - 50 out of")) {
					String count = line.substring(line.indexOf('f') + 1, line.indexOf('<') - 1);
	        		count = count.replaceAll(" ","");
	        		
	        		//System.out.println(count);
	        		resultCount = Integer.parseInt(count);
	        		//System.out.println(resultCount);
	        		
	        		Vector<String> resultPageURLs = new Vector<String>();
	        		
	        		// Generate resultPageURLs
	        		int resultPageCount = resultCount / 50;
	        		if (resultCount % 50 != 0)
	        			resultPageCount++;
	        		for (int i = 0; i < resultPageCount; i++) {
	        			if (i == 0)
	        	    		resultPageURLs.add(searchURL);
	        	    		else {
	        	    			//edit urlString
	        	    			resultPageURLs.add(searchURL.replace("p=1", "p=" + (i + 1)));
	        	    			//add urlString
	        	    		}
	        		}
	        		
	        		System.out.println(resultPageURLs.size() + " pages of results");
		        		
		        		
	        		// Fetch URLs from all results pages
	        		int num = 1;
	            	for (int i = 0; i < resultPageURLs.size(); i++) {
	            		url = new URL(resultPageURLs.get(i));
	        	        is = url.openStream();  // throws an IOException
	        	        br = new BufferedReader(new InputStreamReader(is));
	        	        
	        	        boolean done2 = false;
	        	        while (!done2) {
	        	        	line = br.readLine();
	        	        	if (line == null) {
	        	        		//System.out.println("@@@@@");
	        	        		done2 = true;
	        	        	}
	        	        	else if (line.contains("legacy-container")) {
	        	        		//System.out.println("!!!!!!!!!!!!!!!");
	        	        		while (!(line = br.readLine()).contains("</table>")) {
	        	        			if (line.contains("href")) {
	        	        				String suburl = line.substring(line.indexOf('=') + 3, line.indexOf('>') - 1);
	        	        				
	        	        				//System.out.println(suburl);
	        	        				
	        	        				patentURLs.add("http://www.freepatentsonline.com/" + suburl);
	        	        				done2 = true;
	        	        				//System.out.println(patentURLs.size() + ": " + patentURLs.get(patentURLs.size() - 1));
	        	        				num++;
	        	        			}
	        	        		}
	        	        	}
	        	        }
	            	}
		        	
		        	done = true;
	        	}
				else if (line.contains("legacy-container")) {
	        		//System.out.println("!!!!!!!!!!!!!!!");
	        		boolean done2 = false;
	        		while (!(line = br.readLine()).contains("</table>")) {
	        			if (line.contains("href")) {
	        				String suburl = line.substring(line.indexOf('=') + 3, line.indexOf('>') - 1);
	        				
	        				//System.out.println(suburl);
	        				
	        				patentURLs.add("http://www.freepatentsonline.com/" + suburl);
	        				//done2 = true;
	        				//System.out.println(patentURLs.size() + ": " + patentURLs.get(patentURLs.size() - 1));
	        			}
	        		}
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
	
	public Vector<String> getPatentURLs() {
		return patentURLs;
	}
	
	
}