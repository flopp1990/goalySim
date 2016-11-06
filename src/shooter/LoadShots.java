package shooter;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class LoadShots {
	private final static String regex="(;|,)";
	LinkedList<Shot> shotList;
	boolean isGoal=false;
	
	public LoadShots(String filename){
		load(filename);
	}
	
	@SuppressWarnings("resource")
	private void load(String filename){
		shotList = new LinkedList<Shot>();
		Shot s;
		LinkedList<ShotData> dataList = new LinkedList<ShotData>();
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			String line=null;
			while ((line = input.readLine()) != null){
				if (line.equals("#")){
					if (dataList.isEmpty())
						continue;
					s = new Shot(isGoal,dataList);
					shotList.add(s);
					
					dataList = new LinkedList<ShotData>();
					continue;
				}
				String[] split=line.split(regex);
				ShotData data = new ShotData(
						Integer.parseInt(split[0]),
						Integer.parseInt(split[1]),
						new Point2D.Float(Float.parseFloat(split[2]),Float.parseFloat(split[3])),
						new Point2D.Float(Float.parseFloat(split[4]),Float.parseFloat(split[5])),
						Float.parseFloat(split[6]),
						Float.parseFloat(split[7]),
						Float.parseFloat(split[8]),
						Float.parseFloat(split[9]));
				dataList.add(data);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public LinkedList<Shot> getShotList() {
		return shotList;
	}

}
