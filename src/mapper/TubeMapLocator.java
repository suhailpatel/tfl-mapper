package mapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Suhail
 */
public class TubeMapLocator {
    
    private ArrayList<TubeMapPoint> points;
    private HashMap<String, String> stations;
    
    public TubeMapLocator() throws Exception {        
        // Open the stations file and get the stations
        BufferedReader is = new BufferedReader(new FileReader("stations.csv"));
        this.stations = new HashMap<>();
        
        while (is.ready()) {
            String line = is.readLine();
            String [] station = line.split(",");
            stations.put(station[1], station[0]);
        }
        
        is.close();
        System.out.println(stations.size() + " stations loaded");

        // Open the points file and get any points
        is = new BufferedReader(new FileReader("points.csv"));
        points = new ArrayList<>();
        
        while (is.ready()) {
            String line = is.readLine();
            String [] p = line.split(",");
            
            if (p.length != 3) break;
            
            TubeMapPoint point = new TubeMapPoint();
            point.stationCode = p[0];
            point.x = Integer.valueOf(p[1]);
            point.y = Integer.valueOf(p[2]);
            
            points.add(point);
        }
        
        is.close();
        System.out.println(points.size() + " existing points loaded");
    }
    
    public HashMap<String, String> getStations() {
        return stations;
    }
    
    public ArrayList<TubeMapPoint> getPoints() {
        return points;
    }

    public void addPoint(TubeMapPoint point) {
        points.add(point);
        savePoints();
    }

    public void savePoints() {
        try {        
            BufferedWriter w = new BufferedWriter(new FileWriter("points.csv", false));
            
            for (TubeMapPoint p : points) {
                w.write(p.stationCode + "," + p.x + "," + p.y + "\n");
                w.flush();
            }
            
            w.close();
        } catch (IOException ex) {
            System.out.println("Could not save points!");
        }

    }
    
}

