package hw05;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Quiz extends JDialog implements ActionListener {

	


    

	Random r = new Random();
	int quizes[] = new int[10];
	
	String[][] select;
	int quizes_answer[] =new int[10];
	JPanel[] single_quiz;
	ButtonGroup[] group;
	JRadioButton[][] Button ;
	JButton submit;
	JCheckBox[] word = new JCheckBox[10];
	long time_start;
	long time_finish;
	
	
public Quiz(MyFrame parent,String title, boolean modal) {
		
		super(parent,title,modal);
		this.setSize(700,1000);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		do_quiz(parent);
		
		
		this.setVisible(true);
		
		
	}

public void do_quiz(MyFrame parent) {
	// TODO Auto-generated method stub
	
	make_quiz(parent);
	
	
	
	 JPanel quiz_main = new JPanel(new GridLayout(11,1,2,2));
	
	single_quiz=new JPanel[10];
	Button = new JRadioButton[10][4];
	group= new ButtonGroup[10];
	
	JLabel label =new JLabel("체크박스를 선택하면 북마크에 추가할수있습니다.");
	label.setHorizontalAlignment(JLabel.CENTER);
	label.setFont(new Font("바탕",Font.BOLD,16));
	quiz_main.add(label);
	
	 for(int i=0;i<10;i++) {
		 single_quiz[i]= new JPanel();
		 String eng =parent.voc[quizes[i]].eng;
		 word[i]=new JCheckBox(eng);
		 word[i].getText();
		 word[i].addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int number=0;
				JCheckBox a =(JCheckBox) e.getSource();			
				String temp=a.getText();
				
				while(!temp.equals(parent.voc[number].eng)) {
					number++;
					
				}
				
				if(e.getStateChange()==ItemEvent.SELECTED) {
					parent.voc[number].bookmark=1;
				}
				else if(e.getStateChange()==ItemEvent.DESELECTED) {
					parent.voc[number].bookmark=0;
				}
					
			}
			 
		 });
		 
		 
		 single_quiz[i].add(word[i]);
		 
		 group[i]= new ButtonGroup();
		 
		 for(int j=0;j<4;j++) {
			Button[i][j]= new JRadioButton(select[i][j]);
			group[i].add(Button[i][j]);
			single_quiz[i].add(Button[i][j]);
		 }
		
		
			
			
			
			
		
			
			quiz_main.add(single_quiz[i]);
	 }
	
	submit = new JButton("제출하기 ");
	submit.setSize(50,50);
	submit.addActionListener(this);
	
	this.add(quiz_main,BorderLayout.CENTER);
	this.add(submit,BorderLayout.SOUTH);
	time_start = System.currentTimeMillis();
	
}



public void make_quiz(MyFrame parent) {

	for(int i=0;i<10;i++) {
		quizes[i]=r.nextInt(parent.number);
		
		for(int j=0;j<i;j++) {
			if(quizes[i]==quizes[j] ){
				i--;
				break;
			}
		}
		
		parent.voc[quizes[i]].flu++;
	}
	
	
	select = new String[parent.number][4];
	
	for(int i=0;i<10;i++) {
		select[i][0]=parent.voc[quizes[i]].kor;
		
		for(int j=1;j<4;j++) {
			select[i][j]=parent.voc[r.nextInt(parent.number)].kor;
			
			for(int k =0;k<j;k++) {
				
				if(0==(select[i][j].compareTo(select[i][k]))) {
					j--;
					break;
				}
			}		
			}
	}
	
	
	for(int i=0;i<10;i++) {
	String temp =null;
	int mix =r.nextInt(4);
	temp=select[i][0];
	select[i][0]=select[i][mix];
	select[i][mix]=temp;
	quizes_answer[i]=mix;
	
	}
	
	
	
}



@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	if(e.getSource()==submit) {
		int score=0;
		
		time_finish= System.currentTimeMillis();
		long time=(time_finish-time_start)/1000;
		for(int i=0;i<10;i++) {
			
		
			
		if(Button[i][quizes_answer[i]].isSelected()) {
			score++;
		}
		
		
		}
		
		String temp2="Result  "+score+" / 10 \n";
		temp2+="총 "+time+" 초가 소요되었습니다.";
		
		JOptionPane.showMessageDialog(null,temp2,"Score !",JOptionPane.INFORMATION_MESSAGE);
		dispose();
		
	}
	
}



















}
