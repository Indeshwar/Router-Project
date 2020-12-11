import java.util.*;
import java.io.*;
public class Initializer {
    private int size;
    private Router R;
    public Router initializer(File file){
        try{
            Scanner sc = new Scanner(file);//create an object of Scanner class
            size = Integer.parseInt(sc.nextLine());//parsing the firtLine of of the text that is the size of 2D array
            Router r = new Router(size);//create an instance of Router
            String[] destination = new String[size];
            for(int i = 0; i < size; i++){
                destination[i] = "R" + (i+1);//generating the router name and insert in the destination list
            }
            
            r.setDestination(destination);
            R = r;
            String s2;
            int index = 0;
            Router r2, r3;
            while(sc.hasNext()){
                s2 = sc.nextLine();//reading data from file
                r2 = split(s2, size);//split data and store the data associte with neigbour list and cost list
                r3 = r2.builder();//build a packet
    
                r.listener(r3);//flood the packet and update the routing table
                System.out.print(s2);
                System.out.println();
                
            }
           sc.close();
           return r;//return the refrence variable of CS324Graph
        }catch(Exception e){
            System.out.println("Errors");
        }
  
        return null;
    }
    
    public Router split(String s, int size){
        int n = s.length();//length of string
        char ch;
        //this refrence variable represents the row of routingTable
        int row = 0;
        //this reference variable represents the column of routingTable
        int col = 0;
        int rate = 1;
        int cost = 0;
        int k = 1;
        int[] costs = new int[size];
        String[] neighbour = new String[size];
        Router r = new Router(size);

        String level = "";
        String lev = "";
        int index = -1;
        for(int i = 0; i < n; i++){
            rate = 1;
            ch = s.charAt(i);
            level = "";
            if(ch >= 'A' && ch <= 'Z'){
                if(i == 0){
                    //writting the name of first router of every line from the data file
                    level += s.charAt(i);
                    level += s.charAt(i+1);
                    //find the index and assign in the row
                    index = R.findIndex(level);
                    
                    r.setLevel(level);
                }else{
                    level = "";//set null
                    //writting the name of every routers except first one
                    level += ch;
                    level += s.charAt(i+1);
                    //find the index and assign in the col
                    k = R.findIndex(level);
                    neighbour[k] = level;
                    
                }
            
            
            }else if(ch == ')'){
                for(int j = i - 1; j >=0; j--){
                    if(s.charAt(j) == ','){
                        break;
                    }
                    //writting the cost of router by reading character from file and cast into int
                    cost += (int)(s.charAt(j) - '0')*rate;
                    rate *= 10;
                }
                //inserting the cost in the routing table
                costs[k] = cost;
                cost = 0;//setting zero
            }
         
        }
        
        for(int i = 0; i < size; i++){
            if( i != index && costs[i] == 0){
               costs[i] = -1;//initialize with -1 if there are no directed connected neighbours
            }
        }
        
        r.setCost(costs);
        r.setNeighbour(neighbour);
        return r;
    }
    
}
