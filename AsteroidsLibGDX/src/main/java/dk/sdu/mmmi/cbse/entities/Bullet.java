package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject {

	public Bullet(float x, float y, float direction, float dx, float dy) {
		this.x = x;
		this.y = y;
		this.radians = direction;

		this.radius = 4;

		speed = 100;

		this.dx = dx;
		this.dy = dy;
		this.dx += MathUtils.cos(radians) * speed;
		this.dy += MathUtils.sin(radians) * speed;

		color = new Color();
		color.r = 1;
		color.g = 1;
		color.b = 1;
		color.a = 1;
	}

	public void update(float dt) {
		updatePosition(dt);

		screenWrap();
	}

	@Override
	public void draw(ShapeRenderer sr) {
		sr.setColor(color.r, color.g, color.b, color.a);

		sr.begin(ShapeRenderer.ShapeType.Line);

		sr.circle(x, y, radius);

		sr.end();
	}
}
