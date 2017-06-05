import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;





public class PatentSearcher {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
/*	
	public static void main(String[] args) {
		
	    URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String line;
	    
	    Vector<Concept> concepts = new Vector<Concept>();
	    
	    Vector<PatentInfo> patents = new Vector<PatentInfo>();
	    Vector<String> resultPageURLs = new Vector<String>();
	    Vector<String> urls = new Vector<String>();
	    Vector<String> searchTerms = new Vector<String>();
	    

	    // Get info from user
	    try {
	    	Scanner reader = new Scanner(System.in);  // Reading from System.in
	    	
	    	System.out.println("Enter a results page URL: ");
	    	String urlString = reader.nextLine();
	    	
	    	System.out.println("How many pages of results are there? ");
	    	String pageCountStr = reader.nextLine();
	    	int pageCount = Integer.parseInt(pageCountStr);
	    	
	    	System.out.println("Enter path for search terms file: ");
	    	String termsFile = reader.nextLine();
	    	
	    	reader.close();
	    	
	    	
	    	
	    	// Read search terms from file
	    	try {
		    	BufferedReader termBuf= new BufferedReader(new FileReader(termsFile));
		        String termLine = termBuf.readLine();
		        concepts.add(new Concept("concept"));

		        while (termLine != null) {
		        	System.out.println("***********" + termLine + "*********");
		        	if (termLine.equals("")) {
		        		System.out.println("#############");
		        		concepts.add(new Concept("concept"));
		        	}
		        	else {
		        		searchTerms.add(termLine);
		        		concepts.get(concepts.size() - 1).addTerm(termLine);
		        	}
		        	termLine = termBuf.readLine();
		            // abcdefghijklmnopqrstuvwxyz
		        }
		        
		        termBuf.close();
		        
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// TODO Auto-generated catch block
		    }
	    	
	    	for (int i = 0; i < searchTerms.size(); i++)
	    		System.out.println(searchTerms.get(i));
	    	
	    	System.out.println("!!!!!!!!!!!!!!!!!!!! " + concepts.size());
	    	
	    	//System.out.println("");
	    	
	    	for (int i = 0; i < concepts.size(); i++) {
	    		System.out.println("");
	    		for (int i2 = 0; i2 < concepts.get(i).getTerms().size(); i2++) {
	    			System.out.println(concepts.get(i).getTerms().get(i2));
	    		}
	    	}
	    	
	    	System.out.println("");
	    	
	    	System.out.println("Fetching URLs...");
	    	
	    	
	    	
	    	// Generate result page URLs
	    	for (int i = 0; i < pageCount; i++) {
	    		if (i == 0)
	    			resultPageURLs.add(urlString);
	    		else {
	    			// edit urlString
	    			resultPageURLs.add(urlString.replace("p=1", "p=" + (i + 1)));
	    			// add urlString
	    		}
	    	}
	    	
	    	System.out.println("");
	    	
	    	
	    	
	    	// Fetch URLs from all results pages
	    	for (int i = 0; i < resultPageURLs.size(); i++) {
	    		url = new URL(resultPageURLs.get(i));
		        is = url.openStream();  // throws an IOException
		        br = new BufferedReader(new InputStreamReader(is));
		        
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
		        }
	    	}
	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
	     
	    
	    
	    // Search URLs for keywords
	    System.out.println("Searching Patents...");
	    for (int i = 0; i < urls.size(); i++) {
	    	
	    	System.out.println((i + 1) + "/" + urls.size());
	    	
	    	patents.add(new PatentInfo(urls.get(i), searchTerms));
	    	
		    try {
		        url = new URL(urls.get(i));
		        is = url.openStream();  // throws an IOException
		        br = new BufferedReader(new InputStreamReader(is));
	
		        //Vector<Integer> termCounts = new Vector<Integer>();
		        
		        //for (int i3 = 0; i3 < searchTerms.size(); i3++) {
		        	//termCounts.add(0);
		        //}
		        
		        while ((line = br.readLine()) != null) {
		        	for (int i2 = 0; i2 < searchTerms.size(); i2++) {
		        		int lastIndex = 0;
		        		int count = 0;

		        		while(lastIndex != -1) {

		        		    lastIndex = line.indexOf(searchTerms.get(i2),lastIndex);

		        		    if(lastIndex != -1) {
		        		        count ++;
		        		        lastIndex += searchTerms.get(i2).length();
		        		    }
		        		}
		        		
		        		patents.get(patents.size() - 1).addToTermCount(searchTerms.get(i2), count);;
		        	}
		        }
		    } catch (MalformedURLException mue) {
		         mue.printStackTrace();
		    } catch (IOException ioe) {
		         //ioe.printStackTrace();
		    } finally {
		        try {
		            if (is != null) is.close();
		        } catch (IOException ioe) {
		            // nothing to see here
		        }
		    }
	    }
	    
	    
	    
	    // Sort by unique term count
	    //System.out.println("Sorting by relevance...");
	    for (int i = 0; i < patents.size(); i++) {
	    	if (i < patents.size() - 1) {
	    		for (int i2 = i + 1; i2 < patents.size(); i2++) {
		    		if (patents.get(i).getUniqueTermCount() < patents.get(i2).getUniqueTermCount()) {
		    			Collections.swap(patents, i, i2);
		    		}
		    	}
	    	}
	    }
	    
	    
	    
	    // Sort by concepts fulfilled
	    for (int i = 0; i < patents.size(); i++) {
	    	if (i < patents.size() - 1) {
	    		for (int i2 = i + 1; i2 < patents.size(); i2++) {
		    		if (patents.get(i).getConceptCount(concepts) < patents.get(i2).getConceptCount(concepts)) {
		    			Collections.swap(patents, i, i2);
		    		}
		    	}
	    	}
	    }
	    
	    
	    
	    // Sort terms within each patent
	    for (int i = 0; i < patents.size(); i++) {
	    	patents.get(i).sortTerms();
	    }
	    
	    System.out.println();
	    
	    
	    
	    // Display results and write to file
		try {
			PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
			
		    for (int i = 0; i < patents.size(); i++) {
		    	writer.println(patents.get(i).getName() + " : " + patents.get(i).getConceptCount(concepts));
		    	writer.println(patents.get(i).getURL());
		    	System.out.println(patents.get(i).getName() + " : " + patents.get(i).getConceptCount(concepts));
		    	System.out.println(patents.get(i).getURL());
		    	
		    	for (int i2 = 0; i2 < searchTerms.size(); i2++) {
		    		if (patents.get(i).getTerms().get(i2).getCount() > 0) {
	    	    	    writer.println(patents.get(i).getTerms().get(i2).getTerm() + ": " + patents.get(i).getTermCount(patents.get(i).getTerms().get(i2).getTerm()));
		    			System.out.println(patents.get(i).getTerms().get(i2).getTerm() + ": " + patents.get(i).getTermCount(patents.get(i).getTerms().get(i2).getTerm()));
		    		}
		    		//System.out.println(patents.get(i).getTermCount(searchTerms.get(i2)));
		    	}
		    	
		    	writer.println("");
		    	System.out.println("");
	    	
		    	//System.out.println(i + " : " + patents.get(patents.size() - 1).getURL() + ": " + patents.get(patents.size() - 1).getTotalCount());
		    }
		    
		    writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
*/
}
