package gwu.zqy.main.Dijkstra;

import java.io.*;
import java.util.*;

public class Dijkstra {
	
	
	static int graph[][] = new int[2000][200];
	static int V = 200;
	
	// Creat shortest path set, this set tracks the vertices contained in the shortest path tree, 
	// that is, its minimum distance from the source point has been calculated and determined. 
	// Initially, this set is empty.
	int minDistance(int dist[], Boolean sptSet[]){
		
		// Assigns distance values to all vertices in the input graph. 
		// Initialize all distance values to infinity. 
		// Specify the distance value of the source vertex as 0
			int min = Integer.MAX_VALUE, min_index = -1;
			
			for(int v=0; v<V; v++)
				if (sptSet[v] == false && dist[v] <= min){
					min = dist[v];
					min_index = v;
				}
			return min_index;
	}
	
	public void printSolution(int dist[], int n, int m)
	{
		/*for(int i=0; i<V; i++)
			System.out.println(" The Distance to Vertex " + i + " is " + dist[i]); 
		*/	
		System.out.println("The Shortest Distance is: " + dist[m]);
	}
	
	
	public void dijkstra(int graph[][], int src, int tar){
				int dist[] = new int[V];
				 
				// sptSet[i] will true if vertex i is included in shortest
				Boolean sptSet[] = new Boolean[V];
				 
				// Initialize all distances as INFINITE and stpSet[] as false
				for (int i = 0; i < V; i++) { 
		                dist[i] = Integer.MAX_VALUE; 
		                sptSet[i] = false; 
		        } 
				dist[src] = 0;
				// Find shortest path for all vertices  
				 for (int count = 0; count < V - 1; count++) { 
	            // Pick the minimum distance vertex from the set of vertices 
	            // not yet processed. u is always equal to src in first 
	            // iteration. 
	            int u = minDistance(dist, sptSet); 
	  
	            // Mark the picked vertex as processed 
	            sptSet[u] = true; 
	  
	            // Update dist value of the adjacent vertices of the 
	            // picked vertex. 
	            for (int v = 0; v < V; v++) 
	  
	                // Update dist[v] only if is not in sptSet, there is an 
	                // edge from u to v, and total weight of path from src to 
	                // v through u is smaller than current value of dist[v] 
	                if (!sptSet[v] && graph[u][v] != 0 &&  
	                   dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) 
	                    dist[v] = dist[u] + graph[u][v]; 
	        } 
	  
	        // print the constructed distance array 
	        printSolution(dist, src, tar); 
	    } 
	
	
	
	public static void readFile(){
		BufferedReader reader;
		try {
			String encoding = "UTF-8"; 
			File file = new File("graphs/graph200_0.2/e.txt");
			if (file.isFile() && file.exists()) {       	// if the file exists
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
			
			//input the file name
			reader = new BufferedReader(new FileReader(file)); 
            String line = reader.readLine();
            line = reader.readLine();
			while (line != null) { 
				String[] num = line.split(",");
                graph[Integer.parseInt(num[0])][Integer.parseInt(num[1])]
                            = Integer.parseInt(num[2]);
                graph[Integer.parseInt(num[1])][Integer.parseInt(num[0])]
                        = Integer.parseInt(num[2]);
                line = reader.readLine();
			}
			read.close();
		}
	}
			catch (IOException e) {
	            e.printStackTrace();
	        }
}
	
	
	
	public static void main(String[] args){
		
		readFile();
		
		Scanner scan = new Scanner(System.in);
        System.out.println("Please input the source vertex: ");
        int srcV = scan.nextInt();
        System.out.println("Please input the source vertex: ");
        int tarV = scan.nextInt();
        
        
        Dijkstra d = new Dijkstra();
        d.dijkstra(graph, srcV, tarV);
      
        /*System.out.println("The shortest distacne is: " + 
                dist[src][tar]);
       */
        scan.close();
	}	
}
