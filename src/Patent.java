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
	String number;
	String title;
	String abstractStr;
	String inventorsStr;
	String applicationNumber;
	String publicationDate;
	String filingDate;
	String assignee;
	String primaryClass;
	String otherClassesStr;
	String internationalClassesStr;
	String eclaClassesStr;
	String fieldOfSearchStr;
	String pdfUrlStr;
	String primaryExaminer;
	String assistantExaminer;
	String attorneyAgentOrFirm;
	String claims;
	String description;
	
	
	
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
			number = elements3.first().attr("value");
			
			
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
					title = eText;
				}
				else if (eTitle.equals("Abstract:")) {
					abstractStr = eText;
				}
				else if (eTitle.equals("Representative Image:")) {  // Representative Images
					// = eText;
				}
				else if (eTitle.equals("Inventors:")) {
					inventorsStr = eText;
				}
				else if (eTitle.equals("Application Number:")) {
					applicationNumber = eText;
				}
				else if (eTitle.equals("Publication Date:")) {
					publicationDate = eText;
				}
				else if (eTitle.equals("Filing Date:")) {
					filingDate = eText;
				}
				else if (eTitle.equals("Assignee:")) {
					assignee = eText;
				}
				else if (eTitle.equals("Primary Class:")) {
					primaryClass = eText;
				}
				else if (eTitle.equals("Other Classes:")) {
					otherClassesStr = eText;
				}
				else if (eTitle.equals("International Classes:")) {
					internationalClassesStr = eText;
				}
				else if (eTitle.equals("European Classes:")) {
					eclaClassesStr = eText;
				}
				else if (eTitle.equals("Field of Search:")) {
					fieldOfSearchStr = eText;
				}
				else if (eTitle.equals("View Patent Images:")) {  // PDF
					pdfUrlStr = elements2.select("a[href]").attr("abs:href");
					System.out.println(pdfUrlStr);
				}
				else if (eTitle.equals("Primary Examiner:")) {
					primaryExaminer = eText;
				}
				else if (eTitle.equals("Assistant Examiner:")) {
					assistantExaminer = eText;
				}
				else if (eTitle.equals("Attorney, Agent or Firm:")) {
					attorneyAgentOrFirm = eText;
				}
				else if (eTitle.equals("Claims:")) {
					claims = eText;
				}
				else if (eTitle.equals("Description:")) {
					description = eText;
				}
				

				
				//System.out.println(eTitle);
				//System.out.println(eText);
			}
			
			System.out.println(number + ": " + title);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public Object[] getData(Vector<String> elements) {
		Vector<Object> dataObj = new Vector<Object>();
		Object[] data = new Object[elements.size()];
		
		for (int i = 0; i < elements.size(); i++) {
			if (elements.elementAt(i).equals("#")) {
				data[i] = number;
			}
			else if (elements.elementAt(i).equals("Title")) {
				data[i] = title;
			}
			else if (elements.elementAt(i).equals("Abstract")) {
				data[i] = abstractStr;
			}
			else if (elements.elementAt(i).equals("Claims")) {
				data[i] = claims;
			}
			else if (elements.elementAt(i).equals("Description")) {
				data[i] = description;
			}
			else if (elements.elementAt(i).equals("Inventors")) {
				data[i] = inventorsStr;
			}
			else if (elements.elementAt(i).equals("Publication Date")) {
				data[i] = publicationDate;
			}
			else if (elements.elementAt(i).equals("Filing Date")) {
				data[i] = filingDate;
			}
			else if (elements.elementAt(i).equals("Assignee")) {
				data[i] = assignee;
			}
			else if (elements.elementAt(i).equals("Primary Class")) {
				data[i] = primaryClass;
			}
			else if (elements.elementAt(i).equals("Other Classes")) {
				data[i] = otherClassesStr;
			}
			else if (elements.elementAt(i).equals("International Classes")) {
				data[i] = internationalClassesStr;
			}
			else if (elements.elementAt(i).equals("ECLA Classes")) {
				data[i] = eclaClassesStr;
			}
			else if (elements.elementAt(i).equals("Field of Search")) {
				data[i] = fieldOfSearchStr;
			}
			else if (elements.elementAt(i).equals("Primary Examiner")) {
				data[i] = primaryExaminer;
			}
			else if (elements.elementAt(i).equals("Assistant Examiner")) {
				data[i] = assistantExaminer;
			}
			else if (elements.elementAt(i).equals("Attorney, Agent, or Firm")) {
				data[i] = attorneyAgentOrFirm;
			}
			else if (elements.elementAt(i).equals("Application Number")) {
				data[i] = applicationNumber;
			}
		}
		/*
		data[0] = number;
		data[1] = title;
		data[2] = abstractStr;
		data[3] = inventorsStr;
		data[4] = applicationNumber;
		data[5] = publicationDate;
		data[6] = filingDate;
		data[7] = assignee;
		data[8] = primaryClass;
		data[9] = otherClassesStr;
		data[10] = internationalClassesStr;
		data[11] = eclaClassesStr;
		data[12] = fieldOfSearchStr;
		data[13] = primaryExaminer;
		data[14] = assistantExaminer;
		data[15] = attorneyAgentOrFirm;
		data[16] = claims;
		data[17] = description;
		*/
		return data;
	}
}
