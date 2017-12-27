import com.liang.graph.Graph;
import com.liang.main.MYMethod;
import com.liang.quality.CohesivenessFunction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Main_test {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		Graph graph = new Graph(false);
		File file = new File("./datasets/football.txt");
		BufferedReader reader = null;
		 try {
			    HashMap<String , Integer> map = new HashMap<>();
	            reader = new BufferedReader(new FileReader(file));
	            String tempString ;
	            while ((tempString = reader.readLine()) != null) {
	            	String str[] = tempString.split("\\s+");
	            	if(!map.containsKey(str[0])){
	            		int index = graph.createNode(str[0]);
	            		map.put(str[0], index);
	            	}
	            	if(!map.containsKey(str[1])){
	            		int index = graph.createNode(str[1]);
	            		map.put(str[1], index);
	            	}
	            	graph.createEdge(map.get(str[0]), map.get(str[1]));
	            }
	            int d = 2 * graph.getEdgeCount();
			 	//System.out.println(Graph.nodesNumber);
			    int maxEdges = (Graph.nodesNumber * (Graph.nodesNumber-1)) / 2;
	    		graph.compute_factorial(d);
	    		graph.compute_total_degree(d);

			 MYMethod myMethod = new MYMethod(graph);myMethod.runONGraph();
			 System.out.println();
			 System.out.println(myMethod.hypothesisCount);
			 System.out.println(Math.log(0.001/myMethod.hypothesisCount));
//			 CohesivenessFunction cohesivenessFunction = new CohesivenessFunction();
//			 System.out.println("asdasd " + cohesivenessFunction.logHyperProbability(78,41,6555,613));
//			 System.out.println("asdasd " + cohesivenessFunction.logHyperProbability(78,50,6555,613));

//			 MutableNodeSet testNodeSet = new MutableNodeSet(graph);
//			 MutableNodeSet testNodeSet1 = new MutableNodeSet(graph);
//			 int []nodes = {0,1};
//			 int []nodes1 = {0,1,2};
//			 //int []nodes = {28,46,49,53,58,67,73,88,114};
//			 //int []nodes1 = {46,49,53,67,73,83,88,110,114};
//			 testNodeSet.add(nodes);
//			 testNodeSet1.add(nodes1);
//			 CohesivenessFunction cohesivenessFunction = new CohesivenessFunction();
//			 System.out.println(cohesivenessFunction.getAdditionAffinity(testNodeSet,2));
//			 System.out.println(cohesivenessFunction.getRemovalAffinity(testNodeSet1,2));
			 reader.close();


	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }

	}
}
