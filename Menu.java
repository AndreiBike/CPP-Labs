package AndreiBike;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
public class Menu extends JFrame{

	/**
	 * 
	 */
	private int x=1;
	private static final long serialVersionUID = 1L;

	JPanel panel = new JPanel();
	
	JLabel label = new JLabel("Âubble tank");
	
	JButton button = new JButton("Play");
	
	JButton button1 = new JButton("Settings");
	
	JButton button2 = new JButton("Exit");
	
	
	public Menu(){
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createRigidArea(new Dimension (0,25)));
		setSize(400,400);
		setLocationRelativeTo(null);
		setResizable(false);
		
		label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		label.setFont(new Font("Verdana", Font.ITALIC,30));
		
		
		button.setMaximumSize(new Dimension (200, 100));
		button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		button1.setMaximumSize(new Dimension (200, 100));
		button1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		button2.setMaximumSize(new Dimension(200,100));
		button2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
				GamePanel panel2 = new GamePanel(x);
				JFrame startframe = new JFrame("TanksAndreiBike");
				startframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				startframe.setResizable(false);
				startframe.setContentPane(panel2);
				startframe.pack();
				startframe.setLocationRelativeTo(null);
				startframe.setVisible(true);
				panel2.start();
			}
		
		});
		
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
				panel.disable();
			}
		});
		
		
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension (0,25)));
		panel.add(button);
		
		panel.add(Box.createRigidArea(new Dimension(0, 25)));
	    panel.add(button1);
	
		panel.add(Box.createRigidArea(new Dimension (0,25)));
		panel.add(button2);
		
		add(panel);
		

	}
	
	/*private int buttonWidth;
	private int buttonHeight;
	private Color color;
	private String s;
	
	public Menu(){
		buttonWidth = 120;
		buttonHeight = 60;
		s = "Play";
		color = Color.DARK_GRAY;
	}
	
	
	
	public void draw (Graphics2D g){
		g.setColor(color);
		g.setStroke(new BasicStroke(3));
		g.drawRect(GamePanel.WIDTH/2 - buttonWidth/2, GamePanel.HEIGHT/2 - buttonHeight/2,
				buttonWidth, buttonHeight );
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.BLACK);
		g.setFont(new Font ("Consolas", Font.BOLD, 40));
		long length =(int)g.getFontMetrics().getStringBounds(s,g).getWidth();
		g.drawString(s, GamePanel.WIDTH/2-length/2, GamePanel.HEIGHT/2+15);
	}*/
	
	
}
