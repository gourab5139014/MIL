/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil2012;

import java.io.DataInputStream;

/**
 *
 * @author Gourab
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void displayMenu() throws Exception
   {
        int choice=0;
        DataSetGroup d;
        DataInputStream din = new DataInputStream(System.in);
        final int maxChoice=2; //Declare as constant
        
        do{
            System.out.println("\n**\tEnter Choice of DataSet\t**");
            System.out.println("1. Iris");
            System.out.println("2. Haberman");
            System.out.println("0. Exit");
            choice=Integer.parseInt(din.readLine());
            System.err.println("Choice is "+choice);
        }while(choice<0||choice>maxChoice);
        /*
        System.out.println("\n\tNumber of Instances : ");
        number_of_instances=Integer.parseInt(din.readLine());
        */
        System.err.println("Switch for "+choice);
        switch(choice)
        {
            case 1:
                d = new DataSetGroup("Iris");
                break;
            case 2:
                d = new DataSetGroup("Haberman");
            case 0:
            default:
                System.out.println("Exiting...");
                    
        }

    }

    public static void main(String[] args) {
        // TODO test with multiple data files
        try{
        displayMenu();
        }catch(Exception e)
        {
            System.err.println(e.getStackTrace());
        }
    }

}
