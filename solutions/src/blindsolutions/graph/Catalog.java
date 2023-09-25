package blindsolutions.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Catalog {


    // Consider a CSV file used to hold Amazon's product catalog which has data in the form of 'category -> product'.
    //Input: List catalog.
    //Sample lines in the catalog: ["phone : Pixel 3a", "tablet : iPad"]
    //Output: Find the top 1 or k categories with most number of products under that category or categories.
    private Map<String, Integer> categoryCount;
    private List<String> catalog;

    public Catalog(List<String> catalog) {
        this.catalog = catalog;
        this.categoryCount = new HashMap<>();
    }

    // Solve using MiniHeap technique
    public List<String> findTopCategories(int k) {
        // count the number of products for each category
        for (String entry : catalog) {
            String[] parts = entry.split(" : ");
            String category = parts[0];
            if (!categoryCount.containsKey(category)) {
                categoryCount.put(category, 0);
            }
            categoryCount.put(category, categoryCount.get(category) + 1);
        }

        // use a priority queue to store the top k categories
        PriorityQueue<String> topCategories = new PriorityQueue<>(
                (a, b) -> categoryCount.get(b) - categoryCount.get(a));

        for (String category : categoryCount.keySet()) {
            topCategories.offer(category);
            if (topCategories.size() > k) {
                topCategories.poll();
            }
        }

        // return the result as a list
        List<String> result = new ArrayList<>();
        while (!topCategories.isEmpty()) {
            result.add(topCategories.poll());
        }
        return result;
    }
}
