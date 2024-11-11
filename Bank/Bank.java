//Yerzhanuly Dias //TODO
//Kumichev Igor  //TODO


package bank;

import java.math.BigDecimal;
import java.util.*;

/**
 * A Bank. This class manages customers (<code>Customer</code>) and their
 * accounts (<code>Account</code> and derived classes).
 * 
 * All accounts and all customers managed by this class must have a unique
 * identifier.
 * 
 * Note: There may exist multiple customers that share the same firstname,
 * lastname, and day of birth. Nevertheless, all customers have a unique
 * identifier.
 * 
 * The bank supports two different types of accounts: (i) corporate account,
 * (ii) personal account
 * 
 * (i) A corporate account is allowed to have a negative balance (without
 * limit). Furthermore, a corporate bank account may be owned by multiple
 * customers.
 * 
 * (ii) A personal account is owned by a single customer. Furthermore, they are
 * NOT allowed to have a negative balance.
 * 
 */
public class Bank {
	private static int nextId = 1;
	HashMap<String, Customer> mapCustomers = new HashMap<String, Customer>();
	HashMap<String, Account> mapAccounts = new HashMap<String, Account>();


	/**
	 * Creates and registers a new <code>Customer</code> object whose attributes
	 * correspond to the given values.
	 * 
	 * @param firstName the customer's first name
	 * @param lastName  the customer's last name
	 * @param birthDay  the customer's birthday
	 * @return the id of the created and registered customer object
	 */
	public String registerCustomer(String firstName, String lastName, Date birthDay) {
		String customerId;
		Customer c = new Customer(firstName, lastName, birthDay);

		customerId = String.valueOf(nextId);
		c.setId(customerId);
		mapCustomers.put(customerId, c);
		nextId++;

		return customerId;
	}

	/**
	 * Returns the collection of all registered customers that share the given first
	 * name and last name and are also born on the same day (i.e., they share the
	 * same date, month, and year of birth).
	 * 
	 * @param firstName a first name
	 * @param lastName  a last name
	 * @param birthDay  a day of birth
	 * @return the (possibly empty) collection;
	 */
	public Collection<String> getCustomers(String firstName, String lastName, Date birthDay) {
		HashSet<String> sameNLB = new HashSet<String>();
		for (Customer x : mapCustomers.values()) {
			if (x.getFirstName().equals(firstName) && x.getLastName().equals(lastName) && x.getBirthDay().equals(birthDay)) {
				sameNLB.add(x.getId());
			}
		}
		return sameNLB;
	}

	/**
	 * removes the given account from the bank
	 * 
	 * @param accountId the id of the account
	 * @return true if an account with the given id existed and was successfully
	 *         removed; otherwise false
	 */
	public boolean removeAccount(String accountId) {
		if (mapAccounts.containsKey(accountId)) {
			mapAccounts.remove(accountId);
			return true;
		}
		return false;
	}

	/**
	 * creates and registers a new <code>CorporateAccount</code> where the customers
	 * with the given customerIds shall be owners of the account. Note: A corporate
	 * account may have a negative balance. Note: Corporate accounts may be owned by
	 * multiple customers
	 * 
	 * @param customerId the identifiers of already registered customers who will be
	 *                   the owners of the account
	 * @return the unique id of the newly created account as an {@link Optional}; the {@link Optional} is empty if at least one
	 *         of the customers is not registered in the bank
	 */
	public Optional<String> registerCorporateAccount(String... customerId) {
		for (String x : customerId) {
			if (!mapCustomers.containsKey(x)) {
				return Optional.empty();
			}
		}
		CorporateAccount newCorpAccount = new CorporateAccount(customerId);
		mapAccounts.put(newCorpAccount.getId(), newCorpAccount);
		for (String x : customerId) {
			mapCustomers.get(x).addAccount(newCorpAccount);
		}
		return Optional.ofNullable(newCorpAccount.getId());
	}

	/**
	 * creates and registers a new <code>PersonalAccount</code> where customer with
	 * the given customerId shall be the owner of the account.
	 * 
	 * Note: A personal account must never have a negative balance.
	 * 
	 * @param customerId the identifier of an already registered customer who will
	 *                   be the owner of the account
	 * @return the unique id of the newly created account as an {@link Optional}; the {@link Optional} is empty if the customer
	 *         with the specified id is not registered in the bank
	 */
	public Optional<String> registerPersonalAccount(String customerId) {
		if (!mapCustomers.containsKey(customerId)) {
			return Optional.empty();
		}

		PersonalAccount newPersAccount = new PersonalAccount(customerId);
		mapAccounts.put(newPersAccount.getId(), newPersAccount);
		mapCustomers.get(customerId).addAccount(newPersAccount);

		return Optional.ofNullable(newPersAccount.getId());
	}

	/**
	 * Returns the balance of the account with the given id
	 * 
	 * @param accountId the id of the account
	 * @return the balance of the account as an {@link Optional}; the {@link Optional} is empty if no account for this id is
	 *         registered
	 */
	public Optional<BigDecimal> getBalance(String accountId) {
		if (!mapAccounts.containsKey(accountId)) {
			return Optional.empty();
		}
		return Optional.ofNullable(mapAccounts.get(accountId).getBalance());
	}

	/**
	 * Deposit operation. Adds the given amount to the account with the specified id
	 * 
	 * @param accountId the id of the account
	 * @param amount    the amount that shall be deposited
	 * @return true iff the amount was successfully deposited; false otherwise
	 */
	public boolean deposit(String accountId, BigDecimal amount) {
		if (mapAccounts.containsKey(accountId) && amount != null) {
			mapAccounts.get(accountId).deposit(amount);
			return true;
		}
		return false;
	}

	/**
	 * Widthdraw operation. Substracts the given amount from the account with the
	 * specified id
	 * 
	 * @param accountId the id of the account
	 * @param amount    the amount that shall be widhdrawn
	 * @return true iff the amount was successfully withdrawn; false otherwise
	 */
	public boolean withdraw(String accountId, BigDecimal amount) {
		if (mapAccounts.containsKey(accountId) && amount != null) {
			return mapAccounts.get(accountId).withdraw(amount);
		}
		return false;
	}

	/**
	 * Transfer operation. Transfers the given amount between the accounts with the
	 * specified ids
	 * 
	 * @param fromAccountId the id of the account from which the money is transfered
	 * @param toAccountId   the id of the account to which the money is transfered
	 *                      to
	 * @param amount        the amount that shall be transfered
	 * @return true iff the transfer operation was performed successfully; false
	 *         otherwise
	 */
	public boolean transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
		if (mapAccounts.containsKey(fromAccountId)  && mapAccounts.containsKey(toAccountId) && amount != null) {
			if(mapAccounts.get(fromAccountId).withdraw(amount)) {
				mapAccounts.get(toAccountId).deposit(amount);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the collection of accounts that are owned by the customer with the
	 * given id
	 * 
	 * @param customerId the id of the owning customer
	 * @return the (potentially empty) collection of accounts (e.g., a list) as an {@link Optional};
	 *         the {@link Optional} is empty if no customer with the given id is registered
	 */
	public Optional<Collection<String>> getAccounts(String customerId) {
		if (mapCustomers.containsKey(customerId)) {
			return Optional.ofNullable(mapCustomers.get(customerId).getCustomerAccounts());
		}
		else {
			return Optional.empty();
		}
	}

	/**
	 * Returns the total balance of all accounts owned by the customer with the
	 * specified id
	 * 
	 * @param customerId the id of the customer
	 * @return the sum of all accounts owned by the customer as an {@link Optional}; the {@link Optional} is
	 *         empty if no customer with the given id is registered
	 */
	public Optional<BigDecimal> getTotalBalance(String customerId) {
		if (mapCustomers.containsKey(customerId)) {
			return Optional.ofNullable(mapCustomers.get(customerId).getTotalBalance());
		}
		return Optional.empty();
	}

	public HashMap<String, Customer> getAllCustomers() {
		return mapCustomers;
	}
}
