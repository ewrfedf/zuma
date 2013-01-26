package com.puzzle;

import java.util.ArrayList;
import java.util.List;

public class Remove {
private List<Point> points;
private boolean isexit = false;
private Target[][] targets;
public Remove() {
	
	// TODO Auto-generated constructor stub
	points = new ArrayList<Point>();
	targets = MyGameSurfaceView.targets;
}
	public boolean isExit(int i,int j){
		
		for(int n=0;n<points.size();n++){
			if(points.get(n).getX()==i && points.get(n).getY() ==j){
				isexit = true;
				break;
			}
			
		}
		
		return isexit;
	}
	
	
	public void quxiao(int i,int j){
		points.add(new Point(i, j));
			for(int n=0;n<points.size();n++){
				if(targets[points.get(i).getX()][points.get(j).getY()].getType()==targets[points.get(i).getX()][points.get(j).getY()-1].getType()){
					if(isExit(points.get(i).getX(), points.get(j).getY()-1)){
						points.add(new Point(points.get(i).getX(), points.get(j).getY()-1));
					}
				}
				if(targets[points.get(i).getX()][points.get(j).getY()].getType()==targets[points.get(i).getX()][points.get(j).getY()+1].getType()){
					if(isExit(points.get(i).getX(), points.get(j).getY()+1)){
						points.add(new Point(points.get(i).getX(), points.get(j).getY()+1));
					}
				}
				if(targets[points.get(i).getX()][points.get(j).getY()].getType()==targets[points.get(i).getX()-1][points.get(j).getY()].getType()){
					if(isExit(points.get(i).getX()-1, points.get(j).getY())){
						points.add(new Point(points.get(i).getX()-1, points.get(j).getY()));
					}
				}
				if(targets[points.get(i).getX()][points.get(j).getY()].getType()==targets[points.get(i).getX()-1][points.get(j).getY()+1].getType()){
					if(isExit(points.get(i).getX()-1, points.get(j).getY()+1)){
						points.add(new Point(points.get(i).getX()-1, points.get(j).getY()+1));
					}
				}
				if(targets[points.get(i).getX()][points.get(j).getY()].getType()==targets[points.get(i).getX()+1][points.get(j).getY()].getType()){
					if(isExit(points.get(i).getX()+1, points.get(j).getY())){
						points.add(new Point(points.get(i).getX()+1, points.get(j).getY()));
					}
				}
				if(targets[points.get(i).getX()][points.get(j).getY()].getType()==targets[points.get(i).getX()+1][points.get(j).getY()+1].getType()){
					if(isExit(points.get(i).getX()+1, points.get(j).getY()+1)){
						points.add(new Point(points.get(i).getX()+1, points.get(j).getY()+1));
					}
				}
			}
			
	}
}
