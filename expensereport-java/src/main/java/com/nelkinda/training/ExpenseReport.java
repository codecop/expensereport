package com.nelkinda.training;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

class Expense {
    // smell: could be value object, immutable

    public Expense(ExpenseType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    final ExpenseType type;
    final int amount;

    public String getName() {
        // smell: switch on type code
        String expenseName = "";
        switch (type) {
        case DINNER:
            expenseName = "Dinner";
            break;
        case BREAKFAST:
            expenseName = "Breakfast";
            break;
        case CAR_RENTAL:
            expenseName = "Car Rental";
            break;
        }
        return expenseName;
    }

    public boolean isMealOverExpenses() {
        // smell: switch on type code
        return type == ExpenseType.DINNER && amount > 5000 || type == ExpenseType.BREAKFAST && amount > 1000;
    }

    public int mealAmount() {
        // smell: switch on type code
        int mealAmount = 0;
        if (type == ExpenseType.DINNER || type == ExpenseType.BREAKFAST) {
            mealAmount += amount;
        }
        return mealAmount;
    }
}

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        int total = 0; // smell: primitive obsession
        int mealExpenses = 0;

        System.out.println("Expenses " + new Date());

        for (Expense expense : expenses) {
            mealExpenses += expense.mealAmount();

            String expenseName = expense.getName();

            String mealOverExpensesMarker = expense.isMealOverExpenses() ? "X" : " ";

            // SRP - calculation and print and format in same method -> split phase
            System.out.println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker);

            total += expense.amount;
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + total);
    }

}
