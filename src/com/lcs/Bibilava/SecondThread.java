package com.lcs.Bibilava;

import android.view.SurfaceHolder;
import android.graphics.Canvas;

public class SecondThread extends Thread{ 
	private SurfaceHolder surfaceHolder;
	private Terrain mySurface;
	private boolean run = false;

	public SecondThread(SurfaceHolder surfaceHolder, Terrain mySurface){
		this.surfaceHolder = surfaceHolder; 
		this.mySurface = mySurface;
	}

	public void setRunning(boolean r){ 
		run = r; 
	}

	@Override
	public void run(){ 
		Canvas c;
		while (run)
		{
			c = null;
			try {
				c = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) { 
					mySurface.onDraw(c); 
				} 
			}
			finally {
				if (c != null) surfaceHolder.unlockCanvasAndPost(c);
			}
			try{
				Thread.sleep(1);
			}catch(Exception e){}
		}

	}
}