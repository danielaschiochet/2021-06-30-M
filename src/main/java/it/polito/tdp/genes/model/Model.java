package it.polito.tdp.genes.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private List<Integer> cromosomi;
	private List<ChromosomesGenes> archi;
	
	public Model() {
		
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		dao = new GenesDao();
		cromosomi = dao.getAllChromosomes();
		
		archi = dao.getArchi();
		
	}
	
	public void creaGrafo() {
		
		for(Integer cr: cromosomi) {
			grafo.addVertex(cr);
		}
		
		for(ChromosomesGenes cg: archi) {
			grafo.addEdge(cg.getC1(), cg.getC2());
			grafo.setEdgeWeight(cg.getC1(), cg.getC2(), cg.getIc());
		}

	}
	
	public String getVertici() {
		return "Vertici: "+grafo.vertexSet().size();
	}
	
	public String getArchi() {
		return "Archi: "+grafo.edgeSet().size();
	}
	
	public Double getMinArchi() {
		double best = 1000000.0;
		for(ChromosomesGenes cg: archi) {
			if(cg.getIc()<best)
				best = cg.getIc();
		}
		
		return best;
	}
	
	public Double getMaxArchi() {
		double best = 0.0;
		for(ChromosomesGenes cg: archi) {
			if(cg.getIc()>best)
				best = cg.getIc();
		}
		
		return best;
	}
	
	public Integer getArchiMinori(double S) {
		int conta = 0;
		for(ChromosomesGenes cg: archi) {
			if(cg.getIc()<S)
				conta++;
		}
		
		return conta;
	}
	
	public Integer getArchiMaggiori(double S) {
		int conta = 0;
		for(ChromosomesGenes cg: archi) {
			if(cg.getIc()>S)
				conta++;
		}
		
		return conta;
	}

}