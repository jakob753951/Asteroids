package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.main.Game;

public class SpaceObject {
	
	protected float x;
	protected float y;
	
	protected float dx;
	protected float dy;

	protected float radius;
	
	protected float radians;
	protected float speed;
	protected float rotationSpeed;
	
	protected int width;
	protected int height;
	
	protected float[] shapex;
	protected float[] shapey;

	protected Color color;
	
	protected void screenWrap() {
		if(x < 0) x = Game.WIDTH;
		if(x > Game.WIDTH) x = 0;
		if(y < 0) y = Game.HEIGHT;
		if(y > Game.HEIGHT) y = 0; 
	}

	protected void updatePosition(float dt) {
		x += dx * dt;
		y += dy * dt;
	}

	public void draw(ShapeRenderer sr) {
		sr.setColor(color.r, color.g, color.b, color.a);

		sr.begin(ShapeRenderer.ShapeType.Line);

		for (int i = 0, j = shapex.length - 1;
		     i < shapex.length;
		     j = i++) {

			sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
		}

		sr.end();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float distanceTo(SpaceObject other) {
		float xDiff = Math.abs(this.x - other.getX());
		float yDiff = Math.abs(this.y - other.getY());

		float distance = (float) Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

		return distance;
	}

	public float getRadius() {
		return radius;
	}
}


















