/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil2012.algo130312;

import java.util.ArrayList;
import mil2012.DataSetInstance;

/**
 *
 * @author Gourab
 */
public class mil {
    int dataSetSize;
    public mil() {
        System.out.println("Hello from mil 130312");
    }

    public void setDataSetSize(int size)
    {
        dataSetSize = size;
    }
    public void run(DataSetInstance d,ArrayList<Boolean> if_process_element) throws Exception
    {
        int len = if_process_element.size();
        int i=0;
        Float[] temp = new Float[len]; //size of the dataset
        for(i=0;i<len;i++)
        {
            if(if_process_element.get(i))
            {
                //extract Float[] for the particular column
                temp = d.giveArray(i);

                //call algo for that column
                algo(temp, 1, d.getS()); //GET C FROM SOMEWHERE
                //store back Float[] to the corresponsing ArrayList<>
                d.storeArray(temp, i);
            }
        }
    }
    private void algo(Float a[],int c,int s)
{    
	int n;
        n=c*s;
        int m;
        m=a.length;
        float TS=m/n;
        float max=a[0],min=a[0];
        for(int i=1;i<m;i++)
        {
            if(a[i]>max)
                max=a[i];           //max contains max of the array a[],min contains min of the array a[]
            if(a[i]<min)
                min=a[i];

        }
        int CTS[] = new int[n];     //n = no of sub intervals
        for(int i=0;i<n;i++)
        {
            CTS[i]=0;
        }
        for(int i=0;i<m;i++)
        {
            float z = a[i];
            int j=0;
            for(j=0;j<n;j++)
            {
                float k = (max-min)/n;
                if(min+k*j<=z)
                {
                    if(z<=min+k*(j+1))
                        {CTS[j]++;break;}         //now CTS contains calculated TS for every interval n
                }
            }
        }

    }
}
