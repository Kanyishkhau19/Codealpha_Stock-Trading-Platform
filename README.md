# Stock Trading Platform

A console-based Stock Trading Platform developed using Java that simulates basic stock market operations. Users can view stock prices, buy and sell shares using virtual money, track their portfolio, and review transaction history.

## Project Overview

The Stock Trading Platform is a beginner-friendly Java application designed to demonstrate object-oriented programming, collections, transaction management, and basic financial calculations.

The application uses simulated stock prices and virtual funds to provide a safe environment for learning stock trading concepts without involving real money or live market transactions.

## Features

- **View Market Data:** Display available stocks, company names, and their simulated prices.
- **Buy Stocks:** Purchase shares using the available virtual cash balance.
- **Sell Stocks:** Sell owned shares and receive virtual funds.
- **Portfolio Management:** View stock holdings, quantities, market values, and cost basis.
- **Profit and Loss Calculation:** Calculate unrealized profit or loss based on current simulated prices.
- **Account Valuation:** View available cash, total stock value, and overall account value.
- **Transaction History:** Maintain a record of buy and sell transactions with timestamps.
- **Simulated Price Updates:** Update stock prices manually to test portfolio performance.
- **Input Validation:** Handle invalid menu choices, quantities, prices, and insufficient balances.

## Technologies Used

- **Programming Language:** Java
- **User Interface:** Command-Line Interface (CLI)
- **Java Concepts:** Object-Oriented Programming (OOP), classes, objects, encapsulation, collections, and exception handling
- **Collections Framework:** `HashMap`, `LinkedHashMap`, `ArrayList`, and `Map`
- **Date and Time API:** `LocalDateTime` and `DateTimeFormatter`
- **Development Environment:** Visual Studio Code
- **Version Control:** Git and GitHub (optional)

## Project Structure

```text
StockTradingPlatform/
│
├── src/
│   ├── Main.java
│   ├── Stock.java
│   ├── User.java
│   ├── Transaction.java
│   └── TradingPlatform.java
│
├── out/                  # Compiled class files
└── README.md
```

### Description of Java Files

| File | Responsibility |
|---|---|
| `Main.java` | Entry point of the application; handles user input and menu navigation. |
| `Stock.java` | Represents a stock with its symbol, company name, and price. |
| `User.java` | Manages the investor's cash balance, stock holdings, and buy/sell operations. |
| `Transaction.java` | Stores transaction details, including type, quantity, price, and timestamp. |
| `TradingPlatform.java` | Manages market data, trading operations, portfolio information, and transaction history. |

## Prerequisites

Before running the project, ensure that you have installed:

1. Java Development Kit (JDK), preferably JDK 17 or later.
2. Visual Studio Code or another Java-compatible IDE.
3. A terminal such as Windows PowerShell or Command Prompt.

Verify your Java installation:

```bash
java -version
javac -version
```

## How to Run the Project

### Step 1: Open the Project Folder

Open the `StockTradingPlatform` folder in Visual Studio Code.

### Step 2: Open the Terminal

In VS Code, select **Terminal → New Terminal**.

Ensure that the terminal is in the project root directory, where the `src` folder is located.

### Step 3: Compile the Java Files

Run the following command in Windows PowerShell:

```powershell
javac -d out src\*.java
```

### Step 4: Execute the Application

After successful compilation, run:

```powershell
java -cp out Main
```

### Step 5: Start Trading

Enter your investor name and use the menu to view market data, buy or sell stocks, check your portfolio, view transactions, or update simulated stock prices.

## Available Stocks

The application starts with the following sample stocks and simulated prices:

| Stock Symbol | Company Name | Initial Price |
|---|---|---:|
| TCS | Tata Consultancy | ₹3,500.00 |
| INFY | Infosys | ₹1,800.00 |
| RELIANCE | Reliance Industries | ₹2,900.00 |
| HDFCBANK | HDFC Bank | ₹1,700.00 |
| ITC | ITC Limited | ₹450.00 |
| WIPRO | Wipro Limited | ₹550.00 |

**Initial virtual balance:** ₹100,000.00

*Note: Stock prices are sample values used for simulation. They are not live market prices.*

## Application Menu

```text
============== MAIN MENU ==============
1. View Market Data
2. Buy Stocks
3. Sell Stocks
4. View Portfolio
5. View Transaction History
6. Update Simulated Stock Prices
0. Exit
=======================================
```

## Key Concepts Demonstrated

- Object