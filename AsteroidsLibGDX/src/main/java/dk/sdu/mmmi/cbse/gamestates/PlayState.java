package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.entities.Bullet;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	
	private Player player;
	private Enemy enemy;
	private Set<Bullet> bullets;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		sr = new ShapeRenderer();

		bullets = new HashSet<>();
		
		player = new Player();
		enemy = new Enemy(bullets);
	}
	
	public void update(float dt) {
		handleInput();
		
		player.update(dt);
		enemy.update(dt);

		for (Bullet bullet : bullets) {
			bullet.update(dt);
		}

		collisionDetection();
	}
	
	public void draw() {
		player.draw(sr);
		enemy.draw(sr);

		for (Bullet bullet : bullets) {
			bullet.draw(sr);
		}
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
	}

	public void collisionDetection() {
		boolean flag = false;
		HashSet<Bullet> toRemove = new HashSet<>();
		for (Bullet bullet : bullets) {
			for (Bullet otherBullet : bullets) {
				if (bullet == otherBullet) continue;
				if (bullet.distanceTo(otherBullet) < bullet.getRadius() + otherBullet.getRadius()) {
					toRemove.add(bullet);
					toRemove.add(otherBullet);
					flag = true;
					break;
				}
			}
			if (flag) continue;

			if (bullet.distanceTo(enemy) < bullet.getRadius() + enemy.getRadius()) {
				toRemove.add(bullet);
				continue;
			}

			if (bullet.distanceTo(player) < bullet.getRadius() + player.getRadius()) {
				toRemove.add(bullet);
				continue;
			}
		}
		bullets.removeAll(toRemove);
	}
	
	public void dispose() {}
}









