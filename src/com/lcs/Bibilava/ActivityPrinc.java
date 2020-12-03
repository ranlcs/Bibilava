package com.lcs.Bibilava;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.view.WindowManager;
import android.view.Window;
import android.widget.LinearLayout;
import android.content.SharedPreferences;
import android.content.Context;

public class ActivityPrinc extends Activity 
{
	private Terrain terrain;
	private ViewAmbony ambony;
	private ImageView haut;
	private ImageView bas;
	private ImageView gauche;
	private ImageView droite;
	private ImageView pause;
	private LinearLayout touche;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.terrain_xml);

		terrain = (Terrain) findViewById(R.id.terrain);
		
		ambony= (ViewAmbony) findViewById(R.id.ambony);// pour deplcament glisser
		ambony.setTerrain(terrain);
		// ambony.setVisibility(View.GONE);

		// pour deplacement toucher
		touche = (LinearLayout) findViewById(R.id.touche);
		haut = (ImageView) findViewById(R.id.haut);
		bas = (ImageView) findViewById(R.id.bas);
		droite = (ImageView) findViewById(R.id.droite);
		gauche = (ImageView) findViewById(R.id.gauche);
		pause = (ImageView) findViewById(R.id.pause);

		// touche.setVisibility(View.GONE);
		bas.setFocusable(false);
		haut.setFocusable(false);
		droite.setFocusable(false);
		pause.setFocusable(false);
		gauche.setFocusable(false);
		ambony.setFocusable(false);
		touche.setFocusable(false);
		terrain.setFocusable(true);
	}

	@Override 
	protected void onPause(){
		super.onPause();
		terrain.onPause();
	}
	@Override
	protected void onResume(){
		super.onResume();
		SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
		String txt = pref.getString("txtChecked","Glisser");
		if(txt.equals("Glisser")){
			ambony.setVisibility(View.VISIBLE);
			touche.setVisibility(View.GONE);
		}
		else{
			ambony.setVisibility(View.GONE);
			touche.setVisibility(View.VISIBLE);
		}
		terrain.onResume();
		// ITY SISA DE MI-SAUV LE MANETY
	}

	public void haut(View v){
		terrain.setDirection(CstFonc.HAUT);
	}
	public void gauche(View v){
		terrain.setDirection(CstFonc.GAUCHE);
	}
	public void droite(View v){
		terrain.setDirection(CstFonc.DROITE);
	}
	public void bas(View v){
		terrain.setDirection(CstFonc.BAS);
	}
	public void pause(View v){
		terrain.pause();
	}
}	
