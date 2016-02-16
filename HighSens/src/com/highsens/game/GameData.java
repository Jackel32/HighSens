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
//An instance of this class is used to generate a stream of pseudorandom numbers.
import java.util.Random;

import com.highsens.game.monster.Boss;
import com.highsens.game.monster.FastMonster;
import com.highsens.game.monster.RegularMonster;
import com.highsens.game.tower.ArrowTower;
import com.highsens.game.tower.BlueTower;

///////////////////////////////
// *** New Class ***
// Implements IStrategy, which is an abstract class
// Which requires this class to contain getScore(); within IStrategy
public class GameData implements IStrategy {

	///////////////////////////////
	// Creates a List of object GameFigures
	final List<GameFigure> figures;
	///////////////////////////////

	////////////////////////////////
	// Instantiates each of these classes
	ArrowTower ArrowT;
	BlueTower BlueT;
	RegularMonster regularMonster;
	FastMonster fastMonster;
	Boss boss;
	Missile missile;
	ArrowMissile arrowMissile;
	GameFigure gf;
	///////////////////////////////

	///////////////////////////////
	// Variables for the players Score, Lives, and Money
	public int score;
	public int lives = 20;
	public int money = 100;
	///////////////////////////////

	// Variables for an objects position
	float x, y;

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

	// Time variable to keep track of the bullet speed
	long bulletElapsedTime = 0;
	// The bullets creation and destruction time
	long bStart, bEnd;

	// Time variable to keep track of the monster speed
	long monsterElapsedTime = 0;
	// The monsters creation and destruction time
	long mStart, mEnd;

	///////////////////////////////
	//
	public GameData() {

		///////////////////////////////
		// Starts the timers for the Monsters and the Bullets
		// currentTimeMillis();
		// Returns the difference, measured in milliseconds, between the current
		/////////////////////////////// time and midnight, January 1, 1970 UTC.
		mStart = System.currentTimeMillis();
		bStart = System.currentTimeMillis();
		///////////////////////////////

		// Returns a synchronized (thread-safe) list backed by the specified
		// list.
		figures = Collections.synchronizedList(new ArrayList<GameFigure>());
	}

	///////////////////////////////
	// An instance method in a subclass with the same signature (name, plus the
	/////////////////////////////// number and the type of its parameters)
	// and return type as an instance method in the superclass overrides the
	/////////////////////////////// superclass's method.
	@Override

	///////////////////////////////
	// This function is designed return a score based on the amount of lives the
	/////////////////////////////// user has left at the end.
	// **********BUG*********
	// As a result of the end condition being (lives <= 0): Score will always be
	/////////////////////////////// 0.
	public int getScore() {
		// Commented Out:
		// score = lives * 5; <------------- BUG
		return score;
	}
	///////////////////////////////

	///////////////////////////////
	// Grants the Reguler Tower the ability to shoot.
	public void shoot(float x, float y, ArrowTower tower) {
		// Instantiates a new Missile at the x and y location of the Regular
		// Tower, color is black
		ArrowMissile f = new ArrowMissile(tower.getXofMissileShoot(), tower.getYofMissileShoot(), Color.BLACK);
		// Sets the target of the missile via Vector math
		f.setTarget((int) x, (int) y);
		// Creates a random seed generator
		Random RandGen1 = new Random();
		// Finds a random number between
		int size = RandGen1.nextInt(15); // int size = (int) (Math.random() *
											// 10) + 5;
		// Sets the size of the explosion to the random number
		f.setExplosionMaxSize(size);
		// Adds the explosion into the figures arraylist to be rendered.
		figures.add(f);
	}
	///////////////////////////////

	///////////////////////////////
	// Grants the Blue Tower the ability to shoot.
	public void shoot(float x, float y, BlueTower tower) {
		// Instantiates a new Missile at the x and y location of the Blue Tower,
		// color is blue
		Missile f = new Missile(tower.getXofMissileShoot(), tower.getYofMissileShoot(), Color.BLUE);
		// Sets the target of the missile via Vector math
		f.setTarget((int) x, (int) y);
		// Creates a random seed generator
		Random RandGen2 = new Random();
		// Finds a random number between
		int size = RandGen2.nextInt(15); // int size = (int) (Math.random() *
											// 10) + 5;
		// Sets the size of the explosion to the random number
		f.setExplosionMaxSize(size);
		// Adds the explosion into the figures arraylist to be rendered.
		figures.add(f);
	}
	///////////////////////////////

	///////////////////////////////
	public void minusLives() {
		// Decrements lives
		lives--;
		// Sets the lives at the decremented value
		setLives(lives);
		// Gets the score from the amount of lives left.
		getScore();
	}
	///////////////////////////////

	///////////////////////////////
	public int getLives() {
		// Returns the current amount of lives
		return lives;
	}
	///////////////////////////////

	///////////////////////////////
	public void setLives(int lives) {
		// Sets the amount of lives with the paramater amount.
		this.lives = lives;
	}
	///////////////////////////////

	///////////////////////////////
	public int getWaves() {
		// Returns the current wave
		return wave;
	}
	///////////////////////////////

	///////////////////////////////
	public void setWaves(int waves) {
		// Sets the amount of waves with the parameter amount.
		this.wave = waves;
	}
	///////////////////////////////

	///////////////////////////////
	// Manages the money within the game.
	public void moneyManager(String val, int money) {
		switch (val) {
		case "tower1":
			money -= 50;
			break;
		case "tower2":
			money -= 100;
			break;
		case "kill1":
			money += 5;
			break;
		case "kill2":
			money += 10;
			break;
		case "bossKill":
			money += 50;
			break;
		}
		setMoney(money);
	}
	///////////////////////////////
	
	
	public void monsterManager(String val) {
		switch (val) {
		case "kill1":
			regularMonsterCount--;
			setRegularMonsterCount(regularMonsterCount);
			break;
		case "kill2":
			fastMonsterCount--;
			setFastMonsterCount(fastMonsterCount);
			break;
		case "bossKill":
			bossCount--;
			setBossCount(bossCount);
			break;
		}
	}
	//////////////////////////////////
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
			switch (n) {
			case 0:
				// How many monsters
				waveSize = 4;

				// this staggers the monster creation
				while (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					if (creepCount <= waveSize) {
						// This part is limits regular monsters to half the wave
						// size
						if (creepCount < waveSize / 2) {
							// Adds a monster to the screen
							figures.add(new RegularMonster(-50, 200, this));
							creepCount++;
							regularMonsterCount++;
						} else if (creepCount < waveSize) {
							figures.add(new FastMonster(-50, 200, this));
							creepCount++;
							fastMonsterCount++;
						} else if (creepCount == waveSize) {
							figures.add(new Boss(-50, 120, this));
							creepCount++;
							bossCount++;
						}
					}
				}
				break;
			case 1:
				waveSize = 6;
				if (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					if (creepCount <= waveSize) {
						if (creepCount < waveSize / 2) {
							figures.add(new RegularMonster(-50, 200, this));
							creepCount++;
							regularMonsterCount++;
						} else if (creepCount < waveSize) {
							figures.add(new FastMonster(-50, 200, this));
							creepCount++;
							fastMonsterCount++;
						} else if (creepCount == waveSize) {
							figures.add(new Boss(-50, 120, this));
							creepCount++;
							bossCount++;
						}
					}
				}
				break;
			case 2:
				waveSize = 8;
				if (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					if (creepCount <= waveSize) {
						if (creepCount < waveSize / 2) {
							figures.add(new RegularMonster(-50, 200, this));
							creepCount++;
							regularMonsterCount++;
						} else if (creepCount < waveSize) {
							figures.add(new FastMonster(-50, 200, this));
							creepCount++;
							fastMonsterCount++;
						} else if (creepCount == waveSize) {
							figures.add(new Boss(-50, 120, this));
							creepCount++;
							bossCount++;
						}
					}
				}
				break;
			case 3:
				waveSize = 10;
				if (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					if (creepCount <= waveSize) {
						if (creepCount < waveSize / 2) {
							figures.add(new RegularMonster(-50, 200, this));
							creepCount++;
							regularMonsterCount++;
						} else if (creepCount < waveSize) {
							figures.add(new FastMonster(-50, 200, this));
							creepCount++;
							fastMonsterCount++;
						} else if (creepCount == waveSize) {
							figures.add(new Boss(-50, 120, this));
							creepCount++;
							bossCount++;
						}
					}
				}
				break;
			case 4:
				waveSize = 12;
				if (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					if (creepCount <= waveSize) {
						if (creepCount < waveSize / 2) {
							figures.add(new RegularMonster(-50, 200, this));
							creepCount++;
							regularMonsterCount++;
						} else if (creepCount < waveSize) {
							figures.add(new FastMonster(-50, 200, this));
							creepCount++;
							fastMonsterCount++;
						} else if (creepCount == waveSize) {
							figures.add(new Boss(-50, 120, this));
							creepCount++;
							bossCount++;
						}
					}
				}
				break;
			case 5:
				waveSize = 14;
				if (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					if (creepCount <= waveSize) {
						if (creepCount < waveSize / 2) {
							figures.add(new RegularMonster(-50, 200, this));
							creepCount++;
							regularMonsterCount++;
						} else if (creepCount < waveSize) {
							figures.add(new FastMonster(-50, 200, this));
							creepCount++;
							fastMonsterCount++;
						} else if (creepCount == waveSize) {
							figures.add(new Boss(-50, 120, this));
							creepCount++;
							bossCount++;
						}
					}
				}
				break;
			case 6:
				waveSize = 16;
				if (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					if (creepCount <= waveSize) {
						if (creepCount < waveSize / 2) {
							figures.add(new RegularMonster(-50, 200, this));
							creepCount++;
							regularMonsterCount++;
						} else if (creepCount < waveSize) {
							figures.add(new FastMonster(-50, 200, this));
							creepCount++;
							fastMonsterCount++;
						} else if (creepCount == waveSize) {
							figures.add(new Boss(-50, 120, this));
							creepCount++;
							bossCount++;
						}
					}
				}
				break;
			case 7:
				waveSize = 18;
				if (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					if (creepCount <= waveSize) {
						if (creepCount < waveSize / 2) {
							figures.add(new RegularMonster(-50, 200, this));
							creepCount++;
							regularMonsterCount++;
						} else if (creepCount < waveSize) {
							figures.add(new FastMonster(-50, 200, this));
							creepCount++;
							fastMonsterCount++;
						} else if (creepCount == waveSize) {
							figures.add(new Boss(-50, 120, this));
							creepCount++;
							bossCount++;
						}
					}
				}
				break;
			case 8:
				waveSize = 20;
				if (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					if (creepCount <= waveSize) {
						if (creepCount < waveSize / 2) {
							figures.add(new RegularMonster(-50, 200, this));
							creepCount++;
							regularMonsterCount++;
						} else if (creepCount < waveSize) {
							figures.add(new FastMonster(-50, 200, this));
							creepCount++;
							fastMonsterCount++;
						} else if (creepCount == waveSize) {
							figures.add(new Boss(-50, 120, this));
							creepCount++;
							bossCount++;
						}
					}
				}
				break;
			case 9:
				waveSize = 22;
				if (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					if (creepCount <= waveSize) {
						if (creepCount < waveSize / 2) {
							figures.add(new RegularMonster(-50, 200, this));
							creepCount++;
							regularMonsterCount++;
						} else if (creepCount < waveSize) {
							figures.add(new FastMonster(-50, 200, this));
							creepCount++;
							fastMonsterCount++;
						} else if (creepCount == waveSize) {
							figures.add(new Boss(-50, 120, this));
							creepCount++;
							bossCount++;
						}
					}
				}
				break;
			case 10:
				waveSize = 10;
				if (monsterElapsedTime > 1000) {
					monsterElapsedTime = 0;
					if (creepCount <= waveSize) {
						figures.add(new Boss(-50, 120, this));
						creepCount++;
						bossCount++;
					}
				}
				break;
			}
		}
	}

	public void update() {
		List<GameFigure> remove = new ArrayList<>();
		GameFigure f;

		bEnd = System.currentTimeMillis();
		mEnd = System.currentTimeMillis();

		bulletElapsedTime += bEnd - bStart;
		monsterElapsedTime += mEnd - mStart;

		bStart = bEnd;
		mStart = mEnd;

		startWave(getWaves());

		// This confusing area deals with bullet collision with monsters.
		if (bulletElapsedTime > 350) {
			for (int i = 0; i < figures.size() - 2; i++) {
				for (int j = 0; j < figures.size() - 1; j++) {
					if (figures.get(i) instanceof BlueTower) {
						if (figures.get(j) instanceof RegularMonster) {
							if ((figures.get(i)).collision(figures.get(j))) {
								shoot((float) figures.get(j).getX(), (float) figures.get(j).getY(),
										(BlueTower) figures.get(i));
							}
						} else if (figures.get(j) instanceof FastMonster) {
							if ((figures.get(i)).collision(figures.get(j))) {
								shoot((float) figures.get(j).getX(), (float) figures.get(j).getY(),
										(BlueTower) figures.get(i));
							}
						} else if (figures.get(j) instanceof Boss) {
							if ((figures.get(i)).collision(figures.get(j))) {
								shoot((float) figures.get(j).getX(), (float) figures.get(j).getY(),
										(BlueTower) figures.get(i));
							}
						}
					} else if (figures.get(i) instanceof ArrowTower) {
						if (figures.get(j) instanceof RegularMonster) {
							if ((figures.get(i)).collision(figures.get(j))) {
								shoot((float) figures.get(j).getX(), (float) figures.get(j).getY(),
										(ArrowTower) figures.get(i));
							}
						} else if (figures.get(j) instanceof FastMonster) {
							if ((figures.get(i)).collision(figures.get(j))) {
								shoot((float) figures.get(j).getX(), (float) figures.get(j).getY(),
										(ArrowTower) figures.get(i));
							}
						} else if (figures.get(j) instanceof Boss) {
							if ((figures.get(i)).collision(figures.get(j))) {
								shoot((float) figures.get(j).getX(), (float) figures.get(j).getY(),
										(ArrowTower) figures.get(i));
							}
						}
					}
				}
			}

			for (int i = 0; i < figures.size() - 2; i++) {
				for (int j = 1; j < figures.size() - 1; j++) {
					if (figures.get(i) instanceof Missile && figures.get(j) instanceof RegularMonster) {
						if (((RegularMonster) figures.get(j)).contains((int) figures.get(i).getX(),
								(int) figures.get(i).getY())) {
							figures.get(i).setState(GameFigure.STATE_DONE);
							figures.get(j).updateHealth();
						}
					} else if (figures.get(i) instanceof Missile && figures.get(j) instanceof FastMonster) {
						if (((FastMonster) figures.get(j)).contains((int) figures.get(i).getX(),
								(int) figures.get(i).getY())) {
							figures.get(i).setState(GameFigure.STATE_DONE);
							figures.get(j).updateHealth();
						}
					} else if (figures.get(i) instanceof Missile && figures.get(j) instanceof Boss) {
						if (((Boss) figures.get(j)).contains((int) figures.get(i).getX(),
								(int) figures.get(i).getY())) {
							figures.get(i).setState(GameFigure.STATE_DONE);
							figures.get(j).updateHealth();
						}
					} else if (figures.get(i) instanceof RegularMonster && figures.get(j) instanceof Missile) {
						if (((RegularMonster) figures.get(i)).contains((int) figures.get(j).getX(),
								(int) figures.get(j).getY())) {
							figures.get(j).setState(GameFigure.STATE_DONE);
							figures.get(i).updateHealth();
						}
					} else if (figures.get(i) instanceof FastMonster && figures.get(j) instanceof Missile) {
						if (((FastMonster) figures.get(i)).contains((int) figures.get(j).getX(),
								(int) figures.get(j).getY())) {
							figures.get(j).setState(GameFigure.STATE_DONE);
							figures.get(i).updateHealth();
						}
					} else if (figures.get(i) instanceof Boss && figures.get(j) instanceof Missile) {
						if (((Boss) figures.get(i)).contains((int) figures.get(j).getX(),
								(int) figures.get(j).getY())) {
							figures.get(j).setState(GameFigure.STATE_DONE);
							figures.get(i).updateHealth();
						}
					}
					//
					if (figures.get(i) instanceof ArrowMissile && figures.get(j) instanceof RegularMonster) {
						if (((RegularMonster) figures.get(j)).contains((int) figures.get(i).getX(),
								(int) figures.get(i).getY())) {
							figures.get(i).setState(GameFigure.STATE_DONE);
							figures.get(j).updateHealth();
						}
					} else if (figures.get(i) instanceof ArrowMissile && figures.get(j) instanceof FastMonster) {
						if (((FastMonster) figures.get(j)).contains((int) figures.get(i).getX(),
								(int) figures.get(i).getY())) {
							figures.get(i).setState(GameFigure.STATE_DONE);
							figures.get(j).updateHealth();
						}
					} else if (figures.get(i) instanceof ArrowMissile && figures.get(j) instanceof Boss) {
						if (((Boss) figures.get(j)).contains((int) figures.get(i).getX(),
								(int) figures.get(i).getY())) {
							figures.get(i).setState(GameFigure.STATE_DONE);
							figures.get(j).updateHealth();
						}
					} else if (figures.get(i) instanceof RegularMonster && figures.get(j) instanceof ArrowMissile) {
						if (((RegularMonster) figures.get(i)).contains((int) figures.get(j).getX(),
								(int) figures.get(j).getY())) {
							figures.get(j).setState(GameFigure.STATE_DONE);
							figures.get(i).updateHealth();
						}
					} else if (figures.get(i) instanceof FastMonster && figures.get(j) instanceof ArrowMissile) {
						if (((FastMonster) figures.get(i)).contains((int) figures.get(j).getX(),
								(int) figures.get(j).getY())) {
							figures.get(j).setState(GameFigure.STATE_DONE);
							figures.get(i).updateHealth();
						}
					} else if (figures.get(i) instanceof Boss && figures.get(j) instanceof ArrowMissile) {
						if (((Boss) figures.get(i)).contains((int) figures.get(j).getX(),
								(int) figures.get(j).getY())) {
							figures.get(j).setState(GameFigure.STATE_DONE);
							figures.get(i).updateHealth();
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
	}
}