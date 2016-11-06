package shooter;

import java.awt.geom.Point2D;

public class ShotData {
	//precision;time;ballPosX;ballPosY;ballVeloX;ballVeloY;
	//speed;balltoGoalTime;crossing;travelDist;
	final int precision;
	final int time;
	final Point2D ballPos;
	final Point2D ballVelo;
	final float speed;
	final float ballToGoalTime;
	final float crossing;
	final float travelDist;
	
	public ShotData(int precision, int time, Point2D ballPos, Point2D ballVelo,
			float speed, float ballToGoalTime, float crossing, float travelDist) {
		super();
		this.precision = precision;
		this.time = time;
		this.ballPos = ballPos;
		this.ballVelo = ballVelo;
		this.speed = speed;
		this.ballToGoalTime = ballToGoalTime;
		this.crossing = crossing;
		this.travelDist = travelDist;
	}
	
	public int getPrecision() {
		return precision;
	}

	public int getTime() {
		return time;
	}

	public Point2D getBallPos() {
		return ballPos;
	}

	public Point2D getBallVelo() {
		return ballVelo;
	}

	public float getSpeed() {
		return speed;
	}

	public float getBallToGoalTime() {
		return ballToGoalTime;
	}

	public float getCrossing() {
		return crossing;
	}

	public float getTravelDist() {
		return travelDist;
	}

}
