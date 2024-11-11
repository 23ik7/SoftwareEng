package bank;

import java.math.BigDecimal;

public class CorporateAccount extends Account{
    String[] ownersId;

    CorporateAccount(String... customerId) {
        ownersId = customerId;
        balance = new BigDecimal("0");
        accountId = "c/" + String.valueOf(nextId);
        nextId++;
    }

    public boolean isOwner(String customerId) {
        for (String id : ownersId) {
            if (customerId.equals(id)) {
                return true;
            }
        }
        return false;
    }



    @Override
    protected boolean withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
        return true;
    }

}
