
//package Binary_Demons;
import robocode.*;

import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Binary_Demons - a robot by (your name here)
 */
public class Binary_Demons extends CharlieBot
{

int dist = 30;
	/**
	 * run: Binary_Demons's default behavior
	 */
	/*public void run() {
		// Initialization of the robot should be put here
	
	
		setAdjustGunForRobotTurn(true);
	
		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar
		setBodyColor(new Color(22, 160, 133));
		setBulletColor(new Color(255, 255, 100));
		setScanColor(Color.green);
		setGunColor(Color.pink);

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(50000);
			
			turnGunRight(360);
			back(5000);
			turnGunRight(360);
		
			
		
		}
	/*	while (true) {
			// Tell the game we will want to move ahead 40000 -- some large number
			setAhead(40000);
			movingForward = true;
			// Tell the game we will want to turn right 90
			setTurnRight(90);
			waitFor(new TurnCompleteCondition(this));
			// Note:  We are still moving ahead now, but the turn is complete.
			// Now we'll turn the other way...
			setTurnLeft(180);
			// ... and wait for the turn to finish ...
			waitFor(new TurnCompleteCondition(this));
			// ... then the other way ...
			setTurnRight(180);
			// .. and wait for that turn to finish.
			waitFor(new TurnCompleteCondition(this));
			// then back to the top to do it all again
		}*/
	//}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
/*	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

		// If it's close enough, fire!
		if (Math.abs(bearingFromGun) <= 3) {
			turnGunRight(bearingFromGun);
			// We check gun heat here, because calling fire()
			// uses a turn, which could cause us to lose track
			// of the other robot.
			if (getGunHeat() == 0) {
				fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
			}
			//run1();
		} // otherwise just set the gun to turn.
		// Note:  This will have no effect until we call scan()
		else {
			turnGunRight(bearingFromGun);
		}
		// Generates another scan event if we see a robot.
		// We only need to call this if the gun (and therefore radar)
		// are not turning.  Otherwise, scan is called automatically.
		if (bearingFromGun == 0) {
			scan();
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		turnRight(normalRelativeAngleDegrees(90 + (getHeading() - e.getHeading())));

		ahead(dist);
		dist *= -1;
		scan();
		
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
	
	public void onWin(WinEvent e) {
		// Victory dance
		turnRight(180);
	}
	
public void onHitRobot(HitRobotEvent e) {
		double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());
		int turnDirection = 1;
		//turnRight(5 * turnDirection);
		turnGunRight(turnGunAmt);
		fire(3);
	}
	
public void smartFire(double robotDistance) {
		if (robotDistance > 50 || getEnergy() < 15) {
			fire(2);
		} else if (robotDistance > 30) {
			fire(3);
		} else {
			fire(3);
		}
	}
public void run(){
		// Set colors
		boolean peek; // Don't turn if there's a robot there
		double moveAmount; // How much to move
		setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.orange);
		setBulletColor(Color.cyan);
		setScanColor(Color.cyan);

		// Initialize moveAmount to the maximum possible for this battlefield.
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		// Initialize peek to false
		peek = false;

		// turnLeft to face a wall.
		// getHeading() % 90 means the remainder of
		// getHeading() divided by 90.
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		// Turn the gun to turn right 90 degrees.
		peek = true;
		turnGunRight(-180);
		//turnRight(180);

		while (true) {
			// Look before we turn when ahead() completes.
			peek = true;
			// Move up the wall
			ahead(moveAmount);
			// Don't look now
			peek = false;
			// Turn to the next wall
		
			turnLeft(180);
		}
	}
	public void onRobotDetected(ScannedRobotEvent e) {

				boolean peek;
				peek = false;
			fire(2);
			// Note that scan is called automatically when the robot is moving.
			// By calling it manually here, we make sure we generate another scan event if there's a robot on the next
			// wall, so that we do not start moving up it until it's gone.
		
				
				scan();
				
				
			
		
	}


		
}
