import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		PriorityQueue<Status> pq = new PriorityQueue<>();
		
		Status s = new Status(new int[][]{{0,1,2,3},{4,5,6,7},{8,9,10,11},{12,13,14,15}});
		//Status s = new Status(new int[][]{{0,1,2},{3,4,5},{6,7,8}});
		
		// Change this part to change the number of scrambled times
		s.scrumble(4000);
		s.print();
		pq.add(s);
		int counter = 0;
		//HashSet to avoid a same status from being added twice
		HashSet<Long> hs = new HashSet<Long>();
		Status fina = new Status(new int[][]{});
		while(pq.size() != 0) {
			counter++;
			Status cur = pq.poll();
			long tmp = cur.Hash();
			if(hs.contains(tmp)) continue;
			hs.add(tmp);
			if(cur.Calc() == 0) {
				System.out.println("After iterating through " + counter + " statues, we found the solution in " + cur.scoreG / 3 + " steps!");
				fina = cur;
				break;
			}
			for(int i = 0;i < 4;i++) {
				Status next = cur.move(i);
				if(next.scoreG == -1) continue;
				pq.add(next);
			}
		}
		fina.print();

		
		
	}


}
