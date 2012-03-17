/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil2012.datasets;

import java.util.ArrayList;
import mil2012.DataSetInstance;

/**
 *
 * @author Gourab
 */
public class Iris extends DataSetInstance{
    ArrayList<Float> attribute1;
    ArrayList<Float> attribute2;
    ArrayList<Float> attribute3;
    ArrayList<Float> attribute4;
    ArrayList<String> attribute5;
    
    public Iris() {
        attribute1 = new ArrayList<Float>();
        attribute2 = new ArrayList<Float>();
        attribute3 = new ArrayList<Float>();
        attribute4 = new ArrayList<Float>();
        attribute5 = new ArrayList<String>();
    }

    @Override // << IS THIS PROBLEMATIC ?
    public void storeNext(String dataArray[]) //Stores next tupple of data in attributes
    {
            attribute1.add(Float.parseFloat(dataArray[0].trim()));
            attribute2.add(Float.parseFloat(dataArray[1].trim()));
            attribute3.add(Float.parseFloat(dataArray[2].trim()));
            attribute4.add(Float.parseFloat(dataArray[3].trim()));
            attribute5.add(dataArray[4].trim());
    }
}
