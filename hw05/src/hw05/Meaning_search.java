package hw05;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Meaning_search extends JDialog {
	
	int search_success =0;
	
	public Meaning_search(MyFrame parent,String title, boolean modal) {
		
		super(parent,title,modal);
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		search(parent);
		
		
		this.setVisible(true);
		
		
	}
	
	public void search(MyFrame parent) {
		
		JPanel set = new JPanel();
		JLabel  findword = new JLabel("검색할 단어를 입력하세요.  ");
		JTextField input = new JTextField(20);
		JButton OK =new JButton("검색");
		
		set.add(findword);
		set.add(input);
		set.add(OK);
		this.add(set,BorderLayout.NORTH);
		
		
		
		
		OK.addActionListener(new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				search_success=0;
				
				if(input.getText().length()==0) {
					JOptionPane.showMessageDialog(null,"영어를 입력하세요.");
					
				}
				
				else {
					String word1 =input.getText();
					word1=word1.trim();
					for(Word word :parent.voc) {
						
						if(word!=null) {
							if(word.eng.equals(word1)) {
								
								
				 JOptionPane.showMessageDialog(null,word1+"의 뜻은 :  "+ word.kor,"Search Success!",JOptionPane.INFORMATION_MESSAGE);
								search_success=1;
								
								int result=JOptionPane.showConfirmDialog(null,"북마크에 추가하시겠습니까?","Question",JOptionPane.YES_NO_OPTION);
								
								if(result==JOptionPane.NO_OPTION)
									JOptionPane.showMessageDialog(null,"추가하지 않았습니다.","Cancel",JOptionPane.INFORMATION_MESSAGE);
								
								else if(result==JOptionPane.YES_OPTION){
									
									if(word.bookmark==1) {
									int result2=JOptionPane.showConfirmDialog(null,"이미 추가하였습니다.\n 북마크에 제외시키겠습니까?","Question",JOptionPane.YES_NO_OPTION);
							
										if(result2==JOptionPane.YES_OPTION) {
											JOptionPane.showMessageDialog(null,"제외되었습니다.","Aceept",JOptionPane.INFORMATION_MESSAGE);
											word.bookmark=0;
										}
										
							
									}
									
									
									else {
									JOptionPane.showMessageDialog(null,"추가하였습니다.","Aceept",JOptionPane.INFORMATION_MESSAGE);
									word.bookmark=1;
									}
								}
								
								break;
								
							}
						}
							else {
								
								
							}
						
					}
						
						
						
					}
				if(search_success==0)
					JOptionPane.showMessageDialog(null,input.getText()+"이란 단어는 등록되있지 않습니다.","Search Failed!",JOptionPane.INFORMATION_MESSAGE);
				
				}
				
				
			
			
			
		});
		
		
		
		
		
		
		
		
		
		
	}
	

}
