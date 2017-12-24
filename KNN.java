package toolsMadeByMyself;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class KNN {
	double[][] TM;
	int[] lb;
	int k;

	public KNN(double[][] TrainMatrix, int[] label, int K) { // 
		if (K > TrainMatrix.length) {
			throw new IllegalArgumentException("K is too large");
		}

		this.TM = TrainMatrix;
		this.lb = label;
		this.k = K;
	}

	public KNN() {

	}
	
	public int predict(double[] input) {
		if (input.length != TM[0].length)
			throw new IllegalArgumentException("ERROR: Length of input is wrong");
		ArrayList<Node> list = new ArrayList<Node>();
		for (int i = 0; i < TM.length; i++)
			list.add(new Node(lb[i], Distance(input, TM[i])));
		Comparator<Node> c = new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.val > o2.val)
					return 1;
				else
					return -1;
			}
		};
		list.sort(c); // sort by value
		return Vote(list);
	}

	// Overloading
	public int[] predict(double[][] input) {
		int[] r = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			r[i] = predict(input[i]);
		}
		return r;
	}

	public double Distance(double[] a, double[] b) {
		if (a.length != b.length)
			throw new IllegalArgumentException("Length of feature is different");
		double sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += (a[i] - b[i]) * (a[i] - b[i]);
		}
		return (double) Math.sqrt(sum);
	}

	public int Vote(ArrayList<Node> a) {
		int[] NumKey = new int[k];
		for (int i = 0; i < k; i++) {
			NumKey[i] = a.get(i).getKey();
			// System.out.println(a.get(i).key);
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < NumKey.length; i++) {
			if (map.containsKey(NumKey[i])) {
				int v = map.get(NumKey[i]);
				v += 1;
				map.put(NumKey[i], v);
			} else {
				map.put(NumKey[i], 1);
			}
		}
		Iterator<Integer> iterator = map.keySet().iterator();
		int maxValue = 0;
		int maxNumber = 0;
		while (iterator.hasNext()) {
			int key = (Integer) iterator.next();
			int Value = (Integer) map.get(key);
			if (Value > maxNumber) {
				maxNumber = Value;
				maxValue = key;
			}
		}
		return maxValue;
	}
}
