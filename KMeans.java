package toolsMadeByMyself;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * @Project: Implement Machine Learning Algorithm
 * @Author: Hao Ying
 * @Date: 2017/12/24
 * @Copyright: All rights reserved.
 */

public class KMeans {
	double[][] TM;
	int k;
	double[][] center; //
	int irT;
	int[] label;

	public KMeans(double[][] TrainMatrix, int K, int iterTimes) {
		this.TM = TrainMatrix;
		this.k = K;
		this.irT = iterTimes;
		this.center = new double[k][TM[0].length];
		this.label = new int[TM.length];
		init_center();
	}

	public KMeans() {

	}

	public void init_center() { // initialize center
		Random random = new Random();
		for (int i = 0; i < center.length; i++)
			for (int j = 0; j < center[0].length; j++)
				center[i][j] = random.nextDouble();
	}

	public double[][] start() {
		int count = 1;
		ArrayList<ArrayList<Node>> list = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < k; i++)
			list.add(new ArrayList<>());
		while (count <= irT) {
			HashSet<Integer> set = new HashSet<Integer>();
			for (int i = 0; i < TM.length; i++) { // judge class and mark with 0 ~ k-1
				// label[i] = jdgClass(TM[i]);
				list.get(jdgClass(TM[i])).add(new Node(TM[i]));
				set.add(jdgClass(TM[i]));
			}
			if (set.size() != k)
				throw new IllegalArgumentException("Fail to initial. Please restart");
			getListMeans(list);
			count++;
		}
		return center;
	}

	public int jdgClass(double[] input) {
		double currentDist = Distance(center[0], input);
		int currentClass = 0;
		for (int i = 0; i < k; i++) {
			double dist = Distance(center[i], input);
			if (dist < currentDist) {
				currentClass = i;
				currentDist = dist;
			}
		}
		return currentClass;
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

	public void getListMeans(ArrayList<ArrayList<Node>> list) {
		for (int i = 0; i < list.size(); i++) { // i th feature
			ArrayList<Node> syxlist = list.get(i); //
			double[][] syxnum = new double[syxlist.size()][center[0].length];
			for (int j = 0; j < syxlist.size(); j++) // change the feature to 2D array
				syxnum[j] = syxlist.get(j).getFeature();
			for (int l = 0; l < syxnum[0].length; l++) {
				double sum = 0;
				for (int m = 0; m < syxnum.length; m++) {
					sum += syxnum[m][l];
				}
				center[i][l] = sum / syxlist.size();
			}
		}
	}

}
