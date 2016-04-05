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
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Dimension;

public class Menu extends JFrame  implements Constants{
	
	
	private static final long serialVersionUID = 1L;

	private int chooselevel = LMIN;
	JLabel label = new JLabel("Вubble tank");
	JLabel label2 = new JLabel("Уровень сложности");
	JButton button = new JButton("Играть");
	JButton button1 = new JButton("Settings");
	JButton button2 = new JButton("Выйти");
	JButton playPC = new JButton("Играет ПК");
	static int min = LMIN;
	static int max = LMAX;
	JPanel panel = new JPanel();
	JFrame startframe = new JFrame("TanksAndreiBike");
	JSlider Level = new JSlider(JSlider.HORIZONTAL, min, max, 1);

	public Menu() {
		setTitle("BubbleTank v1.0");
		Level.setMajorTickSpacing(10);
		Level.setMinorTickSpacing(1);
		Level.setPaintTicks(true);
		Level.setPaintLabels(true);
		Font font = new Font("Serif", Font.ITALIC, 10);
		Level.setFont(font);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createRigidArea(new Dimension(0, 25)));
		setSize(MWIDTH, MHEIDTH);
		setLocationRelativeTo(null);
		setResizable(false);
		panel.setBackground(Color.PINK);
		Level.setBackground(Color.PINK);
		label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		label.setFont(new Font("Verdana", Font.ITALIC, 30));
		label2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		label2.setFont(new Font("Calibri", Font.ITALIC, 15));
		button.setMaximumSize(new Dimension(200, 100));
		button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		button1.setMaximumSize(new Dimension(200, 100));
		button1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		button2.setMaximumSize(new Dimension(200, 100));
		button2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		playPC.setMaximumSize(new Dimension(200, 100));
		playPC.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		Level.setMaximumSize(new Dimension(200, 50));
		Level.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(0, 25)));
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(0, 25)));
		panel.add(label2);
		panel.add(Level);
		panel.add(Box.createRigidArea(new Dimension(0, 25)));
		panel.add(playPC);
		panel.add(Box.createRigidArea(new Dimension(0, 25)));
		panel.add(button2);
		panel.add(Box.createRigidArea(new Dimension(0, 25)));
		add(panel);
		pushButton();

	}

	public void pushButton() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				GamePanel panel2 = new GamePanel(chooselevel, false);
				panel2.start();
			}
		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		Level.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider) event.getSource();
				chooselevel = source.getValue();
			}
		});

		playPC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				GamePanel panel2 = new GamePanel(chooselevel, true);
				panel2.start();
			}
		});

	}
}
