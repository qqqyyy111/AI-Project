package gwu.zqy.main.Astar;

import java.io.*;
import java.util.*;

// f(n) = g(n) + h(n)
// H(n) = max { abs(current_cell.x – goal.x), abs(current_cell.y – goal.y) } 


public class Astar {
 
	  public static int INF = Integer.MAX_VALUE;
	  public static int squareSize = 10;
	  public static int[][] vertices = new int[2000][3];
	  public static int[][] edges = new int[2000][2000];
	  
	public static int aStar(int source, int target, int[][] vertices, int[][] edges) {
	    /*
	     *Ascending
	     *return o1-o2;
	     *Descending
	     *return o2-o1;
	     */   
			   int result = 0;
			   PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(new Comparator<Vertex>(){
				   public int compare(Vertex v1, Vertex v2) {
			       return v1.f - v2.f;
			      }
			   });
	   
			   //find the optimal point from 
			   Set<Integer> parent = new HashSet<Integer>();
			   parent.add(source);
			   Vertex ver = new Vertex(source, HDist(source, target, vertices)+0, 0, parent);
			   queue.add(ver);
    
		       //stop when dequeue a goal
		       while(queue.poll().name != target) {
		       for(int i=0; i<vertices.length; i++) {
		       //find all connecting nodes which is not repeated
		       if (edges[source][i] !=0 && edges[source][i] != INF && !parent.contains(i)) {
		       int dist = edges[source][i];
		       int h = HDist(i, target, vertices);
		       int g = dist + ver.g;
		       Set<Integer> parents = new HashSet<Integer>();
		       parents.addAll(parent);
		       parents.add(i);
		       Vertex ver1 = new Vertex(i, g+h, g, parents);
		       queue.add(ver1);
		       }
		   }
		       ver = queue.peek();
		       source = ver.name;
		       result = ver.g;
		       parent = ver.parents;
	}
		   return result;
}

	
  	public static void readFile1() throws IOException, FileNotFoundException {
  
	   	    File file1 = new File("graphs/graph200_0.1/v.txt");
	  	    FileInputStream inputStream1 = new FileInputStream(file1);
		    BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1));
		  	    	  	
		    FileReader fileReader = new FileReader(file1); 	          
  	            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
  	            lineNumberReader.skip(Long.MAX_VALUE);
  	            long lines = lineNumberReader.getLineNumber() + 1;
  	            //System.out.print(lines);
  	            lineNumberReader.close();		
		  	            
  	            String str1 = null;
	            int vertexCount = 0;
	            while((str1 = bufferedReader1.readLine()) != null) {
			    //read Square from the graph
	            String[] num = str1.split(",");
	            while(vertexCount < lines & vertexCount > 0){
	            	vertices[vertexCount][0] = Integer.parseInt(num[1]);
	            	vertexCount ++;
	            }   
	            
}
	            for(int m=0; m<edges.length; m++) {
				    for(int n=0; n<20; n++) {
					   if(m != n)
						   edges[m][n] = INF;
					   else
						   edges[m][n] = 0;
				   	}
	}       	
			inputStream1.close();
			bufferedReader1.close();
		  	            
}

  	public static void readFile2() throws NumberFormatException, IOException {
  				
	  		BufferedReader reader;
			try {
				String encoding = "UTF-8"; 
				File file2 = new File("graphs/graph200_0.1/e.txt");
				if (file2.isFile() && file2.exists()) {       	// if the file exists
				InputStreamReader read = new InputStreamReader(new FileInputStream(file2), encoding);
				
				//input the file name
				reader = new BufferedReader(new FileReader(file2)); 
	            String line = reader.readLine();
	            line = reader.readLine();
				while (line != null) { 
					String[] num = line.split(",");
					
	                edges[Integer.parseInt(num[0])][Integer.parseInt(num[1])]
	                            = Integer.parseInt(num[2]);
	                edges[Integer.parseInt(num[1])][Integer.parseInt(num[0])]
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
			 

  	public static int HDist(int source, int target, int[][] vertices){
	  	
	  		return squareSize * Math.max(Math.abs(vertices[source][0] - vertices[target][0]), 
	  		Math.abs(vertices[source][1] - vertices[target][1]));
}
			
			
  	public static void main(String[] args) throws FileNotFoundException, IOException{
			   readFile1();
			   readFile2();
			   
			   Scanner in = new Scanner(System.in);
			   System.out.println("Please input the source vertex: ");
			   int source = in.nextInt();
			   System.out.println("Please input the target vertex: ");
			   int target = in.nextInt();
			   
			   int result = aStar(source, target, vertices, edges);
			   System.out.println("The Shortest Path is: " + result);
			   
  }
}
