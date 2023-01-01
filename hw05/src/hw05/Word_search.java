package hw05;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Word_search extends JDialog {
	
	
	String[] top= {"영어","한글"};
	DefaultTableModel model = new DefaultTableModel(top,0);
	JTable table = new JTable(model);
	
	
		
		
		
		
	
	
	public  Word_search(MyFrame parent,String title, boolean modal) {
		
		super(parent,title,modal);
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout(10,30));
		
		
		search(parent);
		
		
		this.setVisible(true);
		
		
	}
	
	public void search(MyFrame parent) {
		
		JPanel set = new JPanel();
		JPanel result = new JPanel();
		
		JTextField input = new JTextField(20);
		JLabel  findword = new JLabel("검색할 단어를 입력하세요.");
		
		
	
		JButton OK =new JButton("검색");
		
		
		
		set.add(findword);
		set.add(input);
		set.add(OK);
		
		
		this.add(set,BorderLayout.NORTH);
		

		OK.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				model.setNumRows(0);
				if(input.getText().length()==0) {
					JOptionPane.showMessageDialog(null,"영어를 입력하세요.");
					
				}
				
				else {
					String word1 =input.getText();
					word1=word1.trim();
					for(Word word :parent.voc) {
						
						if(word!=null) {
							if(word.eng.contains(word1)) {
								String[] a = {word.eng,word.kor};
								model.addRow(a);				
								
													
							}
						}
							
						
					}
					
					
					
		JOptionPane.showMessageDialog(null,"검색을 완료하였습니다.\n 클릭하면 북마크에 추가할수있습니다.","Search!",JOptionPane.INFORMATION_MESSAGE);
						
						
					}
					
				}
				
				
			
			
		});
		table.addMouseListener(new MouseListener() {

			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			
		        int num=0;
				int row =table.getSelectedRow();
				String temp =(String) table.getValueAt(row, 0);
				
				while(!temp.equals(parent.voc[num].eng)) {
					num++;
					
				}
				
				
				int result=JOptionPane.showConfirmDialog(null,"북마크에 추가하시겠습니까?","Question",JOptionPane.YES_NO_OPTION);
				
				if(result==JOptionPane.NO_OPTION)
					JOptionPane.showMessageDialog(null,"추가하지 않았습니다.","Cancel",JOptionPane.INFORMATION_MESSAGE);
				
				else if(result==JOptionPane.YES_OPTION){
					
					if(parent.voc[num].bookmark==1) {
					int result2=JOptionPane.showConfirmDialog(null,"이미 추가하였습니다.\n 북마크에 제외시키겠습니까?","Question",JOptionPane.YES_NO_OPTION);
			
						if(result2==JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null,"제외되었습니다.","Aceept",JOptionPane.INFORMATION_MESSAGE);
							parent.voc[num].bookmark=0;
						}
						
			
					}
					
					
					else {
					JOptionPane.showMessageDialog(null,"추가하였습니다.","Aceept",JOptionPane.INFORMATION_MESSAGE);
					parent.voc[num].bookmark=1;
					}
				}
				
				
				
		
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
			
			
		});
		
		
		

			result.add(new JScrollPane(table));
		
		this.add(result,BorderLayout.CENTER);
		
		
	}

	
	
	

		
		
	}


