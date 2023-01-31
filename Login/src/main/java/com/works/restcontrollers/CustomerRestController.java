package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerRestController {

    final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register( @RequestBody Customer customer ) {
        return customerService.register(customer);
    }

    @PostMapping("/login")
    public ResponseEntity login( @Valid @RequestBody Customer customer ) {
        return customerService.login(customer);
    }

    @PostMapping("/status")
    public ResponseEntity status(HttpServletRequest req) {
        return customerService.status(req);
    }

}
