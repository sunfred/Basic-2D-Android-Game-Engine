package GameEngine.Core;

import GameEngine.Scene.IScene;
import android.content.Context;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class GameEngine {
	// Public Properties
	public static long PresetDelayTime = 15;
	public static boolean Closeable;
	// Public Methods
	public GameEngine(){
		mDataHandler = new DataHandler();
		mGraphics = new GraphicsHandler();
		mInputs = new InputHandler();
		mSceneHandler = new SceneHandler();
		frameStartTime = System.nanoTime();
		Closeable = false;
	}		
	public void Setup(SurfaceHolder SurfaceHolder, Context c){
		frameStartTime = System.nanoTime();
		mSurfaceHolder = SurfaceHolder;
		mDataHandler.Setup(c);
		mGraphics.Setup(new RectF(SurfaceHolder.getSurfaceFrame()));
		mSceneHandler.Setup();
		mThread = new GameThread(this);
		mThread.SetRunning(true);
		mThread.start();
		Log.d("Game Engine", "Setup");
	}
	public void GameLoop() {
		previousFrameTime = frameStartTime;
		frameStartTime = System.nanoTime();
		float fTime = (frameStartTime - previousFrameTime) / 1000000.0f;
		
		if(mSceneHandler != null){
			mSceneHandler.Update(fTime, mInputs.Controls());
		}
		
		mThread.Delay(frameStartTime, PresetDelayTime);
		
		if(mSurfaceHolder != null && mSceneHandler != null){
			mGraphics.DrawScene(mSurfaceHolder, mSceneHandler.CurrentScene());
		}
	}
	public void Inputs(MotionEvent ev){
		if(mInputs != null){
			mInputs.ProcessInput(ev);
		}
	}
	public void BackPressed() {
		if(mSceneHandler != null){
			if(mSceneHandler.CurrentScene() != null){
				mSceneHandler.CurrentScene().BackPressed();
			} else {
				Closeable = true;
			}
		}
	}
	public void Pause() {
		if(mSceneHandler != null){
			mSceneHandler.IsPaused = true;
		}
	}
	public void Shutdown(){
		boolean retry = true;
		mThread.SetRunning(false);
		while(retry){
			try{
				mThread.join();
			} catch (InterruptedException e){
				Log.d("Thread destroyed error", e.toString());
			}
			retry = false;
			Log.d("Game Engine", "Thread destroyed cleanly");
		}
		mSceneHandler.ShutDown();
		mDataHandler.Shutdown();
		Log.d("Game Engine", "Shutdown");
	}
	public void addScene(IScene newScene) {
		if(mSceneHandler != null){
			mSceneHandler.addScene(newScene);
		}
		
	}
	// Private Properties
	private SurfaceHolder mSurfaceHolder;
	private GameThread mThread;
	private GraphicsHandler mGraphics;
	private InputHandler mInputs;
	private SceneHandler mSceneHandler;	
	private long frameStartTime;
	private long previousFrameTime;
	private DataHandler mDataHandler;
}
