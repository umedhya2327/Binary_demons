
//package Binary_demons;
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
public class Binary_demons extends BravoBot
{
	int turnDirection = 1;
int dist = 50;
int trigger; 
boolean peek; // Don't turn if there's a robot there
	double moveAmount; // How much to move

	/**
	 * run: Move around the walls
	 */
	public void run() {
	
		setBodyColor(Color.red);
		setGunColor(Color.orange);
		setRadarColor(Color.black);	
		
		turnGunRight(90);
		turnRight(90);
		while (true) {
			turnRight(5 * turnDirection);
		}	

	}

	/**
	 * onHitRobot:  Move away a bit.
	 */
public void onHitByBullet(HitByBulletEvent e) {
		turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

		ahead(dist);
		dist *= -1;
		scan();
	}

	/**
	 * onHitRobot:  Aim at it.  Fire Hard!
	 */
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;
		}
		turnRight(e.getBearing());

		// Determine a shot that won't kill the robot...
		// We want to ram him instead for bonus points
		if (e.getEnergy() > 16) {
			fire(10);
		} else if (e.getEnergy() > 10) {
			fire(8);
		} else if (e.getEnergy() > 4) {
			fire(6);
		} else if (e.getEnergy() > 2) {
			fire(1);
		} else if (e.getEnergy() > .4) {
			fire(.5);
		}
		ahead(20);
	}

	/**
	 * onScannedRobot:  Fire!
	 */
public void onScannedRobot(ScannedRobotEvent e) {
		// Calculate exact location of the robot

	if (e.getBearing() >= 0 || (e.getDistance() < 100 && getEnergy() > 50)) {
			turnDirection = 45;
			fire(5);
		} else {
			turnDirection = -45;
			fire(2);
		}

		turnRight(e.getBearing());
		ahead(e.getDistance() + 20);
		scan();
		
	/*	if (trackName != null && !e.getName().equals(trackName)) {
			return;
		}

		// If we don't have a target, well, now we do!
		if (trackName == null) {
			trackName = e.getName();
			out.println("Tracking " + trackName);
		}
		// This is our target.  Reset count (see the run method)
		count = 0;
		// If our target is too far away, turn and move toward it.
		if (e.getDistance() > 150) {
			gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

			turnGunRight(gunTurnAmt); // Try changing these to setTurnGunRight,
			turnRight(e.getBearing()); // and see how much Tracker improves...
			// (you'll have to make Tracker an AdvancedRobot)
			ahead(e.getDistance() - 140);
			return;
		}

		// Our target is close.
		gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmt);
		fire(3);

		// Our target is too close!  Back up.
		if (e.getDistance() < 100) {
			if (e.getBearing() > -90 && e.getBearing() <= 90) {
				back(40);
			} else {
				ahead(40);
			}
		}
		scan();*/
	}
	
	public void onCustomEvent(CustomEvent e) {
		// If our custom event "triggerhit" went off,
		if (e.getCondition().getName().equals("triggerhit")) {
			// Adjust the trigger value, or
			// else the event will fire again and again and again...
			trigger -= 20;
			out.println("Ouch, down to " + (int) (getEnergy() + .5) + " energy.");
			// move around a bit.
			turnLeft(75);
			ahead(100);
		}
	}
}
