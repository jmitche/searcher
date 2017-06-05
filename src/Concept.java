import java.util.Vector;

/**
 * @author Dan
 *
 */
public class Concept {
	String name;
	Vector<String> terms;
	
	
	
	/**
	 * Constructor
	 * @param name
	 */
	public Concept(String name) {
		this.name = name;
		terms = new Vector<String>();
	}
	
	/**
	 * addTerm
	 * @param term
	 */
	public void addTerm(String term) {
		terms.add(term);
	}
	
	/**
	 * hasTerm
	 * @param term
	 * @return
	 */
	public boolean hasTerm(String term) {
		if (terms.contains(term))
			return true;
		
		return false;
	}
	
	/**
	 * getTerms
	 * @return terms
	 */
	public Vector<String> getTerms() {return terms;}
	
	/**
	 * getName
	 * @return name
	 */
	public String getName() {return name;}
}
