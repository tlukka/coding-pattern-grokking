package grokkingpattern.combine.graphpatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/evaluate-division/
//https://leetcode.com/problems/evaluate-division/discuss/1992891/java-oror-DFS-Solution-with-comments-oror-Evaluate-Division-%3A
//You are given an array of variable pairs equations and an array of real numbers values,
// where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
// Each Ai or Bi is a string that represents a single variable.

//You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query
// where you must find the answer for Cj / Dj = ?.

//Return the answers to all queries. If a single answer cannot be determined, return -1.0.
public class CalculateEquationProblem {
    //Build Graph
    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {

        Map<String, Map<String, Double>> graph = new HashMap<>();
        // build a graph
        // like a -> b = values[i]
        // and b -> a  = 1.0 / values[i];
        String u, v;
        for (int i = 0; i < equations.size(); i++) {
            u = equations.get(i).get(0);
            v = equations.get(i).get(1);
            graph.putIfAbsent(u, new HashMap<>());
            graph.get(u).put(v, values[i]);
            graph.putIfAbsent(v, new HashMap<>());
            graph.get(v).put(u, 1 / values[i]);
        }
        return graph;
    }

    private double dfs(String src, String dest, Set<String> visited, Map<String, Map<String, Double>> graph) {
        // check the terminated Case
        // if string is not present in graph return -1.0;
        // like [a, e] or [x, x] :)
        if (graph.containsKey(src) == false)
            return -1.0;

        // simply say check src and dest are equal :) then return dest
        // store it in weight varaible;
        //case like [a,a] also handle
        if (graph.get(src).containsKey(dest)) {
            return graph.get(src).get(dest);
        }

        visited.add(src);

        for (Map.Entry<String, Double> nbr : graph.get(src).entrySet()) {
            if (visited.contains(nbr.getKey()) == false) {
                double weight = dfs(nbr.getKey(), dest, visited, graph);

                // if weight is not -1.0(terminate case)
                // then mutliply it
                // like in querie   a -> c => 2 * 3 = 6
                if (weight != -1.0) {
                    return nbr.getValue() * weight;
                }
            }
        }
        return -1.0;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Convert graph
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        double[] ans = new double[queries.size()];
        // check for every Queries and store it in ans array;
        for (int i = 0; i < queries.size(); i++) {
            ans[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), new HashSet<>(), graph);
        }

        return ans;

    }

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        List<String> equation1 = Arrays.asList(new String[]{"a", "b"});
        List<String> equation2 = Arrays.asList(new String[]{"b", "c"});

        equations.add(equation1);
        equations.add(equation2);

        double[] values = new double[]{2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        List<String> q1 = Arrays.asList(new String[]{"a", "c"});
        List<String> q2 = Arrays.asList(new String[]{"b", "a"});
        List<String> q3 = Arrays.asList(new String[]{"a", "e"});
        List<String> q4 = Arrays.asList(new String[]{"a", "a"});
        List<String> q5 = Arrays.asList(new String[]{"x", "x"});

        queries.add(q1);
        queries.add(q2);
        queries.add(q3);
        queries.add(q4);
        queries.add(q5);
        CalculateEquationProblem calP = new CalculateEquationProblem();
        double[] calAns = calP.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(calAns));
    }
}
