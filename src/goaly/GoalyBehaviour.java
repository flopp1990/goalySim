package goaly;

import java.util.LinkedList;

import shooter.Shot;
import shooter.ShotData;

public class GoalyBehaviour {
	int status;
	int lastTimeThrown;
	boolean doIt;
	float speed_old;
	final int NO_DICISION = -2;

	public int run(Shot s){
		status=NO_DICISION;
		lastTimeThrown=0;
		doIt=false;
		speed_old=0;
		
		for (ShotData data : s.getDataList()){
			if (!startCondition(data))
				continue;
			if (!intoGoalCondition(data))
				continue;
			if (!throwCondition(data))
				continue;
			choseMotion(data.getCrossing());
			//break;
		}
		createStats(s.getDataList());
		return status;
	}
	
	private boolean startCondition(ShotData data){
		float parameterSpeed=0.075f;
		if (data.getSpeed() > parameterSpeed
				&& data.getBallVelo().getY() < 0
				&& data.getPrecision() > 2)
			return true;
		return false;
	}
	
	private boolean intoGoalCondition(ShotData data){
		float paramCrossing = 1.f;
		float maxBallToGoal = 1.5f;
		//System.out.println(data.getSpeed() + " => " + data.getBallToGoalTime() + " vs " + data.getTravelDist());
		if (Math.abs(data.getCrossing()) < paramCrossing){
			if (data.getBallToGoalTime() < maxBallToGoal && 0.85f < data.getSpeed()){
				doIt=true;
			}else if (data.getBallToGoalTime() < 0.65f && speed_old < data.getSpeed()){
				doIt=true;
			}
			speed_old=data.getSpeed();
			return true;
		}
		return false;
	}
	
	private boolean throwCondition(ShotData data){
		int diffTime = 500;
		if (doIt && data.getTime()-lastTimeThrown > diffTime){
			lastTimeThrown=data.getTime();
			return true;
		}
		return false;
	}
	
	private void choseMotion(float crossing){
		if (Math.abs(crossing) < 0.175f){
			status=0;
		}else if (crossing > 0.f){
			status=1;
		}else if (crossing < 0.f){
			status=-1;
		}
	}
	private void createStats(LinkedList<ShotData> list){
		LinkedList<ShotData> buffer = new LinkedList<ShotData>();
		LinkedList<ShotData> dataOfInterest = new LinkedList<ShotData>();
		float sumSpeed=0;
		float maxSpeed=0;
		for (ShotData data : list){
			if (data.getPrecision()>2){
				if (data.getSpeed() == 0){
					if (sumSpeed > maxSpeed){
						maxSpeed=sumSpeed;
						dataOfInterest=new LinkedList<ShotData>(buffer);
					}
					sumSpeed=0;
					buffer = new LinkedList<ShotData>();
				}else{
					sumSpeed+=data.getSpeed();
					buffer.add(data);
				}
			}
		}
		if (sumSpeed > maxSpeed)
			dataOfInterest=new LinkedList<ShotData>(buffer);
		
		speedStats(dataOfInterest);
	}
	
	private void speedStats(LinkedList<ShotData> list){
		float avgSpeed=0;
		float max=0;
		
		for (ShotData data :list){
			if (data.getSpeed() > max)
				max=data.getSpeed();
			avgSpeed+=data.getSpeed();
		}
		System.out.println("FoundBall Frames: " + list.size());
		System.out.println("MAX-Speed: " + max);
		System.out.println("AVG-Speed: " + avgSpeed/list.size());
	}
}
