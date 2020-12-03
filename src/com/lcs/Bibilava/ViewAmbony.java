package com.lcs.Bibilava;

import android.view.View;
import android.content.Context;
import android.view.MotionEvent;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.view.Display;
import java.util.Calendar;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;


public class ViewAmbony extends View{
	private Terrain ter;
	private int tailleX=0;
	private int tailleY=0;
	private int downX=0;
	private int downY=0;
	private int sec=0;
	private boolean actif=true;
	private Context cont;

	public ViewAmbony(Context tre){
		super(tre);
		initDim(tre);
		cont=tre;
	}
	public ViewAmbony(Context tre,AttributeSet a){
		super(tre,a);
		initDim(tre);
		cont=tre;
	}
	public void setTerrain(Terrain te){
		ter=te;
	}
	@Override
	protected void onMeasure(int a,int b){
		setMeasuredDimension(tailleX,tailleY);
	}

	public void initDim(Context tre){
		WindowManager w = (WindowManager) tre.getSystemService(Context.WINDOW_SERVICE);
		Display ecran = w.getDefaultDisplay();
		Point p = new Point();
		ecran.getSize(p);
		tailleX = p.x;
		// tailleY = reduire(p.y)-150-40;
		tailleY = p.y; // POUR TITRE ET DATE;s
	}

	public int orientation(MotionEvent m){
		int res=ter.getDirection();
		float difX = downX-m.getX();
		float difY = downY-m.getY();

		if(difX<0 && difY>0){
			if(Math.abs(difX) > Math.abs(difY))
				res = CstFonc.DROITE;
			else
				res = CstFonc.HAUT;
		}
		else if(difX>0 && difY>0){
			if(Math.abs(difX) > Math.abs(difY))
				res = CstFonc.GAUCHE;
			else
				res = CstFonc.HAUT;
		}
		else if(difX<0 && difY<0){
			if(Math.abs(difX) > Math.abs(difY))
				res = CstFonc.DROITE;
			else
				res = CstFonc.BAS;
		}
		else if(difX>0 && difY<0){
			if(Math.abs(difX) > Math.abs(difY))
				res = CstFonc.GAUCHE;
			else
				res = CstFonc.BAS;
		}
		return res;
	}
	@Override
	public boolean onTouchEvent(MotionEvent m){
		boolean ret = true;
		switch(m.getAction()){
			case MotionEvent.ACTION_DOWN:{
				downX = (int)m.getX();
				downY = (int)m.getY();
				Calendar cal = Calendar.getInstance();
				sec=cal.get(Calendar.SECOND);
			}
			break;
			case MotionEvent.ACTION_UP:{
				float difX=Math.abs(downX-m.getX());
				float difY=Math.abs(downY-m.getY());
				Calendar cal = Calendar.getInstance();
				int seco = Math.abs(sec-cal.get(Calendar.SECOND));
				if(difX<5 && difY<5){
					if(seco<=1)
						ter.pause();
					else{
						Intent t= new Intent(cont,ActivityChoix.class);
						cont.startActivity(t);
					}
				}
				else
					ter.setDirection(orientation(m));
				ret = false;
			}
		}
		return ret;
	}
}