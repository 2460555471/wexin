package trans;

public class Data 
{
	private String word_name;	//字的名字
	private Symbols[] symbols;//符号数组
	
	
	public String getWord_name() {
		return word_name;
	}
	public void setWord_name(String word_name) {
		this.word_name = word_name;
	}
	public Symbols[] getSymbols() {
		return symbols;
	}
	public void setSymbols(Symbols[] symbols) {
		this.symbols = symbols;
	}
}
