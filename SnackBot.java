import java.util.Scanner;

public class SnackBot {

    // types of qualities snacks can have
    static String[] snackTypes = {"sweet", "salty", "spicy", "healthy"};
    static String[] textures = {"crunchy", "soft", "chewy", "smooth"};
    static String[] priceRanges = {"low", "medium", "high"};
    static String[] spicyLevels = {"mild", "hot", "none"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // food objects
        Food[] snacks = {
            new Food("Chocolate-Covered Almonds", 3.99, "Mild", "Crunchy", "Sweet"),
            new Food("Spicy Nacho Chips", 2.49, "Hot", "Crunchy", "Spicy"),
            new Food("Granola Bar", 1.99, "None", "Soft", "Healthy"),
            new Food("Salted Pretzels", 1.49, "None", "Crunchy", "Salty"),
            new Food("Cheese Puffs", 2.29, "None", "Chewy", "Salty"),
            new Food("Spicy Hummus with Chips", 3.49, "Hot", "Smooth", "Spicy"),
            new Food("Peanut Butter Cups", 1.99, "None", "Chewy", "Sweet"),
            new Food("Veggie Chips", 2.99, "None", "Crunchy", "Healthy"),
            new Food("Crispy Rice Cakes", 1.49, "None", "Crunchy", "Healthy"),
            new Food("Chocolate Chip Cookies", 3.49, "None", "Soft", "Sweet"),
            new Food("Chili Cheese Nachos", 3.99, "Hot", "Crunchy", "Spicy"),
            new Food("Trail Mix", 2.49, "None", "Chewy", "Healthy"),
            new Food("Salted Caramel Popcorn", 2.99, "None", "Crunchy", "Sweet"),
            new Food("Barbecue Chips", 1.79, "None", "Crunchy", "Salty"),
            new Food("Mango Salsa with Tortilla Chips", 3.49, "Hot", "Crunchy", "Spicy")
        };

        greetUser();

//start of while loop
        while (true) {
            System.out.println("\nType 'start' to start a new recommendation, or 'bye' to leave your best (and only) friend. If you don't want to go through the step by step recommendation process, just tell me what kind of snacks you like, and I got you.");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("bye")) {
                System.out.println("See ya! Come back next time if you lose your friends!");
                break;
            }

            // check user mentioned a snack
            if (checkForSnackKeywords(input)) {
                continue;
            }

            // otherwise
            if (input.contains("start")) {
                askPreferences(scanner, snacks);
            } else {
                randomResponse();
            }
        }

        scanner.close();
    }

    private static void greetUser() {
        System.out.println("Yo, I'm SnackBot, I can recommend snacks based on your preferences.");
    }

    // asks what they want
    private static void askPreferences(Scanner scanner, Food[] snacks) {
        System.out.println("\nWhat texture do you prefer? (Crunchy, Soft, Chewy, Smooth)");
        String texture = getValidInput(scanner, textures);

        System.out.println("What's your price range? (low, medium, high)");
        String priceRange = getValidInput(scanner, priceRanges);

        System.out.println("What type of snack would you like? (Sweet, Salty, Spicy, Healthy)");
        String snackType = getValidInput(scanner, snackTypes);

        System.out.println("Do you want something spicy? (Mild, Hot, None)");
        String spicyLevel = getValidInput(scanner, spicyLevels);

        recommendSnacks(snacks, texture, priceRange, snackType, spicyLevel);
    }

    // gets input in form of a method to make it easier
    private static String getValidInput(Scanner scanner, String[] validOptions) {
        String userInput;
        while (true) {
            userInput = scanner.nextLine().toLowerCase();
            for (String option : validOptions) {
                if (userInput.equals(option)) {
                    return userInput;
                }
            }
            System.out.println("You can't do that buddy. Choose from: " + String.join(", ", validOptions));
        }
    }

    // recommends snacks based on preferences
    private static void recommendSnacks(Food[] snacks, String texture, String priceRange, String snackType, String spicyLevel) {
        System.out.println("\nHere is a snack (or some snacks) that may match some of your preferences:");
    
        int matchesCount = 0;
        for (Food snack : snacks) {
            // get the matched preferences
            String matchedPreferences = snack.getMatchedPreferences(texture, priceRange, snackType, spicyLevel);
    
            // if at least 1 preference matches
            if (!matchedPreferences.isEmpty()) { // if the matched preferences string is not empty...
                snack.displayInfo();
                System.out.println("Matched Preferences: " + matchedPreferences);
                matchesCount++;
            }
        }
    
        // If no snacks match, inform the user
        if (matchesCount == 0) {
            System.out.println("Sorry, there aren't any snacks that match some of your preferences.");
        }
    }
    
    

    // checks for keywords
    private static boolean checkForSnackKeywords(String input) {
        String[][] keywordSnacks = {
            {"chocolate", "Chocolate-Covered Almonds"},
            {"chips", "Spicy Nacho Chips"},
            {"granola", "Granola Bar"},
            {"pretzel", "Salted Pretzels"},
            {"cheese", "Cheese Puffs"},
            {"hummus", "Spicy Hummus with Chips"},
            {"peanut", "Peanut Butter Cups"},
            {"veggie", "Veggie Chips"},
            {"cake", "Crispy Rice Cakes"},
            {"cookie", "Chocolate Chip Cookies"},
            {"nacho", "Chili Cheese Nachos"},
            {"trail mix", "Trail Mix"},
            {"popcorn", "Salted Caramel Popcorn"},
            {"barbecue", "Barbecue Chips"},
            {"fruit", "Mango Salsa with Tortilla Chips"},
            {"snack", "snacks, but you gotta tell me what kind"}
        };

        // check if the keywords match input
        for (String[] pair : keywordSnacks) {
            if (input.contains(pair[0])) {
                System.out.println("Seems like you want some " + pair[1] + "!");
                return true;
            }
        }
        return false;
    }

    // random response when it doesnt understand
    private static void randomResponse() {
        String[] responses = {
            "Hmm, I'm tryna think what you said but can't understand. Could you ask that in another way?",
            "Sorry, I didn’t quite get that. Try asking me about snacks and stuff, or you can go through the step by step recommendation process!",
            "That’s an interesting set of characters that you have typed on your keyboard, but I can only help with snacks.",
            "I don't know the answer, but I can recommend snacks",
            "You need to be more clear because that set of characters is not in my vocabulary."
        };

        // random index generated to get a random response
        int randomIndex = (int) (Math.random() * responses.length);
        System.out.println(responses[randomIndex]);
    }
}
