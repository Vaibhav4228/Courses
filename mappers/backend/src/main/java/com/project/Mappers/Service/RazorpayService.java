package com.project.Mappers.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {

    private RazorpayClient client;

    @Value("${razorpay.key_id}")
    private String razorpayKeyId;

    @Value("${razorpay.key_secret}")
    private String razorpaySecret;
    public void init() throws Exception {
        if (client == null) {
            client = new RazorpayClient(razorpayKeyId, razorpaySecret);
        }
    }

    public String createOrder(double amount, String currency, String receipt) throws Exception {
        init();
        long amountInPaise = (long) (amount * 100);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amountInPaise);
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receipt);
        orderRequest.put("payment_capture", 1);
        Order order = client.orders.create(orderRequest);
        return order.get("id");
    }
}
