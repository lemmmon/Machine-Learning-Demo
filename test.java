package toolsMadeByMyself;

public class test {
	public static void main(String[] args) {
		// KNN
		// double[][] a = { { 0.0, 0.0 }, { 1.0, 0.0 }, { 9.0, 8.0 }, { 10.0, 10.0 }, {
		// 1, 10 }, { 3, 10 }, { 9, 1 },
		// { 8, 1 }, { 8, 2 } };
		// int[] label = { 1, 1, 3, 3, 4, 4, 2, 2, 2 };
		// double[][] test = { {8, 9.8},{1,1}};
		// KNN knn = new KNN(a, label, 4);
		// int[] j = knn.predict(test);
		// for(int i = 0;i<j.length;i++) {
		// System.out.println("The "+(i+1)+" test sample: "+j[i]);
		// }

		// K-means
		double[][] a = { { 0.0, 0.0 }, { 1.0, 0.0 }, { 9.0, 8.0 }, { 10.0, 10.0 }, { 1, 10 }, { 3, 10 }, { 9, 1 },
				{ 8, 1 }, { 8, 2 } };
		KMeans kMeans = new KMeans(a, 4, 1000);
		double[][] c = kMeans.start();
		System.out.println("size"+c.length+"  "+c[0].length);
		for(int i = 0;i<c.length;i++)
			for(int j = 0;j<c[0].length;j++)
				System.out.println(c[i][j]);

	}
}
