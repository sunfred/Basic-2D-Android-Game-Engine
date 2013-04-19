package GameEngine.Objects.GameObjects;
import GameEngine.Core.GraphicsHandler.Renderer;
import GameEngine.Objects.Object;

public abstract class GameObjects extends Object {
	// Public Methods
	public GameObjects(){
		super();
	}
	public GameObjects(float x, float y){
		super(x, y);
	}
	public abstract void Update(float t);
	public abstract void Draw(Renderer r);
	public abstract boolean Contains(float x, float y);
	public abstract boolean Contains(GameObjects o);
}
