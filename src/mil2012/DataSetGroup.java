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
    ArrayList<Boolean> if_process_element;
    int dataSetSize; //Characteristics of the dataset
    
    String DataSetType; //Which child of DataSetInstance
    String infile_name;
    String infile_extension;
    BufferedReader fileReader;

    public enum Parameters {
            IRIS
         }
    public DataSetGroup() {
        m = new mil();
        dataSetSize=0;
        this.run();
    }

    public DataSetGroup(String DataSetType)
    {
        this.DataSetType = DataSetType;
        infile_name = DataSetType;
        infile_extension = "data";
        m = new mil(); //Operate MIL from run
        instances = new ArrayList();
        System.err.println("Running DataSetGroup...");
        this.run();
    }

    private void initFile()
    {
        try{
        fileReader = new BufferedReader(new FileReader(infile_name+"."+infile_extension));
        
        }catch(FileNotFoundException e)
        {
            System.err.println("Inside Initfile "+e.getLocalizedMessage());
        }
    }

    private void closeFile()
    {
        try{
        fileReader.close();
        }catch(IOException e)
        {
            System.err.println("Inside Closefile "+e.getLocalizedMessage());
        }
    }
    private void initInstances() throws Exception
    {
     // Use the DataSetType to initialize array of instances to proper chilc class
         //Parameters p = Parameters.valueOf(DataSetType);
         this.initFile();
         int i=0,j=0;
         DataSetInstance temp;
         String dataRow;
         String dataArray[];
         int p=DataSetType.hashCode();
         System.out.println("hash code : "+p+" for "+DataSetType);
         
         switch(p){
             case 2287667: //Case for IRIS
                 System.err.println("case for IRIS");
                 for(i=0;i<dataSetSize;i++)
                 {
                     temp = new Iris();
                     temp.s = 3; //no of classes
                     instances.add(temp);
                 }
                 System.out.println(" Instances Empty -> "+instances.isEmpty());
                 for(i=0;i<dataSetSize;i++) //put a copy of data in each of the instances
                 {
                     dataRow = fileReader.readLine();
                     //System.out.println(" Read from file "+dataRow);
                     if(dataRow == null) continue;
                     dataArray = dataRow.split(",");

                     //for(String s : dataArray) { System.out.print(" "+s);}
                     //System.out.println("");
                     for(j=0;j<dataSetSize;j++)
                     {
                         temp = instances.get(j);
                        System.out.println("Storing dataRow "+i+" in instance "+j);
                        temp.storeNext(dataArray);
                     }
                     
                 }
                 //System.out.println(" Display value from 1st instance");
                 //temp=instances.get(0); temp.show();
                 break;
         }
         this.closeFile();
    }

    private int get_dataSetSize() throws IOException
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
    private void set_process_elements()
    {
        //CODE
    }
    private void run()
    {
        try {
            System.err.println(" Inside RUn..");
            dataSetSize = this.get_dataSetSize(); //Check this
            set_process_elements();
            System.err.println(" DataSet Size :"+dataSetSize);
            this.initInstances();
            m.setDataSetSize(dataSetSize);
            //Call mil
        }catch(Exception e)
        {
            System.err.println("Inside Run "+e.getLocalizedMessage());
        }
    }

}
