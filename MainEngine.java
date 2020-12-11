import java.io.*;
import java.util.*;
public class MainEngine {
    public static void main(String[] args){
        File file = new File("/Users/indeshwarchaudhary/Desktop/Indeshwar_ChaudharyDisktra.data/Indeshwar_Chaudhary_distkra2.txt");
        try{
            Initializer i = new Initializer();//create an object of class Initializer
            
            Router r = i.initializer(file);//invoked initializer method from class Initializer
         
            System.out.println("Enter the following commands: ");
            System.out.println("Display_Table:" + "To display Routing table \n" + "Shortest_Path: " + " To display shorted Paths of all the routers from source Router \n" + "Router_Name: " + "a router name whose neighbour should be changed \n" + "Change_Cost: " + "To change the cost of Router \n" + "Exit : " + "To terminate the program \n");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the command name: " );
            String input = sc.nextLine();
            int identifier, cost;
            while(!input.equals("Exit")){
               if(input.equals("Display_Table")){
                  r.displayTable();//display the initial routing table 
                 
               }else if(input.equals("Shortest_Path")){
                  System.out.println("Enter the name of source router: ");
                  input = sc.nextLine();
                  System.out.println("Source Router is " + input);
                  r.computeShortestPath(r.findIndex(input));//display the shortest path routing table
               }else if(input.equals("Change_Cost")){
                  System.out.println("Enter the name of router: ");
                  input = sc.nextLine();
                  Router R1 = r.router(input);
                  System.out.println("Enter the position of neighbur router: ");
                  input = sc.nextLine();
                  identifier = Integer.parseInt(input);
                  System.out.println("Enter the cost of router: ");
                  input = sc.nextLine();
                  cost = Integer.parseInt(input);
                  R1.Change_Cost(identifier, cost);//change the cost
                  Router r1 =R1.builder();//create a packet
                  r.listener(r1);//listen the packet and update the routing table

               }else{
                  System.out.println("You have entered wrong command! Try again");
        
               }
               
               System.out.print("Enter the command name: " );  
               input = sc.nextLine();
               
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
}