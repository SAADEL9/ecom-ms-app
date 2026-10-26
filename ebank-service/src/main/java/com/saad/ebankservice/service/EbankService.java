package com.saad.ebankservice.service;

import com.saad.ebankservice.entities.BankAccount;
import com.saad.ebankservice.feign.CustomerRestClient;
import com.saad.ebankservice.model.Customer;
import com.saad.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    public EbankService(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }


    public List<BankAccount> getAllBankAccounts (){
        return bankAccountRepository.findAll();
    }
    public BankAccount getBankAccountById(String id)
    {
        BankAccount bankAccount= bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found"));
        bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
        return bankAccount;
    }
    public BankAccount save(BankAccount bankAccount)
    {  try{
        Customer customer=customerRestClient.getCustomerById(bankAccount.getCustomerId());
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        return bankAccountRepository.save(bankAccount); } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
    }
    }
}
