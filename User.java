
import java.util.HashMap;
import java.util.Map;

public class User {
    private final String name;
    private double cashBalance;

    private final Map<String, Integer> holdings = new HashMap<>();
    private final Map<String, Double> costBasis = new HashMap<>();

    public User(String name, double initialBalance) {
        this.name = name;
        this.cashBalance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public int getQuantity(String symbol) {
        return holdings.getOrDefault(symbol, 0);
    }

    public double getCostBasis(String symbol) {
        return costBasis.getOrDefault(symbol, 0.0);
    }

    public Map<String, Integer> getHoldings() {
        return new HashMap<>(holdings);
    }

    public boolean buyStock(Stock stock, int quantity) {
        if (quantity <= 0) {
            return false;
        }

        double totalCost = stock.getPrice() * quantity;

        if (!Double.isFinite(totalCost) ||
                totalCost > cashBalance) {
            return false;
        }

        String symbol = stock.getSymbol();

        cashBalance -= totalCost;
        holdings.put(symbol, getQuantity(symbol) + quantity);
        costBasis.put(symbol, getCostBasis(symbol) + totalCost);

        return true;
    }

    public boolean sellStock(Stock stock, int quantity) {
        String symbol = stock.getSymbol();
        int owned = getQuantity(symbol);

        if (quantity <= 0 || quantity > owned) {
            return false;
        }

        double saleAmount = stock.getPrice() * quantity;

        if (!Double.isFinite(saleAmount)) {
            return false;
        }

        double oldCost = getCostBasis(symbol);
        double costRemoved = oldCost * quantity / owned;
        int remaining = owned - quantity;

        cashBalance += saleAmount;

        if (remaining == 0) {
            holdings.remove(symbol);
            costBasis.remove(symbol);
        } else {
            holdings.put(symbol, remaining);
            costBasis.put(symbol, oldCost - costRemoved);
        }

        return true;
    }

    public double getPortfolioValue(Map<String, Stock> market) {
        double total = 0;

        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            Stock stock = market.get(entry.getKey());

            if (stock != null) {
                total += stock.getPrice() * entry.getValue();
            }
        }

        return total;
    }

    public double getUnrealizedProfitLoss(Map<String, Stock> market) {
        double currentValue = getPortfolioValue(market);
        double totalCost = 0;

        for (double cost : costBasis.values()) {
            totalCost += cost;
        }

        return currentValue - totalCost;
    }

    public double getTotalAccountValue(Map<String, Stock> market) {
        return cashBalance + getPortfolioValue(market);
    }
}
