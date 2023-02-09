package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;
import dk.sdu.mmmi.cbse.util.SimplexNoise;

import java.util.Random;
import java.util.Set;

public class Enemy extends SpaceObject {

	private float maxSpeed;
	private float acceleration;
	private float deceleration;

	private long frame;
	private Random rng;

	private Set<Bullet> bullets;

	public Enemy(Set<Bullet> bullets) {
		x = Game.WIDTH / 3.f * 2;
		y = Game.HEIGHT / 3.f * 2;

		maxSpeed = 300;
		acceleration = 200;
		deceleration = 10;

		shapex = new float[4];
		shapey = new float[4];

		radians = 3.1415f;
		rotationSpeed = 3;

		frame = 0;

		color = new Color();
		color.r = 1;
		color.g = 1;
		color.b = 1;
		color.a = 1;

		this.bullets = bullets;

		rng = new Random();

		radius = 4;
	}

	private float getRandomTurnAngle() {
		return (float) (SimplexNoise.noise(frame / 200.f, 0.f) * rotationSpeed);
	}

	private float getRandomAcceleration() {
		return (float) (SimplexNoise.noise(frame / 200.f, 1.f) + 1) / 2.f * acceleration;
	}

	private void decelerate(float dt) {
		float vec = (float) Math.sqrt(dx * dx + dy * dy);
		if (vec > 0) {
			dx -= (dx / vec) * deceleration * dt;
			dy -= (dy / vec) * deceleration * dt;
		}
		if (vec > maxSpeed) {
			dx = (dx / vec) * maxSpeed;
			dy = (dy / vec) * maxSpeed;
		}
	}

	public void update(float dt) {
		frame++;

		radians += getRandomTurnAngle() * dt;

		float accel = getRandomAcceleration();
		dx += MathUtils.cos(radians) * accel * dt;
		dy += MathUtils.sin(radians) * accel * dt;

		decelerate(dt);

		updatePosition(dt);

		if (rng.nextFloat() < 0.01) {
			fireBullet();
		}

		setShape();

		screenWrap();
	}

	private void fireBullet() {
		float barrelLength = 20;
		float bulletX = x + MathUtils.cos(radians) * barrelLength;
		float bulletY = y + MathUtils.sin(radians) * barrelLength;
		Bullet bullet = new Bullet(bulletX, bulletY, radians, dx, dy);
		bullets.add(bullet);
	}

	private void setShape() {
		shapex[0] = x + MathUtils.cos(radians) * 8;
		shapey[0] = y + MathUtils.sin(radians) * 8;

		shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
		shapey[1] = y + MathUtils.sin(radians - 4 * 3.1145f / 5) * 8;

		shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
		shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

		shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
		shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
	}
}