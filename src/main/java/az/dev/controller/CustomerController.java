package az.dev.controller;


import az.dev.request.ReqCustomer;
import az.dev.response.RespCustomerList;
import az.dev.response.RespStatus;
import az.dev.service.CustomerService;
import az.dev.response.RespCustomer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {


    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping(value = "/getCustomerList")
    public RespCustomerList getCustomerList() {
        return customerService.getCustomerList();
    }

    @GetMapping(value = "/getCustomerById/{customerId}")
    public RespCustomer getCustomerById(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping(value = "/updateCustomer")
    public RespStatus updateCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.updateCustomer(reqCustomer);
    }

    @PostMapping(value = "/createCustomer")
    public RespStatus createCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.createCustomer(reqCustomer);
    }

    @PostMapping(value = "/deleteCustomer/{customerId}")
    public RespStatus deleteCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.deleteCustomer(customerId);
    }

}
