import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        List<Integer> v = new ArrayList<>();
        System.out.print("\nEnter values: ");
        
        // Input the values into the list
        for (int i = 0; i < n; i++) {
            System.out.print("\nElement " + (i + 1) + ": ");
            int x = scanner.nextInt();
            v.add(x);
        }

        System.out.print("\nThe array you entered is: ");
        
        // Print the entered array
        for (int i = 0; i < n; i++) {
            System.out.print(v.get(i) + " ");
        }

        System.out.println("\n\nPerforming Selection Sort on the given array");
        
        // Selection Sort algorithm
        for (int i = 0; i < n; i++) {
            int z = i;
            
            // Find the index of the smallest element
            for (int j = i + 1; j < n; j++) {
                if (v.get(j) < v.get(z)) {
                    z = j;
                }
            }
            
            // Swap the smallest element with the current element
            int t = v.get(z);
            v.set(z, v.get(i));
            v.set(i, t);
        }

        System.out.print("\nThe sorted array is: ");
        
        // Print the sorted array
        for (int i = 0; i < n; i++) {
            System.out.print(v.get(i) + " ");
        }
    }
}
