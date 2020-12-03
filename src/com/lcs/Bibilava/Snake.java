package com.lcs.Bibilava;

import java.util.Vector;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Canvas;

public class Snake{
	private Vector<Carre> snake = new Vector<Carre>();
	private int compte=0;
	private int vitesse=0;
	private int compteVitesse=0;
	private int compteAjout=0;

	public Snake(Context cont){
		init(cont);
	}
	public void init(Context cont){
		snake.clear();
		int xDef = CstFonc.COTE*10;
		int yDef = CstFonc.COTE*10;
		snake.add(new Tete(cont,xDef,yDef));
		for(int i=0;i<3;i++){
			xDef = xDef-CstFonc.COTE;
			snake.add(new Carre(cont,xDef,yDef,CstFonc.DROITE,CstFonc.SIMPLE));
		}
		xDef = xDef-CstFonc.COTE;
		snake.add(new Carre(cont,xDef,yDef,CstFonc.DROITE,CstFonc.QUEUE));
		vitesse=CstFonc.VITESSE;
		compteAjout=0;
		compteVitesse=0;
		CstFonc.initSon(cont);
	}
	public void setDirection(int dir){
		snake.elementAt(0).setDirection(dir);
	}

	public void seDeplacer(){
		if(compte==vitesse){
			CstFonc.joueDepl();
			snake.elementAt(0).seDeplacer();
			for(int i=1;i<snake.size();i++)
				snake.elementAt(i).seDeplacer(snake.elementAt(i-1));
			compte=0;

		}
		compte++;
	}
	public int getDirection(){
		return snake.elementAt(0).getDirection();
	}
	public void manger(Context cont){
		CstFonc.joueMange();
		snake.elementAt(snake.size()-1).setType(cont,CstFonc.SIMPLE);
		snake.elementAt(snake.size()-1).setDessiner(false);
		int xDef = snake.elementAt(snake.size()-1).getX();
		int yDef = snake.elementAt(snake.size()-1).getY();
		int dir = snake.elementAt(snake.size()-1).getDirection();
		snake.add(new Carre(cont,xDef,yDef,dir,CstFonc.QUEUE));
		snake.elementAt(snake.size()-1).setDirection(snake.elementAt(snake.size()-2));
		compteAjout++;
		diminue();
	}
	public void dessiner(Canvas c,Paint p){
		for(int i=0;i<snake.size();i++)
			snake.elementAt(i).dessiner(c,p);
	}
	public boolean toucheObstacle(Obstacle obs){
		return CstFonc.toucheObstacle(obs,snake.elementAt(0));
	}
	public boolean touche(Carre car){
		return CstFonc.toucheCarre(snake.elementAt(0),car);
	}
	public void invalidate(Canvas c,Paint p){
		for(int i=0;i<snake.size();i++)
			snake.elementAt(i).invalidate(c,p);
	}

	public int longueur(){
		return snake.size();
	}
	public int getX(){
		return snake.elementAt(0).getX();
	}
	public int getY(){
		return snake.elementAt(0).getY();
	}
	public boolean seTouche(){
		return CstFonc.toucheCarres(snake,snake.elementAt(0),1);
	}
	public void diminue(){
		if(compteVitesse==1){
			if(vitesse>CstFonc.VITESSE_MINIMALE)
				vitesse--;
			compteVitesse=0;
		}
		compteVitesse++;
	}
	public int aAjouter(){
		int l=0;
		int lon = snake.size();
		if(lon>=8 && lon<15)
			l=1;
		else if(lon>=15 && lon<30)
			l=2;
		else if(lon>=30)
			l=3;
		return l;
	}
}