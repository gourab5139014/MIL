/*
Each patient is represented in the data set by six biomechanical attributes derived from the shape and orientation of the
pelvis and lumbar spine (in this order): pelvic incidence, pelvic tilt, lumbar lordosis angle, sacral slope, pelvic radius
 and grade of spondylolisthesis. The following convention is used for the class labels: DH (Disk Hernia), Spondylolisthesis (SL),
 Normal (NO) and Abnormal (AB).
 */

package mil2012.datasets;

import java.util.ArrayList;
import mil2012.DataSetInstance;

/**
 *
 * @author Gourab
 */
public class Vertebral extends DataSetInstance{

    ArrayList<Float> attribute1;
    ArrayList<Float> attribute2;
    ArrayList<Float> attribute3;
    ArrayList<Float> attribute4;
    ArrayList<Float> attribute5;
    ArrayList<Float> attribute6;
    ArrayList<String> attribute7;

    public Vertebral() {
        attribute1 = new ArrayList<Float>();
        attribute2 = new ArrayList<Float>();
        attribute3 = new ArrayList<Float>();
        attribute4 = new ArrayList<Float>();
        attribute5 = new ArrayList<Float>();
        attribute6 = new ArrayList<Float>();
        attribute7 = new ArrayList<String>();
    }
    @Override
    public void storeNext(String dataArray[]) //Stores next tupple of data in attributes
    {
            attribute1.add(Float.parseFloat(dataArray[0].trim()));
            attribute2.add(Float.parseFloat(dataArray[1].trim()));
            attribute3.add(Float.parseFloat(dataArray[2].trim()));
            attribute4.add(Float.parseFloat(dataArray[3].trim()));
            attribute5.add(Float.parseFloat(dataArray[4].trim()));
            attribute6.add(Float.parseFloat(dataArray[5].trim()));
            attribute7.add(dataArray[6].trim());
            //if(!(attribute1.isEmpty() || attribute2.isEmpty() || attribute3.isEmpty() || attribute4.isEmpty() || attribute5.isEmpty() ))
              //  System.out.println("Stored Next Row :) ");
    }
    @Override
    public String[] giveNext(int i)
    {
        String[] dataArray={Float.toString((float)attribute1.get(i)),Float.toString((float)attribute2.get(i)),Float.toString((float)attribute3.get(i)),Float.toString((float)attribute4.get(i)),Float.toString((float)attribute5.get(i)),Float.toString((float)attribute6.get(i)),attribute7.get(i)};
        return dataArray;
    }
    @Override
    public void show()
    {
        Object a1[] = attribute1.toArray();
        Object a2[] = attribute2.toArray();
        Object a3[] = attribute3.toArray();
        Object a4[] = attribute4.toArray();
        Object a5[] = attribute5.toArray();
        Object a6[] = attribute6.toArray();
        Object a7[] = attribute7.toArray();
        for(int i=0;i<attribute1.size();i++)
        {
            System.out.println(a1[i] + " " + a2[i] + " " +a3[i] + " " +a4[i] + " " +a5[i]+" "+a6[i]+" "+a7[i]);
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
            case 4:
                return attribute5.toArray(new Float[attribute5.size()]);
            case 5:
                return attribute6.toArray(new Float[attribute6.size()]);
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
                case 4:
                    attribute5.remove(i);
                    attribute5.add(i, a[i]);
                    break;
                case 5:
                    attribute6.remove(i);
                    attribute6.add(i, a[i]);
                    break;
            }
        }
    }
}
