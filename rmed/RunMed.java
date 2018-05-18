//Victor Lin
//APCS2 pd01
//HW50 Run Run Run
//2018-05-18

/***
	1) if next val < root of MAX --> add to MAX
	   else add to MIN
	2) if size diff < 2 --> heaps are balanced
	   else remove from larger heap and add to smaller until balanced
	3) find med by 
			a) heap sizes == : median = mean of roots
			b) heap sizes != : median = root of larger heap
***/

import java.util.NoSuchElementException;

public class RunMed{
	private ALHeapMin lilVals;
	private ALHeapMax bigVals;

	public RunMed(){
		lilVals = new ALHeapMin();
		bigVals = new ALHeapMax();
	}

	public double getMedian(){
		if(lilVals.isEmpty()&&bigVals.isEmpty())
			throw new NoSuchElementException();
		else if(lilVals.size()==bigVals.size())
			return (bigVals.peek().intValue() + lilVals.peek().intValue())/2.0;
		else{
			if(bigVals.size()>lilVals.size())
				return bigVals.peek().intValue();
			else
				return lilVals.peek().intValue();
		}
	}

	public void add(Integer newVal){
		if(lilVals.isEmpty())
			lilVals.add(newVal);
		else if(bigVals.isEmpty())
			bigVals.add(newVal);
		else if(newVal < bigVals.peek())
			bigVals.add(newVal);
		else
			lilVals.add(newVal);
		balance();
	}

	public void balance(){
		while(Math.abs(bigVals.size()-lilVals.size())>=2){
			if(lilVals.size()<=bigVals.size()-2)
				lilVals.add(bigVals.remove());
			else if(lilVals.size()-2>=bigVals.size())
				bigVals.add(lilVals.remove());
		}
	}

	public static void main(String []args){
		RunMed temp = new RunMed();
		for(int i=1;i<100;i++){
			temp.add(new Integer(i));
			System.out.println(temp.getMedian());
		}
	}
}