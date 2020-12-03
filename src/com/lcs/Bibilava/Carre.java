package com.lcs.Bibilava;

import android.graphics.Canvas;
import android.graphics.Paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Color;
import android.graphics.BitmapFactory;

public class Carre{
	protected int x=0;
	protected int y=0;
	protected int lx = 0;
	protected int ly = 0;
	protected boolean mivily = false;
	protected int direction = CstFonc.DROITE;
	protected int type = CstFonc.SIMPLE;
	protected Bitmap bitmap = null;
	protected Context cont;
	protected boolean dessiner = true;

	public Carre(){}
	public Carre(Context cont,int xr,int yr,int dir,int typer){
		this.cont = cont;
		lx=xr;
		ly=yr;
		x=xr;
		y=yr;
		direction = dir;
		setType(cont,typer);
	}
	public void setType(Context cont,int typer){
		type = typer;
		switch(type){
			case CstFonc.SIMPLE: bitmap = BitmapFactory.decodeResource(cont.getResources(),R.drawable.simpleh);break;
			case CstFonc.QUEUE: bitmap=BitmapFactory.decodeResource(cont.getResources(),R.drawable.queued);break;
			case CstFonc.NOURR: bitmap=BitmapFactory.decodeResource(cont.getResources(),R.drawable.nourr);break;
			case CstFonc.MUR: bitmap = BitmapFactory.decodeResource(cont.getResources(),R.drawable.mur);break;
			case CstFonc.TETE:{
				switch(direction){
					case CstFonc.DROITE:bitmap =  BitmapFactory.decodeResource(cont.getResources(),R.drawable.teted);break;
					case CstFonc.GAUCHE:bitmap =  BitmapFactory.decodeResource(cont.getResources(),R.drawable.teteg);break;
					case CstFonc.HAUT:bitmap =  BitmapFactory.decodeResource(cont.getResources(),R.drawable.teteh);break;
					case CstFonc.BAS:bitmap =  BitmapFactory.decodeResource(cont.getResources(),R.drawable.teteb);break;
				}
			}
			break;
		}
	}
	public Carre(Context cont,int xr,int yr,int dir){
		this(cont,xr,yr,dir,CstFonc.SIMPLE);
	}
	public Carre(Context cont,int xr,int yr){
		this(cont,xr,yr,0,CstFonc.MUR);
	}
	public Carre(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void setMivily(boolean r){
		mivily = r;
	}
	public void seDeplacer(){
		lx=x;
		ly=y;
		switch(direction){
			case CstFonc.GAUCHE:{
				x=x-CstFonc.COTE;
			};
			break;
			case CstFonc.DROITE:{
				x=x+CstFonc.COTE;
			};
			break;
			case CstFonc.HAUT:{
				y=y-CstFonc.COTE;
			};
			break;
			case CstFonc.BAS:{
				y=y+CstFonc.COTE;
			};
			break;
		}
	}
	public void setDessiner(boolean des){
		dessiner=des;
	}
	public void seDeplacer(Carre avant){
		int difX = Math.abs(x-avant.x);
		int difY = Math.abs(y-avant.y);
		setDirection(avant);
		lx=x;
		ly=y;
		x=avant.lx;
		y=avant.ly;
		dessiner=true;

	}
	public void setXY(int xr,int yr){
		lx=x;
		ly=y;
		x = xr;
		y = yr;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getDirection(){
		return direction;
	}
	
	public void dessiner(Canvas c,Paint p){
		if(dessiner)
			c.drawBitmap(bitmap,null,new Rect(x,y,x+CstFonc.COTE,y+CstFonc.COTE),p);
	}
	public boolean isReverse(int dir){
		boolean reverse = false;
		if((direction==CstFonc.GAUCHE && dir ==CstFonc.DROITE) ||
				(direction==CstFonc.DROITE && dir==CstFonc.GAUCHE) ||
					(direction==CstFonc.HAUT && dir==CstFonc.BAS) ||
						(direction==CstFonc.BAS && dir== CstFonc.HAUT))
			reverse=true;
		return reverse;
	}
	public void setDirection(int dir){
	if(!isReverse(dir)){	
			if(type==CstFonc.TETE){
				switch(dir){
					case CstFonc.DROITE:bitmap =  BitmapFactory.decodeResource(cont.getResources(),R.drawable.teted);break;
					case CstFonc.GAUCHE:bitmap =  BitmapFactory.decodeResource(cont.getResources(),R.drawable.teteg);break;
					case CstFonc.HAUT:bitmap =  BitmapFactory.decodeResource(cont.getResources(),R.drawable.teteh);break;
					case CstFonc.BAS:bitmap =  BitmapFactory.decodeResource(cont.getResources(),R.drawable.teteb);break;
				}
			}
			direction = dir;
		}
	}
	public void setDirection(Carre car){
		if(type==CstFonc.SIMPLE){
			int difX = x-car.x;
			int difY = y-car.y;
			if(difX!=0 && difY!=0){
				bitmap = BitmapFactory.decodeResource(cont.getResources(),R.drawable.simplec);
			}
			else if(difY==0)
				bitmap = BitmapFactory.decodeResource(cont.getResources(),R.drawable.simpleh);
			else if(difX==0)
				bitmap = BitmapFactory.decodeResource(cont.getResources(),R.drawable.simplev);
		}
		else if(type==CstFonc.QUEUE){
			int difX2 = car.x-car.lx;
			int difY2 = car.y-car.ly;
			if (difX2>0)//(difY==0 && difX>0)
				bitmap = BitmapFactory.decodeResource(cont.getResources(),R.drawable.queued);
			else if(difX2<0)//(difY==0 && difX<0)
				bitmap = BitmapFactory.decodeResource(cont.getResources(),R.drawable.queueg);
			else if(difY2>0)//((difX==0 && difY>0) || (difX==1 && difY==1))
				bitmap = BitmapFactory.decodeResource(cont.getResources(),R.drawable.queueb);
			else if(difY2<0)//((difX==0 && difY<0) || (difX==1 && difY==-1))
				bitmap = BitmapFactory.decodeResource(cont.getResources(),R.drawable.queueh);

		}
	}
	public void invalidate(Canvas c,Paint p){
		p.setColor(Color.BLACK);
		p.setStyle(Paint.Style.FILL);
		c.drawRect(lx,ly,lx+CstFonc.COTE,ly+CstFonc.COTE,p);
	}
}