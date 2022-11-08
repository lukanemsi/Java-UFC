package ge.ufc.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class BalanceRequest
{
    private static final Gson gson = new Gson();

    @SerializedName("payment_id")
    private final String paymentId;
    @SerializedName("user_id")
    private final int userId;
    @SerializedName("amount")
    private final long amount;

    public BalanceRequest(String paymentId, int userId, long amount) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.amount = amount;
    }

    public String toJson()
    {
        return gson.toJson(this);
    }

    public String getPaymentId() {
        return paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public long getAmount() {
        return amount;
    }
}
