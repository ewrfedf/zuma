package com.puzzle;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.view.MotionEvent;

public class Shooter {
	private Bitmap shooter;

	private int x, y;
	public static int shooterH, shooterW;
	public static float angel ;
	

	public Shooter(Bitmap shooter) {
		// TODO Auto-generated constructor stub
		this.shooter = shooter;
		shooterW = shooter.getWidth();
		shooterH = shooter.getHeight();
		x = MyGameSurfaceView.screenW/2;
		y = MyGameSurfaceView.screenH - shooterH/3*2;
		
		angel =0;
	}
	public void MyDraw(Canvas canvas, Paint paint){
		
		canvas.save();
		canvas.rotate(angel, x, y);
		canvas.drawBitmap(shooter, x-shooterW/2, y-shooterH/2, paint);
		canvas.restore();
		
	}
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(Bullet.isshoot == false){
		int pressX = (int)event.getX();
		int pressY = (int) event.getY();
		if(event.getAction() == MotionEvent.ACTION_DOWN
				|| event.getAction() == MotionEvent.ACTION_MOVE){
			if(pressY < y){
				
//			angel =  (float) ((float) Math.atan((pressX-x)/(y-pressY))*(180/3.14));
		angel = (float) (Math.atan2((pressX-x),(y-pressY))*180/Math.PI);
//			System.out.println(x+","+
//				y+"   "+pressX+","+pressY+"   "+angel);
			}
		}
		if(event.getAction() == MotionEvent.ACTION_UP){
//			System.out.println(angel);
			
		}
		}
		return true;
	}

}
