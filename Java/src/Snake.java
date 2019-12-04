import java.awt.Graphics;

import javax.swing.JPanel;

public class Snake {

	//======================================================= Properties
	public static int SIZE = 12;
	public static JPanel canvas = new JPanel();
	
	public BodyPart head = new BodyPart();
	public int bodyPartCount = 0;
	public int mx = 0, my = 0;
	
	//======================================================= Constructors
	
	public Snake(int x, int y, int mx, int my) {
		head = new BodyPart(x,y,SIZE);
		head.attached = true;
		setSpeed(mx, my);
		bodyPartCount = 1;
	}
	
	public Snake() {
		this(0,0,0,0);
	}
	
	//======================================================= Methods
	
	public void setSpeed(int mx, int my) {
		this.mx = mx;
		this.my = my;
	}
	
	public void draw(Graphics g) {
		head.draw(g);
	}
	
	public boolean move() {
		head.move(mx, my);
		return canvas.getBounds().contains(head);
	}
	
	public void addBodyPart(BodyPart tail) {
		bodyPartCount++;
		tail = head;
		while(tail.next != null) tail = tail.next;
		tail.next = new BodyPart(tail.x, tail.y, SIZE);
		tail.next.attached = true;
	}
	
}
