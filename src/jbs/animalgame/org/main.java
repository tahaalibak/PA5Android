package jbs.animalgame.org;

import java.util.HashMap;
import java.util.LinkedList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class main extends Activity implements OnClickListener {
    
	EditText edittext;
	TextView display;
	Animal zebra = new Animal("zebra");
	Animal root = zebra;
	Animal reference = new Animal("reference");
	Animal current;
	String input;
	String new_animal;
	String new_question;
	String win = "";
	String lose = "";
	int status = -1;
	int passby = 0;
		
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);       
        
        edittext = (EditText) findViewById(R.id.edittext);
        display = (TextView) this.findViewById(R.id.outputtext);
        zebra.setQuestion("Is it like a striped horse?");
        display.setText("Hit Enter to begin.");
        
		View enter = findViewById(R.id.submit);
		enter.setOnClickListener(this);
		edittext.setVisibility(View.INVISIBLE);
		
		current = root;
    }



	@Override
	public void onClick(View v) {
		switch(v.getId()) {	
		case R.id.submit:
			
			edittext.setVisibility(View.VISIBLE);
			if (status!=-1){
				input = edittext.getText().toString().toLowerCase();
			}			
            edittext.setText(null);
			
			if (!current.equals(zebra)){
				reference = current;
			}
            
            if(status == -1){
				display.setText(current.getQuestion());
				status = 0;
			}
			
			else if(status==0){
				if (input.equals("yes")){				
					if(current.getCheckYes()==0){
						display.setText("Is your animal a/an "+current.getAnimal()+"?");
						status = 3;
					}
					else{
						current = current.getYes();
						display.setText(current.getQuestion());
						status = 0;						
					}
					
				}
				else {
					if(current.getCheckNo()==0){
						lose = "loss";
						status = 4;
						passby = 1;
					}			
					else{
						current = current.getNo();
						display.setText(current.getQuestion());
						status = 0;
					}

				}
			}
			else if (status==3){
				if (input.equals("yes")){
					win = "win";
				}
				else{
					lose = "yesloss";					
				}
				passby = 1;
				status = 4;
			}

			///////////////////////////////////////////////////////////////////////
			
			else if(status == 4){
				if (win.equals("win")){
					display.setText("I win!!! Thank you for playing. Would you like to play again?");
					reference = current;
					status = 7;
				}
				else{
					display.setText("You win! What is your animal?");
					status = 5;
				}
			}
            
			else if(status == 5){
				new_animal = input;
				display.setText("Can you give me a question that would be answered yes for this animal, but no for what I guessed?");
				status = 6;
			}
			else if(status == 6){
            	new_question = input;
				Animal temp = new Animal(new_animal);
				temp.setNo(zebra);
				temp.setQuestion(new_question);
				if (lose.equals("loss")){
						reference.setNo(temp);
				}
				else{
						reference.setYes(temp);
				}				
				if(root.getAnimal().equals("zebra")){
					root = temp;
				}
				display.setText("Would you like to play again?");
				status = 7;
            }
			else if(status == 7){
				if (input.equals("yes")){
					win = "";
					lose = "";
					status = 0;
					current = root;
					display.setText(current.getQuestion());					
				}
				else{
					finish();
				}
			}
            
            
            if(status == 4 && passby==1){
				if (win.equals("win")){
					display.setText("I win!!! Thank you for playing. Would you like to play again?");
					reference = current;
					status = 7;
				}
				else{
					display.setText("You win! What is your animal?");
					status = 5;
				}
				passby = 0;
			}
			break;
		}
	}
}