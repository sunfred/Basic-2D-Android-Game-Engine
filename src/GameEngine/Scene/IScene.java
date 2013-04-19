package GameEngine.Scene;


import GameEngine.Core.GraphicsHandler.Renderer;
import GameEngine.Core.InputHandler.Controls;
import android.graphics.RectF;


public interface IScene {
	
	public String SceneName();
	public Boolean IsActive();
	
	public void InitScene(RectF Viewport, boolean IsPaused);
	public void CleanUp();
	
	public void Inputs(Controls c);
	public void BackPressed();
	public void Update(float t);
	public void DrawScene(Renderer r);
	
}