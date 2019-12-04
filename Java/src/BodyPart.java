import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BodyPart extends Rectangle {

	//======================================================= Properties
	public BodyPart next = null;
	public boolean attached = false;
	
	//======================================================= Constructor
	
	public BodyPart() {
		this(0,0,0);
	}
	
	public BodyPart(int x, int y, int size) {
		super(x,y,size,size);
		next = null;
	}
	
	//======================================================= Methods
	
	public void draw(Graphics g) {
		if(next != null) next.draw(g);
		g.setColor(attached ? Color.red : Color.blue);
		g.fillRect(x+1, y+1, width-2, height-2);
		g.setColor(Color.black);
		g.drawRect(x+1, y+1, width-2, height-2);
		
	}
	
	public void move(int mx, int my) {
		moveTo(x+mx,y+my);
	}
	
	public void moveTo(int newX, int newY) {
		if(next!=null) next.moveTo(x, y);
		x = newX;
		y = newY;
	}
	
	public void addNewTail() {
		if(next != null) next.addNewTail();
		else {
			next = new BodyPart(x,y,width);
			next.attached = true;
		}
	}
}
