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
        
        int nowWidth=dm.widthPixels;//��ÿؼ���ԭʼ���
        int nowheight=dm.heightPixels;//��ÿؼ���ԭʼ�߶�
        int density  = (int) dm.density;//�������������ܶ�
        //�����ֻ����ܶ�����Ϊÿһ���ֻ����ڲ��죬
        int screenW = (int) (nowWidth * density);//��õ�ǰ�ֻ��Ŀ��
        int screenH = (int) (nowheight * density);//��õ�ǰ�ֻ��ĸ߶�
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
		final MenuItem seMang = menu.add("ɫäģʽ");
		final MenuItem soundCtrl = menu.add("������");
		seMang.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			

			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				if(isBlind){
					seMang.setTitle("ɫäģʽ");
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
					seMang.setTitle("����ģʽ");
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
					soundCtrl.setTitle("������");
					MyGameSurfaceView.soundFlag = false;
				}else{
					soundCtrl.setTitle("������");
					MyGameSurfaceView.soundFlag = true;
				}
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}
}