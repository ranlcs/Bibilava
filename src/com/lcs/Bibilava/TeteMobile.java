package com.lcs.Bibilava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.Rect;

public class TeteMobile extends CarreMobile{
	private int nDir;
	public TeteMobile(Context c){
		super(c);
		bitmap = BitmapFactory.decodeResource(c.getResources(),R.drawable.jarvis);
		setXY(CstFonc.reduire(0),CstFonc.reduire(0));
		direction=CstFonc.DROITE;
		nDir=CstFonc.DROITE;
	}

	public void setDirection(int dir){
		nDir=dir;
	}
	public void seDeplacer(){
		if(x%CstFonc.COTE==0 && y%CstFonc.COTE==0 && direction!=nDir)
			direction=nDir;
		switch(direction){
			case CstFonc.GAUCHE:seDeplacerGauche();break;
			case CstFonc.DROITE:seDeplacerDroite();break;
			case CstFonc.HAUT:seDeplacerHaut();break;
			case CstFonc.BAS:seDeplacerBas();break;
		}
	}
	public void seDeplacerDroite(){
		etatX=etatX+4;
		etatX=etatX%24;
		x=x+1;
	}
	public void seDeplacerGauche(){
		etatX=etatX+4;
		etatX=etatX%24;
		x--;
	}
	public void seDeplacerHaut(){
		etatX=etatX+4;
		etatX=etatX%24;
		y=y-1;
	}
	public void seDeplacerBas(){
		etatX=etatX+4;
		etatX=etatX%24;
		y=y+1;
	}
	public void dessiner(Canvas c,Paint p){
		if(direction == CstFonc.DROITE)
			c.drawBitmap(bitmap,new Rect(etatX*24,32,(etatX+1)*24,64),new RectF(x,y,x+CstFonc.IMG_W,y+CstFonc.IMG_H),p);
		else if(direction==CstFonc.GAUCHE)
			c.drawBitmap(bitmap,new Rect(etatX*24,96,(etatX+1)*24,128),new RectF(x,y,x+CstFonc.IMG_W,y+CstFonc.IMG_H),p);
		else if(direction==CstFonc.HAUT)
			c.drawBitmap(bitmap,new Rect(etatX*24,0,(etatX+1)*24,32),new Rect(x,y,x+CstFonc.IMG_W,y+CstFonc.IMG_H),p);
		else if(direction==CstFonc.BAS)
			c.drawBitmap(bitmap,new Rect(etatX*24,64,(etatX+1)*24,96),new Rect(x,y,x+CstFonc.IMG_W,y+CstFonc.IMG_H),p);
	}
}