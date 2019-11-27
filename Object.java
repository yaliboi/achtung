package DinoGame;

import java.awt.Color;
import java.awt.Graphics;
import java.math.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
public class Object {
	
	private double xCoor,yCoor,size,width,height,mx,my,prevx,prevy,dir,speed;
	private  Color col;
	private int id;
	private String color;
	private boolean alive;
	public double getPrevx() {
		return prevx;
	}

	public void setPrevx(double prevx) {
		this.prevx = prevx;
	}

	public double getPrevy() {
		return prevy;
	}

	public double getDir() {
		return dir;
	}

	public void setDir(double dir) {
		this.dir = dir;
	}

	public void setPrevy(double prevy) {
		this.prevy = prevy;
	}

	public Object(double xCoor,double yCoor, double size,double mx, double my,double dir,double speed,Color col,int id,
			String color,boolean alive) {
	this.xCoor=xCoor;
	this.yCoor=yCoor;
	this.mx=mx;
	this.my=my;
	this.speed=speed;
	this.col=col;
	this.id=id;
	this.color=color;
	this.alive=alive;
	this.size=size;
	width=size;
	height=size;
	}
	
	
	public boolean getAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}


	

	
	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		RenderingHints hints = new RenderingHints(
		    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(col);
		if(this.speed>0) {
			g2d.setColor(Color.YELLOW);

		}
        g2d.setRenderingHints(hints);

		g2d.fill(new Ellipse2D.Float((int)Math.round(xCoor), (int)Math.round(yCoor), (int)Math.round(width), (int)Math.round(height)));
	}
	public double getxCoor() {
		return xCoor;
	}
	public void setxCoor(double xCoor) {
		this.xCoor = xCoor;
	}
	
	public double getmx() {
		return mx;
	}
	public void setmx(double mx) {
		this.mx = mx;
	}
	
	public double getmy() {
		return my;
	}
	public void setmy(double my) {
		this.my = my;
	}
	
	public double getyCoor() {
		return yCoor;
	}
	public void setyCoor(double yCoor) {
		this.yCoor = yCoor;
	}
	
}
