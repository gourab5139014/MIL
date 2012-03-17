/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil2012;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import mil2012.algo130312.*;
/**
 *
 * @author Gourab
 */
public class DataSetGroup {
    mil m;
    ArrayList instances;
    int number_of_instances;
    String DataSetType;
    String infile_name;
    String infile_extension;
    BufferedReader fileReader;

    public enum Parameters {
            IRIS
         }
    public DataSetGroup() {
        m = new mil();
        number_of_instances=0;
    }

    public DataSetGroup(String DataSetType)
    {
        this.DataSetType = DataSetType;
        m = new mil();
        instances = new ArrayList();
    }

    private void initFile()
    {
        try{
        fileReader = new BufferedReader(new FileReader(infile_name+"."+infile_extension));
        
        }catch(FileNotFoundException e)
        {
            System.err.println(e.getStackTrace());
        }
    }
    private void initInstances()
    {
     // Use the DataSetType to initialize array of instances to proper chilc class
         Parameters p = Parameters.valueOf(DataSetType);
         switch(p){
             case IRIS:
                 
                 break;
         }

    }



}
