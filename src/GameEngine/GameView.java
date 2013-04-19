package GameEngine;

import GameEngine.Core.GameEngine;
import GameEngine.Scene.IScene;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	// Public Properties
	// Public Methods
	public GameView(Context context) {
		super(context);
		Setup(context);
	}	
	public GameView(Context context, AttributeSet attrs){
		super(context, attrs);
		Setup(context);
	}
	public GameView(Context context, AttributeSet attrs, int defaultStyle) {
		super(context, attrs, defaultStyle);
		Setup(context);
		
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if(mEngine != null){
			mEngine.Setup(getHolder(), this.getContext());
		}
		Log.d("Game View", "Surface Created");
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if(mEngine != null){
			mEngine.Shutdown();
		}
		Log.d("Game View", "Surface Destroyed");
	}	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(mEngine != null){
			mEngine.Inputs(event);
		}
		return true;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(GameEngine.Closeable){ 
				new AlertDialog.Builder(this.getContext())
				.setIcon(android.R.drawable.ic_dialog_alert).setTitle("Quit")
		        .setMessage("Do you want to quit?")
		        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
		            @Override
		            public void onClick(DialogInterface dialog, int which) {
		            	if(mEngine != null){
		            		mEngine.Shutdown();
		            	}
		            	((Activity)getContext()).finish();
		            }
		        })
		        .setNegativeButton("No", null)
		        .show();
				return false;
			} else if(!mPressed){
				mEngine.BackPressed();					
				mPressed = true;
			}
			return false;
		}	
		return true;
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && mPressed){
			mPressed = false;
		}
		return super.onKeyUp(keyCode, event);
	}
	public void onPause() {
		if(mEngine != null){
			mEngine.Pause();
		}
	}
	public void addScene(IScene NewScene){
		if(mEngine != null){
			mEngine.addScene(NewScene);
		}
	}
	// Private Properties
	private static GameEngine mEngine;
	private boolean mPressed;
	// Private Methods	
	private void Setup(Context context) {
		getHolder().addCallback(this);
		mPressed = false;
		if(mEngine == null){
			mEngine = new GameEngine();
		}	
	}	
}
