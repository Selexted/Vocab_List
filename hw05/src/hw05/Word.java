package hw05;

public class Word {
	String eng;
	String kor;
	int flu=0;
	int bookmark=0;
	
	
	
	public Word(String eng, String kor) {
		super();
		this.eng = eng;
		this.kor = kor;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return eng+" : "+kor;
	}
	
	

}
