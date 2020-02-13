package gwu.zqy.main.Astar;

import java.util.Set;

public class Vertex {
  public int name;
  public int g;
  public int f;
  
  //
  public Set<Integer>parents;
  
  public Vertex(int name, int fLen, int gLen, Set<Integer> parents) {
   this.name=name;
   this.g=gLen;
   this.f=fLen;
   this.parents=parents;  
  }
  
  
  
  /*
  public int getName() {
   return name;
  }
  public void setName(int name) {
   this.name = name;
  }
  
  public int getG() {
   return g;
  }
  public void setG(int g) {
   this.g = g;
  }
  
  public int getF() {
   return f;
  }
  public void setF(int f) {
   this.f = f;
  }
  
  public Set<Integer> getParents() {
   return parents;
  }
  public void setParents(Set<Integer> parents) {
   this.parents = parents;
  }
  */
}