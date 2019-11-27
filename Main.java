package DinoGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
public class Main {
public Main() {
	
	JFrame frame = new JFrame();
	Gamepannel gamepanel = new Gamepannel(); 
	

	frame.add(gamepanel);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setTitle("TEST");
//	frame.setLayout(new BorderLayout());

//	frame.add(new JLabel("Hello World"), BorderLayout.CENTER);

	frame.pack();
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);

}
	public static void main(String[] args) {
		new Main();
	}

}
