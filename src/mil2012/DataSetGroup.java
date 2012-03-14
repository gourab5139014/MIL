/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil2012;
import mil2012.algo130312.*;
/**
 *
 * @author Gourab
 */
public class DataSetGroup {
    mil m;
    DataSetInstance instances[];
    String DataSetType;

    public DataSetGroup() {
        m = new mil();
    }

    public DataSetGroup(String DataSetType) {
        this.DataSetType = DataSetType;
    }

     private void initInstances()
    {
     // Use the DataSetType to initialize array of instances to proper chilc class
    }



}
