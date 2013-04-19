package GameEngine.Objects;

import GameEngine.Core.GraphicsHandler.Renderer;
import GameEngine.Objects.Interfaces.IObject;

public abstract class Object implements IObject {
	public float x, y;
	public Object() {
		x = y = 0;
	}
	public Object(float x, float y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public abstract boolean Contains(float x, float y);
	@Override
	public abstract void Draw(Renderer r);
}
