package class1;

import java.util.Comparator;
import java.util.PriorityQueue;

// leetcode problem 502
public class Problem_13_IPO {

	public static class Project {
		public int p;
		public int c;

		public Project(int profit, int capital) {
			p = profit;
			c = capital;
		}
	}

	public static class CapUp implements Comparator<Project> {

		@Override
		public int compare(Project o1, Project o2) {
			return o1.c - o2.c;
		}

	}

	public static class ProDown implements Comparator<Project> {

		@Override
		public int compare(Project o1, Project o2) {
			return o2.p - o1.p;
		}

	}

	public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
		if (K < 1 || W < 0 || Profits == null || Profits.length == 0) {
			return 0;
		}
		PriorityQueue<Project> cUpq = new PriorityQueue<Project>(new CapUp());
		PriorityQueue<Project> pDownq = new PriorityQueue<Project>(new ProDown());
		for (int i = 0; i < Profits.length; i++) {
			cUpq.add(new Project(Profits[i], Capital[i]));
		}
		while (K-- != 0) {
			while (!cUpq.isEmpty() && cUpq.peek().c <= W) {
				pDownq.add(cUpq.poll());
			}
			if (pDownq.isEmpty()) {
				break;
			}
			W += pDownq.poll().p;
		}
		return W;
	}

}
