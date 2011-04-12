package br.com.jera.locaweb;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import br.com.jera.graphic.Sprite;
import br.com.jera.towerdefenselib.TDActivity;
import br.com.jera.util.CommonMath.Vector2;

public class Locaweb extends TDActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		int tilemapSizeX = 14;
		int tilemapSizeY = 14;
		Vector2 tileSize = new Vector2(64, 64).multiply(Sprite.getGlobalSpriteScale());
		int[] mainLayer = new int[] {
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
		};
		int[] pathLayer = new int[] {
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1,  5,  6,  7,  8, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1,  4, -1, -1,  9, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1,  3, -1, -1, 10, -1, -1, 27, 28, 29, 30, 31, -1, 
				 0,  1,  2, -1, -1, 11, -1, -1, 26, -1, -1, -1, 32, -1, 
				-1, -1, -1, -1, -1, 12, -1, -1, 25, 24, -1, -1, 33, 34, 
				-1, -1, -1, -1, -1, 13, -1, -1, -1, 23, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, 14, -1, -1, -1, 22, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, 15, 16, -1, 20, 21, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, 17, 18, 19, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
		};
		
		startGame(new LWResourceIdRetriever(), tilemapSizeX, tilemapSizeY, tileSize, mainLayer, pathLayer);
    }
}