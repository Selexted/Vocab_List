package hw05;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JDialog implements ActionListener {
	
	Container frame = this.getContentPane();
	MyFrame parent;
	JPanel GamePanel;
	JPanel showPanel;
	JPanel ButtonPanel;
	
	String img = "img/char1.png";
	ImageIcon icons = new ImageIcon(img);
	JLabel hero;
	
	String img_cpu = "img/char2.jpg";
	ImageIcon icons_cpu = new ImageIcon(img_cpu);
	JLabel cpu;
	
	JLabel question;
	JButton yes;
	JButton no;
	
	String inform;
	int answer;
	int choice;
	
	int many=0;
	int score=0;
	
	Random r = new Random();
	CPUmove cm ;
	
	
	
	int user_go =50;
	int user_back=10;
	int cpu_speed=5;
	
	
	
	
public Game(MyFrame parent,String title, int level, boolean modal) {
		
		super(parent,title,modal);
		this.parent=parent;
		
		user_back += (5*(parent.game_level-1));
		cpu_speed +=(parent.game_level-1);
		
		
		
		this.setSize(1200,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(2,1,1,10));
		
		
		Game(parent);
		
		
		this.setVisible(true);
		
	}

public void Game(MyFrame parent) {
	// TODO Auto-generated method stub
	
	GamePanel= new JPanel();
	showPanel= new JPanel();
	ButtonPanel= new JPanel();
	
	GamePanel.setBackground(Color.white);
	
	hero = new JLabel(icons);
	cpu= new JLabel(icons_cpu);
	
	Point start =new  Point(100,0);
	hero.setLocation(start);
	hero.setSize(icons.getIconWidth(),icons.getIconHeight());
	
	cpu.setLocation(new Point(100,150));
	cpu.setSize(icons.getIconWidth(),icons.getIconHeight());
	
	
	GamePanel.setLayout(null);
	GamePanel.add(hero);
	GamePanel.add(cpu);
	
	frame.add(GamePanel);
	
	showPanel.setLayout(new GridLayout(2,1,5,5));
	
	question = new JLabel();
	question.setFont(new Font("바탕",Font.BOLD,30));
	question.setHorizontalAlignment(JLabel.CENTER);
	question.setSize(1200,250);
	
	
	yes =new JButton("YES");
	no =new JButton("NO");
	
	yes.setSize(300,100);
	no.setSize(300,100);
	
	showPanel.add(question);
	
	ButtonPanel.add(yes);
	ButtonPanel.add(no);
	
	yes.addActionListener(this);
	no.addActionListener(this);

	showPanel.add(ButtonPanel);
	frame.add(showPanel);
	
	JOptionPane.showMessageDialog(null,"          -----Level "+parent.game_level+"-----");
	
	
	
	
	randomquiz(parent);
	
	
	cm = new  CPUmove(cpu,cpu_speed);
	cm.start();
	
	
}

void randomquiz(MyFrame parent) {
	many++;
	
	int rand = r.nextInt(2);
	if(rand==1)
	randomquiz1(parent);
	else
	randomquiz2(parent);
}

void randomquiz1(MyFrame parent) {	
	
	
	
	int num =r.nextInt(parent.number);
	
	
	int rand =r.nextInt(2);
	
	if(rand==0) {
	inform= parent.voc[num].eng+"의 뜻은 "+parent.voc[num].kor+" 이다.";
	answer=0;
	
	
	}
	else {
		int rand2;
		
		while(true) {
		rand2=r.nextInt(parent.number);
		if(rand2!=num)
			break;
		}
		
		inform= parent.voc[num].eng+"의 뜻은 "+parent.voc[rand2].kor+" 이다.";
		answer=1;
		
		
	}
	question.setText(inform);
	
}

void randomquiz2(MyFrame parent) {	
	
	
	
	int num =r.nextInt(parent.number);
	
	
	int rand =r.nextInt(2);
	
	if(rand==0) {
	inform= parent.voc[num].kor+"는 영어로 "+parent.voc[num].eng+" 이다.";
	answer=0;
	
	
	}
	else {
		int rand2;
		
		while(true) {
		rand2=r.nextInt(parent.number);
		if(parent.voc[num].kor != (parent.voc[rand2].kor))
			break;
		}
		
		inform= parent.voc[num].kor+"는 영어로 "+parent.voc[rand2].eng+" 이다.";
		answer=1;
		
		
	}
	question.setText(inform);
	
}









class CPUmove extends Thread{
	JLabel cpu;
    int speed;
	Point pos;
	int gamestop=0;
	
	 CPUmove(JLabel cpu, int speed){
		this.cpu=cpu;
		this.speed=speed;
		pos= new Point(cpu.getX(),cpu.getY());
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true) {
			pos.x +=speed;
			
			if(pos.x>1100) {
				gamestop=1;
			}
			
			cpu.setLocation(pos);
			cpu.getParent().repaint();
			
			try {
				if(gamestop==1) {
					JOptionPane.showMessageDialog(null,"CPU에게 패배하였습니다.\n스코어 "+score+" / "+many);
				return;
				}
				else
				Thread.sleep(300);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	

	
}

@SuppressWarnings("deprecation")
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	if(e.getSource()==yes) {
		choice=0;
		Point p = new Point(hero.getX(),hero.getY());
		if(choice==answer) {
			p.x +=user_go;
			hero.setLocation(p);
			score++;
		}
		else {
			p.x -=user_back;
			hero.setLocation(p);
			
		}
		
		
		
		
	}
	
	
	else if(e.getSource()==no) {
		
		choice=1;
		Point p = new Point(hero.getX(),hero.getY());
		if(choice==answer) {
			p.x +=user_go;
			hero.setLocation(p);
			score++;
		}
		else {
			p.x -=user_back;
			hero.setLocation(p);
			
		}
		
		
		
		
	}
		
	randomquiz(parent);
	frame.repaint();
	hero.getParent().repaint();
	
	if(hero.getX()>1100) {
		cm.stop();
		JOptionPane.showMessageDialog(null,"승리하였습니다.\n스코어 "+score+" / "+many+"\n승리하였으므로 승급하였습니다.");
		parent.game_level++;
		dispose();
		
	}
	
	if(hero.getX()<icons.getIconWidth()/2) {
		cm.stop();
		
		if(parent.game_level>0) {
		JOptionPane.showMessageDialog(null,"실격하였습니다.\n스코어 "+score+" / "+many+"\n실격하였으므로 강등당하였습니다.");
		parent.game_level--;
		}
		else
			JOptionPane.showMessageDialog(null,"실격하였습니다.\n스코어 "+score+" / "+many+"\n더이상 강등당할 레벌이 없습니다.");
		
		
		dispose();
	}
		
	}

	
}


