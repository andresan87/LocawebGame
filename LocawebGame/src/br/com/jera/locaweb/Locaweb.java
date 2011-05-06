package br.com.jera.locaweb;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import br.com.jera.audio.AudioPlayer;
import br.com.jera.graphic.Sprite;
import br.com.jera.platform.android.JGRunnable;
import br.com.jera.towerdefenselib.TDActivity;
import br.com.jera.towers.TowerProfile;
import br.com.jera.util.CommonMath.Vector2;
import br.com.jera.weapons.Axe;
import br.com.jera.weapons.Snapshot;
import br.com.jera.weapons.Spear;
import br.com.jera.weapons.WeaponProfile;

public class Locaweb extends TDActivity {

	private static MediaPlayer mPlayer; 
	
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
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
		};
		int[] pathLayer = new int[] {
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1,  5,  6,  7,  8, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1,  4, -1, -1,  9, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1,  3, -1, -1, 10, -1, -1, 27, 28, 29, 30, -1, -1, 
				 0,  1,  2, -1, -1, 11, -1, -1, 26, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, 12, -1, -1, 25, 24, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, 13, -1, -1, -1, 23, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, 14, -1, -1, -1, 22, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, 15, 16, -1, 20, 21, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, 17, 18, 19, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
		};

		final LWResourceIdRetriever resRet = new LWResourceIdRetriever();
		TowerProfile[] towerProfiles = new TowerProfile[3];
		towerProfiles[0] = new TowerProfile() {
			public int getResourceId() {
				return resRet.getBmpTower01();
			}
			public WeaponProfile getWeapon() {
				return weapon;
			}
			private WeaponProfile weapon = new Spear(resRet);
		};
		towerProfiles[1] = new TowerProfile() {
			public int getResourceId() {
				return resRet.getBmpTower02();
			}
			public WeaponProfile getWeapon() {
				return weapon;
			}
			private WeaponProfile weapon = new Snapshot(resRet);
		};
		towerProfiles[2] = new TowerProfile() {
			public int getResourceId() {
				return resRet.getBmpTower03();
			}
			public WeaponProfile getWeapon() {
				return weapon;
			}
			private WeaponProfile weapon = new Axe(resRet);
		};

		mPlayer = MediaPlayer.create(this, resRet.getSfxMenuSong());

		JGRunnable runnable = new JGRunnable() {

			public void run(final String status, Activity activity, final AudioPlayer audioPlayer) {
				activity.runOnUiThread(new Runnable() {

					public void run() {
						if (mPlayer != null && audioPlayer != null) {
							mPlayer.setVolume(audioPlayer.getGlobalVolume(), audioPlayer.getGlobalVolume());
							if (status.equals("mainMenu")) {
								if (!mPlayer.isPlaying()) {
									mPlayer.start();
								}
							} else {
								//mPlayer.stop();
								mPlayer.pause();
								//mPlayer.seekTo(0);
							}
						}
					}
				});
			}
		};
		startGame(resRet, tilemapSizeX, tilemapSizeY, tileSize, mainLayer, pathLayer, towerProfiles, runnable );
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
		if (mPlayer != null)
			mPlayer.pause();
    }

    @Override
    protected void onDestroy() {
    	super.onPause();
		if (mPlayer != null)
			mPlayer.stop();
    }
}