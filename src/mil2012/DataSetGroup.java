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
import mil2012.datasets.Haberman;
import mil2012.mil280312.*;
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
                     temp.setType(DataSetType);
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
             case -275727164:
                 System.err.println("case for Haberman");
                 for(i=0;i<instance_count;i++)
                 {
                     temp = new Haberman();
                     temp.setType(DataSetType);
                     temp.s = 2; //no of classes in Haberman
                     temp.attribute_count = 4; //no. of attributes in Haberman
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
    private void resultToFiles(String extension)
    {
        //Testing output to a directory
        try{
            DataSetInstance temp;
            File outFile;
            BufferedWriter writer;
            String[] dataArray;

            for(int i=0;i<instance_count;i++)
            {
                temp=instances.get(i);
                outFile = new File("."+File.separator+"results"+File.separator +"out"+DataSetType+"_"+temp.getC()+"_"+temp.getK()+"."+extension);
                if(!outFile.exists()) outFile.createNewFile();
                writer = new BufferedWriter(new FileWriter(outFile));
                //FETCH DATA HERE
                for(int j=0;j<dataSetSize;j++)
                {
                    dataArray = temp.giveNext(j);
                    writer.write(dataArray[0]);
                    for(int k=1;k<(dataArray.length-1);k++)
                    {
                        if(extension.equals("csv")) writer.write(","); else writer.write("\t");
                        writer.write(dataArray[k]);
                    }
                    writer.newLine();
                }
                writer.close();
            }
        }catch(Exception e)
        {
           System.err.println("Inside resultToFile "+e.getLocalizedMessage());
        }
    }
    
    /*
    private void plotInDetail(String extension)
    {
        try{
            DataSetInstance temp;
            File outFile;
            BufferedWriter writer;
            String firstName; String type="CTS"; int arr[];
            for(int i=0;i<instance_count;i++)
            {
                temp = instances.get(i);
                for(int j=0;j<temp.attribute_count;j++)
                {
                    firstName = "."+File.separator+"results"+File.separator+"details"+File.separator+type+"_"+DataSetType+"_C"+temp.getC()+"K"+temp.getK()+"Index"+j;
                    arr = this.m.getCTS();
                    this.plotArray(arr, firstName, extension);
                }
            }
        }catch(Exception e)
        {
            System.err.println("Inside Plot in Detail - "+e.getLocalizedMessage());
        }
    }
     *
     */
    private void run()
    {
        try {
            System.err.println(" Inside Run..");
            dataSetSize = this.read_dataSetSize();
            instance_count = this.read_instanceCount();
            System.err.println(" DataSet Size :"+dataSetSize+" Instances Count (c,k) : "+instance_count);
            this.initInstances();
            processMIL();
            resultToFiles("csv");
            //plotArray(m.getCTS(),DataSetType+"CTS","data");
            //plotArray(m.getMerge(), DataSetType+"Merge", "data");
        }catch(Exception e)
        {
            System.err.println("Inside Run "+e.getLocalizedMessage());
        }
    }

}
