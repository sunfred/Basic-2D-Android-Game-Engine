package GameEngine.Objects.GameObjects;

import android.graphics.Color;
import android.graphics.RectF;
import GameEngine.Core.GraphicsHandler.Renderer;
import GameEngine.Objects.Interfaces.IBox;

public abstract class BoxPhysicGameObject extends PhysicGameObjects implements IBox {
	
	public int Width, Height;
	public int color;
	
	public BoxPhysicGameObject(){
		super();
		Width = Height = 0;
		color = Color.WHITE;
	}
	public BoxPhysicGameObject(float x, float y){
		super(x, y);
		Width = Height = 0;
		color = Color.WHITE;
	}
	public BoxPhysicGameObject(float x, float y, int Width, int Height){
		super(x, y);
		this.Width = Width;
		this.Height = Height;
		color = Color.WHITE;
	}
	public BoxPhysicGameObject(float x, float y, int Width, int Height, int color){
		super(x, y);
		this.Width = Width;
		this.Height = Height;
		this.color = color;
	}
	public BoxPhysicGameObject(float x, float y, int Width, int Height, int color,float velX, float velY){
		super(x, y, velX, velY);
		this.Width = Width;
		this.Height = Height;
		this.color = color;
	}
	public BoxPhysicGameObject(float x, float y, int Width, int Height, int color, float velX, float velY, float Friction){
		super(x, y, velX, velY, Friction);
		this.Width = Width;
		this.Height = Height;
		this.color = color;
	}
	@Override
	public boolean Contains(float x, float y) {
		if(BoundingBox().contains(x, y)){
			return true;
		}
		return false;
	}
	@Override
	public boolean Contains(GameObjects o) {
		if(o instanceof IBox){
			if(((IBox) o).BoundingBox().contains(this.BoundingBox())){
				return true;
			}
		} else {
			return Contains(o.x, o.y);
		}
		return false;
	}
	@Override
	public RectF BoundingBox() {
		return new RectF(this.x , this.y, this.x + this.Width, this.y + this.Height);
	}
	@Override
	public void Draw(Renderer r) {
		r.DrawBlock(x, y, Width, Height, color);		
	}
}
