
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("          STOCK TRADING PLATFORM");
        System.out.println("============================================");

        System.out.print("Enter your investor name: ");
        String name = sc.nextLine().trim();

        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter your name: ");
            name = sc.nextLine().trim();
        }

        User user = new User(name, 100000);
        TradingPlatform platform = new TradingPlatform(user);

        System.out.println("\nWelcome, " + name + "!");
        System.out.println("Your initial virtual balance is ₹100000.00");

        boolean running = true;

        while (running) {
            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    platform.displayMarketData();
                    break;

                case 2:
                    buyShares(platform);
                    break;

                case 3:
                    sellShares(platform);
                    break;

                case 4:
                    platform.displayPortfolio();
                    break;

                case 5:
                    platform.displayTransactionHistory();
                    break;

                case 6:
                    updatePrice(platform);
                    break;

                case 0:
                    running = false;
                    System.out.println(
                            "Thank you for using Stock Trading Platform!");
                    System.out.println("Goodbye, " + name + "!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please select a valid option.");
            }
        }

        sc.close();
    }

    private static void displayMenu() {
        System.out.println("\n============== MAIN MENU ==============");
        System.out.println("1. View Market Data");
        System.out.println("2. Buy Stocks");
        System.out.println("3. Sell Stocks");
        System.out.println("4. View Portfolio");
        System.out.println("5. View Transaction History");
        System.out.println("6. Update Simulated Stock Prices");
        System.out.println("0. Exit");
        System.out.println("=======================================");
    }

    private static void buyShares(TradingPlatform platform) {
        System.out.println("\n--- Buy Stocks ---");

        platform.displayMarketData();

        System.out.print("Enter stock symbol: ");
        String symbol = sc.nextLine().trim();

        int quantity = readInt("Enter quantity to buy: ");

        platform.buyStock(symbol, quantity);
    }

    private static void sellShares(TradingPlatform platform) {
        System.out.println("\n--- Sell Stocks ---");

        System.out.print("Enter stock symbol: ");
        String symbol = sc.nextLine().trim();

        int quantity = readInt("Enter quantity to sell: ");

        platform.sellStock(symbol, quantity);
    }

    private static void updatePrice(TradingPlatform platform) {
        System.out.println("\n--- Update Simulated Stock Price ---");

        System.out.print("Enter stock symbol: ");
        String symbol = sc.nextLine().trim();

        double price = readDouble("Enter new price: ");

        platform.updateStockPrice(symbol, price);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                double value = Double.parseDouble(sc.nextLine().trim());

                if (Double.isFinite(value)) {
                    return value;
                }

                System.out.println("Please enter a valid finite number.");

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Please enter a numeric value.");
            }
        }
    }
}
