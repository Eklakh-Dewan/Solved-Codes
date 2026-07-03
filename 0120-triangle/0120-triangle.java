class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        
        // Start from the second to last row and move upwards
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // Get the two adjacent values from the row below
                int val1 = triangle.get(i + 1).get(j);
                int val2 = triangle.get(i + 1).get(j + 1);
                
                // Add the minimum of the two to the current cell
                int currentSum = triangle.get(i).get(j) + Math.min(val1, val2);
                triangle.get(i).set(j, currentSum);
            }
        }
        
        // The top element now contains the minimum path sum
        return triangle.get(0).get(0);
    }
}

// import java.util.List;
// import java.util.Collections;

// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
//         int n = triangle.size(); // Total number of classes (rows)
        
//         // Initialize exactly ONE 1D array to store the minimum of each class
//         int[] minValues = new int[n];
//         int totalMinSum = 0;
        
//         // Loop through each class
//         for (int i = 0; i < n; i++) {
//             List<Integer> currentClass = triangle.get(i);
            
//             // Use Collections.min() to instantly find the smallest number in the list
//             int currentMin = Collections.min(currentClass);
            
//             // Store it in your initialized array
//             minValues[i] = currentMin;
            
//             // Add it to the running total sum
//             totalMinSum += currentMin;
//         }
        
//         return totalMinSum; 
//     }
// }
