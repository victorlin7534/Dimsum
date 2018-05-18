import java.util.ArrayList;

public class ALHeapMax
{

  //instance vars
  private ArrayList<Integer> _heap; 
  private int _size;

  /*****************************************************
   * default constructor  ---  inits empty heap
   *****************************************************/
  public ALHeapMax() 
  { 
    _heap = new ArrayList<Integer>();
    _size = 0;
  }

  public int size(){
    return _size;
  }

  /*****************************************************
   * toString()  ---  overrides inherited method
   * Returns either 
   * a) a level-order traversal of the tree (simple version)
   * b) ASCII representation of the tree (more complicated, more fun)
   *****************************************************/
  public String toString() 
  { 
    String str = "";
    int level = 0;
    for(int i=0;i<_heap.size();i++){
      if(i+1 == Math.pow(2,level)){
        str += "\n";
        level++;
      }
      str += _heap.get(i) + " ";
    }
    return str;
  }//O(n)

  public String makeSpace(int num){
    String temp = "";
    for(int i=0;i<num;i++)
      temp+=" ";
    return temp;
  }


  /*****************************************************
   * boolean isEmpty()
   * Returns true if no meaningful elements in heap, false otherwise
   *****************************************************/
  public boolean isEmpty()
  { 
    return _size==0;
  }//O(1)


  /*****************************************************
   * Integer peekMin()
   * Returns min value in heap
   * Postcondition: Heap remains unchanged.
   *****************************************************/
  public Integer peek()
  { 
    return _heap.get(0);
  }//O(1)


  /*****************************************************
   * add(Integer) 
   * Inserts an element in the heap
   * Postcondition: Tree exhibits heap property.
   * Algo: 1) add to end of list
   *       2) if parent (floor of n-1/2) > addVal --> swap
   *       3) swap until addVal is in position where is greater 
   *          than everything below and less than its parent
   *****************************************************/
  public void add( Integer addVal )
  { 
    _heap.add(addVal);
    for(int i=_heap.size()-1;i>0;i=(int)Math.floor((i-1)/2)){
      if(_heap.get(i).compareTo(_heap.get((int)Math.floor((i-1)/2)))>0)
        swap(i,(int)Math.floor((i-1)/2));
    }
    _size++;
  }//O(logn)

  /*****************************************************
   * removeMin()  ---  means of removing an element from heap
   * Removes and returns least element in heap.
   * Postcondition: Tree maintains heap property.
   * Algo: 1) shift all the smaller(left vs right child) values up a level
              this is done by swapping min val and smallest child in each 
              successive level
           2) minVal is guaranteed to be at the bottom level where it is removed
   *****************************************************/
  public Integer remove()
  { 
    if(isEmpty())
      return null;
    int pos = 0;
    while(pos < _heap.size()){
      int temp = maxChildPos(pos);
      if(temp==-1)
        break;
      swap(pos,temp);
      pos = temp;
    }
    _size--;
    return _heap.remove(pos);
  }//O(logn)


  /*****************************************************
   * minChildPos(int)  ---  helper fxn for removeMin()
   * Returns index of least child, or 
   * -1 if no children, or if input pos is not in ArrayList
   * Postcondition: Tree unchanged
   *****************************************************/
  private int maxChildPos( int pos )
  { 
    if(_heap.size()-1<pos||hasChild(pos))
      return -1;
    if(_heap.get(pos*2+1)>_heap.get(pos*2+2))
      return pos*2+1;
    return pos*2+2;
  }//O(1)
  
  private boolean hasChild(int pos){
    return _heap.size()-1 < pos*2+1 || pos*2+2 > _heap.size()-1;
  }

  //************ aux helper fxns ***************
  private Integer maxOf( Integer a, Integer b )
  {
    if ( a.compareTo(b) < 0 )
      return b;
    else
      return a;
  }

  //swap for an ArrayList
  private void swap( int pos1, int pos2 )
  {
    _heap.set( pos1, _heap.set( pos2, _heap.get(pos1) ) );  
  }
  //********************************************
}//end class ALHeapMax
