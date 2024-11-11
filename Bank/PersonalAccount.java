package bank;

import java.math.BigDecimal;

public class PersonalAccount extends Account {
    String ownerId;

    PersonalAccount (String ownderId) {
        this.ownerId = ownderId;
        balance = new BigDecimal("0");
        accountId = "p/" + ownderId + "/" +String.valueOf(nextId);
        nextId++;
    }

    @Override
    protected boolean withdraw(BigDecimal amount) {
        double d = amount.doubleValue();
        double bal = balance.doubleValue();

        if ( bal - d < 0) {
            return false;
        }
        balance = balance.subtract(amount);
        return true;
    }

}
