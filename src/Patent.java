import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Patent {
	URL url;
	String numberStr;
	String titleStr;
	String abstractStr;
	String claimsStr;
	String descriptionStr;
	
	
	
	/**
	 * Constructor
	 */
	public Patent(String urlString) {
		InputStream is = null;
		BufferedReader br = null;
	    String line;
	    boolean done = false;
	    
	    
	    
	    
	    
		try {
			//url = new URL(urlString);
			//is = url.openStream();  // throws an IOException
			//br = new BufferedReader(new InputStreamReader(is));
			Elements elements;
			
			/// Main
			// ID number
			
			Document doc = Jsoup.connect(urlString).get();
			
			elements = doc.select("form[name=biblio]");
			Elements elements3 = elements.select("input[name=patent]");
			numberStr = elements3.first().attr("value");
			
			
			elements = doc.select("div[class=disp_doc2]");
			
			for (int i = 0; i < elements.size(); i++) {
				String eTitle = "no title";
				String eText = "no text";
				
				Elements elements2 = elements.get(i).select("div[class=disp_elm_title]");
				if (elements2.size() != 0) {
					eTitle = elements2.select("div[class=disp_elm_title]").first().text();
				}
				
				elements2 = elements.get(i).select("div[class=disp_elm_text]");
				if (elements2.size() != 0) {
					eText = elements2.select("div[class=disp_elm_text]").first().text();
				}
				
				if (eTitle.equals("")) {
					
				}
				else if (eTitle.equals("Title:")) {
					titleStr = eText;
				}
				else if (eTitle.equals("Abstract:")) {
					abstractStr = eText;
				}
				else if (eTitle.equals("Inventors:")) {
					 = eText;
				}
				else if (eTitle.equals("Application Number:")) {
					 = eText;
				}
				else if (eTitle.equals("Publication Date:")) {
					 = eText;
				}
				else if (eTitle.equals("Filing Date:")) {
					 = eText;
				}
				else if (eTitle.equals("Asignee:")) {
					 = eText;
				}
				else if (eTitle.equals("Primary Class:")) {
					 = eText;
				}
				else if (eTitle.equals("")) { // Other classes
					 = eText;
				}
				else if (eTitle.equals("International Classes:")) {
					 = eText;
				}
				else if (eTitle.equals("")) {
					 = eText;
				}
				
				
				
				
				
				
				// Images
				
				// Assignee
				
				
				
				// Attorneys/Agents
				
				// App Num
				
				// Pub Date
				
				// File Date
				
				// Referenced by
				
				// Primary Class
				
				// International Classes
				
				// Field of Search
				
				// Primary Examiner

				
				/// Claims
				
				/// Description
				
				/// Citations
				
				/// PDF
				
				
				
				//System.out.println(eTitle);
				//System.out.println(eText);
			}
			
			System.out.println(numberStr + ": " + titleStr);
			//System.out.println(doc.toString());
			
			//Elements elements = doc.select("div[class=well well-small]");
			//System.out.println(elements.size());
			//System.out.println(elements.first().toString());
			
			
			
			//Elements elements = doc.select("div[class=legacy-container]");
			//System.out.println("# of elements: " + elements.size());
			//System.out.println(elements.get(0).select("a[href]").attr("abs:href").toString());
			
		

			//done = false;
			//while (!(line = br.readLine()).contains("<input type=\"hidden\" name=\"patent\"")) {

			//}
			
			//numberStr = line.substring(line.indexOf("ue=\"") + 4, line.indexOf("\"/>"));
			//if (titleStr == "")
				//titleStr = "no title";
			//System.out.println(numberStr);
			
			// Title - 207
			//done = false;
			//while (!(line = br.readLine()).contains("<input type=\"hidden\" name=\"title\"")) {

			//}
			
			//titleStr = line.substring(line.indexOf("ue=\"") + 4, line.indexOf("\"/>"));
			//if (titleStr == "")
				//titleStr = "no title";
			//System.out.println(titleStr);
			
			/// Biblio
			// Abstract - <!--	Abstract  -->
			//done = false;
			//while (!(line = br.readLine()).contains("<!--\tAbstract  -->")) {
				
			//}
			//System.out.println(line);
			//done = false;
			
			//int num = 0;
			//while (!done) {
			//	line = br.readLine();
			//	if (line == null) {
					//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			//		done = true;
			//	}
			//	else if (line.contains("<div class=\"disp_elm_text\">")) {
			//		line = br.readLine();
			//		//System.out.println(line);
			//		
			//		// Delete </div> at end
			//		line = line.replaceAll("</div>", "");
			//		
			//		// Delete leading and trailing whitespaces
			//		line = line.trim();
					
					
					
					
					
					// Delete text augmentations (highlight, bold, etc...)
			//		abstractStr = line;
					//.substring(1, 3 /*line.indexOf("</")*/);
			//		done = true;
			//	}
					
				//System.out.println(line);
			//	num++;
			//}
			
			//if (abstractStr == null)
			//	abstractStr = "ABSTRACT";
			
			
			//System.out.println(abstractStr);
			
			// Images
			
			// Assignee
			
			// Inventors
			
			// Attorneys/Agents
			
			// App Num
			
			// Pub Date
			
			// File Date
			
			// Referenced by
			
			// Primary Class
			
			// International Classes
			
			// Field of Search
			
			// Primary Examiner

			
			/// Claims
			
			/// Description
			
			/// Citations
			
			/// PDF
			
			
/*
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
	        }*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public Object[] getData() {
		Object[] data = new Object[3];
		
		data[0] = numberStr;
		data[1] = titleStr;
		data[2] = abstractStr;
		
		return data;
	}
}
