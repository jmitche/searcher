
public class SearchTerm {
	String term;
	int count;
	
	public SearchTerm(String t) {
		term = t;
		count = 0;
	}
	
	public String getTerm() {
		return term;
	}
	
	public void add(int c) {
		count += c;
	}
	
	public int getCount() {
		return count;
	}
}
