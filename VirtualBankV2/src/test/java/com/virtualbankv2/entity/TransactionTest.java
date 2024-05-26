package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the Transaction class.
 * It includes tests for the constructor, getter, and setter methods.
 *
 * @version 1.0
 * @since 2024-05-10
 * @author Ji Zheng
 */
public class TransactionTest {

    /**
     * Tests the constructor of the Transaction class.
     */
    @Test
    void testTransactionConstructor() {
        Transaction transaction = new Transaction("T123", "123456789", "987654321", 100.0, "2024-05-07", "Transfer");
        assertNotNull(transaction);
        assertEquals("T123", transaction.getTransactionID());
        assertEquals("123456789", transaction.getAccountFrom());
        assertEquals("987654321", transaction.getAccountTo());
        assertEquals(100.0, transaction.getAmount());
        assertEquals("2024-05-07", transaction.getDate());
        assertEquals("Transfer", transaction.getDescription());
    }

    /**
     * Tests the getter and setter methods of the Transaction class.
     */
    @Test
    void testTransactionGettersAndSetters() {
        Transaction transaction = new Transaction("T123", "123456789", "987654321", 100.0, "2024-05-07", "Transfer");
        assertNotNull(transaction);

        transaction.setTransactionID("T456");
        assertEquals("T456", transaction.getTransactionID());

        transaction.setAccountFrom("987654321");
        assertEquals("987654321", transaction.getAccountFrom());

        transaction.setAccountTo("123456789");
        assertEquals("123456789", transaction.getAccountTo());

        transaction.setAmount(200.0);
        assertEquals(200.0, transaction.getAmount());

        transaction.setDate("2024-05-08");
        assertEquals("2024-05-08", transaction.getDate());

        transaction.setDescription("Payment");
        assertEquals("Payment", transaction.getDescription());
    }
}
