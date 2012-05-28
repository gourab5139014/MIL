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
    @Override
    public void storeNext(String dataArray[]) //Stores next tupple of data in attributes
    {
        try{
        //System.out.println("HELLO FROM STORENEXT FOR "+dataArray);
            //System.out.print("Now Storing -");
            //for(int i=0;i<dataArray.length;i++) System.out.print(dataArray[i]+" ");
            //System.out.print("\n");
            attribute1.add(Float.parseFloat(dataArray[0].trim()));
            attribute2.add(dataArray[1].trim());
            //if(!(attribute1.isEmpty() || attribute2.isEmpty() || attribute3.isEmpty() || attribute4.isEmpty() || attribute5.isEmpty() ))
              //  System.out.println("Stored Next Row :) ");
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public String[] giveNext(int i)
    {
        String[] dataArray={Float.toString((float)attribute1.get(i)),attribute2.get(i)};
        return dataArray;
    }
    @Override
    public void show()
    {
        Object a1[] = attribute1.toArray();
        Object a2[] = attribute2.toArray();
        for(int i=0;i<attribute1.size();i++)
        {
            System.out.println(a1[i] + " " + a2[i]);
        }
    }

    @Override
    public Float[] giveArray(int index) throws Exception
    {
        switch(index)
        {
            case 0:
                return attribute1.toArray(new Float[attribute1.size()]);
            default:
                throw new Exception("Invalid Request to return a Float array :: The parameter requested contains a class value");

        }
    }
    @Override
    public void storeArray(Float a[],int column)
    {
        //store array as column in AL
        for(int i=0;i<a.length;i++)
        {
            switch(column)
            {
                case 0:
                    attribute1.remove(i);
                    attribute1.add(i, a[i]);
                    break;
            }
        }
    }
}
