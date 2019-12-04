import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tester extends JPanel{

	public static void main(String[] args) {
		new Tester();
	}

	//======================================================= Properties

	static final int INITIAL_SPEED = 100;

	JFrame window = new JFrame("2019 Snake ~~~:");
	Timer tmr = null;
	BodyPart food = new BodyPart();
	Snake snake = new Snake();


	//======================================================= Constructors

	public Tester() {

		window.setBounds(0,0,500,500);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.getContentPane().add(this);
		window.setAlwaysOnTop(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		tmr = new Timer(INITIAL_SPEED, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!snake.move()) {
					tmr.stop();
					repaint();
					JOptionPane.showMessageDialog(window, "Game Over");
				}
				else {
					if(snake.head.intersects(food)) {
						snake.addBodyPart(food);
						if(snake.bodyPartCount % 5 == 0) {
							tmr.setDelay(Math.max(1, tmr.getDelay()-5));
						}
						setTitle();
						placeFood();
					}
				}
				repaint();
			}
		});

		//Keys
		window.addKeyListener(new KeyListener() {

			@Override public void keyTyped(KeyEvent e) {}
			@Override public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {

				switch(e.getExtendedKeyCode()) {
				case 87: case 104: case 38: snake.setSpeed(0, -Snake.SIZE); break;
				case 68: case 102: case 39: snake.setSpeed(Snake.SIZE, 0); break;
				case 83: case 98: case 40: snake.setSpeed(0, Snake.SIZE); break;
				case 65: case 100: case 37: snake.setSpeed(-Snake.SIZE, 0); break;
				
				}

			}
		});

		Snake.canvas = this;
		Snake.SIZE = 15;

		start();
	}

	//======================================================= Methods

	public void setTitle() {
		window.setTitle("Body parts: " + snake.bodyPartCount +
				" Speed: " + (INITIAL_SPEED+1 - tmr.getDelay()) );
	}

	public void start() {
		placeFood();
		snake = new Snake(getWidth()/2,getHeight()/2, Snake.SIZE,0);
		setTitle();
		tmr.setDelay(INITIAL_SPEED);
		tmr.start();
		placeFood();
	}

	public void placeFood() {
		Random rnd = new Random();
		food = new BodyPart(rnd.nextInt(Math.abs(getWidth()-Snake.SIZE)),
				rnd.nextInt(Math.abs(getHeight()-Snake.SIZE)),
				Snake.SIZE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		snake.draw(g);
		food.draw(g);
	}
}
