package com.example.oms;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    void testInventoryUpdate() {
        OrderController controller = new OrderController();
        String result = controller.placeOrder("laptop", 2);
        assertTrue(result.contains("SUCCESS"));
    }
}
