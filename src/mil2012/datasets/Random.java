/*
 * Used for modelling any attribute extracted from a large dataset using Excel. The CSV file must contain the attribute
 * to be discretized and the class value
 */

package mil2012.datasets;

import java.util.ArrayList;
import mil2012.DataSetInstance;

/**
 *
 * @author Gourab
 */
public class Random extends DataSetInstance{
    ArrayList<Float> attribute1;
    ArrayList<String> attribute2;

    public Random() {
        attribute1 = new ArrayList<Float>();
        attribute2 = new ArrayList<String>();
    }

}
