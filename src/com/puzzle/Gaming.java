package com.puzzle;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;

public class Gaming {
	private Canvas c;
	Rect src;
	Rect dst;
	private Bitmap bitmap_bg;
	private Bitmap bitmap;
	public Gaming(Bitmap bitmap_bg) {
		// TODO Auto-generated constructor stub
		this.bitmap_bg = bitmap_bg;
		
		bitmap = Bitmap.createBitmap(bitmap_bg, bitmap_bg.getWidth()/4, 0, bitmap_bg.getWidth()/2,bitmap_bg.getHeight());
		
		c = new Canvas(bitmap);
		src = new Rect(0, 0, bitmap_bg.getWidth()/2, bitmap_bg.getHeight());
		dst = new Rect(0,0,MyGameSurfaceView.screenW,MyGameSurfaceView.screenH);
		
	}
	public void myDraw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		
		Bitmap bitmap = Bitmap.createBitmap(bitmap_bg, bitmap_bg.getWidth()/4, 0, bitmap_bg.getWidth()/2,bitmap_bg.getHeight());
		c = new Canvas(bitmap);
		src = new Rect(0, 0, bitmap_bg.getWidth()/2,bitmap_bg.getHeight());
		dst = new Rect(0,0,MyGameSurfaceView.screenW,MyGameSurfaceView.screenH);		
		canvas.drawBitmap(bitmap, src, dst, paint);
		
		
	}
}
