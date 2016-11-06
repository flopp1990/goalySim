package shooter;

import java.util.LinkedList;

public class Shot {
	boolean isGoal;
	LinkedList<ShotData> dataList;

	public Shot(boolean isGoal, LinkedList<ShotData> pos){
		this.isGoal=isGoal;
		this.dataList=pos;
	}

	public boolean isGoal() {
		return isGoal;
	}

	public LinkedList<ShotData> getDataList() {
		return dataList;
	}
	

}
