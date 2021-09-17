package com.nelkinda.training;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseReportTest {


    private PrintStream out;
    private ByteArrayOutputStream stdOut;

    @BeforeEach
    void before() {
        out = System.out;
        stdOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdOut));
    }

    @AfterEach
    void after() {
        System.setOut(out);
    }

    public String getStdOut() {
        return stdOut.toString();
    }

    @Test
    void printReport() {
        ExpenseReport expenseReport = new ExpenseReport();
        List<Expense> expenses = Arrays.asList(
                newExpense(ExpenseType.BREAKFAST, 10),
                newExpense(ExpenseType.BREAKFAST, 1000),
                newExpense(ExpenseType.BREAKFAST, 1001),
                newExpense(ExpenseType.DINNER, 40),
                newExpense(ExpenseType.DINNER, 5000),
                newExpense(ExpenseType.DINNER, 5001),
                newExpense(ExpenseType.CAR_RENTAL, 200)
        );

        expenseReport.printReport(expenses);

        String actualStdOut = getStdOut().
                replaceAll("\\w{3} \\w{3} \\d{2} \\d{2}:\\d{2}:\\d{2} [A-Z]{4} \\d{4}", "<Date>").
                replaceAll(System.lineSeparator(), "\n");
        assertEquals("Expenses <Date>\n" +
                "Breakfast\t10\t \n" +
                "Breakfast\t1000\t \n" +
                "Breakfast\t1001\tX\n" +
                "Dinner\t40\t \n" +
                "Dinner\t5000\t \n" +
                "Dinner\t5001\tX\n" +
                "Car Rental\t200\t \n" +
                "Meal expenses: 12052\n" +
                "Total expenses: 12252\n", actualStdOut);
    }

    private Expense newExpense(ExpenseType type, int amount) {
        Expense expense = new Expense();
        expense.type = type;
        expense.amount = amount;
        return expense;
    }
}

// test smell: schreibt auf Stdout
// test smell: schreibt Datum
