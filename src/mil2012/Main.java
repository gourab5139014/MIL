/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil2012;

/**
 *
 * @author Gourab
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void displayMenu()
   {
        int choice=0;
        int maxChoice=1; //Declare as constant
        do{
        System.out.println("\n**\tEnter Choice of DataSet\t**");
        System.out.println("\n1. Iris");
        System.out.println("\n0. Exit");
        }while(choice<0||choice>maxChoice);
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
        displayMenu();
    }

}
