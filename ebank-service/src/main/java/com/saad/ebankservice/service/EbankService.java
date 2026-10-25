package com.saad.ebankservice.service;

import com.saad.ebankservice.entities.BankAccount;
import com.saad.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbankService {
    private BankAccountRepository bankAccountRepository;

    public EbankService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
    public List<BankAccount> getAllBankAccounts (){
        return bankAccountRepository.findAll();
    }
    public BankAccount getBankAccountById(String id)
    {
        return bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found"));
    }
    public BankAccount save(BankAccount bankAccount)
    {
        return bankAccountRepository.save(bankAccount);
    }
}
