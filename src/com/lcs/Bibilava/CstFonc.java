package com.lcs.Bibilava;

import java.util.Vector;
import android.content.Context;
import android.widget.Toast;
import java.util.Calendar;
import android.media.MediaPlayer;

public class CstFonc{
	public static MediaPlayer sonDepl;
	public static MediaPlayer sonMange;
	private CstFonc(){}

	public static final int PAUSE=0;
	public static final int JEU=1;
	public static final int GAME=2;
	
	public static final int COTE = 12;
	public static final int DEPLA =3;
	public static final int VITESSE = 18;
	public static final int VITESSE_MINIMALE=5;

	public static final int HAUT = 0;
	public static final int DROITE = 1;
	public static final int BAS = 2;
	public static final int GAUCHE = 3;

	public static final int SIMPLE = 0;
	public static final int QUEUE = 1;
	public static final int NOURR = 2;
	public static final int TETE = 3;
	public static final int MUR = 4;

	public static final int IMG_W=24; 
	public static final int IMG_H=32;

	public static final void initSon(Context cont){
		sonDepl = MediaPlayer.create(cont,R.raw.son_depl);
		sonMange = MediaPlayer.create(cont,R.raw.son_mange);
	}

	public static final boolean toucheCarre(Carre a,Carre b){
		return a.getX()==b.getX() && a.getY()==b.getY();
	}
	public static final boolean toucheCarre(Tete a,Carre b){
		return a.getX()==b.getX() && a.getY()==b.getY();
	}
	public static boolean toucheCarre(Carre a,int x,int y){
		return a.getX()==x && a.getY()==y;
	}
	public static final boolean toucheCarres(Vector<Carre> vect,Carre car,int debut){
		int i=debut;
		boolean touche = false;
		while(i<vect.size() && !touche){
			touche = toucheCarre(vect.elementAt(i),car);
			i++;
		}
		return touche;
	}
	public static final boolean toucheCarres(Vector<Carre> vect,int x,int y,int debut){
		int i=debut;
		boolean touche=false;
		while(i<vect.size() && !touche){
			touche=toucheCarre(vect.elementAt(i),x,y);
			i++;
		}
		return touche;
	}
	public static final boolean toucheCarres(Vector<Carre> vect,Carre car){
		return toucheCarres(vect,car,0);
	}
	public static final boolean toucheCarres(Vector<Carre> vect,int x,int y){
		return toucheCarres(vect,x,y,0);
	}
	
	public static final boolean toucheObstacle(Obstacle obs,Carre car){
		return toucheCarres(obs.getObs(),car) || toucheCarres(obs.getBord(),car);
	}
	
	public static final int randomS(int a){
		return random(a,Calendar.getInstance().get(Calendar.SECOND));
	}
	public static final int randomM(int a){
		return random(a,Calendar.getInstance().get(Calendar.MILLISECOND));
	}


	public static final int random(int a,int b){	
		int temp = (int)((Math.random()*b*10000000))%(a-2*CstFonc.COTE);
		temp=reduire(temp);
		if(temp==0)
			temp=2*CstFonc.COTE;
		if(temp==a || temp==a-CstFonc.COTE)
			temp=a-2*CstFonc.COTE;
		return temp;
	}
	public static final int reduire(int x){
		while(x%CstFonc.COTE!=0){
			x=x-1;
		}
		return x;
	}
	public static final int augmenter(int x){
		while(x%CstFonc.COTE!=0)
			x++;
		return x;
	}

	public static final void joueSon(MediaPlayer mp){
		if(mp.isPlaying()){
			mp.stop();
			try{
				mp.prepare();
			}catch(Exception r){}
			mp.seekTo(0);
		}
		if(mp!=null)
			mp.start();
	}
	public static final void joueDepl(){
		joueSon(sonDepl);
	}
	public static final void joueMange(){
		joueSon(sonMange);
	}
}