package GameEngine;

import android.os.Bundle;
import android.view.KeyEvent;
import android.app.Activity;
import GameEngine.Scene.GameScene;

public abstract class EngineActivity extends Activity {
	private GameView View;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(View == null){
			View = new GameView(this);
			Setup();
		}
		setContentView(View);
	}	
	@Override
	protected void onPause() {
		super.onPause();
		View.onPause();
	}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {	
		return View.onKeyDown(keyCode, event);
    }
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return View.onKeyUp(keyCode, event);
	}
	// Add Scene to in setup
	protected abstract void Setup();
	protected void AddScene(GameScene NewScene){
		if(View != null){
			View.addScene(NewScene);
		}
	}
}
