import java.util.ArrayList;

public class ArrayPriorityQueue implements PriorityQueue{
    private ArrayList<String> pq;

    public ArrayPriorityQueue(){
		pq=new ArrayList<String>();
    }
    public void add(String s){
    	pq.add(s);
    }//O(1)

    public boolean isEmpty(){//O(1)
		return pq.size()==0;
    }

    //if pq is empty, return null
    public String peekMin(){//O(n)
		if (isEmpty())
			return null;
		String smallest = pq.get(0);
		for(String x:pq){
			if(x.compareTo(smallest)<0)
				smallest = x;
		}
		return smallest;
    }

    public String removeMin(){//O(n)
		if (isEmpty())
			return null;
		int index = 0;
		for(int i=0;i<pq.size();i++){
			if(pq.get(i).compareTo(pq.get(index))<0)
				index = i;
		}
		return pq.remove(index);
    }

    public static void main(String[] args){
		ArrayPriorityQueue apq = new ArrayPriorityQueue();

		apq.add("aoo");
		apq.add("Aoo");
		apq.add("aooo");// A, a, ao

		System.out.println(apq.removeMin());//Aoo
		System.out.println(apq.removeMin());//aoo
		System.out.println(apq.removeMin());//aooo
		System.out.println(apq.removeMin());//null

		apq.add("goo");
		apq.add("aoo");
		System.out.println(apq.removeMin());//aoo
		apq.add("zoo");
		System.out.println(apq.removeMin());//goo
		System.out.println(apq.removeMin());//zoo
		for (int i = 0; i<12; i++){
		    apq.add(i+"oo");
		}
		while (!apq.isEmpty()){
		    System.out.println(apq.removeMin());
		}
    }
}
