/*
This is the new one that implements a DESC % window for merging from the previous CTS
 */

package mil2012.algo300312;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import mil2012.DataSetInstance;

/**
 *
 * @author Gourab
 */
public class mil {
    int dataSetSize;
    int CTS[];
    int merge[];
    String stamp = "algo300312";

    public String getStamp() {
        return stamp;
    }

    public mil() {

        System.out.println("Hello from" + this.stamp);
    }

    public int[] getCTS() {
        return CTS;
    }

    public int[] getMerge() {
        return merge;
    }


        public void setDataSetSize(int dataSetSize) {
        this.dataSetSize = dataSetSize;
    }


    public void run(DataSetInstance d) throws Exception
    {
        int len=d.getAttribute_count(); //total number of attributes in the dataset
        int i=0;
        Float[] temp = new Float[len]; //size of the dataset
        int[] plot;
        String fileName;
        for(i=0;i<(len-1);i++)
        {

                //extract Float[] for the particular column
                temp = d.giveArray(i);
                //call algo for that column
                algo(temp, d.getC(), d.getS(),d.getK());
                //plot CTS/Merge for current Attribute i  for the given C,K
                //store back Float[] to the corresponsing ArrayList<>
                plot = this.getCTS();
                String base = "."+File.separator+"results"+File.separator+stamp+File.separator+"details"+d.getType()+File.separator+"C"+d.getC()+"K"+d.getK();
                new File(base).mkdirs();
                fileName = base+File.separator+"CTS"+"Attr"+i;
                plotArray(plot,fileName,"data");
                plot = this.getMerge();
                fileName = base+File.separator+"Merge"+"Attr"+i;
                plotArray(plot,fileName,"data");
                d.storeArray(temp, i);

        }
    }
    private void algo(Float a[],int c,int s,int k)
{
	float DESC = (float) 0.2;
        int n;
        n=c*s;
        CTS = new int[n];     //n = no of sub intervals
        merge=new int[n+1];
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
        //int CTS[] = new int[n];     //n = no of sub intervals
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
                float t = (max-min)/n;
                if(min+t*j<=z)
                {
                    if(z<=min+t*(j+1))
                        {CTS[j]++;break;}         //now CTS contains calculated TS for every interval n
                }
            }
        }
        int i =0;
    for(i=0;i<=n;i++)
    {
        merge[i]=1;
    }
    int t=0;
    i=0;
    int TOT_CTS=0;
    boolean last_small_merge=false;
    while(i<n)
    {
        TOT_CTS+=CTS[i];
        if(TOT_CTS<TS/k)
        {
            TS=TS+m/n;
            i++;
            if(i<n)
            merge[t]++;             //no of intervals merged + 1
            last_small_merge=true;
         }
        else
        {
            if(i<(n-1) && !last_small_merge && CTS[i+1] <= (1+ DESC)*CTS[i] && CTS[i+1] >= (1-DESC)*CTS[i])
            {
                i++;
                merge[t]++;             //no of intervals merged + 1
            }
            else
            {
                t++;
                i++;
                TOT_CTS = 0;
                TS = m / n;
                last_small_merge = false;
            }
        }            //t contains no of final merged intervals
    }

    float cumm[]=new float[t+2];
    float r = (max-min)/n;
    cumm[0]=min;
    //System.out.println(merge[t]);
    for(i=1;i<=t+1;i++)
    {
        cumm[i]=cumm[i-1]+r*merge[i-1];
    }
   for(i=0;i<m;i++)
    {
        for(int j=0;j<t;j++)
        {
            if(a[i]<cumm[j+1])
                if(a[i]>cumm[j])
                {
                    a[i] = (cumm[j] + cumm[j + 1]) / 2;
//                    int x;
//                    x=(int)((a[i]+0.005)*100.0);    //ease rounding off
//                    float y;
//                    y= (float)x/100;
//                    a[i]=y;
                }
        }
    }
    }
    private void plotArray(int[] a,String firstName,String extension)
    {
        try{
            File outFile;
            BufferedWriter writer;
            outFile = new File(firstName+"."+extension);
            if(!outFile.exists()) outFile.createNewFile();
            writer = new BufferedWriter(new FileWriter(outFile));

            for(int i=0;i<a.length;i++)
            {

                writer.write(Integer.toString(i)+"\t"+Integer.toString(a[i]));
                writer.newLine();
            }
            writer.close();
        }catch(Exception e)
        {
            System.err.println(e.getLocalizedMessage());
        }
    }
}

