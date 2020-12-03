package com.lcs.Bibilava;

import java.util.Vector;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.content.Context;
import android.widget.Toast;

public class Obstacle{
	private Vector<Carre> obs = new Vector<Carre>();
	private Vector<Carre> bord = new Vector<Carre>();
	private int nbBase;
	private int tailleX = 0;
	private int tailleY=0;

	public Obstacle(Context cont,int lon,int lar){
		for(int i=0;i<lon;i++){
			bord.add(new Carre(cont,i*CstFonc.COTE,0));
			bord.add(new Carre(cont,i*CstFonc.COTE,lar-CstFonc.COTE));
		}
		for(int i=1;i<lar-CstFonc.COTE;i++){
			bord.add(new Carre(cont,0,i*CstFonc.COTE));
			bord.add(new Carre(cont,lon-CstFonc.COTE,i*CstFonc.COTE));
		}
		nbBase = obs.size();
		tailleX=lon;
		tailleY=lar;
	}
	public void dessiner(Canvas c,Paint p){
		for(int i=0;i<bord.size();i++)
			bord.elementAt(i).dessiner(c,p);
		for(int i=0;i<obs.size();i++)
			obs.elementAt(i).dessiner(c,p);
	}

	public Vector<Carre> getObs(){
		return obs;
	}
	public Vector<Carre> getBord(){
		return bord;
	}
	public void ajouterObs(Context cont,int nb){
		for(int i=0;i<nb;i++){
			if(obs.size()<(nbBase+250)){
				int xTemp = CstFonc.randomM(tailleX);
				int yTemp = CstFonc.randomM(tailleY);
				obs.add(new Carre(cont,xTemp,yTemp));
			}
		}
	}
	public void ajouterObs(Context cont){
		ajouterObs(cont,1);
	}
	public boolean appartient(Context cont,int x,int y){
		return CstFonc.toucheCarres(obs,x,y);
	}
	public void init(){
		obs.clear();
	}
	
}