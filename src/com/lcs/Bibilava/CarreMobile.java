package com.lcs.Bibilava;

import android.graphics.Bitmap;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.BitmapFactory;

public class CarreMobile extends Carre{
	protected int etat=0;
	protected int etatX=0;

	public CarreMobile(Context c){
		cont=c;
		bitmap = BitmapFactory.decodeResource(c.getResources(),R.drawable.bombe);
		type=CstFonc.SIMPLE;
	}
	public void setXY(int x,int y){
		lx=x;
		ly=y;
		this.x=x;
		this.y=y;
	}
	
	public void seDeplacer(CarreMobile car){
		lx=x;
		ly=y;
		x=car.lx;
		y=car.ly;
		etat=etat%24;
	}
	
	public void dessiner(Canvas c,Paint p){
 		if(type==CstFonc.SIMPLE){
 			c.drawBitmap(bitmap,null,new RectF(x,y,x+CstFonc.COTE,y+CstFonc.COTE),p);
 		}
	}
}