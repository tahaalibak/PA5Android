package jbs.animalgame.org;

public class Animal {
	
	//fields
	private String question;
	private String animal;
	private Animal yes;
	private Animal no;
	private int checkYes = 0;
	private int checkNo = 0;
	
	//constructor
	public Animal(String animal){
		this.animal = animal;		
	}
	
	public void setQuestion(String question){
		this.question = question;
	}
	
	public String getQuestion(){
		return this.question;
	}
	
	public String getAnimal(){
		return this.animal;
	}
	
	public void setYes(Animal yes){
		this.yes = yes;
		this.checkYes++;
	}
		
	public Animal getYes(){
		return this.yes;
	}
	
	public void setNo(Animal no){
		this.no = no;
		this.checkNo++;
	}

	public Animal getNo(){
		return this.no;
	}
	
	public int getCheckYes(){
		return this.checkYes;
	}
	
	public int getCheckNo(){
		return this.checkNo;
	}
}
