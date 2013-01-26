package com.puzzle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.game.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MyGameSurfaceView extends SurfaceView implements Callback, Runnable {
	
	private Canvas canvas;
	private Paint paint;
	private SurfaceHolder sfh;
	public static int screenH, screenW;
	Thread th;
	private boolean flag;
	
	private Shooter shooter;
	private Bitmap bitmap_shooter;
	
	private Gaming gaming;
	private Bitmap bitmap_bg;
	
	public static Bullet bullet;
	public List<Target> list = new ArrayList<Target>();
	public static LinkedList<Bullet> bullets;
	public static Bitmap bitmap_bullet;	
	public static Bitmap bitmap_onebullet;
	public static Bitmap[] normal_bullet;
	public static Bitmap[] frozen_bullet;
	public static Bitmap[] blind_bullet;
	public static Bitmap bitmap_win;
	public static Bitmap bitmap_loss;
	public static Target[][] targets;
	public static Random random;

	private List<Point> points ;
	private List<Point> added ;
	
	public static SoundPool sp;
	public static int[] spid;
	public static boolean soundFlag = true;
	private int firstX ,firstY;
	public static int n;
	public static int[][][]position; 
//	private int[][][] position = {
//			{{27,48},{58,38},{89,38},{120,38},{151,38},{182,38},{213,38},{244,38},},
//			{{43,65},{74,65},{105,65},{136,65},{167,65},{198,65},{229,65},{260,65},},
//			{{27,92},{58,92},{89,92},{120,92},{151,92},{182,92},{213,92},{244,92},},
//			{{43,119},{74,119},{105,119},{136,119},{167,119},{198,119},{229,119},{260,119},},
//			{{27,146},{58,146},{89,146},{120,146},{151,146},{182,146},{213,146},{244,146},},
//			{{43,173},{74,173},{105,173},{136,173},{167,173},{198,173},{229,173},{260,173},},
//			{{27,200},{58,200},{89,200},{120,200},{151,200},{182,200},{213,200},{244,200},},
//			{{43,227},{74,227},{105,227},{136,227},{167,227},{198,227},{229,227},{260,227},},
//			{{27,254},{58,254},{89,254},{120,254},{151,254},{182,254},{213,254},{244,254},},
//			{{43,281},{74,281},{105,281},{136,281},{167,281},{198,281},{229,281},{260,281},},
//			{{27,308},{58,308},{89,308},{120,308},{151,308},{182,308},{213,308},{244,308},},
//			{{43,335},{74,335},{105,335},{136,335},{167,335},{198,335},{229,335},{260,335},},
//			{{27,362},{58,362},{89,362},{120,362},{151,362},{182,362},{213,362},{244,362}},
//	};
	public MyGameSurfaceView(Context context) {
		super(context);
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		setFocusable(true);
		setFocusableInTouchMode(true);
	}
	public void xiaoqu(int i,int j){
		points.clear();
		added.clear();
		points.add(new Point(i, j));
		xiaoqu(i,j,0);
	}

	public void xiaoqu(int i,int j,int a){
//		System.out.println("points.size = "+points.size());
//		for(int i1=0;i1<points.size();i1++){
//			System.out.println("points"+i+" = "+points.get(i1).getX()+","+points.get(i1).getY());
//		}
//		System.out.println("added.size = "+added.size());
//		for(int i1=0;i1<added.size();i1++){
//			System.out.println("added"+i+" = "+added.get(i1).getX()+","+added.get(i1).getY());
//		}
		
			if(a != 1){
				if(i != 0){
					if(i%2 ==0){
						if(j >= 1){
							if(targets[i][j].getType() == targets[i-1][j-1].getType()){
								if(isAdded(new Point(i-1,j-1)) == false)
								{
								points.add(new Point(i-1,j-1));
//								added.add(new Point(i,j));
//								xiaoqu(i-1,j-1,6);
								}
							}
						}
					}
					else if(i%2 == 1){
						if(targets[i][j].getType() == targets[i-1][j].getType()){
							if(isAdded(new Point(i-1,j)) == false)
							{
							points.add(new Point(i-1,j));
//							added.add(new Point(i,j));
//							xiaoqu(i-1,j,6);
							}
						}
					}
					
				}
			}
////////////////////////////////////////////			
			if(a != 2){
				if(i != 0){
					if(i%2 ==0){
							if(targets[i][j].getType() == targets[i-1][j].getType()){
								if(isAdded(new Point(i-1,j)) == false)
								{
								points.add(new Point(i-1,j));
//								added.add(new Point(i,j));
//								xiaoqu(i-1,j,5);
								}
						}
					}
					else if(i%2 == 1){
						if(j <= 6){
						if(targets[i][j].getType() == targets[i-1][j+1].getType()){
							if(isAdded(new Point(i-1,j+1)) == false)
							{
							points.add(new Point(i-1,j+1));
//							added.add(new Point(i,j));
//							xiaoqu(i-1,j+1,5);
						}
						}
					}
					}
				}
				
			}
//////////////////////////////////
			if(a != 3){
				if(j >= 1){
					if(targets[i][j].getType() == targets[i][j-1].getType()){
						if(isAdded(new Point(i,j-1)) == false)
						{
						points.add(new Point(i,j-1));
//						added.add(new Point(i,j));
//						xiaoqu(i,j-1,4);
					}
					}
				}
			}
/////////////////////////////////////////			
		if(a != 4){
			if(j <= 6){
				if(targets[i][j].getType() == targets[i][j+1].getType()){
					if(isAdded(new Point(i,j+1)) == false)
					{
					points.add(new Point(i,j+1));
//					added.add(new Point(i,j));
//					xiaoqu(i,j+1,3);
					}
				}	
			}
		}
///////////////////////////////////
		if(a != 5){
			if(i < targets.length-1){
				if(i %2 ==0){
					if(j>=1){
						if(targets[i][j].getType() == targets[i+1][j-1].getType()){
							if(isAdded(new Point(i+1,j-1)) == false)
							{
							points.add(new Point(i+1,j-1));
//							added.add(new Point(i,j));
//							xiaoqu(i+1,j-1,2);
							}
						}	
					}
				}
				else if(i %2 == 1){
					if(targets[i][j].getType() == targets[i+1][j].getType()){
						if(isAdded(new Point(i+1,j)) == false)
						{
						points.add(new Point(i+1,j));
//						added.add(new Point(i,j));
//						xiaoqu(i+1,j,2);
						}
					}	
				}
			}
		}
//////////////////////////////
		if(a != 6){
			if(i < targets.length-1){
				if(i %2 ==0){
					if(targets[i][j].getType() == targets[i+1][j].getType()){
						if(isAdded(new Point(i+1,j)) == false)
						{
						points.add(new Point(i+1,j));
//						added.add(new Point(i,j));
//						xiaoqu(i+1,j,1);
						}
					}		
				}
				else if(i %2 == 1){
					if(j >= 1){
					if(targets[i][j].getType() == targets[i+1][j-1].getType()){
						if(isAdded(new Point(i+1,j-1)) == false)
						{
						points.add(new Point(i+1,j-1));
//						added.add(new Point(i,j));
//						xiaoqu(i+1,j-1,1);
						}
					}	
				}
				}
			}
		}
		added.add(new Point(i,j));
		for(int n=0;n<points.size();n++){
			System.out.println("points.size()="+points.size());
			if(isAddedPoint(points.get(n))==false){
				xiaoqu(points.get(n).getX(),points.get(n).getY(),0);
			}
		}
	}
	
	public boolean isAddedPoint(Point point){
		boolean isadd = false;
		for(int i=0;i<added.size();i++){
			System.out.println(i);
			if(added.get(i).getX() == point.getX() && added.get(i).getY() == point.getY()) {
				isadd = true;
				break;
		}
		}
		return isadd;
	}
	public boolean isAdded(Point point){
		boolean isadded = false;
		for(int i=0;i<points.size();i++){
			if(points.get(i).getX() == point.getX() && points.get(i).getY() == point.getY()) {
				isadded = true;
				break;
		}
		}
		return isadded;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(flag){
			long start =System.currentTimeMillis();
			myDraw();
			logic();
			long end = System.currentTimeMillis();
			if(end-start<50){
				try {
					Thread.sleep(50-(end-start));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private void logic() {
		// TODO Auto-generated method stub

		bullet.logic();
		if(Bullet.isshoot==true){
			int i;
			int j;
		for(i=0;i<targets.length;i++){
			for(j=0; j<targets[0].length;j++){
			if(bullet.isCollsionWith(targets[i][j])) {
				Bullet.isshoot = false;
				Bullet.isCollsion = false;
//				System.out.println("Åöµ½ÁË     "+bullet.x+","+bullet.y+"   "
//				+targets[i][j].getPoint().getX()+","+targets[i][j].getPoint().getY());

				if(bullet.x - targets[i][j].getPoint().getX() > 0){
				if(i%2 ==0){
					if(targets[i+1][j].getType() == 0){
					targets[i+1][j].setBitmap_target(bullets.get(0).getBitmap_bullet());
					targets[i+1][j].setType(bullets.get(0).getType());
					xiaoqu(i+1,j);
					}
				} else {
					if(targets[i+1][j+1].getType() == 0){
					targets[i+1][j+1].setBitmap_target(bullets.get(0).getBitmap_bullet());
					targets[i+1][j+1].setType(bullets.get(0).getType());
					xiaoqu(i+1,j+1);	
					
					}
				}
				}
				else if(bullet.x - targets[i][j].getPoint().getX() < 0){
					if(i%2 ==0){
					if(targets[i+1][j-1].getType() == 0){
					targets[i+1][j-1].setBitmap_target(bullets.get(0).getBitmap_bullet());
					targets[i+1][j-1].setType(bullets.get(0).getType());
					xiaoqu(i+1,j-1);
					
					}
					}
					else {
					if(targets[i+1][j].getType() == 0){
					targets[i+1][j].setBitmap_target(bullets.get(0).getBitmap_bullet());
					targets[i+1][j].setType(bullets.get(0).getType());
					xiaoqu(i+1,j);
					
					}
					}
				}
				else if(bullet.x - targets[i][j].getPoint().getX() == 31){
					if(bullets.get(0).direction ==Bullet.DIRECTION_LEFT){
						if(j<7){
						if(targets[i][j+1].getType() == 0){
						targets[i][j+1].setBitmap_target(bullets.get(0).getBitmap_bullet());
						targets[i][j+1].setType(bullets.get(0).getType());
					xiaoqu(i,j+1);
						}
						}
					}
					}
					else if(bullet.x - targets[i][j].getPoint().getX() == -31) {
						if(bullet.direction ==Bullet.DIRECTION_RIGHT){
							if(targets[i][j-1].getType() == 0){
						targets[i][j-1].setBitmap_target(bullets.get(0).getBitmap_bullet());
						targets[i][j-1].setType(bullets.get(0).getType());
						
					xiaoqu(i,j-1);
						
							}
					}
					}

				
				for(int a=0;a<bullets.size();a++){
					System.out.println("bullet["+a+"]= "+bullets.get(a).getType());
				}
				bullets.remove(0);
				int type3 = random.nextInt(300)%8+1;
				bullets.add(newBullet(type3));
//				System.out.println("size = "+bullets.size());
				System.out.println("anctionDownSTOP");
				System.out.println("bullet.type = "+bullet.getType());
				for(int a=0;a<bullets.size();a++){
					System.out.println("bullet["+a+"]= "+bullets.get(a).getType());
				}
				if(soundFlag){
					sp.play(spid[5], 1, 1, 0, 0, 1);
				}
				break;
			}
			}
			}
			

		
		if(bullet.y <= targets[0][0].getPoint().getY()){
			Bullet.isshoot = false;
			for(j=0;j<targets[0].length;j++){
//				System.out.println(0+","+j);
				if(targets[0][j].getPoint().getX() < bullet.x &&
						targets[0][j].getPoint().getX()+31 > bullet.x){
					if(targets[0][j].getType() == 0){
					targets[0][j].setBitmap_target(bullets.get(0).getBitmap_bullet());
					targets[0][j].setType(bullets.get(0).getType());
					if(soundFlag){
						sp.play(spid[5], 1, 1, 0, 0, 1);
					}
					xiaoqu(0,j);
					}
					bullet.direction = Bullet.DIRECTION_STOP;
					break;
				}
			
			}
			bullets.remove(0);
			int type3 = random.nextInt(300)%8+1;
			bullets.add(newBullet(type3));
			
		}
		}

		if(points.size() >= 3){
			for(int n=0;n<points.size();n++){
				targets[points.get(n).getX()][points.get(n).getY()].setBitmap_target(null);
				targets[points.get(n).getX()][points.get(n).getY()].setType(0);
			}
		}
//		if(isWin()){
//			paint.setColor(Color.RED);
//			paint.setTextSize(30);
//			canvas.drawText("YOU WIN!", 100, 200, paint);
//		}
//		
//			if(isLoss()){
//				paint.setColor(Color.RED);
//				paint.setTextSize(30);
//				canvas.drawText("GAME  OVAE!", 100, 200, paint);
//			}
			
	}
	private boolean isLoss(){
		boolean isLoss = false;
		for(int j=0;j<targets[0].length;j++){
			if(targets[position.length-1][j].getType() != 0){
				isLoss = true;
				break;
			}
		}
		return isLoss;
	}
	
	private boolean isWin(){
		boolean isWin = true;
		for(int i=0;i<targets.length;i++){
			for(int j=0;j<targets[0].length;j++){
				if(targets[i][j].getType() != 0){
					isWin = false;
					break;
				}
				else isWin = true;
			}
			if(isWin == false){
				break;
			}
		}
		return isWin;
	}
	private void myDraw() {
		// TODO Auto-generated method stub
		canvas = sfh.lockCanvas();
		try {
		if(canvas != null){
			
			gaming.myDraw(canvas, paint);
			
			bullet.MyDraw(canvas, paint);
			shooter.MyDraw(canvas, paint);
			
			for(int i=0; i<targets.length;i++){
				for(int j=0; j<targets[0].length;j++){
				if(targets[i][j].getType() != 0){
				targets[i][j].MyDraw(canvas, paint);
				}
			}
			}
			
			if(isLoss()){
				if(soundFlag){
					sp.play(spid[4], 1, 1, 0, 0, 1);
					sp.play(spid[6], 1, 1, 0, 0, 1);
				}
				for(int i=0; i<targets.length;i++){
				for(int j=0; j<targets[0].length;j++){
				if(targets[i][j].getType() != 0){
				targets[i][j].setBitmap_target(frozen_bullet[targets[i][j].getType()-1]);
				targets[i][j].MyDraw(canvas, paint);
				}
			}
			}
				for(int i=0;i<bullets.size();i++){
					bullets.get(i).setBitmap_bullet(frozen_bullet[bullets.get(i).getType()-1]);
				}
				canvas.drawBitmap(bitmap_loss, 0, screenH/2 - bitmap_loss.getHeight()/2, paint);
				Bullet.isshoot = true;
				
			}
			

			if(isWin()){
				if(soundFlag){
					sp.play(spid[0], 1, 1, 0, 0, 1);
				}
				canvas.drawBitmap(bitmap_win, 0, screenH/2 - bitmap_win.getHeight()/2, paint);
				Bullet.isshoot = true;
			}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(canvas!=null)
			sfh.unlockCanvasAndPost(canvas);
		}
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		shooter.onTouchEvent(event);

		bullet.onTouchEvent(event);

		return true;
		
	}
	
	private void init() {
		// TODO Auto-generated method stub
		bitmap_shooter = BitmapFactory.decodeResource(getResources(), 
				R.drawable.shooter);
		bitmap_bg = BitmapFactory.decodeResource(getResources(), 
				R.drawable.background);
		
		shooter = new Shooter(bitmap_shooter);
		gaming = new Gaming(bitmap_bg);
		
		
	}
	
	public Bullet newBullet(int type){
		if(type >0){
			if(PuzzleBobbleActivity.isBlind == false){
		bullet = new Bullet(normal_bullet[type-1], type);
			}
			else {
		bullet = new Bullet(blind_bullet[type-1], type);
			}
		}
		
		return bullet;
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
		
		
		screenW = this.getWidth();
		screenH = this.getHeight();
		
		bullets = new LinkedList<Bullet>();
		points = new ArrayList<Point>();
		added = new ArrayList<Point>();
		normal_bullet = new Bitmap[8];
		frozen_bullet = new Bitmap[8];
		blind_bullet = new Bitmap[8];
		initSoundPool();
		initBullet();
		random = new Random();
		bitmap_loss = BitmapFactory.decodeResource(getResources(), R.drawable.lose_panel);
		bitmap_win = BitmapFactory.decodeResource(getResources(), R.drawable.win_panel);
		bitmap_onebullet = BitmapFactory.decodeResource(getResources(),
				R.drawable.bubble_1);
		
		for(int line=0;;line++){
			if(screenH*3/4.0 < screenH/14.0+line*bitmap_onebullet.getHeight()*5/7.0){
				n = line-1;
				break;
			}
		}
		
		firstX = screenW/16;
		firstY = screenH/14;
		position = new int[n][8][8];
		for(int i=0;i<position.length;i++){
			for(int j=0;j<position[0].length;j++){
				if(i%2 == 0) {
					position[i][j][0] = firstX + j*bitmap_onebullet.getWidth();
					position[i][j][1] =	firstY + i*bitmap_onebullet.getWidth()*5/6;
				}
				else if(i%2 ==1) {
					position[i][j][0] = firstX + j*bitmap_onebullet.getWidth() +bitmap_onebullet.getWidth()/2;
					position[i][j][1] =	firstY + i*bitmap_onebullet.getWidth()*5/6;
				}
//				System.out.println("position["+i+"]["+j+"]"+position[i][j][0]+","+position[i][j][1]);
			}
		}
//		bullet = new Bullet(bitmap_onebullet,1);
		init();
		
		int type1 = random.nextInt(100)%8+1;
		bullets.add(newBullet(type1));
		
//		bullet.setBitmap_bullet(bullets.get(0).getBitmap_bullet());
//		bullet.setType(bullets.get(0).getType());
		
		
		
		targets = new Target[position.length][position[0].length];
		for(int i=0;i< targets.length;i++){
			for(int j=0; j<targets[0].length;j++){
			targets[i][j] = new Target(null, 0, new Point(position[i][j][0], position[i][j][1]));
//			targets[i][j].setBitmap_target(bullets.get(0).getBitmap_bullet());
//			targets[i][j].setPoint(new Point(position[i][j][0], position[i][j][1]));
//			targets[i][j].setType(bullets.get(0).getType());
		}
		}
		
//		targets[0][0].setBitmap_target(newBullet(2).bitmap_bullet);
//		targets[0][0].setType(2);
//		targets[0][2].setBitmap_target(newBullet(2).bitmap_bullet);
//		targets[0][2].setType(2);
//		targets[0][1].setBitmap_target(newBullet(2).bitmap_bullet);
//		targets[0][1].setType(2);
		for(int i=0;i<GameMap.map.length;i++){
			for(int j=0;j<GameMap.map[i].length;j++){
				targets[i][j].setBitmap_target(newBullet(GameMap.map[i][j]).getBitmap_bullet());
				targets[i][j].setType(newBullet(GameMap.map[i][j]).getType());
			}
		}
		int type2 = random.nextInt(100)%8+1;
		bullets.add(newBullet(type2));
		int type3 = random.nextInt(100)%8+1;
		bullets.add(newBullet(type3));
		System.out.println("bullet = "+bullet.getType());
		System.out.println(type1+"---"+type2+"---"+type3);
		for(int i=0;i<bullets.size();i++){
			System.out.println("bullets["+i+"]="+bullets.get(i).getType());
		}
		flag = true;
		th = new Thread(this);
		th.start();
//		System.out.println("hunter = "+bitmap_shooter.getWidth()+","+bitmap_shooter.getHeight());
//		System.out.println("bullet = "+bitmap_bullet.getWidth()+","+bitmap_bullet.getHeight());
	}

	private void initBullet() {
		// TODO Auto-generated method stub
		for(int i=0;i<8;i++){
			normal_bullet[i] = BitmapFactory.decodeResource(getResources(), R.drawable.bubble_1+i);
			frozen_bullet[i] = BitmapFactory.decodeResource(getResources(), R.drawable.frozen_1+i);
			blind_bullet[i] = BitmapFactory.decodeResource(getResources(), R.drawable.blind_1+i);
		}
		
	}
	private void initSoundPool() {
		// TODO Auto-generated method stub
		sp = new SoundPool(9, AudioManager.STREAM_MUSIC, 100);
		spid = new int[9];
		spid[0] = sp.load(getContext(), R.raw.applause, 1);
		spid[1] = sp.load(getContext(), R.raw.destroy_group, 1);
		spid[2] = sp.load(getContext(), R.raw.hurry, 1);
		spid[3] = sp.load(getContext(), R.raw.launch, 1);
		spid[4] = sp.load(getContext(), R.raw.lose, 1);
		spid[5] = sp.load(getContext(), R.raw.newroot_solo, 1);
		spid[6] = sp.load(getContext(), R.raw.noh, 1);
		spid[7] = sp.load(getContext(), R.raw.rebound, 1);
		spid[8] = sp.load(getContext(), R.raw.stick, 1);
		
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		flag = false;

	}

}
