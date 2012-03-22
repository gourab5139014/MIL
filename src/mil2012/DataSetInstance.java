/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil2012;

/**
 *
 * @author Gourab
 */
public class DataSetInstance {
    int s; //number of classes
    
    public DataSetInstance() {
    }
    public void storeNext(String dataArray[]){
        System.err.println("Hello from storeNext in DataSetInstance BASE Class");
    }
    public void show()
    {
        System.err.println("Hello from show in DataSetInstance BASE Class");
    }
    public Float[] giveArray(int index) throws Exception
    {
        Float[] a = new Float[1]; // RANDOM
        System.err.println("Hello from giveArray in DataSetInstance BASE Class");
        return a;
    }
    public void storeArray(Float a[],int column)
    {
        System.err.println("Hello from storeArray in DataSetInstance BASE Class");
    }
    public int getS()
    {
        return s;
    }

}
