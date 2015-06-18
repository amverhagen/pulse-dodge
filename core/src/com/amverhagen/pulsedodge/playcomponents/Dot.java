package com.amverhagen.pulsedodge.playcomponents;

public class Dot {

	private float x_pos;
	private float y_pos;
	private Dot next;
	private Dot previous;

	public Dot(float x, float y) {
		x_pos = x;
		y_pos = y;
	}
	
	public boolean hasNext(){
		if(next != null){
			return true;
		}else{
			return false;
		}
	}
	
	public void setNext(Dot d){
		next = d;
	}
	
	public void setPrevious(Dot d){
		previous = d;
	}
	
	public Dot getNext(){
		return next;
	}
	
	public Dot getPrevious(){
		return previous;
	}

	public float getX() {
		return x_pos;
	}

	public float getY() {
		return y_pos;
	}

	public void setX(float x) {
		x_pos = x;
	}

	public void setXY(float x, float y) {
		x_pos = x;
		y_pos = y;
	}
}
