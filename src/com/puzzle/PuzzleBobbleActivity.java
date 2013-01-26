package com.puzzle;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

public class PuzzleBobbleActivity extends Activity {
    /** Called when the activity is first created. */
	public static int screenH;
	public static int screenW;
	public static boolean isBlind = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGameSurfaceView(this));
        
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        
        int nowWidth=dm.widthPixels;//获得控件的原始宽度
        int nowheight=dm.heightPixels;//获得控件的原始高度
        int density  = (int) dm.density;//获得真机的像素密度
        //乘以手机的密度是因为每一个手机存在差异，
        int screenW = (int) (nowWidth * density);//获得当前手机的宽度
        int screenH = (int) (nowheight * density);//获得当前手机的高度
        System.out.println(screenW+","+screenH);
    }
    public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		System.exit(0);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		final MenuItem seMang = menu.add("色盲模式");
		final MenuItem soundCtrl = menu.add("声音关");
		seMang.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			

			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				if(isBlind){
					seMang.setTitle("色盲模式");
					isBlind = false;
					for(int i=0;i<MyGameSurfaceView.targets.length;i++){
						for(int j=0;j<MyGameSurfaceView.targets[0].length;j++){
							if(MyGameSurfaceView.targets[i][j].getType() != 0){
								MyGameSurfaceView.targets[i][j].setBitmap_target(MyGameSurfaceView.normal_bullet[MyGameSurfaceView.targets[i][j].getType()-1]);
								}
						}
					}
					for(int i=0;i<MyGameSurfaceView.bullets.size();i++){
						MyGameSurfaceView.bullets.get(i).setBitmap_bullet(MyGameSurfaceView.normal_bullet[MyGameSurfaceView.bullets.get(i).getType()-1]);
					}
					
				}else{
					seMang.setTitle("正常模式");
					isBlind = true;
					for(int i=0;i<MyGameSurfaceView.targets.length;i++){
						for(int j=0;j<MyGameSurfaceView.targets[0].length;j++){
							if(MyGameSurfaceView.targets[i][j].getType() != 0){
								MyGameSurfaceView.targets[i][j].setBitmap_target(MyGameSurfaceView.blind_bullet[MyGameSurfaceView.targets[i][j].getType()-1]);
								}
						}
					}
					for(int i=0;i<MyGameSurfaceView.bullets.size();i++){
						MyGameSurfaceView.bullets.get(i).setBitmap_bullet(MyGameSurfaceView.blind_bullet[MyGameSurfaceView.bullets.get(i).getType()-1]);
					}
				}
				return false;
			}
		});
		soundCtrl.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				if(MyGameSurfaceView.soundFlag){
					soundCtrl.setTitle("声音关");
					MyGameSurfaceView.soundFlag = false;
				}else{
					soundCtrl.setTitle("声音开");
					MyGameSurfaceView.soundFlag = true;
				}
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}
}