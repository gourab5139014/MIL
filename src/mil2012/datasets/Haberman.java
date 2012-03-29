/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/* ATTRIBUTE INFORMATION
1. Age of patient at time of operation (numerical)
2. Patient's year of operation (year - 1900, numerical)
3. Number of positive axillary nodes detected (numerical)
4. Survival status (class attribute)
-- 1 = the patient survived 5 years or longer
-- 2 = the patient died within 5 year
*/

package mil2012.datasets;

import java.util.ArrayList;
import mil2012.DataSetInstance;

/**
 *
 * @author Gourab
 */
public class Haberman extends DataSetInstance{
    ArrayList<Float> attribute1;
    ArrayList<Float> attribute2;
    ArrayList<Float> attribute3;
    ArrayList<String> attribute4;

    public Haberman() {
        attribute1 = new ArrayList<Float>();
        attribute2 = new ArrayList<Float>();
        attribute3 = new ArrayList<Float>();
        attribute4 = new ArrayList<String>();
    }
    @Override
    public void storeNext(String dataArray[]) //Stores next tupple of data in attributes
    {
            attribute1.add(Float.parseFloat(dataArray[0].trim()));
            attribute2.add(Float.parseFloat(dataArray[1].trim()));
            attribute3.add(Float.parseFloat(dataArray[2].trim()));
            attribute4.add(dataArray[3].trim());
            //if(!(attribute1.isEmpty() || attribute2.isEmpty() || attribute3.isEmpty() || attribute4.isEmpty() || attribute5.isEmpty() ))
              //  System.out.println("Stored Next Row :) ");
    }
    @Override
    public String[] giveNext(int i)
    {
        String[] dataArray={Float.toString((float)attribute1.get(i)),Float.toString((float)attribute2.get(i)),Float.toString((float)attribute3.get(i)),attribute4.get(i)};
        return dataArray;
    }
    @Override
    public void show()
    {
        Object a1[] = attribute1.toArray();
        Object a2[] = attribute2.toArray();
        Object a3[] = attribute3.toArray();
        for(int i=0;i<attribute1.size();i++)
        {
            System.out.println(a1[i] + " " + a2[i] + " " +a3[i]);
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
            }
        }
    }


}
