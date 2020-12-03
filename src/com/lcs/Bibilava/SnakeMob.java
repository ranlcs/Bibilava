package com.lcs.Bibilava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Vector;

public class SnakeMob{
	private TeteMobile tete;
	private Vector<CarreMobile> snake = new Vector<CarreMobile>();
	private int vitesse = 2;
	private int compte=0;
	public SnakeMob(Context c,int xr,int yr){
		tete = new TeteMobile(c);
		int xDef=xr;
		tete.setXY(xDef,yr);
		for(int i=0;i<3;i++){
			xDef=xDef-CstFonc.COTE;
			snake.add(new CarreMobile(c));
			snake.elementAt(i).setXY(xDef,yr);
		}
	}
	public void setDirection(int dir){
		tete.setDirection(dir);
	}
	public void seDeplacer(){
		if(compte==vitesse){
			tete.seDeplacer();
			compte=0;
		}
		compte++;
	}
	public void dessiner(Canvas c,Paint p){
		tete.dessiner(c,p);
	}
}