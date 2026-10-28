
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String type;
    private final String stockSymbol;
    private final int quantity;
    private final double price;
    private final double totalAmount;
    private final LocalDateTime timestamp;

    public Transaction(String type, String stockSymbol,
                       int quantity, double price) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = quantity * price;
        this.timestamp = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return String.format(
                "%-6s %-10s Qty: %-5d Price: ₹%-10.2f Total: ₹%-12.2f Time: %s",
                type,
                stockSymbol,
                quantity,
                price,
                totalAmount,
                timestamp.format(formatter)
        );
    }
}
