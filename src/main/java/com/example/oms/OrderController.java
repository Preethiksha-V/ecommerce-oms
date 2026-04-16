package com.example.oms;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class OrderController {
    private static ConcurrentHashMap<String, Integer> inventory = new ConcurrentHashMap<>();
    static { inventory.put("laptop", 10); }

    @PostMapping("/order")
    public String placeOrder(@RequestParam String item, @RequestParam int qty) {
        int stock = inventory.getOrDefault(item, 0);
        if (stock >= qty) {
            inventory.put(item, stock - qty);
            return "SUCCESS: Order placed. Remaining: " + (stock - qty);
        }
        return "FAILURE: Out of stock";
    }
}
