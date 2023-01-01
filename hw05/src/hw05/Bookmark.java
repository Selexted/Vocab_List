package hw05;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;





public class Bookmark extends JDialog {
	
	Container frame = this.getContentPane();
	
	String[] top= {"영어","한글"};
	DefaultTableModel model = new DefaultTableModel(top,0);
	JTable table = new JTable(model);
	
	
public  Bookmark(MyFrame parent,String title, boolean modal) {
		
		super(parent,title,modal);
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		
		search(parent);
		
		
		this.setVisible(true);
		
		
	}



       public void search(MyFrame parent) {
	// TODO Auto-generated method stub
    	   
    	   JPanel result = new JPanel();
			JLabel  sort = new JLabel("북마크된 단어들");
		sort.setHorizontalAlignment(JLabel.CENTER);
			
			
			for(Word word :parent.voc) {
				
				if(word!=null) {
					if(word.bookmark==1) {
						String[] a = {word.eng,word.kor};
						model.addRow(a);				
						
											
					}
				}
					
				
			}
			
		
		this.add(sort,BorderLayout.NORTH);
    	  result.add(new JScrollPane(table));
    	  this.add(result);
    	  
    	  JOptionPane.showMessageDialog(null,"검색완료!\n 클릭하면 북마크에서 제거할수있습니다.","Search!",JOptionPane.INFORMATION_MESSAGE);
    	  
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
					
					
					int result=JOptionPane.showConfirmDialog(null,"북마크에서 제거하시겠습니까?","Question",JOptionPane.YES_NO_OPTION);
					
					if(result==JOptionPane.NO_OPTION)
						JOptionPane.showMessageDialog(null,"제거하지 않았습니다.","Cancel",JOptionPane.INFORMATION_MESSAGE);
					
					else if(result==JOptionPane.YES_OPTION){
						parent.voc[num].bookmark=0;
						JOptionPane.showMessageDialog(null,"제거하였습니다.","Delete",JOptionPane.INFORMATION_MESSAGE);
						model.removeRow(row);
					
						
					}
					
					frame.revalidate();
					frame.repaint();
					
					
			
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
			
			
			

				
			
		}
    	   
    	   
	
}
	


