package GameEngine.Objects.Interfaces;

import GameEngine.Core.GraphicsHandler.Renderer;

public interface IObject {
	public boolean Contains(float x, float y);
	public void Draw(Renderer r);
}
