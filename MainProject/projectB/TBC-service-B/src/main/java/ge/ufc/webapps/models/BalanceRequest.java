package ge.ufc.webapps.models;

import org.codehaus.jackson.annotate.JsonProperty;

public class BalanceRequest
{
    private String paymentId;
    private int userId;
    private double amount;

    public String getPaymentId() {
        return paymentId;
    }

    @JsonProperty("payment_id")
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }
    @JsonProperty("user_id")
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }
    @JsonProperty("amount")
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
