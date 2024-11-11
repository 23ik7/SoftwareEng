package bank;

import java.util.Date;
import javax.xml.crypto.Data;
import java.math.BigDecimal;

public class TestMain {
    public static void main(String[] args) {
        /*CorporateAccount corp1 = new CorporateAccount("1", "2", "3");
        System.out.println(corp1.getBalance());
        BigDecimal hundred = new BigDecimal("100");
        BigDecimal thousand = new BigDecimal("1000");
        System.out.println(corp1.accountId);
        System.out.println(corp1.withdraw(hundred));
        System.out.println(corp1.getBalance());
        System.out.println(corp1.withdraw(thousand));
        System.out.println(corp1.getBalance());

        System.out.println("//////////////////////////////////////");
        CorporateAccount corp2 = new CorporateAccount("1", "2", "3");
        System.out.println(corp2.accountId);


        System.out.println("/////////////////////////////");
        System.out.println(corp1.isOwner("1"));
        System.out.println(corp1.isOwner("999"));

        System.out.println("##################");
        PersonalAccount per1 = new PersonalAccount("1");
        System.out.println(per1.accountId);
        System.out.println(per1.withdraw(hundred));
        System.out.println(per1.getBalance());

        PersonalAccount per2 = new PersonalAccount("4");
        System.out.println(per2.accountId);*/

        Bank b1 = new Bank();
        Date d1 = new Date(2000, 06, 02);
        b1.registerCustomer("vasya", "pupkin", d1);
        b1.registerCustomer("igor", "iverson", new Date(1914, 9, 11));
        System.out.println(b1.getCustomers("vasya", "pupkin", d1));
        System.out.println(b1.getCustomers("igor", "iverson", new Date(1914, 9, 11)));
        b1.registerCorporateAccount("1", "2");

        for (Customer x : b1.getAllCustomers().values()) {
            System.out.println(x.showAccounts());
        }

        b1.registerCorporateAccount("1", "2");

        for (Customer x : b1.getAllCustomers().values()) {
            System.out.println(x.showAccounts());
        }



    }
}
