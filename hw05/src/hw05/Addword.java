package hw05;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Addword extends JDialog{
	
	String file_route;
	
	String en = new String();
    String kor = new String();
	String add;

public Addword(MyFrame parent,String title, String file_route,boolean modal) {
	
	super(parent,title,modal);
	
	this.file_route=file_route;
	
	this.setSize(500,500);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.setLayout(new BorderLayout());
	
	add(parent);
	
	
	this.setVisible(true);
	
	
}

public void add(MyFrame parent) {
	
	JPanel set = new JPanel();
	set.setLayout(new GridLayout(10,2,5,5) );
	JLabel  addword = new JLabel("추가할 단어를 입력하세요.");
	addword.setHorizontalAlignment(JLabel.CENTER);
	this.add(addword,BorderLayout.NORTH);
	
	JLabel  enL = new JLabel("영어");
	JLabel  korL = new JLabel("한글");
	enL.setHorizontalAlignment(JLabel.CENTER);
	korL.setHorizontalAlignment(JLabel.CENTER);
	
	JTextField input_en = new JTextField(20);
	JTextField input_kor = new JTextField(20);
	JButton OK =new JButton("추가");
	
	set.add(enL);
	set.add(input_en);
	set.add(korL);
	set.add(input_kor);
	this.add(OK,BorderLayout.SOUTH);
	this.add(set);
	
	OK.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(input_en.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"추가할 영어를 입력하세요.");
				
			}
			
			else if(input_kor.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"한글뜻를 입력하세요.");
				
			}
			
			else {
				en=input_en.getText();
				kor=input_kor.getText();
				add= en +"	"+ kor;
				parent.addWord(new Word(en,kor));
				
				BufferedWriter bw;
				try {
					bw = new BufferedWriter(new FileWriter(file_route,true));
					PrintWriter pw  = new PrintWriter(bw,true);
					
					
				  pw.write(add+"\n");
				  pw.flush();
				  pw.close();
					
				  JOptionPane.showMessageDialog(null,"추가 완료되었습니다.","Aceept",JOptionPane.INFORMATION_MESSAGE);
				  input_en.setText(null);
				  input_kor.setText(null);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"에러가 발생하였습니다!","ERROR!",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
				
			}
		}
		
	});
	
	
	
	
}
}