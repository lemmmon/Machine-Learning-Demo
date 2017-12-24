package toolsMadeByMyself;

public class Node {
	int key;	// class
	double val;	//value
	double[] feature;
	
	public Node(int key, double val) {
		this.key = key;
		this.val = val;
	}
	
	public Node(double[] feat) {
		this.feature = feat;
	}
	
	public double[] getFeature() {
		return feature;
	}
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public double getVal() {
		return val;
	}
	public void setVal(double val) {
		this.val = val;
	}
	

}
