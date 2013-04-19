package GameEngine.Scene;
import GameEngine.Core.GraphicsHandler.Renderer;
import GameEngine.Core.InputHandler.Controls;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.Log;

public abstract class GameScene implements IScene {

	// Public Properties
	// Public Methods
	public GameScene(String Name){
		mSceneName = Name;
		mActive = false;
		BackgroundColor = Color.BLACK;
	}
	@Override
	public String SceneName() {
		return mSceneName;
	}
	@Override
	public Boolean IsActive() {
		return mActive;
	}
	@Override
	public void InitScene(RectF Viewport, boolean isPaused) {
		mActive = true;
		this.Viewport = Viewport;
		Init(isPaused);
		Log.d(TAG, "Init");
	}
	@Override
	public void CleanUp() {
		mActive = false;
		Log.d(TAG, "Clean up");
	}
	@Override
	public void DrawScene(Renderer r) {
		r.SetBackground(BackgroundColor);
		Draw(r);
	}
	public abstract void Init(boolean isPaused);
	@Override
	public abstract void Inputs(Controls c);
	@Override
	public abstract void BackPressed();
	@Override
	public abstract void Update(float t);
	public abstract void Draw(Renderer r);
	// Protected Properties
	/**
	 * @uml.property  name="viewport"
	 * @uml.associationEnd  
	 */
	protected RectF Viewport;
	/**
	 * @uml.property  name="tAG"
	 */
	protected final String TAG = getClass().getSimpleName();
	/**
	 * @uml.property  name="backgroundColor"
	 */
	protected int BackgroundColor;
	// Protected Methods
	// Private Properties
	/**
	 * @uml.property  name="mSceneName"
	 */
	private String mSceneName;
	/**
	 * @uml.property  name="mActive"
	 */
	private boolean mActive;
	// Private Methods
}
