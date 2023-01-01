package hw05;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;




public class MyFrame extends JFrame implements ActionListener{

	JButton btn0 = new JButton("영단어의 뜻 검색하기");
	JButton btn1 = new JButton("영단어 검색하기");
	JButton btn2 = new JButton("객관식 퀴즈");
	JButton btn3 = new JButton("빈출 단어 리스트");
	
	JButton btn4 = new JButton("북마크");
	JButton btn5 = new JButton("단어 추가");
	JButton btn6 = new JButton(" VS CPU ");
	
	JMenuBar mb = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenuItem open= new JMenuItem("Open");
	
	int open_success=1;
	String file_route;
	
	public Word[] voc = new Word[100];
	public int number =0;
	
	public int game_level=1;
	
	public MyFrame() {
		
		this("201711404 원유현의 단어장");
		
	}
	
	public MyFrame(String title) {
		super(title);
		this.setSize(1000,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setLayout(new BorderLayout());
	    
		
		setMenu();
		setmainbutton();
		
		this.setVisible(true);
		
		}
	
	public void addWord(Word word) {
		
		if(number<voc.length)
			voc[number++]=word;
		
		else
			JOptionPane.showMessageDialog(null,"단어장이 꽉찼습니다.");
		
	}
	
	public void setmainbutton() {
		
		GridLayout mainlayout = new GridLayout(1,5);
		GridLayout mainlayout2 = new GridLayout(1,2);
		
		JPanel main_panel = new JPanel();
	    JPanel additional = new JPanel();
		
		main_panel.setLayout(mainlayout);
		additional.setLayout(mainlayout2);
		
		main_panel.add(btn0);
		main_panel.add(btn1);
		main_panel.add(btn2);
		main_panel.add(btn3);
		
		additional.add(btn4);
		additional.add(btn5);
		additional.add(btn6);
		
		btn0.addActionListener(this); 
		btn1.addActionListener(this); 
		btn2.addActionListener(this); 
		btn3.addActionListener(this); 
	
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn6.addActionListener(this);
		
		this.add(main_panel,BorderLayout.NORTH);
		this.add(additional,BorderLayout.SOUTH);
	}
	
	public void setMenu() {
		
		
		fileMenu.add(open);
		open.addActionListener(this);
		mb.add(fileMenu);
		this.setJMenuBar(mb);
		
	
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==open) {
			
			JFileChooser c = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("txt 파일", "txt");
			c.setCurrentDirectory(new File(System.getProperty("user.home") + "//" + "Desktop"));
			c.setFileFilter(filter);


			if(open_success!=0) {
			open_success = c.showOpenDialog(null);
			
			if(open_success == 0) {
				
				
				file_route=c.getSelectedFile().toString();
				
				if(file_route.contains(".txt")) {
					
				
				try(Scanner scan = new Scanner(new File(file_route))){
					
					while(scan.hasNextLine()) {
						
						String str = scan.nextLine();
						String[] temp =str.split("\t");
						this.addWord(new Word(temp[0].trim(),temp[1].trim()));
					}
					
					JOptionPane.showMessageDialog(null,"단어장이 생성되었습니다.");
					
					
					
					
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"파일을 다시 확인해주세요.","ERROR!",JOptionPane.ERROR_MESSAGE);
					open_success=1;
					voc = new Word[100];
					number =0;
				}
				catch (ArrayIndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null,"파일을 다시 확인해주세요.","ERROR!",JOptionPane.ERROR_MESSAGE);
					open_success=1;
					voc = new Word[100];
					number =0;
				}

			}
			
			
			else {
				open_success=1;
				JOptionPane.showMessageDialog(null,"txt 파일이 아닙니다.","ERROR!",JOptionPane.ERROR_MESSAGE);
				
			}
			}
			}
			
			else
				JOptionPane.showMessageDialog(null,"파일이 이미 오픈되었습니다.");
			
		}
		
 
		if(e.getSource()==btn0) {
			if(open_success !=1)
			meaning_search();	
			else
				JOptionPane.showMessageDialog(null,"파일을 먼저 오픈해주세요.","ERROR!",JOptionPane.ERROR_MESSAGE);
		}
		else if(e.getSource()==btn1) 
			if(open_success !=1)
			word_search();	
		
			else
				JOptionPane.showMessageDialog(null,"파일을 먼저 오픈해주세요.","ERROR!",JOptionPane.ERROR_MESSAGE);
		
		else if(e.getSource()==btn2) 
			if(open_success !=1)
			quiz();		
		
			else
				JOptionPane.showMessageDialog(null,"파일을 먼저 오픈해주세요.","ERROR!",JOptionPane.ERROR_MESSAGE);
		
		else if(e.getSource()==btn3) 
			if(open_success !=1)
			frequency_list();		
			else
				JOptionPane.showMessageDialog(null,"파일을 먼저 오픈해주세요.","ERROR!",JOptionPane.ERROR_MESSAGE);
		
		else if(e.getSource()==btn4) 
			if(open_success !=1)
			Bookmark();
			else
				JOptionPane.showMessageDialog(null,"파일을 먼저 오픈해주세요.","ERROR!",JOptionPane.ERROR_MESSAGE);	
		
		else if(e.getSource()==btn5) 
			
			if(open_success !=1)
			add();
			else
				JOptionPane.showMessageDialog(null,"파일을 먼저 오픈해주세요.","ERROR!",JOptionPane.ERROR_MESSAGE);	
        else if(e.getSource()==btn6) 
        
		if(open_success !=1)
			game();
		else
			JOptionPane.showMessageDialog(null,"파일을 먼저 오픈해주세요.","ERROR!",JOptionPane.ERROR_MESSAGE);	
		
	}

	public void meaning_search() {
		// TODO Auto-generated method stub
		
		Meaning_search ms = new Meaning_search(MyFrame.this,btn0.getText(),true);
			
	}
	public void word_search()  {
		// TODO Auto-generated method stub
		Word_search ws = new Word_search(MyFrame.this,btn1.getText(),true);
	}
	
	public void quiz()	 {
		// TODO Auto-generated method stub
		Quiz qz =new Quiz(MyFrame.this,btn2.getText(),true);
		
	}
	public void frequency_list() {
		// TODO Auto-generated method stub
		Frequency_list fl = new Frequency_list(MyFrame.this,btn3.getText(),true);
	}
	
   public void Bookmark() {
	   Bookmark bm = new Bookmark(MyFrame.this,btn4.getText(),true);
   }
	

   public void add() {
	   Addword ad = new Addword(MyFrame.this,btn5.getText(),file_route,true);   
   }
   public void game() {
	   Game gm = new Game(MyFrame.this,btn6.getText(),this.game_level,true);
	  
   }
   
}
