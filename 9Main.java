import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a HashMap to store the chatbot responses
        Map<String, String> m = new HashMap<>();

        // Add key-value pairs for different user inputs and corresponding chatbot responses
        m.put("Hello", "Hi! How may I help you?");
        m.put("Hi", "Hello! How may I help you?");
        m.put("How are you?", "I am good. What about you?");
        m.put("I am fine", "That's great! How may I help you?");
        m.put("Recharge plans", "These are some Popular Combo Plans:\n" +
                "Rs. 299 ------> Unlimited Calls + 1.5Gb/day for 28 days\n" +
                "Rs. 719 ------> Unlimited Calls + 1.5Gb/day for 84 days\n" +
                "Rs. 399 ------> Unlimited Calls + 2.5Gb/day for 28 days\n" +
                "Rs. 499 ------> Unlimited Calls + 3Gb/day for 28 days");
        m.put("Data plans", "These are some Popular Data Plans:\n" +
                "Rs. 151 ------> 8Gb for 30 days\n" +
                "Rs. 108 ------> 6Gb for 15 days\n" +
                "Rs. 58 ------> 3Gb for 28 days\n" +
                "Rs. 39 ------> 3Gb for 7 days\n" +
                "Rs. 75 ------> 6Gb for 7 days");
        m.put("Validity plans", "These are some Popular Validity Plans:\n" +
                "Rs. 99 ------> Rs. 99 talktime + 200Mb for 28 days\n" +
                "Rs. 279 ------> Rs. 279 talktime + 500Mb for 90 days\n" +
                "Rs. 107 ------> Rs. 107 talktime + 200Mb for 30 days\n" +
                "Rs. 111 ------> Rs. 111 talktime + 200Mb for 28 days");
        m.put("Yearly plans", "These are some Popular Yearly Plans:\n" +
                "Rs. 3099 ------> Unlimited Calls + 2Gb/day for 365 days\n" +
                "Rs. 2999 ------> Unlimited Calls + 850Gb for 365 days\n" +
                "Rs. 2899 ------> Unlimited Calls + 1.5Gb/day for 365 days\n" +
                "Rs. 1799 ------> Unlimited Calls + 24Gb for 365 days");
        m.put("Thank you", "Welcome :)");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter something to talk with the ChatBot");

        while (true) {
            System.out.print("\nYou: ");
            String s = scanner.nextLine();

            // Check if the user wants to quit or exit the chat
            if (s.equalsIgnoreCase("quit") || s.equalsIgnoreCase("exit")) {
                System.out.println("\nChatBot: Thank You :)");
                return;
            }

            // Check if the user's input is valid and retrieve the corresponding chatbot response
            if (!m.containsKey(s) || s.isEmpty()) {
                System.out.println("\nChatBot: Sorry for the inconvenience. However, I can only answer the following questions:\n" +
                        "1. Recharge plans\n" +
                        "2. Yearly plans\n" +
                        "3. Data plans\n" +
                        "4. Validity plans\n" +
                        "5. Quit/Exit\n" +
                        "Hope you understand :)");
            } else {
                System.out.println("\nChatBot: " + m.get(s));
            }
        }
    }
}
