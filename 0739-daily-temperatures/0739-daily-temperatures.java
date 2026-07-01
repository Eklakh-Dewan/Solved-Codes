// class Solution {
//     public int[] dailyTemperatures(int[] temperatures) {
//         int n=temperatures.length;
//         int [] arr[n];
//         for(int i=0;i<temperatures.lenth-1;i++){
//             for (int j=i+1;j<temperatures.length;j++){
//                 if(temperatures[i]>temperature[j]){
//                     arr.append(j-i);
//                     return;

//                 }

//             }
            
//         }
        
//     }
// }



import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // While stack is not empty and the current temperature 
            // is greater than the temperature at the index stored at the top
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                // The difference in indices is the number of days waited
                result[prevIndex] = i - prevIndex;
            }
            // Push the current index onto the stack
            stack.push(i);
        }
        
        return result;
    }
}