package com.lcs.Bibilava;

import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.graphics.Bitmap;

public class Game{
	private Bitmap[] chiffre = new Bitmap[10];
	private Bitmap game;
	private Bitmap score;
	private Bitmap best;

	public Game(Context cont){
		chiffre[0]=BitmapFactory.decodeResource(cont.getResources(),R.drawable.zero);
		chiffre[1]=BitmapFactory.decodeResource(cont.getResources(),R.drawable.un);
		chiffre[2]=BitmapFactory.decodeResource(cont.getResources(),R.drawable.deux);
		chiffre[3]=BitmapFactory.decodeResource(cont.getResources(),R.drawable.trois);
		chiffre[4]=BitmapFactory.decodeResource(cont.getResources(),R.drawable.quatre);
		chiffre[5]=BitmapFactory.decodeResource(cont.getResources(),R.drawable.cinq);
		chiffre[6]=BitmapFactory.decodeResource(cont.getResources(),R.drawable.six);
		chiffre[7]=BitmapFactory.decodeResource(cont.getResources(),R.drawable.sept);
		chiffre[8]=BitmapFactory.decodeResource(cont.getResources(),R.drawable.huit);
		chiffre[9]=BitmapFactory.decodeResource(cont.getResources(),R.drawable.neuf);
		game=BitmapFactory.decodeResource(cont.getResources(),R.drawable.game);
		score=BitmapFactory.decodeResource(cont.getResources(),R.drawable.score);
		best=BitmapFactory.decodeResource(cont.getResources(),R.drawable.best);
	}


	public void dessinerNombre(Canvas c,Paint p,char t,int x,int y){
		int nb = Integer.parseInt(String.valueOf(t));
		c.drawBitmap(chiffre[nb],x,y,p);
	}

	public void dessinerString(Canvas c,Paint p,String nb,int x,int y){
		int rx = x;
		for(int i=0;i<nb.length();i++){
			dessinerNombre(c,p,nb.charAt(i),rx,y);
			rx=rx+chiffre[0].getWidth();
		}
	}
	public void dessinerGame(Canvas c,Paint p,String scor,String bes,int x,int y){
		int ry=(game.getHeight()+best.getHeight()+score.getHeight());
		if(y<=ry)
			ry=0;
		else
			ry=y/2-ry/2-66;
		int rx=x/2-game.getWidth()/2;
		c.drawBitmap(game,rx,ry,p);

		rx=rx+56;
		ry=ry+game.getHeight()+5;
		c.drawBitmap(best,rx,ry,p);

		int rxx=rx+best.getWidth()+5;
		int ryy = ry+best.getHeight()-28;
		dessinerString(c,p,bes,rxx,ryy);

		ry = ry+best.getHeight()+5;
		c.drawBitmap(score,rx,ry,p);

		rxx=rx+score.getWidth()+5;
		ryy=ry+score.getHeight()-28;
		dessinerString(c,p,scor,rxx,ryy);
	}
	public void dessinerGame(Canvas c,Paint p,int s,int b,int x,int y){
		dessinerGame(c,p,String.valueOf(s),String.valueOf(b),x,y);
	}
}