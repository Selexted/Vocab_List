package hw05;

import java.awt.BorderLayout;
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





public class Frequency_list extends JDialog {
	
	
	String[] top= {"순위","영어","한글","빈도수"};
	DefaultTableModel model = new DefaultTableModel(top,0);
	JTable table = new JTable(model);
	
	
public  Frequency_list(MyFrame parent,String title, boolean modal) {
		
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
    	   
    	   Map<String, Word> map= new HashMap<String, Word>();
    	   
    	   for(int i =0;i<parent.number;i++)
    	   map.put(parent.voc[i].eng,parent.voc[i]);
    	   
			List<Word> list = new ArrayList<>();
			
			
			Set<String> keyset = map.keySet();
			Iterator<String> keyit=keyset.iterator();
			
			while(keyit.hasNext()) {
				String n = keyit.next();
				
				if(map.get(n).flu>0) {
					list.add(map.get(n));
					
				}
				
			}
			
			Collections.sort(list, new Comparator<Word>() {

				@Override
				public int compare(Word o1, Word o2) {
					// TODO Auto-generated method stub
					return o2.flu-o1.flu;
				}
				
			});
			
			
			JPanel result = new JPanel();
			JLabel  sort = new JLabel("빈도수에 의한 단어 정렬");
			sort.setHorizontalAlignment(JLabel.CENTER);
			
			this.add(sort,BorderLayout.NORTH);
			int i =0;
			
			Iterator<Word> listit = list.iterator();
			while(listit.hasNext()) {
				
				i++;
				Word a =listit.next();
				String[] b = {Integer.toString(i),a.eng,a.kor,Integer.toString(a.flu)};
				model.addRow(b);
				
				
			}
			
			
		
			
    	  result.add(new JScrollPane(table));
    	  this.add(result);
    	  
    	  JOptionPane.showMessageDialog(null,"검색완료!\n 클릭하면 북마크에 추가할수있습니다.","Search!",JOptionPane.INFORMATION_MESSAGE);
    	  
	table.addMouseListener(new MouseListener() {

				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				
			        int num=0;
					int row =table.getSelectedRow();
					String temp =(String) table.getValueAt(row, 1);
					
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
			
			
			

				
			
		}
    	   
    	   
	
}
	


