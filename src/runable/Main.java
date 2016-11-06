package runable;

import goaly.GoalyBehaviour;

import java.util.LinkedList;

import shooter.LoadShots;
import shooter.Shot;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String filename="/home/flopp/ecliWorkspace/GoalySim/data/parameterGoalPenalty.csv";
//		final String filename="/home/flopp/ecliWorkspace/GoalySim/data/parameterGoalDistance.csv";
//		final String filename="/home/flopp/ecliWorkspace/GoalySim/data/parameterNoGoal.csv";
//		final String filename="/home/flopp/ecliWorkspace/GoalySim/data/parameterNoGoalRandome.csv";
		
		LoadShots loader = new LoadShots(filename);
		LinkedList<Shot> shotList = loader.getShotList();
		for (Shot s : shotList){
			GoalyBehaviour goaly = new GoalyBehaviour();
			switch(goaly.run(s)){
				case 0:
					System.out.println("throw mid");
					break;
				case 1:
					System.out.println("throw right");
					break;
				case -1:
					System.out.println("throw left");
					break;
				default:
					System.out.println("do nothing");
			}
		}

	}

}
