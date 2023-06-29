package it.polito.tdp.genes.model;

import java.util.Objects;

public class ChromosomesGenes {
	
	private int c1;
	private int c2;
	private double ic;
	
	public ChromosomesGenes(int c1, int c2, double ic) {
		super();
		this.c1 = c1;
		this.c2 = c2;
		this.ic = ic;
	}
	public int getC1() {
		return c1;
	}
	public void setC1(int c1) {
		this.c1 = c1;
	}
	public int getC2() {
		return c2;
	}
	public void setC2(int c2) {
		this.c2 = c2;
	}
	public double getIc() {
		return ic;
	}
	public void setIc(double ic) {
		this.ic = ic;
	}
	@Override
	public int hashCode() {
		return Objects.hash(c1, c2, ic);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChromosomesGenes other = (ChromosomesGenes) obj;
		return c1 == other.c1 && c2 == other.c2	&& Double.doubleToLongBits(ic) == Double.doubleToLongBits(other.ic);
	}
	
	
	
	
	

}
