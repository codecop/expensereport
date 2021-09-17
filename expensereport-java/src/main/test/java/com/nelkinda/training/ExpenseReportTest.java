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
                new Expense(ExpenseType.BREAKFAST, 10),
                new Expense(ExpenseType.BREAKFAST, 1000),
                new Expense(ExpenseType.BREAKFAST, 1001),
                new Expense(ExpenseType.DINNER, 40),
                new Expense(ExpenseType.DINNER, 5000),
                new Expense(ExpenseType.DINNER, 5001),
                new Expense(ExpenseType.CAR_RENTAL, 200)
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

}

// test smell: schreibt auf Stdout
// test smell: schreibt Datum
