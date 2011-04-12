package br.com.jera.locaweb;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import br.com.jera.towerdefenselib.TDActivity;

public class Locaweb extends TDActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		startGame(new LWResourceIdRetriever());
    }
}