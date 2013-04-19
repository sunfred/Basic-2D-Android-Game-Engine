package GameEngine.Objects.GameObjects;

import android.graphics.PointF;

public abstract class PhysicGameObjects extends GameObjects {
	/**
	 * @uml.property  name="velocity"
	 * @uml.associationEnd  
	 */
	public PointF Velocity;
	/**
	 * @uml.property  name="friction"
	 */
	public float Friction;
	public PhysicGameObjects() {
		super();
		Velocity = new PointF();
		Friction = 1;
	}
	public PhysicGameObjects(float x, float y){
		super(x, y);
		Velocity = new PointF();
		Friction = 0.99f;
	}
	public PhysicGameObjects(float x, float y, float velX, float velY){
		super(x, y);
		Velocity.x = velX;
		Velocity.y = velY;
		Friction = 0.99f;
	}
	public PhysicGameObjects(float x, float y, float velX, float velY, float friction){
		super(x, y);
		Velocity.x = velX;
		Velocity.y = velY;
		Friction = friction;
	}	
	protected void MoveConstVel(float t){
		this.x += Velocity.x * t;
		this.y += Velocity.y * t;
		
		this.Velocity.x *= Friction;
		this.Velocity.y *= Friction;
	}
}