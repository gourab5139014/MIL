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
        int choice=0,number_of_instances=0;
        DataInputStream din = new DataInputStream(System.in);
        final int maxChoice=1; //Declare as constant
        
        do{
        System.out.println("\n**\tEnter Choice of DataSet\t**");
        System.out.println("\n1. Iris");
        System.out.println("\n0. Exit");
        }while(choice<0||choice>maxChoice);
        /*
        System.out.println("\n\tNumber of Instances : ");
        number_of_instances=Integer.parseInt(din.readLine());
        */
        switch(choice)
        {
            case 1:
                DataSetGroup d = new DataSetGroup("Iris");
                break;
            case 0:
            default:
                System.out.println("Exiting...");
                    
        }

    }

    public static void main(String[] args) {
        // TODO code application logic here
        try{
        displayMenu();
        }catch(Exception e)
        {
            System.err.println(e.getStackTrace());
        }
    }

}
