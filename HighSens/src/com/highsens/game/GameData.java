package com.highsens.game;

//The Color class is used to encapsulate colors in the default RGB color space
import java.awt.Color;
//Resizable-array implementation of the List interface.
//Implements all optional list operations, and permits all elements, including null.
//In addition to implementing the List interface,
//this class provides methods to manipulate the size of the array that is used internally to store the list.
import java.util.ArrayList;
//This class consists exclusively of static methods that operate on or return collections.
//It contains polymorphic algorithms that operate on collections, "wrappers", which return a new collection backed by a specified collection, and a few other odds and ends.
import java.util.Collections;
//A List is an ordered Collection (sometimes called a sequence).
import java.util.List;
import java.util.Random;

import com.highsens.game.monster.BloonMonster;
import com.highsens.game.monster.Boss;
import com.highsens.game.monster.FastMonster;
import com.highsens.game.monster.RegularMonster;
import com.highsens.game.tower.Airplane;
import com.highsens.game.tower.ArrowTower;
import com.highsens.game.tower.BlueTower;
import com.highsens.game.tower.GreenTower;
import com.highsens.game.tower.Landmine;

///////////////////////////////
// *** New Class ***
// Implements IStrategy, which is an abstract class
// Which requires this class to contain getScore(); within IStrategy
public class GameData implements IStrategy {

	///////////////////////////////
	// Creates a List of object Gamefigures
	List<GameFigure> figures;
	///////////////////////////////

	final List<SellManager> sellFigures;

	final List<Landmine> armsFigures;

	////////////////////////////////
	// Instantiates each of these classes
	ArrowTower ArrowT;
	BlueTower BlueT;
	Landmine LMine;
	Airplane airplane;
	RegularMonster regularMonster;
	FastMonster fastMonster;
	Boss boss;
	BloonMonster bloonMonster;
	Missile missile;
	ArrowMissile arrowMissile;
	GameFigure gf;

	// Variables for the players Score, Lives, and Money
	public int score;
	public int lives = 9999;
	public int money = 1000;
	///////////////////////////////
	
	Random rn;
	int n = 4 - 1 + 1;
	int r = 0;
	int randomNum = 0;

	// Variables for an objects position
	float x, y;

	// Shooting boolean variable
	boolean shoot = false;

	// A wave has not started yet.
	boolean waveStarted = false;

	// global variable for the wave amount counter
	int wave = 0;

	// Global variable for the size of a wave
	int waveSize;

	// global variable for the amount of creep per wave
	int creepCount = 0;

	int regularMonsterCount = 0;

	int fastMonsterCount = 0;

	int bossCount = 0;

	int bloonMonsterCount = 0;

	// Time variable to keep track of the bullet speed
	long bulletElapsedTime = 0;
	// The bullets creation and destruction time
	long bStart, bEnd;

	// Time variable to keep track of the monster speed
	long monsterElapsedTime = 0;
	// The monsters creation and destruction time
	long mStart, mEnd;

	public GameData() {
		mStart = System.currentTimeMillis();
		bStart = System.currentTimeMillis();
		figures = Collections.synchronizedList(new ArrayList<GameFigure>());
		sellFigures = Collections.synchronizedList(new ArrayList<SellManager>());
		armsFigures = Collections.synchronizedList(new ArrayList<Landmine>());
		figures.add(0, new Airplane(300,100));

	}

	@Override
	public int getScore() {
		return score;
	}

	public void shoot(GameFigure tower, GameFigure monster, int bulletCount) {
		if (tower instanceof BlueTower && monster instanceof Boss) {
			shoot((BlueTower) tower, monster, ((BlueTower) tower).getBulletCount());
		} else if (tower instanceof ArrowTower || tower instanceof BlueTower || tower instanceof GreenTower || tower instanceof Airplane){
			// Instantiates a new Missile at the x and y location of the Regular
			// Tower, color is black
			ArrowMissile f = new ArrowMissile(tower.getXofMissileShoot(), tower.getYofMissileShoot(), Color.BLACK);
			// Sets the target of the missile via Vector math
			f.setTarget((int) monster.getX(), (int) monster.getY());
			// Creates a random seed generator
			// Random RandGen1 = new Random();
			// Finds a random number between
			// int size = RandGen1.nextInt(15); // int size = (int)
			// (Math.random()
			// *
			// 10) + 5;
			// Sets the size of the explosion to the random number
			// f.setExplosionMaxSize(size);
			// Adds the explosion into the figures arraylist to be rendered.
			figures.add(f);
		}
	}

	public void shoot(BlueTower tower, GameFigure monster, int bulletCount) {
		if (tower.getCurrentTarget() == null) {
			tower.setCurrentTarget(monster);
		}

		Missile f = new Missile(tower.getXofMissileShoot(), tower.getYofMissileShoot(), Color.BLUE);
		f.setTarget((int) tower.getCurrentTarget().getX(), (int) tower.getCurrentTarget().getY());
		Random RandGen2 = new Random();
		int size = RandGen2.nextInt(15);
		f.setExplosionMaxSize(size);
		figures.add(f);
	}

	public void minusLives() {
		lives--;
		setLives(lives);
		getScore();
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getWaves() {
		// Returns the current wave
		return wave;
	}

	public void setWaves(int waves) {
		this.wave = waves;
	}

	public List<GameFigure> returnList() {
		return figures;
	}

	public void moneyManager(String val, int money) {
		switch (val) {
		case "RegularTower":
			money -= 50;
			break;
		case "BlueTower":
			money -= 100;
			break;
		case "Landmine":
			money -= 200;
			break;
		case "GreenTower":
			money -= 150;
			break;
		case "regularKill":
			money += 5;
			break;
		case "fastKill":
			money += 10;
			break;
		case "bossKill":
			money += 50;
			break;
		case "bloonKill":
			money += 100;
			break;
		case "sellArrowTower":
			money += 25;
			break;
		case "sellBlueTower":
			money += 50;
			break;
		}
		setMoney(money);
	}

	public void monsterManager(String val) {
		switch (val) {
		case "regularKill":
			regularMonsterCount--;
			setRegularMonsterCount(regularMonsterCount);
			break;
		case "fastKill":
			fastMonsterCount--;
			setFastMonsterCount(fastMonsterCount);
			break;
		case "bloonKill":
			bloonMonsterCount--;
			setBloonMonsterCount(bloonMonsterCount);
			break;
		case "bossKill":
			bossCount--;
			setBossCount(bossCount);
			break;
		}
	}

	public int getRegularMonsterCount() {
		return regularMonsterCount;
	}

	public void setRegularMonsterCount(int regularMonsterCount) {
		this.regularMonsterCount = regularMonsterCount;
	}

	public int getFastMonsterCount() {
		return fastMonsterCount;
	}

	public void setFastMonsterCount(int fastMonsterCount) {
		this.fastMonsterCount = fastMonsterCount;
	}

	public void setBloonMonsterCount(int bloonMonsterCount) {
		this.bloonMonsterCount = bloonMonsterCount;
	}

	public int getBloonMonsterCount() {
		return bloonMonsterCount;
	}

	public int getBossCount() {
		return bossCount;
	}

	public void setBossCount(int bossCount) {
		this.bossCount = bossCount;
	}

	///////////////////////////////

	public void resetCreepCount() {
		creepCount = 0;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	// This controls each wave.
	public void startWave(int n) {
		
		if (!waveStarted) {
			
			Random ran = new Random();
			int randomNum = ran.nextInt(3) + 1;
			
			switch (n) {
			case 1:
				// How many monsters
				waveSize = 20;

				// this staggers the monster creation
				while (monsterElapsedTime > 1000) {
					
					monsterElapsedTime = 0;
					
					for(int i = 0; i < 20; i++)
					{
						figures.add(new BloonMonster(-50, 200, this, randomNum));
						creepCount++;
						bloonMonsterCount++;
					}

					CurrentPlayer.setGameCash(CurrentPlayer.getGameCash() + 200);
					
				}
				break;
			case 2:
				waveSize = 4;
				if (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					
					for(int i = 0; i < 10; i++)
					{
						figures.add(new BloonMonster(-50, 200, this, randomNum));
						creepCount++;
						bloonMonsterCount++;
					}
					
					CurrentPlayer.setGameCash(CurrentPlayer.getGameCash() + 200);
				}
				break;
			case 3:
				waveSize = 8;
				if (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					
					for(int i = 0; i < 10; i++)
					{
						figures.add(new BloonMonster(-50, 200, this, randomNum));
						creepCount++;
						bloonMonsterCount++;
					}
					
					CurrentPlayer.setGameCash(CurrentPlayer.getGameCash() + 200);
				}
				break;
				
			case 4:
				waveSize = 8;
				if (monsterElapsedTime > 3000) {
					monsterElapsedTime = 0;
					
					for(int i = 0; i < 10; i++)
					{
						figures.add(new Boss(-50, 140, this));
						creepCount++;
						bossCount++;
					}
					
					CurrentPlayer.setGameCash(CurrentPlayer.getGameCash() + 200);
				}
				break;
			case 5:
				waveSize = 8;
				if (monsterElapsedTime > 3000) {
					monsterElapsedTime = 0;
					
					for(int i = 0; i < 10; i++)
					{
						figures.add(new Boss(-50, 140, this));
						creepCount++;
						bossCount++;
					}
					
					CurrentPlayer.setGameCash(CurrentPlayer.getGameCash() + 200);
				}
				break;
			}
		}
	}

	public void update() {
		List<GameFigure> remove = new ArrayList<>();
		GameFigure f;
		Landmine l;

		bEnd = System.currentTimeMillis();
		mEnd = System.currentTimeMillis();

		// if(shoot == false) {
		// bulletElapsedTime += bEnd - bStart;
		// }

		// System.out.println("Bullet Start time: " + bStart + " , Bullet End
		// Time: " + bEnd);
		// System.out.println("Monster Start time: " + mStart + " , Monster End
		// Time: " + mEnd);

		bulletElapsedTime += bEnd - bStart;
		monsterElapsedTime += mEnd - mStart;

		bStart = bEnd;
		mStart = mEnd;

		startWave(getWaves());

		// This confusing area deals with bullet collision with monsters.
		if (bulletElapsedTime >= 1050) {

			bulletElapsedTime = 0;
			bStart = System.currentTimeMillis();

			// System.out.println("Bullet Elapsed Time: " + bulletElapsedTime);
			shoot = false;
			for (int i = 0; i < figures.size(); i++) { // -2
				for (int j = 0; j < figures.size(); j++) { // -1
					if (figures.get(i) instanceof BlueTower || figures.get(i) instanceof GreenTower || figures.get(i) instanceof ArrowTower || figures.get(i) instanceof Airplane) {
						if (figures.get(j) instanceof RegularMonster || figures.get(j) instanceof FastMonster
								|| figures.get(j) instanceof BloonMonster || figures.get(j) instanceof Boss) {
							if (shoot == false) {
								if (figures.get(i).collision(figures.get(j))) {
									// if(figures.get(i) instanceof Landmine){
									// figures.get(i).update();
									// figures.get(j).updateHealth();
									// }
									// else if (figures.get(j).getIsAngry()) {
									if (figures.get(j).getIsAngry()) {
										if (!figures.get(j).collision(figures.get(i))) {
											// shoot(figures.get(i),
											// figures.get(j),
											// figures.get(i).getBulletCount());
										} else {
											shoot(figures.get(i), figures.get(j), figures.get(i).getBulletCount());
											shoot = true;
										}
									} else {
										shoot(figures.get(i), figures.get(j), figures.get(i).getBulletCount());
										shoot = true;
									}
								}
							}
							shoot = false;
						}
					}
				}
			}
		}

		for (int i = 0; i < armsFigures.size(); i++) {
			for (int j = 0; j < figures.size() - 1; j++) {
				if (figures.get(j) instanceof RegularMonster || figures.get(j) instanceof FastMonster
						|| figures.get(j) instanceof BloonMonster || figures.get(j) instanceof Boss) {
					if (armsFigures.get(i).collision(figures.get(j))) {
						armsFigures.get(i).update();
					}
				}
			}

		}

		for (int i = 0; i < figures.size(); i++) { // -2
			for (int j = 0; j < figures.size(); j++) { // -1
				if (figures.get(i) instanceof Missile || figures.get(i) instanceof ArrowMissile
						|| figures.get(i) instanceof Landmine) {
					if (figures.get(j) instanceof RegularMonster || figures.get(j) instanceof FastMonster
							|| figures.get(j) instanceof BloonMonster || figures.get(j) instanceof Boss) {
						if (figures.get(j).contains((float) figures.get(i).getX(), (float) figures.get(i).getY())) {
							figures.get(j).updateHealth();
							if (figures.get(i) instanceof Landmine) {
								System.out.println("Testing");
								figures.get(i).setState(GameFigure.STATE_EXPLODING);
							} else {
								figures.get(i).setState(GameFigure.STATE_DONE);
							}
						}
					}
				}
			}
		}

		synchronized (figures) {
			for (int i = 0; i < figures.size(); i++) {
				f = figures.get(i);
				f.update();
				if (f.getState() == GameFigure.STATE_DONE) {
					remove.add(f);
				}
			}
			figures.removeAll(remove);
		}

		synchronized (armsFigures) {
			for (int i = 0; i < armsFigures.size(); i++) {
				l = armsFigures.get(i);
				l.update();
				if (l.getState() == GameFigure.STATE_DONE) {
					remove.add(l);
				}
			}
			armsFigures.removeAll(remove);
		}

	}
}