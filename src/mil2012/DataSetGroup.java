/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil2012;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import mil2012.algo130312.*;
import mil2012.datasets.Iris;
/**
 *
 * @author Gourab
 */
public class DataSetGroup {
    mil m;
    ArrayList<DataSetInstance> instances;

    int number_of_instances; //Characteristics of the dataset
    
    String DataSetType; //Which child of DataSetInstance
    String infile_name;
    String infile_extension;
    BufferedReader fileReader;

    public enum Parameters {
            IRIS
         }
    public DataSetGroup() {
        m = new mil();
        number_of_instances=0;
        this.run();
    }

    public DataSetGroup(String DataSetType)
    {
        this.DataSetType = DataSetType;
        m = new mil(); //Operate MIL from run
        instances = new ArrayList();
        this.run();
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

    private void closeFile()
    {
        try{
        fileReader.close();
        }catch(IOException e)
        {
            System.err.println(e.getStackTrace());
        }
    }
    private void initInstances() throws Exception
    {
     // Use the DataSetType to initialize array of instances to proper chilc class
         Parameters p = Parameters.valueOf(DataSetType);
         this.initFile();
         int i=0;
         DataSetInstance temp;
         String dataRow;
         String dataArray[];

         switch(p){
             case IRIS:
                 for(i=0;i<number_of_instances;i++)
                 {
                     temp = new Iris();
                     instances.add(temp);
                 }

                 for(i=0;i<number_of_instances;i++) //put a copy of data in each of the instances
                 {
                     dataRow = fileReader.readLine();
                     dataArray = dataRow.split("'");
                     temp=instances.get(i);
                     temp.storeNext(dataArray);
                 }
                 break;
         }
         this.closeFile();
    }

    private int get_number_of_instances() throws IOException
    {
        int no=0;
        this.initFile();
        String dataRow=fileReader.readLine();

        do
        {
            dataRow=fileReader.readLine();
            no++;
        }while(dataRow != null);

        this.closeFile();
        return no;
    }
    private void run() 
    {
        try {
            number_of_instances = this.get_number_of_instances();
            this.initInstances();
        }catch(Exception e)
        {
            System.err.println(e.getStackTrace());
        }
    }

}
