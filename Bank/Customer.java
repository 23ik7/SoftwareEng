package bank;

import java.math.BigDecimal;
import java.util.*;


/**
 * A customer. Customers are identified by a unique id.
 *
 */
public class Customer {
	String customerId;
	String firstName;
	String lastName;
	Date birthDay;
	HashSet<Account> setOfAccounts;

	Customer(String firstName, String lastName, Date birthDay) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDay = birthDay;
		setOfAccounts = new HashSet<Account>();
	}

	/**
	 * @return the first name of this customer
	 */
	protected String getFirstName() {
		return firstName;
	}

	/**
	 * @return the last name of this customer
	 */
	protected String getLastName() {
		return lastName;
	}

	/**
	 * @return the birthday of this customer
	 */
	protected Date getBirthDay() {
		return birthDay;
	}

	public String getId() {
		return customerId;
	}

	public String setId(String customerId) {
		this.customerId = customerId;
		return customerId;
	}

	public void addAccount(Account newAccount) {
		setOfAccounts.add(newAccount);
	}

	public HashSet<String> getCustomerAccounts() {
		HashSet<String> customerAccountsIds = new HashSet<String>();
		for (Account a : setOfAccounts) {
			customerAccountsIds.add(a.getId());
		}
		return customerAccountsIds;
	}

	public String showAccounts() {
		String s = "[ ";
		for (Account x : setOfAccounts) {
			s += x.getId() + ", ";
		}
		return s + " ]";
	}

	public boolean removeAccount(String accountId) {
		boolean found = false;
		for (Account x : setOfAccounts) {
			if (x.getId().equals(accountId)) {
				setOfAccounts.remove(x);
				found = true;
				return found;
			}
		}
		return found;
    }

	public BigDecimal getTotalBalance() {
		BigDecimal total = new BigDecimal(0);
		for (Account x : setOfAccounts) {
            total = total.add(x.getBalance());
		}
        return total;
    }
}
