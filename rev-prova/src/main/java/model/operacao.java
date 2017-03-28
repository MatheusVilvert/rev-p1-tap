package model;

public class operacao {
	
	 private double n1;
	 private double n2;
	String op;
	
	public operacao(double n1,double n2,String op){
		this.n1 = n1;
		this.n2 = n2;
		this.op = op;
	}

	public double getN1() {
		return n1;
	}

	public void setN1(double n1) {
		this.n1 = n1;
	}

	public double getN2() {
		return n2;
	}

	public void setN2(double n2) {
		this.n2 = n2;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
	
	
	
	
}
