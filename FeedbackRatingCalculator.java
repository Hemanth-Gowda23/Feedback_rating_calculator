import java.util.Scanner;

public class FeedbackRatingCalculator {
    private static final int MAX_RATING = 5;
    private static final int MIN_RATING = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Feedback Rating Calculator");
        
        // Get feedback type
        System.out.print("Is this a positive or negative feedback? (P/N): ");
        String feedbackType = scanner.nextLine().toUpperCase();
        
        // Calculate ratings
        double cesScore = calculateCustomerEffortScore(scanner);
        double csatScore = calculateCustomerSatisfactionScore(scanner);
        
        // Calculate overall rating
        double overallRating = calculateOverallRating(feedbackType, cesScore, csatScore);
        
        // Display results
        System.out.println("\nResults:");
        System.out.printf("Customer Effort Score: %.2f\n", cesScore);
        System.out.printf("Customer Satisfaction Score: %.2f\n", csatScore);
        System.out.printf("Overall Rating: %.2f out of %d\n", overallRating, MAX_RATING);
        
        scanner.close();
    }
    
    private static double calculateCustomerEffortScore(Scanner scanner) {
        System.out.print("On a scale of 1-5, how easy was your experience? (1 = Very Difficult, 5 = Very Easy): ");
        int cesScore = getValidRating(scanner);
        return cesScore;
    }
    
    private static double calculateCustomerSatisfactionScore(Scanner scanner) {
        System.out.print("On a scale of 1-5, how satisfied are you with the product? (1 = Very Unsatisfied, 5 = Very Satisfied): ");
        int csatScore = getValidRating(scanner);
        return csatScore;
    }
    
    private static int getValidRating(Scanner scanner) {
        int rating;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next();
            }
            rating = scanner.nextInt();
            if (rating < MIN_RATING || rating > MAX_RATING) {
                System.out.println("Invalid rating. Please enter a number between 1 and 5.");
            }
        } while (rating < MIN_RATING || rating > MAX_RATING);
        scanner.nextLine(); // Consume newline
        return rating;
    }
    
    private static double calculateOverallRating(String feedbackType, double cesScore, double csatScore) {
        double overallRating;
        if (feedbackType.equals("P")) {
            // For positive feedback, we give more weight to CSAT
            overallRating = (cesScore + (2 * csatScore)) / 3;
        } else {
            // For negative feedback, we give more weight to CES
            overallRating = ((2 * cesScore) + csatScore) / 3;
        }
        return overallRating;
    }
}

#jfdddfh