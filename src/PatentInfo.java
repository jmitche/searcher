import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Vector;

public class PatentInfo {
	Vector<SearchTerm> terms = new Vector<SearchTerm>();
	String urlStr;
	String title;
	String uspc;
	String cpc;
	String description;
	String claims;
	
	
	
	
	
	public PatentInfo(String address, Vector<String> t) {
		for (int i = 0; i < t.size(); i++) {
			terms.add(new SearchTerm(t.get(i)));
		}
		
		urlStr = address;
		
		try {
	    	URL url = new URL(urlStr);
		    InputStream is = url.openStream();  // throws an IOException
		    BufferedReader br = new BufferedReader(new InputStreamReader(is));
		    String line;
		    
	        while ((line = br.readLine()) != null) {
	        	if (line.contains("<meta name=\"title\"")) {
	        		System.out.println(line);
					title = line.substring(line.indexOf("content=") + 9, line.indexOf('>') - 5);
	        	}
	        	else if (line.contains("<!--   Abstract  -->")) {
	        		
	        	}
	        }
	        
	        is.close();
	
	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	    	//
	    }
	}
		
	public void addToTermCount(String term, int count) {
		for (int i = 0; i < terms.size(); i++) {
			if (terms.get(i).getTerm() == term)
				terms.get(i).add(count);
		}
	}
	
	public String getURL() {
		return urlStr;
	}
	
	public int getTotalTermCount() {
		int count = 0;
		
		for (int i = 0; i < terms.size(); i++) {
			count += terms.get(i).getCount();
		}
		
		return count;
	}
	
	public int getTermCount(String term) {
		int count = 0;
		
		for (int i = 0; i < terms.size(); i++) {
			if (terms.get(i).getTerm() == term)
				count = terms.get(i).getCount();
		}
		
		return count;
	}
	
	public int getUniqueTermCount() {
		int count = 0;
		
		for (int i = 0; i < terms.size(); i++) {
			if (terms.get(i).getCount() > 0)
				count++;
		}
		
		return count;
	}
	
	public void sortTerms() {
		
		for (int i = 0; i < terms.size(); i++) {
	    	if (i < terms.size() - 1) {
	    		for (int i2 = i + 1; i2 < terms.size(); i2++) {
		    		if (terms.get(i).getCount() < terms.get(i2).getCount()) {
		    			Collections.swap(terms, i, i2);
		    		}
		    	}
	    	}
	    }
	}
	
	public Vector<SearchTerm> getTerms() {
		return terms;
	}
	
	public int getConceptCount(Vector<Concept> concepts) {
		int conceptCount = 0;
		
		for (int i = 0; i < concepts.size(); i++) {
			for (int i2 = 0; i2 < concepts.get(i).getTerms().size(); i2++) {
				for (int i3 = 0; i3 < terms.size(); i3++) {
					if (concepts.get(i).getTerms().get(i2) == terms.get(i3).getTerm() && terms.get(i3).getCount() > 0) {
						//System.out.println("@@@@@@ " + concepts.get(i).getTerms().get(i2) + " : " + terms.get(i3).getTerm());
						conceptCount++;
						i3 = terms.size();
						i2 = concepts.get(i).getTerms().size();
					}
				}
			}
		}
		
		return conceptCount;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAsignee() {
		return title;
	}
}
