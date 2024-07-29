package org.bank.demo.BL;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Currency;
import org.bank.demo.beans.Customer;
import org.bank.demo.dao.AccountDAO;
import org.bank.demo.dao.CustomerDAO;
import org.bank.demo.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerBL {
    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private AccountBL accountBL;

    public List<Customer> getAllCustomers(){
        return this.customerDAO.findAll();
    }

    public Customer addCustomer(Customer customer) throws CustomerAlreadyExistException, Exception {
        if(customer !=null){

            if(customer.getIDNumber() == null){
                throw new IdNumberNotValidException();
            }
            Customer existingCustomer = this.customerDAO.findCustomerByIDNumber(customer.getIDNumber());
            if(existingCustomer != null)
            {
                throw new CustomerAlreadyExistException(existingCustomer);
            }
            return this.customerDAO.save(customer);
        }
        throw new Exception("InvalidCustomerData");
    }


    public Customer getCustomer(int id) throws CustomerNotFoundException {
        Optional<Customer> customer = Optional.ofNullable(this.customerDAO.findCustomerByIDNumber(id));
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new CustomerNotFoundException();
    }

    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
        Optional<Customer> existingCustomer = Optional.ofNullable(this.customerDAO.findCustomerByIDNumber(customer.getIDNumber()));
        if(existingCustomer.isEmpty())
        {
            throw new CustomerNotFoundException();
        }
        return this.customerDAO.save(customer);
    }

    public Account addAccountToCustomer(Account account, Customer customer) throws CurrencyNotFoundException, CustomerNotFoundException {

        customer = this.getCustomer(customer.getIDNumber());

        account.setCustomer(customer);
        customer.addAccount(account);

        this.updateCustomer(customer);
        try {
            this.accountBL.addAccount(account);
        } catch (AccountAlreadyExistException e) {
            account =e.getExistingAccount();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return account;
    }
}
