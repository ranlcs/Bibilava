package com.lcs.Bibilava;

import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.view.Display;
import android.graphics.Point;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import android.view.MotionEvent;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import android.view.KeyEvent;
import android.content.Intent;

public class Terrain extends SurfaceView implements SurfaceHolder.Callback,View.OnLongClickListener{
	private Snake snake;
	private Obstacle obs;
	private Carre nour;
	private Paint p = new Paint();
	private int tailleX;
	private int tailleY;
	private boolean pause = true;
	private boolean gameB = false;
	private Context cont;
	private SecondThread thread;
	private int downX=0;
	private int downY=0;
	private int score=0;
	private int bestScore = 0;
	private Game game;
	private SnakeMob sMob;

	public Terrain(Context cont){
		super(cont);
		getHolder().addCallback(this);
		initDim(cont);
		// thread = new SecondThread(getHolder(),this);
		snake = new Snake(cont);
		nour = new Carre(cont,CstFonc.randomS(tailleX),CstFonc.randomS(tailleY),0,CstFonc.NOURR);
		obs = new Obstacle(cont,tailleX,tailleY);
		this.cont=cont;
		game = new Game(cont);
		sMob = new SnakeMob(cont,CstFonc.COTE*10,CstFonc.COTE*10);
		initScore();
	}
	public Terrain(Context cont,AttributeSet a){
		super(cont,a);
		getHolder().addCallback(this);
		// thread = new SecondThread(getHolder(),this);
		initDim(cont);
		snake = new Snake(cont);
		nour = new Carre(cont,CstFonc.randomS(tailleX),CstFonc.randomS(tailleY),0,CstFonc.NOURR);
		obs = new Obstacle(cont,tailleX,tailleY);
		this.cont=cont;
		game = new Game(cont);
		sMob = new SnakeMob(cont,CstFonc.COTE*10,CstFonc.COTE*10);
		initScore();
	}
	public void initScore(){
		try{
			FileInputStream temp = cont.openFileInput("score.txt");
			byte[] bt = new byte[temp.available()];
			temp.read(bt,0,temp.available());
			bestScore=Integer.valueOf(new String(bt));
		}catch(Exception e){ }
	}
	public void initDim(Context tre){
		WindowManager w = (WindowManager) tre.getSystemService(Context.WINDOW_SERVICE);
		Display ecran = w.getDefaultDisplay();
		Point p = new Point();
		ecran.getSize(p);
		tailleX = CstFonc.reduire(p.x);
		// tailleY = reduire(p.y)-150-40;
		tailleY = CstFonc.reduire(p.y)-48; // POUR TITRE ET DATE;s
	}
	
	@Override
	protected void onMeasure(int a,int b){
		setMeasuredDimension(tailleX,tailleY);
	}

	@Override
	public void onDraw(Canvas c){
		p.setColor(Color.BLACK);
		p.setStyle(Paint.Style.FILL);
		c.drawRect(10,10,tailleX-10,tailleY-10,p);
		obs.dessiner(c,p);
		snake.dessiner(c,p);
		nour.dessiner(c,p);
		sMob.dessiner(c,p);
		if(gameB){
			game.dessinerGame(c,p,score,bestScore,tailleX,tailleY);
		}
		if(!pause){
			snake.seDeplacer();
			sMob.seDeplacer();
			if(snake.toucheObstacle(obs) || snake.seTouche()){
				pause=true;
				snake.init(cont);
				obs.init();
				if(score>bestScore)
					bestScore = score;
				sauvegarder();
				gameB=true;
				initNour();
			}
			if(snake.touche(nour)){
				score++;
				snake.manger(cont);
				obs.ajouterObs(cont,snake.aAjouter());
				initNour();
			}
		}
	}
	@Override
	public boolean onLongClick(View v){
		Intent t= new Intent(cont,ActivityChoix.class);
		cont.startActivity(t);
		return true;
	}
	
	
	
	public void setDirection(int dir){
		if(!pause){
			snake.setDirection(dir);
			sMob.setDirection(dir);
			/*snake.seDeplacer();
			if(snake.toucheObstacle(obs)){
				pause=!pause;
			}
			if(snake.touche(nour)){
				snake.manger(cont);
				nour.setXY(random(tailleX),random(tailleY));
				thread.diminue();
			}*/
		}
	}
	public int getDirection(){
		return snake.getDirection();
	}
	public void sauvegarder(){
		try{
			FileOutputStream file = cont.openFileOutput("score.txt",Context.MODE_PRIVATE);
			String temp = String.valueOf(bestScore);
			file.write(temp.getBytes(),0,temp.length());
			file.flush();
			file.close();
		}catch(Exception e){ }

	}

	@Override
	public boolean onKeyDown(int key_code,KeyEvent k){
		switch(key_code){
			case KeyEvent.KEYCODE_5:case KeyEvent.KEYCODE_NUMPAD_5:case KeyEvent.KEYCODE_BUTTON_5:case KeyEvent.KEYCODE_DPAD_CENTER:{
				pause();
			}break;
			case KeyEvent.KEYCODE_2:case KeyEvent.KEYCODE_NUMPAD_2:case KeyEvent.KEYCODE_BUTTON_2:case KeyEvent.KEYCODE_DPAD_UP:{
				setDirection(CstFonc.HAUT);
			}break;
			case KeyEvent.KEYCODE_8:case KeyEvent.KEYCODE_NUMPAD_8:case KeyEvent.KEYCODE_BUTTON_8:case KeyEvent.KEYCODE_DPAD_DOWN:{
				setDirection(CstFonc.BAS);
			}break;
			case KeyEvent.KEYCODE_4:case KeyEvent.KEYCODE_NUMPAD_4:case KeyEvent.KEYCODE_BUTTON_4:case KeyEvent.KEYCODE_DPAD_LEFT:{
				setDirection(CstFonc.GAUCHE);
			}break;
			case KeyEvent.KEYCODE_6:case KeyEvent.KEYCODE_NUMPAD_6:case KeyEvent.KEYCODE_BUTTON_6:case KeyEvent.KEYCODE_DPAD_RIGHT:{
				setDirection(CstFonc.DROITE);
			}break;
		}
		return true;
	}
	
	public void initNour(){
		int xTemp=0;
		int yTemp=0;
		do{
			xTemp=CstFonc.randomS(tailleX);
			yTemp=CstFonc.randomS(tailleY);
		}while(obs.appartient(cont,tailleX,tailleY));
		nour.setXY(xTemp,yTemp);
	}
	public void onPause(){
		if(!pause)
			pause();
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try { 
				thread.join();
				retry = false; 
			} catch (InterruptedException e) {} 
		}
		// thread=null;
	}
	public void onResume(){
		thread=new SecondThread(getHolder(),this);
	}
	public void pause(){
		if(gameB==true){
			gameB=false;
			score=0;
		}
		else
			pause=!pause;
	}












	












	// Methode DE SurfaceView
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) { 
		thread.setRunning(true);
		thread.start(); 
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder){
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try { 
				thread.join();
				retry = false; 
			} catch (InterruptedException e) {} 
		}
	}
}