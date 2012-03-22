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
            //if(!(attribute1.isEmpty() || attribute2.isEmpty() || attribute3.isEmpty() || attribute4.isEmpty() || attribute5.isEmpty() ))
              //  System.out.println("Stored Next Row :) ");
    }
    @Override
    public void show()
    {
        Object a1[] = attribute1.toArray();
        Object a2[] = attribute2.toArray();
        Object a3[] = attribute3.toArray();
        Object a4[] = attribute4.toArray();
        Object a5[] = attribute5.toArray();
        for(int i=0;i<attribute1.size();i++)
        {
            System.out.println(a1[i] + " " + a2[i] + " " +a3[i] + " " +a4[i] + " " +a5[i]);
        }
    }

    @Override
    public Float[] giveArray(int index) throws Exception
    {
        switch(index)
        {
            case 0:
                return attribute1.toArray(new Float[attribute1.size()]);
            case 1:
                return attribute2.toArray(new Float[attribute2.size()]);
            case 2:
                return attribute3.toArray(new Float[attribute3.size()]);
            case 3:
                return attribute4.toArray(new Float[attribute4.size()]);
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
                case 1:
                    attribute2.remove(i);
                    attribute2.add(i, a[i]);
                    break;
                case 2:
                    attribute3.remove(i);
                    attribute3.add(i, a[i]);
                    break;
                case 3:
                    attribute4.remove(i);
                    attribute4.add(i, a[i]);
                    break;
            }
        }
    }

}
