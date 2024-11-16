package com.demo.MSAccountDemo.service.impl;


import com.demo.MSAccountDemo.dto.AccountsDto;
import com.demo.MSAccountDemo.dto.CardsDto;
import com.demo.MSAccountDemo.dto.CustomerDetailsDto;
import com.demo.MSAccountDemo.dto.LoansDto;
import com.demo.MSAccountDemo.entity.Accounts;
import com.demo.MSAccountDemo.entity.Customer;
import com.demo.MSAccountDemo.exception.ResourceNotFoundException;
import com.demo.MSAccountDemo.mapper.AccountsMapper;
import com.demo.MSAccountDemo.mapper.CustomerMapper;
import com.demo.MSAccountDemo.repository.AccountsRepository;
import com.demo.MSAccountDemo.repository.CustomerRepository;
import com.demo.MSAccountDemo.service.ICustomersService;
import com.demo.MSAccountDemo.service.client.CardsFeignClient;
import com.demo.MSAccountDemo.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String correlationId,String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails( correlationId, mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }

}
