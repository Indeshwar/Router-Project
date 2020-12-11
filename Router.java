public class Router {
    private String level;
    private String[] neighbour;
    private int[] cost;
    private String[] destination;
    private int[][] RoutingTable;
    
    public Router(int size){
        this.neighbour = new String[size];
        this.cost = new int[size];
        this.destination = new String[size];
        this.RoutingTable = new int[size][size];
        
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
               if(i != j){
                  this.RoutingTable[i][j] = -1;//initialize with -1 
               }
            }
        }
        
        
    }
    
    public Router builder(){
      int n = destination.length;
      Router r = new Router(n);//create an object that work as a packet
      String[] neighbour = new String[n];
      r.level = this.level;
      int[] cost = new int[n];
      for(int i = 1; i < n; i++){
         r.neighbour[i] = this.neighbour[i];
         r.cost[i] = this.cost[i];
      }
      return r;//return refrence variable of Router object
    }
    
    public void listener(Router r){
      int row = findIndex(r.level);//find the index of a row to be updated
      int col;
      //flooding
      for(int i = 1; i < r.neighbour.length; i++){
         this.RoutingTable[row][i] = r.cost[i];//update the routing table

      }
    }
    
    //This method return an object of given router
    public Router router(String r){
      int n = destination.length;
      Router r1 = new Router(n);
      int row = findIndex(r);//find the index of router
      r1.level = r;
      for(int i = 0; i < n; i++){
        if(this.RoutingTable[row][i] != 0){
           r1.cost[i] = this.RoutingTable[row][i];
        }
      }
      return r1;
    }
    
    //this method change the cost of neighbour of a router
    //the first parameter is the index of router and second parameter is the cost
    public void Change_Cost(int identifier, int cost){
         this.cost[identifier] = cost;//update the new cost
    }
    
    //display the routing table
    public void displayTable(){
      int n = this.destination.length;
      for(int i = 0; i < n; i++){
         for(int j = 0; j < n; j++){
            System.out.printf("%-4d", RoutingTable[i][j]);
         }  
         System.out.println();
      }
    }
    
    //compute the index of minimum cost
    public int minIndex(int[] distance, boolean[] visited){
      int n = distance.length;
      int min = Integer.MAX_VALUE;
      int minIndex = 0;
      for(int i = 0; i < n; i++){
         if(distance[i] < min && visited[i] == false){
            min = distance[i];//update the minimum cost
            minIndex = i;//assign the index of minimum cost
            
         }
      }
      return minIndex;//return the refrence variable of temIndex
    }
    
    public void computeShortestPath(int source){
      int n = this.RoutingTable.length;
      boolean[] visited = new boolean[n]; 
      int[] distance = new int[n];
      int[] parent = new int[n];
      
      for(int i = 0; i < n; i++){
         visited[i] = false;//initialize with false value
         distance[i] = Integer.MAX_VALUE;//initialize with maximum value 
      }
      
      distance[source] = 0;//initialize distance of source 0 since distance of source is always 0
      int nEgdges = 0;
      int vNear = 0;//it temporaly holds index of minimum distance
            
      //this while loop executed n-1 times since numbers of edges are always one less than
      //the numbers of vertices(By the properties of Miminimum Spanning Tree)
      while(nEgdges < n-1){
         vNear = minIndex(distance, visited);//find the index of minimum distance and assign in the vNear
         visited[vNear] = true;//set true as index vNear as visited
         for(int i = 0; i < n; i++){
            //check whether distance should be updated
            if(!visited[i] && this.RoutingTable[vNear][i] > 0 && distance[vNear] != Integer.MAX_VALUE && distance[vNear] + this.RoutingTable[vNear][i] < distance[i]){
               distance[i] = this.RoutingTable[vNear][i] + distance[vNear];//update the shortest distance
               parent[i] = vNear;//store the nVnear in the parent array
            }
         }
         nEgdges++;//increment
         
      }
      printShortestPath(parent, distance);//print the shortest path
    }
    
    //display the shortest path table
    public void printShortestPath(int parent[], int[] distance){
      int n = parent.length;
		System.out.printf("%-8s%8s%12s\n", "Destination", "Line", "Cost");
		for (int i = 0; i < n; i++){
         if(distance[i] != Integer.MAX_VALUE && distance[i] != 0){
			   System.out.printf("%-8s%9s%12d\n", destination[i], destination[parent[i]], distance[i]);
         }
      }  
	 }
    
    //find the index of router
    public int findIndex(String level){
      int tempIndex = -1;
      int n = destination.length;
      for(int i = 0; i < n; i++){
         if(destination[i].equals(level)){
            tempIndex = i;
         }
      }
      return tempIndex;
    }
    

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the neighbour
     */
    public String[] getNeighbour() {
        return neighbour;
    }

    /**
     * @param neighbour the neighbour to set
     */
    public void setNeighbour(String[] neighbour) {
        this.neighbour = neighbour;
    }

    /**
     * @return the cost
     */
    public int[] getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int[] cost) {
        this.cost = cost;
    }

    /**
     * @return the destination
     */
    public String[] getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String[] destination) {
        this.destination = destination;
    }

    /**
     * @return the RoutingTable
     */
    public int[][] getRoutingTable() {
        return RoutingTable;
    }

    /**
     * @param RoutingTable the RoutingTable to set
     */
    public void setRoutingTable(int[][] RoutingTable) {
        this.RoutingTable = RoutingTable;
    }
    
    
}
