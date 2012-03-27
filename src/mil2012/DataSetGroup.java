/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil2012;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
    //ArrayList<Boolean> if_process_element;
    int dataSetSize; //Number of tupples in the dataSet
    int instance_count; //no of c,k  value pairs available
    
    String DataSetType; //Which child of DataSetInstance
    String infile_name;
    String infile_extension;
    BufferedReader fileReader;

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

    private BufferedReader initFile(String name, String extension)
    {
        try{
            BufferedReader Reader = new BufferedReader(new FileReader(name+"."+extension));
            return Reader;
        
        }catch(FileNotFoundException e)
        {
            System.err.println("Inside Initfile "+e.getLocalizedMessage());
            return null;
        }
    }

    private void closeFile(BufferedReader Reader)
    {
        try{
            Reader.close();
        }catch(IOException e)
        {
            System.err.println("Inside Closefile "+e.getLocalizedMessage());
        }
    }
    private void initInstances() throws Exception
    {
     // Use the DataSetType to initialize array of instances to proper chilc class
         //Parameters p = Parameters.valueOf(DataSetType);
         this.fileReader = this.initFile(infile_name,infile_extension);
         int i=0,j=0;
         DataSetInstance temp;
         String dataRow;
         String dataArray[];
         int p=DataSetType.hashCode();
         System.err.println("hash code : "+p+" for "+DataSetType);
         
         switch(p){
             case 2287667: //Case for IRIS
                 System.err.println("case for IRIS");
                 for(i=0;i<instance_count;i++)
                 {
                     temp = new Iris();
                     temp.s = 3; //no of classes in IRIS
                     temp.attribute_count = 5; //no. of attributes in IRIS
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
                     for(j=0;j<instance_count;j++) //store in instances
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
         this.closeFile(fileReader);
    }

    private int read_dataSetSize() throws IOException
    {
        int no=0;
        fileReader = this.initFile(infile_name,infile_extension);
        String dataRow=fileReader.readLine();

        do
        {
            dataRow=fileReader.readLine();
            no++;
        }while(dataRow != null);

        this.closeFile(fileReader);
        return no;
    }

    private int read_instanceCount() throws IOException
    {
        int no=0;
        this.fileReader=this.initFile("ck"+DataSetType,"data");
        String dataRow=fileReader.readLine();

        do
        {
            dataRow=fileReader.readLine();
            no++;
        }while(dataRow != null);

        this.closeFile(this.fileReader);
        return no;
    }
    
    private void processMIL()
    {
        try{
            DataSetInstance temp;
            String dataRow;
            String dataArray[];
            //String fileName = "ck"+DataSetType;
            //System.err.println("Trying to open "+fileName+".data");
            BufferedReader ckdata = initFile("ck"+DataSetType, "data");
            for(int i=0;i<instance_count;i++)
            {
                dataRow = ckdata.readLine();
                if (dataRow == null) continue;
                dataArray = dataRow.split(",");
                m.setDataSetSize(dataSetSize);
                temp = instances.get(i);
                temp.setC(Integer.parseInt(dataArray[0]));
                temp.setK(Integer.parseInt(dataArray[1]));
                m.run(temp);
            }
            closeFile(ckdata);
        }catch(Exception e)
        {
            System.err.println("Inside processMIL "+e.getLocalizedMessage());
        }
    }
    private void resultToFiles()
    {
        //Testing output to a directory
        try{
        DataSetInstance temp = instances.get(0);
        String firstName= "."+File.separator+"results"+File.separator +"out"+DataSetType+"_"+temp.getC()+"_"+temp.getK();
        File outFile = new File(firstName+".data");
        if(!outFile.exists()) outFile.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        writer.write(dataSetSize); //Test
        writer.close();
        }catch(Exception e)
        {
           System.err.println("Inside resultToFile "+e.getLocalizedMessage());
        }
    }
    private void run()
    {
        try {
            System.err.println(" Inside Run..");
            dataSetSize = this.read_dataSetSize();
            instance_count = this.read_instanceCount();
            System.err.println(" DataSet Size :"+dataSetSize+" Instances Count (c,k) : "+instance_count);
            this.initInstances();
            processMIL();
            resultToFiles();
        }catch(Exception e)
        {
            System.err.println("Inside Run "+e.getLocalizedMessage());
        }
    }

}
