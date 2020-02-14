package gwu.zqy.main.Astar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

// f(n) = g(n) + h(n)
// H(n) = max { abs(current_cell.x ¨C goal.x), abs(current_cell.y ¨C goal.y) } 


public class Astar {
 
	  public static int INF = Integer.MAX_VALUE;
	  public static int squareSize = 10;
	  public static int[][] vertices = new int[2000][10];
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
	   
			   //find the optimal point
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
			   String str1 = null;
			   int vertexCount = 0;
			   while((str1 = bufferedReader1.readLine()) != null) {
			    //read Square from the graph
			    int i = Integer.parseInt(str1.split(",")[1]); 
			    vertices[vertexCount][0] = i;
			    vertexCount++;
			   }
			   
			   for(int m=0; m<edges.length; m++) {
			     for(int n=0; n<500; n++) {
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
    
			    File file2 =new File("graphs/graph200_0.1/e.txt");
			    FileInputStream inputStream2 = new FileInputStream(file2);
			    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2));
			    String str2 = null;
			    while((str2 = bufferedReader2.readLine()) != null) {
			     //read from Index, to Index, pathCost from graph
			     int i = Integer.parseInt(str2.split(",")[0]);
			     int j = Integer.parseInt(str2.split(",")[1]);
			     int dist = Integer.parseInt(str2.split(",")[2]);
			     edges[i][j]=dist;
			     edges[j][i]=dist;
			    }
			    inputStream2.close();
			    bufferedReader2.close();
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
			   System.out.println("Please input the source vertex: ");
			   int target = in.nextInt();
			   
			   int result = aStar(source, target, vertices, edges);
			   System.out.println("The Shortest Path is: " + result);
			   
  }
}
