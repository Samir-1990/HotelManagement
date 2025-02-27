package az.dev.service;

import az.dev.request.ReqCustomer;
import az.dev.response.RespCustomerList;
import az.dev.response.RespStatus;
import az.dev.response.RespCustomer;

public interface CustomerService {

    RespCustomerList getCustomerList();

    RespCustomer getCustomerById(Long customerId);

    RespStatus updateCustomer(ReqCustomer reqCustomer);

    RespStatus createCustomer(ReqCustomer reqCustomer);

    RespStatus deleteCustomer(Long customerId);

}
