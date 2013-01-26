package com.puzzle;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Target {
	private Point point;
	private int type;
	private Bitmap bitmap_target;
	public Target(Bitmap bitmap_target,int type, Point point) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.point = point;
		this.bitmap_target = bitmap_target;
	}
	@Override
	public String toString() {
		return "Target [point=" + point + ", type=" + type + ", bitmap_target="
				+ bitmap_target + "]";
	}
	public void MyDraw(Canvas canvas, Paint paint){
		canvas.drawBitmap(bitmap_target, 
				point.getX(), point.getY(), paint);
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Bitmap getBitmap_target() {
		return bitmap_target;
	}
	public void setBitmap_target(Bitmap bitmap_target) {
		this.bitmap_target = bitmap_target;
	}
	public void logic(){
		
	}

}
