package GameEngine.Core;

import java.util.ArrayList;
import GameEngine.Core.GraphicsHandler.Renderer;
import GameEngine.Core.InputHandler.Controls;
import GameEngine.Scene.IScene;

public class SceneHandler {
	// Public Properties
	public boolean IsPaused;
	// Public Methods
	public SceneHandler(){
		mScenes = new ArrayList<IScene>();
		mCurrentScene = null;
		mSceneChanged = false;
	}
	public static void ChangeScene(String Name){
		if(mScenes != null && mScenes.size() > 0){
			for(IScene s : mScenes){
				if(s.SceneName() == Name){
					ChangeScene(s);
				}
			}
		}
	}
	public void addScene(IScene Scene){
		if(mScenes != null){
			if(!mScenes.contains(Scene)){
				mScenes.add(Scene);
			}
		}
	}
	public void Setup() {
		if(mCurrentScene != null){
			mCurrentScene.InitScene(Renderer.Viewport, IsPaused);
			if(IsPaused){
				IsPaused = false;
			}
		}
	}
	public void ShutDown(){
		if(mCurrentScene != null){
			mCurrentScene.CleanUp();
		}
	}
	public void Update(float t, Controls c) {
		if(mCurrentScene != null){
			if(!mSceneChanged && mCurrentScene.IsActive()){
				mCurrentScene.Inputs(c);
				mCurrentScene.Update(t);
			}
			if(!c.isTouched() && mSceneChanged){
				mSceneChanged = false;
			}
		}	
	}
	public IScene CurrentScene() {
		if(mCurrentScene == null && mScenes.size() > 0){
			ChangeScene(mScenes.get(0));
		}
		return mCurrentScene;
	}
	// Private Properties
	private static ArrayList<IScene> mScenes;	
	private static IScene mCurrentScene;
	private static boolean mSceneChanged;
	// Private Methods
	private static void ChangeScene(IScene Scene){
		if(mScenes.size() > 1){
			GameEngine.Closeable = false;
		}
		mSceneChanged = true;
		if(mCurrentScene != null){
			mCurrentScene.CleanUp();
		}
		mCurrentScene = Scene;
		mCurrentScene.InitScene(Renderer.Viewport, false);
	}
}