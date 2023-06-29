package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.ChromosomesGenes;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}

	public List<Integer> getAllChromosomes(){
		String sql = "SELECT DISTINCT g.Chromosome "
				+ "FROM genes g "
				+ "HAVING g.Chromosome != 0";
		List<Integer> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(res.getInt("g.Chromosome"));
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
	public List<ChromosomesGenes> getArchi(){
		String sql = "SELECT c1, c2, SUM(ec) AS somma "
				+ "FROM ( "
				+ "SELECT g1.Chromosome AS c1, g2.Chromosome AS c2, g1.GeneID AS gi1, g2.GeneID AS gi2, i.Expression_Corr AS ec "
				+ "FROM genes g1, genes g2, interactions i "
				+ "WHERE g1.Chromosome!=g2.Chromosome AND g1.GeneID = i.GeneID1 AND g2.GeneID = i.GeneID2 AND g1.Chromosome!=0 AND g2.Chromosome!=0 "
				+ "GROUP BY g1.Chromosome, g2.Chromosome, g1.GeneID, g2.GeneID) AS tab "
				+ "GROUP BY c1, c2";
		List<ChromosomesGenes> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				ChromosomesGenes genes = new ChromosomesGenes(res.getInt("c1"), res.getInt("c2"), res.getDouble("somma"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
		
	}


	
}
