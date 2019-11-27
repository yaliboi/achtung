package DinoGame;
import java.awt.Color;
import java.math.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Gamepannel extends JPanel  implements Runnable, KeyListener,MouseListener{

/**
	 * 
	 */
	//------------------------------------------------------/-VALUES
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 600, HEIGHT = 400;


	//private Object b;
	private Thread thread;
	private boolean pressed1=false,pressed2=false   , pressedA=false, pressedD = false ,pressedLE=false, pressedRI = false;
	private boolean running,pause,pause1=false;
	private boolean right = true, left= false,up=false,down=false;


	private int xCoor = 10,yCoor=10,size=5;
	private int ticks = 0,prevtick=-1 , nAlive;
	
	private boolean first = true;
    ArrayList<Object> trail = new ArrayList<Object>();
    ArrayList<Object> snake = new ArrayList<Object>();
    
	//--------------------------------------------------------VALUES/
	
	//--------------------------------------------------------/ESSENTIALS
public Gamepannel() {
		
		setFocusable(true);
		
        addMouseListener(this);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this);
		
		start();

	}

public void start() {
	running = true;
	pause=false;
	thread = new Thread(this);
	thread.start();
	double dire = Math.random()*360;
	double x = Math.random()*(WIDTH-100)+50;
	double y = Math.random()*(HEIGHT-100)+50;

	Object dot = new Object(x,y,7,0,0,dire,1.5*2,Color.GREEN,0,"Green",true);
	dot.setDir(dire);
	 dire = Math.random()*360;
	 x = Math.random()*(WIDTH-100)+50;
	 y = Math.random()*(HEIGHT-100)+50;

	Object dot2 = new Object(x,y,7,0,0,dire,1.5*2,Color.RED,1,"Red",true);
	dot2.setDir(dire);

	snake.add(dot);
	snake.add(dot2);


}

public void stop() {
	running = false;
	try {
		thread.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void reset() {
	snake.clear();
	trail.clear();
	double dire = Math.random()*360;
	double x = Math.random()*(WIDTH-100)+50;
	double y = Math.random()*(HEIGHT-100)+50;

	Object dot = new Object(x,y,7,0,0,dire,1.5*2,Color.GREEN,0,"Green",true);
	dot.setDir(dire);
	 dire = Math.random()*360;
	 x = Math.random()*(WIDTH-100)+50;
	 y = Math.random()*(HEIGHT-100)+50;

	Object dot2 = new Object(x,y,7,0,0,dire,1.5*2,Color.RED,1,"Red",true);
	dot2.setDir(dire);
	running=true;
	snake.add(dot);
	snake.add(dot2);
		quickTick();
		quickTick();
	repaint();

}
@Override
public void run() {
	// TODO Auto-generated method stub
	while(running) {
		tick();
	}
}
//------------------------------------------------------------ESSENTIALS/

//-------------------------------------------------------------TICK/FRAMES

public void tick() {
	
	ticks++;



	if(ticks>20000000) {
		if(pause==true) {
			
			
		}
		if(pause==false) {

		repaint();

		for(int i = 0; i<snake.size(); i++) {



		if(snake.get(i).getAlive()==true) {
			nAlive+=1;
		}

		if (snake.get(i).getyCoor()>HEIGHT-7 || snake.get(i).getyCoor()<3) {
			snake.get(i).setmy(snake.get(i).getmy()*-0.9);
			snake.get(i).setyCoor(snake.get(i).getyCoor()+snake.get(i).getmy()*1.2);
		snake.get(i).setAlive(false);
		}

		if (snake.get(i).getxCoor()>WIDTH-10 || snake.get(i).getxCoor()<2) {
		
			snake.get(i).setmx(snake.get(i).getmx()*-0.9);
			snake.get(i).setxCoor(snake.get(i).getxCoor()+snake.get(i).getmx()*1.2);
				snake.get(i).setAlive(false);

		}


		
		if(prevtick==1) {
			//happens every two frames cuz logic
			//dot.setPrevx(-dot.getxCoor());
		}
		if(prevtick==1) {
			prevtick=0;
			//happens every two other frames
			
			//dot.setmx(dot.getPrevx()-dot.getxCoor());
			

		

		}
		
		prevtick+=1;
		
		Object b = new Object(snake.get(i).getxCoor(),snake.get(i).getyCoor(),7,0,0,0,0,snake.get(i).getCol(),i,"nan",true);


		trail.add(b);
		
		if(pressedA==true) {
			
			snake.get(0).setDir(snake.get(0).getDir()-4*snake.get(0).getSpeed());

		}
		
		if(pressedD==true) {
			
			snake.get(0).setDir(snake.get(0).getDir()+4*snake.get(0).getSpeed());

		}
		if(pressedLE==true) {
			
			snake.get(1).setDir(snake.get(1).getDir()-4*snake.get(1).getSpeed());

		}
		
		if(pressedRI==true) {
			
			snake.get(1).setDir(snake.get(1).getDir()+4*snake.get(1).getSpeed());

		}
		
		
		/*
		if(pressed1==true) {	
		if(snake.get(i).getxCoor()<MouseInfo.getPointerInfo().getLocation().x-460) {
		dot.setmx(dot.getmx()+((MouseInfo.getPointerInfo().getLocation().x-460)-dot.getxCoor())/1000);
		}
		if(dot.getxCoor()>MouseInfo.getPointerInfo().getLocation().x-460) {
			dot.setmx(dot.getmx()-(dot.getxCoor()-(MouseInfo.getPointerInfo().getLocation().x-460))/1000);
		}
		
		if(dot.getyCoor()<MouseInfo.getPointerInfo().getLocation().y-30) {
			dot.setmy(dot.getmy()+((MouseInfo.getPointerInfo().getLocation().y-30)-dot.getyCoor())/1000);
		}
		if(dot.getyCoor()>MouseInfo.getPointerInfo().getLocation().y-30) {
			dot.setmy(dot.getmy()-(dot.getyCoor()-(MouseInfo.getPointerInfo().getLocation().y-30))/1000);
		}
			
		
			
			
			
		}

		
		if(pressed2==true) {	
			if(dot.getxCoor()<MouseInfo.getPointerInfo().getLocation().x-460) {
			dot.setmx(dot.getmx()+((MouseInfo.getPointerInfo().getLocation().x-460)-dot.getxCoor())/1000);
			}
			if(dot.getxCoor()>MouseInfo.getPointerInfo().getLocation().x-460) {
				dot.setmx(dot.getmx()-(dot.getxCoor()-(MouseInfo.getPointerInfo().getLocation().x-460))/1000);
			}
			
			if(dot.getyCoor()<MouseInfo.getPointerInfo().getLocation().y-30) {
				dot.setmy(dot.getmy()+((MouseInfo.getPointerInfo().getLocation().y-30)-dot.getyCoor())/1000);
			}
			if(dot.getyCoor()>MouseInfo.getPointerInfo().getLocation().y-30) {
				dot.setmy(dot.getmy()-(dot.getyCoor()-(MouseInfo.getPointerInfo().getLocation().y-30))/1000);
			}
				
			*/
				
				
				
			//}
	//	dot.setmy(dot.getmy()+0.1);
	snake.get(i).setmx( (Math.sin(snake.get(i).getDir()/100) ) * snake.get(i).getSpeed());
	snake.get(i).setmy( ( -Math.cos(snake.get(i).getDir()/100)) * snake.get(i).getSpeed() );
	if(snake.get(i).getAlive()==true) {
	snake.get(i).setxCoor(snake.get(i).getxCoor()+snake.get(i).getmx());
	snake.get(i).setyCoor(snake.get(i).getyCoor()+snake.get(i).getmy());
	}
		
		for (int v = 0; v<trail.size()-5; v++) {
			if(trail.get(v).getxCoor()-snake.get(i).getxCoor()<snake.get(i).getSize() && trail.get(v).getxCoor()-snake.get(i).getxCoor() >-snake.get(i).getSize() && trail.get(v).getyCoor()-snake.get(i).getyCoor()<snake.get(i).getSize() && trail.get(v).getyCoor()-snake.get(i).getyCoor() >-snake.get(i).getSize()) {
				
				snake.get(i).setAlive(false);
			}
			
		}
		
		ticks=0;
		
		
		}//snake closing
		
		if(nAlive==1) {
			pause=true;
		}
		nAlive=0;
		}

	}
	}

//-------------------------------------------------------------TICK/FRAMES/

//-------------------------------------------------------------PAINT
public void paint(Graphics g) {
	g.clearRect(0, 0, WIDTH, HEIGHT);
	
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, WIDTH, HEIGHT);
	
	for(int i=0; i<WIDTH/10; i++) {
		g.drawLine(i * 10, 0, i*10, HEIGHT);
	}
	for(int i=0; i<HEIGHT/10; i++) {
		g.drawLine(0,i*10,HEIGHT,i*10);
	}
/*
	if(pressed1==true) {
		g.drawLine((int)Math.round(dot.getxCoor())+5, (int)Math.round(dot.getyCoor())+5, MouseInfo.getPointerInfo().getLocation().x-460, MouseInfo.getPointerInfo().getLocation().y-30);
	}
	g.setColor(Color.RED);

	if(pressed2==true) {
		g.drawLine((int)Math.round(dot.getxCoor())+5, (int)Math.round(dot.getyCoor())+5, MouseInfo.getPointerInfo().getLocation().x-460, MouseInfo.getPointerInfo().getLocation().y-30);
	}
*/
	Graphics2D g2d = (Graphics2D)g.create();
	g2d.setColor(Color.CYAN);
	
	RenderingHints hints = new RenderingHints(
	    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	if(pause) {
		for(int i = 0; i<snake.size(); i++) {		

			if(snake.get(i).getAlive()==true && pause1 == false) {
				 g2d.drawString(snake.get(i).getColor() + " won! " +"Press space to continue, ", WIDTH/2-100, 20);
			}
		}
	}
		  
			for(int i = 0; i<snake.size(); i++) {

				for(int v = 0; v < trail.size() ;v++) {
					   trail.get(v).draw(g);
				   
				 }
		   snake.get(i).draw(g);
			}
			for(int i = 0; i<snake.size(); i++) {		
				snake.get(i).draw(g);
			}

}

//-------------------------------------------------------------PAINT/





//-------------------------------------------------------------KEYBOARD AND MOUSE
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();
	
	if(key==KeyEvent.VK_SPACE) {

		System.out.println(pause);
		if(pause1==true) {
			pause=false;
			pause1=false;

		}
		else {
			reset();

		pause1=true;

		}
	}
	for(int i = 0; i<snake.size(); i++) {

		

		
		
	if(key==KeyEvent.VK_D) {
		pressedD=true;
	}
	if(key==KeyEvent.VK_A) {
		pressedA=true;
	}
	
	if(key==KeyEvent.VK_LEFT) {
		pressedLE=true;

		
	}
	if(key==KeyEvent.VK_RIGHT) {
		pressedRI=true;

	}
}
	}


@Override
public void keyReleased(KeyEvent e) {
	int key = e.getKeyCode();

	if(key==KeyEvent.VK_D) {
		pressedD=false;
	}
	if(key==KeyEvent.VK_A) {
		pressedA=false;
	}
	if(key==KeyEvent.VK_LEFT) {
		pressedLE=false;

		
	}
	if(key==KeyEvent.VK_RIGHT) {
		pressedRI=false;

	}
	

}


@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub

}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	int key = e.getButton();
	if(key==1) {
	pressed1=true;
	}
	if(key==2) {
		pressed2=true;
	}
}

@Override
public void mouseReleased(MouseEvent e) {
	int key = e.getButton();

	if(key==1) {
		pressed1=false;
	}
	if(key==2) {
		pressed2=false;
	}
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub

}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

}

//-------------------------------------------------------------KEYBOARD AND MOUSE/


public void quickTick() {
	
	
	repaint();

	for(int i = 0; i<snake.size(); i++) {



	if(snake.get(i).getAlive()==true) {
		nAlive+=1;
	}

	if (snake.get(i).getyCoor()>HEIGHT-10 || snake.get(i).getyCoor()<0) {
		//snake.get(i).setmy(snake.get(i).getmy()*-0.9);
		//snake.get(i).setyCoor(snake.get(i).getyCoor()+snake.get(i).getmy()*1.2);
	snake.get(i).setAlive(false);
	}

	if (snake.get(i).getxCoor()>WIDTH-10 || snake.get(i).getxCoor()<0) {
	
		//snake.get(i).setmx(snake.get(i).getmx()*-0.9);
		//snake.get(i).setxCoor(snake.get(i).getxCoor()+snake.get(i).getmx()*1.2);
			snake.get(i).setAlive(false);

	}


	
	if(prevtick==1) {
		//happens every two frames cuz logic
		//dot.setPrevx(-dot.getxCoor());
	}
	if(prevtick==1) {
		prevtick=0;
		//happens every two other frames
		
		//dot.setmx(dot.getPrevx()-dot.getxCoor());
		

	

	}
	
	prevtick+=1;
	
	Object b = new Object(snake.get(i).getxCoor(),snake.get(i).getyCoor(),7,0,0,0,0,snake.get(i).getCol(),i,"nan",true);


	trail.add(b);
	
	if(pressedA==true) {
		
		snake.get(0).setDir(snake.get(0).getDir()-4*snake.get(0).getSpeed());

	}
	
	if(pressedD==true) {
		
		snake.get(0).setDir(snake.get(0).getDir()+4*snake.get(0).getSpeed());

	}
	if(pressedLE==true) {
		
		snake.get(1).setDir(snake.get(1).getDir()-4*snake.get(1).getSpeed());

	}
	
	if(pressedRI==true) {
		
		snake.get(1).setDir(snake.get(1).getDir()+4*snake.get(1).getSpeed());

	}
	
	
	/*
	if(pressed1==true) {	
	if(snake.get(i).getxCoor()<MouseInfo.getPointerInfo().getLocation().x-460) {
	dot.setmx(dot.getmx()+((MouseInfo.getPointerInfo().getLocation().x-460)-dot.getxCoor())/1000);
	}
	if(dot.getxCoor()>MouseInfo.getPointerInfo().getLocation().x-460) {
		dot.setmx(dot.getmx()-(dot.getxCoor()-(MouseInfo.getPointerInfo().getLocation().x-460))/1000);
	}
	
	if(dot.getyCoor()<MouseInfo.getPointerInfo().getLocation().y-30) {
		dot.setmy(dot.getmy()+((MouseInfo.getPointerInfo().getLocation().y-30)-dot.getyCoor())/1000);
	}
	if(dot.getyCoor()>MouseInfo.getPointerInfo().getLocation().y-30) {
		dot.setmy(dot.getmy()-(dot.getyCoor()-(MouseInfo.getPointerInfo().getLocation().y-30))/1000);
	}
		
	
		
		
		
	}

	
	if(pressed2==true) {	
		if(dot.getxCoor()<MouseInfo.getPointerInfo().getLocation().x-460) {
		dot.setmx(dot.getmx()+((MouseInfo.getPointerInfo().getLocation().x-460)-dot.getxCoor())/1000);
		}
		if(dot.getxCoor()>MouseInfo.getPointerInfo().getLocation().x-460) {
			dot.setmx(dot.getmx()-(dot.getxCoor()-(MouseInfo.getPointerInfo().getLocation().x-460))/1000);
		}
		
		if(dot.getyCoor()<MouseInfo.getPointerInfo().getLocation().y-30) {
			dot.setmy(dot.getmy()+((MouseInfo.getPointerInfo().getLocation().y-30)-dot.getyCoor())/1000);
		}
		if(dot.getyCoor()>MouseInfo.getPointerInfo().getLocation().y-30) {
			dot.setmy(dot.getmy()-(dot.getyCoor()-(MouseInfo.getPointerInfo().getLocation().y-30))/1000);
		}
			
		*/
			
			
			
		//}
//	dot.setmy(dot.getmy()+0.1);
snake.get(i).setmx( (Math.sin(snake.get(i).getDir()/100) ) * snake.get(i).getSpeed());
snake.get(i).setmy( ( -Math.cos(snake.get(i).getDir()/100)) * snake.get(i).getSpeed() );
if(snake.get(i).getAlive()==true) {
snake.get(i).setxCoor(snake.get(i).getxCoor()+snake.get(i).getmx());
snake.get(i).setyCoor(snake.get(i).getyCoor()+snake.get(i).getmy());
}
	
	for (int v = 0; v<trail.size()-5; v++) {
		if(trail.get(v).getxCoor()-snake.get(i).getxCoor()<4 && trail.get(v).getxCoor()-snake.get(i).getxCoor() >-4 && trail.get(v).getyCoor()-snake.get(i).getyCoor()<4 && trail.get(v).getyCoor()-snake.get(i).getyCoor() >-4) {
			
			snake.get(i).setAlive(false);
		}
		
	}
	
	ticks=0;
	
	
	}//snake closing
	
	if(nAlive==1) {
		pause=true;
	}
	nAlive=0;
	}	
	
}



