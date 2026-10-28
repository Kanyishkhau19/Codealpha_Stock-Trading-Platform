
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TradingPlatform {
    private final Map<String, Stock> market = new LinkedHashMap<>();
    private final List<Transaction> transactions = new ArrayList<>();
    private final User user;

    public TradingPlatform(User user) {
        this.user = user;
        initializeMarket();
    }

    private void initializeMarket() {
        market.put("TCS", new Stock("TCS", "Tata Consultancy", 3500));
        market.put("INFY", new Stock("INFY", "Infosys", 1800));
        market.put("RELIANCE", new Stock("RELIANCE", "Reliance Industries", 2900));
        market.put("HDFCBANK", new Stock("HDFCBANK", "HDFC Bank", 1700));
        market.put("ITC", new Stock("ITC", "ITC Limited", 450));
        market.put("WIPRO", new Stock("WIPRO", "Wipro Limited", 550));
    }

    public void displayMarketData() {
        System.out.println("\n=============== STOCK MARKET ===============");
        System.out.printf("%-12s %-25s %s%n",
                "Symbol", "Company", "Price");
        System.out.println("--------------------------------------------");

        for (Stock stock : market.values()) {
            System.out.println(stock);
        }

        System.out.println("============================================");
        System.out.println("Note: Prices are simulated, not live market data.");
    }

    public void buyStock(String symbol, int quantity) {
        Stock stock = market.get(symbol.toUpperCase());

        if (stock == null) {
            System.out.println("Stock symbol not found.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        double totalCost = stock.getPrice() * quantity;

        if (!Double.isFinite(totalCost)) {
            System.out.println("Invalid transaction amount.");
            return;
        }

        if (user.buyStock(stock, quantity)) {
            transactions.add(new Transaction(
                    "BUY", stock.getSymbol(), quantity, stock.getPrice()));

            System.out.printf(
                    "Purchase successful! %d shares of %s bought for ₹%.2f%n",
                    quantity, symbol.toUpperCase(), totalCost);
        } else {
            System.out.println("Insufficient balance or invalid quantity.");
        }
    }

    public void sellStock(String symbol, int quantity) {
        Stock stock = market.get(symbol.toUpperCase());

        if (stock == null) {
            System.out.println("Stock symbol not found.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        if (quantity > user.getQuantity(stock.getSymbol())) {
            System.out.println("You do not own enough shares to sell.");
            return;
        }

        double saleAmount = stock.getPrice() * quantity;

        if (user.sellStock(stock, quantity)) {
            transactions.add(new Transaction(
                    "SELL", stock.getSymbol(), quantity, stock.getPrice()));

            System.out.printf(
                    "Sale successful! %d shares of %s sold for ₹%.2f%n",
                    quantity, symbol.toUpperCase(), saleAmount);
        } else {
            System.out.println("Sale failed.");
        }
    }

    public void displayPortfolio() {
        System.out.println("\n=============== MY PORTFOLIO ===============");
        System.out.println("Investor: " + user.getName());
        System.out.printf("Available Cash: ₹%.2f%n", user.getCashBalance());

        Map<String, Integer> holdings = user.getHoldings();

        if (holdings.isEmpty()) {
            System.out.println("No stocks currently owned.");
        } else {
            System.out.printf("%-12s %-10s %-15s %-15s%n",
                    "Symbol", "Quantity", "Market Value", "Cost Basis");

            for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
                Stock stock = market.get(entry.getKey());
                int quantity = entry.getValue();

                double currentValue = stock.getPrice() * quantity;
                double cost = user.getCostBasis(entry.getKey());

                System.out.printf("%-12s %-10d ₹%-14.2f ₹%-14.2f%n",
                        entry.getKey(), quantity, currentValue, cost);
            }
        }

        System.out.println("--------------------------------------------");
        System.out.printf("Current Stock Value : ₹%.2f%n",
                user.getPortfolioValue(market));

        System.out.printf("Unrealized Profit/Loss: ₹%.2f%n",
                user.getUnrealizedProfitLoss(market));

        System.out.printf("Total Account Value : ₹%.2f%n",
                user.getTotalAccountValue(market));

        System.out.println("============================================");
    }

    public void displayTransactionHistory() {
        System.out.println("\n============ TRANSACTION HISTORY ============");

        if (transactions.isEmpty()) {
            System.out.println("No transactions have been made.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        System.out.println("=============================================");
    }

    public void updateStockPrice(String symbol, double newPrice) {
        Stock stock = market.get(symbol.toUpperCase());

        if (stock == null) {
            System.out.println("Stock symbol not found.");
            return;
        }

        if (!Double.isFinite(newPrice) || newPrice <= 0) {
            System.out.println("Price must be a positive number.");
            return;
        }

        stock.setPrice(newPrice);

        System.out.printf("Simulated price of %s updated to ₹%.2f%n",
                symbol.toUpperCase(), newPrice);
    }

    public boolean stockExists(String symbol) {
        return market.containsKey(symbol.toUpperCase());
    }
}
