package com.liang.quality;

import com.liang.main.MutableNodeSet;
import com.liang.main.NodeSet;
import com.liang.graph.Graph;

/**
 * Calculates the cohesiveness measure of a nodeset.
 * 
 * The cohesiveness of a {@link com.liang.main.NodeSet} is defined as the total internal weight
 * of the nodeset divided by the sum of the total internal and boundary weight
 * of the nodeset.
 * 
 * Optionally, the user may also specify a penalty value for each node. If the
 * penalty is nonzero, it is assumed that each node in the nodeset (and also the
 * ones to be added) has an extra boundary weight equal to the value of the
 * penalty. This can be used to account for situations when the dataset is noisy;
 * in this case, the fact that an external boundary node of a nodeset has a single
 * boundary edge towards the nodeset and no other (completely external) edges does
 * not necessarily mean that the node should belong to the nodeset; it may happen
 * that the node has more external connections, which are not present in the dataset
 * due to noise. Therefore, the addition of such nodes is feasible only if the
 * connections to the nodeset are strong enough to counterbalance the effect of the
 * penalty.
 * 
 * @author tamas
 */
public class CohesivenessFunction implements QualityFunction {
	/**
	 * Penalty value associated with each node.
	 */
	double penalty = 0.0;
	
	/**
	 * Constructs a new cohesiveness function instance with no penalty.
	 */
	public CohesivenessFunction() {
		this(0.0);
	}
	
	/**
	 * Constructs a new cohesiveness function instance.
	 * 
	 * @param  penalty  the penalty value associated with each internal node.
	 */
	public CohesivenessFunction(double penalty) {
		this.penalty = penalty;
	}
	
	/**
	 * Calculates the cohesiveness of a nodeset.
	 * 
	 * @param   NodeSet  the nodeset for which we need the cohesiveness
	 * @return  the cohesiveness
	 */

	public double logHyperProbability(double a,double b,double c,double d)
	{
		double logH = logC(a,b) - logC(c-a,d-b) - logC(c,d);
		return logH/Math.log(10);
	}

	public double logC(double a,double b)
	{
		if(a == b || b == 0)
		{
			return 0;
		}
		else if(a >= 1000 && b >= 1000)
		{
			return a * Math.log(a) - (a-b) * Math.log(a-b) -b * Math.log(b);
		}
		else
		{
			return Graph.factorial[(int)a] - Graph.factorial[(int)b] - Graph.factorial[(int)(a-b)];
		}
	}
	public double sumLog(double nextP,double P){
		double common;
		double diffExponent;
		if(nextP > P){
			common = nextP;
			diffExponent = nextP - P;
		}
		else{
			common = P;
			diffExponent = P - nextP;
		}
		double result = common + ((Math.log(1+Math.pow(10,diffExponent)))) / Math.log(10);
		return result;
	}


//	//surprise
//    public double calculate(NodeSet nodeSet)
//	{
//		double graphNodes = Graph.nodesNumber;
//		double graphEdges = Graph.edgesNumber;
//		double max_graphEdges =((graphNodes)*(graphNodes-1))/2;
//		double nodesetNodes = nodeSet.size();
//		double max_nodesetEdges = ((nodesetNodes)*(nodesetNodes-1))/2;
//		double up = Math.min(graphEdges,max_nodesetEdges);
//		double nodesetEdges = nodeSet.totalInternalEdgeWeight;
//		double temp = 0;
//		double result = 0;
//		int j = (int)nodesetEdges;
//		temp = logHyperProbability(max_nodesetEdges,j,max_graphEdges,graphEdges);
//		result = temp;
//		System.out.println("result = graphNodes " + graphNodes + " graphEdges " + graphEdges +
//				" nodesetNodes " + nodesetNodes + " nodesetEdges " + nodesetEdges + " up " + up +
//				" max_graphEdges " + max_graphEdges + " max_nodesetEdges " + max_nodesetEdges);
//		while(j < up)
//		{
//			j++;
//			//if(result - logHyperProbability(max_nodesetEdges,j,max_graphEdges,graphEdges) < 4) break;
//			result = sumLog(result,logHyperProbability(max_nodesetEdges,j,max_graphEdges,graphEdges));
//			//System.out.println(result + " j="+j);
//
//		}
//		return result;
//	}



	// 2017.0526
	public double calculate(NodeSet nodeSet)
	{
		double Da,Ds,Dg; // 图的度和 子图的度和 剩余度和
		Ds = nodeSet.totalInternalEdgeWeight * 2 + nodeSet.totalBoundaryEdgeWeight;
		Da = Graph.total_degree;
		Dg = Graph.total_degree - Ds;
		double m_out = nodeSet.totalBoundaryEdgeWeight;
		double m_in = nodeSet.totalInternalEdgeWeight;
		double graphNodesNumber = Graph.nodesNumber;
		double nodesetNodesNumber = nodeSet.size();
		System.out.println("out="+m_out+" in=" +m_in + " D=" + Da + " Ds=" + Ds);
		double item1 = Graph.factorial[(int)Ds] - ( Graph.factorial[(int)(m_in * 2)] + Graph.factorial[(int)(Ds - 2 * m_in)] );
		double item2 = Graph.factorial[(int)Dg] - ( Graph.factorial[(int)m_out] + Graph.factorial[(int) (Dg-m_out)] );
		double item3 = Graph.factorial[(int)Da] - ( Graph.factorial[(int)Ds] + Graph.factorial[(int)(Da-Ds)] );
		//System.out.println("item1 = " + item1 + "item2 = " +item2 + "item3 = " + item3 + "   " + Math.exp(item1+item2));
		// fisher 求和
		double result = 0;
//		int nodeNumber = nodeSet.size();
//		for(int j = (int)m_in; j <=(Ds)/2;j++)
//		{
//			int i = 0;
//			i = j*2;
//			double temp1 = Graph.factorial[(int)Ds] - ( Graph.factorial[i] + Graph.factorial[(int)(Ds - i)] );
//			double temp2 = Graph.factorial[(int)Dg] - ( Graph.factorial[(int)(Ds-i)] + Graph.factorial[(int) (Dg-Ds+i)] );
//			double temp3 = Graph.factorial[(int)Da] - ( Graph.factorial[(int)Ds] + Graph.factorial[(int)(Da-Ds)] );
//		//	System.out.println("temp1 = " + temp1 + "temp2 = " +temp2 + "temp3 = " + temp3 + "  " + Math.exp(temp1+temp2-temp3));
//			result = result + Math.exp(temp1+temp2-temp3+500);
//		//	System.out.println("result+=" + result);
//		}
		//System.out.println();
		result = Math.exp(item1+item2-item3);
		double q1 = (m_out*m_out)/((2*m_in)*(Dg-m_out));
		double upperbound = (1-Math.pow(q1,m_out+1))/(1-q1);
		result = result*upperbound;
		System.out.println("item1 = " + item1 + " item2 = " +item2 + " item3 = " + item3 + "  upper = " + upperbound );
		System.out.println("calculate = "  + result);
		return item1+item2-item3+Math.log(upperbound);
	}


	
	/**
	 * Returns the addition affinity of a node to a mutable nodeset
	 * 
	 * The addition affinity of an external node is defined as the value of the
	 * quality function when the nodeset is augmented by the given node.
	 * 
	 * @param   nodeSet     the nodeset being checked
	 * @param   index   the index of the node being added to the nodeset
	 * @precondition   the node is not in the set
	 */

//	//surprise
//	public double getAdditionAffinity( MutableNodeSet nodeSet, int index)
//	{
//		double graphNodes = Graph.nodesNumber;
//		double graphEdges = Graph.edgesNumber;
//		double max_graphEdges =((graphNodes)*(graphNodes-1))/2;
//		double nodesetNodes = nodeSet.size()+1;
//		double max_nodesetEdges = ((nodesetNodes)*(nodesetNodes-1))/2;
//		double up = Math.min(graphEdges,max_nodesetEdges);
//		double nodesetEdges = nodeSet.totalInternalEdgeWeight+nodeSet.inWeights[index];
//		double temp = 0;
//		double result = 0;
//		int j = (int)nodesetEdges;
//		temp = logHyperProbability(max_nodesetEdges,j,max_graphEdges,graphEdges);
//		result = temp;
//		while( j < up)
//		{
//			j++;
//			//if(result - logHyperProbability(max_nodesetEdges,j,max_graphEdges,graphEdges) < 4) break;
//			result = sumLog(result,logHyperProbability(max_nodesetEdges,j,max_graphEdges,graphEdges));
//		}
//		return result;
//	}


	 // 2017.0526
	public double getAdditionAffinity( MutableNodeSet nodeSet, int index)
	{
		double Da,Ds,Dg;
		Da = Graph.total_degree;
		Ds = nodeSet.totalInternalEdgeWeight * 2+ nodeSet.totalBoundaryEdgeWeight + nodeSet.totalWeights[index];
		Dg = Da - Ds;
		double m_out = nodeSet.totalBoundaryEdgeWeight - nodeSet.inWeights[index] + (nodeSet.totalWeights[index] - nodeSet.inWeights[index]);
		double m_in = nodeSet.totalInternalEdgeWeight + nodeSet.inWeights[index];
		double graphNodesNumber = Graph.nodesNumber;
		double nodesetNodesNumber = nodeSet.size();
		double item1 = Graph.factorial[(int)Ds] - ( Graph.factorial[(int)(m_in * 2)] + Graph.factorial[(int)(Ds - 2 * m_in)] );
		double item2 = Graph.factorial[(int)Dg] - ( Graph.factorial[(int)m_out] + Graph.factorial[(int) (Dg-m_out)] );
		double item3 = Graph.factorial[(int)Da] - ( Graph.factorial[(int)Ds] + Graph.factorial[(int)(Da-Ds)] );
		// fisher 求和
		double result = 0;
//		int nodeNumber = nodeSet.size();
//		int i;
//		for(int j = (int)m_in; j <=(Ds)/2;j++)
//		{
//			i = j*2;
//			double temp1 = Graph.factorial[(int)Ds] - ( Graph.factorial[i] + Graph.factorial[(int)(Ds - i)] );
//			double temp2 = Graph.factorial[(int)Dg] - ( Graph.factorial[(int)(Ds-i)] + Graph.factorial[(int) (Dg-Ds+i)] );
//			double temp3 = Graph.factorial[(int)Da] - ( Graph.factorial[(int)Ds] + Graph.factorial[(int)(Da-Ds)] );
//			result = result + Math.exp(temp1+temp2-temp3+500);
//		}

		result = Math.exp(item1+item2-item3);
		double q1 = (m_out*m_out)/((2*m_in)*(Dg-m_out));
		double upperbound = (1-Math.pow(q1,m_out))/(1-q1);
		result = result*upperbound;
		//System.out.println(2*m_in + "   " + Ds + "   " + "   " + Dg);
		//System.out.println("add = "  + result);
		return item1+item2-item3+Math.log(upperbound);
		//return result;

		//System.out.println(item1 + "   " + item2 + "   " + item3);
		//System.out.println("add = "  + Math.exp(item1+item2-item3));
		//return Math.exp(item1+item2-item3+500);
	//	return Math.exp(item1+item2-item3);
	}

	
	/**
	 * Returns the removal affinity of a node to this nodeset
	 * 
	 * The removal affinity of an internal node is defined as the value of the quality
	 * function when the node is removed from the nodeset.
	 * 
	 * @param   nodeSet     the nodeset being checked
	 * @param   index   the index of the node
	 * @precondition    the node is already in the set
	 */
//	//surprise
//	public double getRemovalAffinity( MutableNodeSet nodeSet, int index)
//	{
//		double graphNodes = Graph.nodesNumber;
//		double graphEdges = Graph.edgesNumber;
//		double max_graphEdges =((graphNodes)*(graphNodes-1))/2;
//		double nodesetNodes = nodeSet.size()-1;
//		double max_nodesetEdges = ((nodesetNodes)*(nodesetNodes-1))/2;
//		double up = Math.min(graphEdges,max_nodesetEdges);
//		double nodesetEdges = nodeSet.totalInternalEdgeWeight-nodeSet.inWeights[index];
//		double temp = 0;
//		double result = 0;
//		int j = (int)nodesetEdges;
//		temp = logHyperProbability(max_nodesetEdges,j,max_graphEdges,graphEdges);
//		result = temp;
//		while( j < up)
//		{
//			j++;
//			//if(result - logHyperProbability(max_nodesetEdges,j,max_graphEdges,graphEdges) < 4) break;
//			result = sumLog(result,logHyperProbability(max_nodesetEdges,j,max_graphEdges,graphEdges));
//		}
//		return result;
//	}


	// 2017.0526
	public double getRemovalAffinity(MutableNodeSet nodeSet, int index)
	{
		double Da,Ds,Dg;
		Da = Graph.total_degree;
		Ds = nodeSet.totalInternalEdgeWeight * 2 + nodeSet.totalBoundaryEdgeWeight - nodeSet.totalWeights[index];
		Dg = Da - Ds;
		double m_out = nodeSet.totalBoundaryEdgeWeight -(nodeSet.totalWeights[index] - nodeSet.inWeights[index]) + nodeSet.inWeights[index];
		double m_in = nodeSet.totalInternalEdgeWeight - nodeSet.inWeights[index];
		double graphNodesNumber = Graph.nodesNumber;
		double nodesetNodesNumber = nodeSet.size();
		double item4 = Graph.factorial[(int)graphNodesNumber] - (Graph.factorial[(int)nodesetNodesNumber] + Graph.factorial[(int)(graphNodesNumber-nodesetNodesNumber)]);
		//System.out.println("out="+m_out+" in=" +m_in + " D=" + Da + " Ds=" + Ds);
		double item1 = Graph.factorial[(int)Ds] - ( Graph.factorial[(int)(m_in * 2)] + Graph.factorial[(int)(Ds - 2 * m_in)] );
		double item2 = Graph.factorial[(int)Dg] - ( Graph.factorial[(int)m_out] + Graph.factorial[(int) (Dg-m_out)] );
		double item3 = Graph.factorial[(int)Da] - ( Graph.factorial[(int)Ds] + Graph.factorial[(int)(Da-Ds)] );

		// fisher 求和
		double result = 0;
//		int nodeNumber = nodeSet.size();
//		int i;
//		for(int j = (int)m_in; j <=(Ds)/2;j++)
//		{
//			i = j*2;
//			double temp1 = Graph.factorial[(int)Ds] - ( Graph.factorial[i] + Graph.factorial[(int)(Ds - i)] );
//			double temp2 = Graph.factorial[(int)Dg] - ( Graph.factorial[(int)(Ds-i)] + Graph.factorial[(int) (Dg-Ds+i)] );
//			double temp3 = Graph.factorial[(int)Da] - ( Graph.factorial[(int)Ds] + Graph.factorial[(int)(Da-Ds)] );
//			result = result + Math.exp(temp1+temp2-temp3+500);
//		}

		result = Math.exp(item1+item2-item3);
		//System.out.println(item1 + "   " + item2 + "   " + item3);
		double q1 = (m_out*m_out)/((2*m_in)*(Dg-m_out));
		double upperbound = (1-Math.pow(q1,m_out))/(1-q1);
		result = result*upperbound;
		//System.out.println(2*m_in + "   " + Ds + "   " + "   " + Dg);
		//System.out.println("calculate = "  + result);
		return item1+item2-item3+Math.log(upperbound);
		//return result;

		//System.out.println(item1 + "   " + item2 + "   " + item3);
		//System.out.println("remove = "  + Math.exp(item1+item2-item3));
		//return Math.exp(item1+item2-item3+500);
		//return Math.exp(item1+item2-item3);
	}

}
