package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PatchMapping("/{uuid}")
  public ResponseEntity<Void> patchCustomerById(
      @PathVariable UUID uuid, @RequestBody Customer customer) {
    customerService.patchCustomerById(uuid, customer);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{uuid}")
  public ResponseEntity<Void> deleteCustomerById(@PathVariable UUID uuid) {
    customerService.deleteCustomerById(uuid);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{uuid}")
  public ResponseEntity<Void> updateCustomer(
      @PathVariable UUID uuid, @RequestBody Customer customer) {
    customerService.updateCustomer(uuid, customer);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Void> saveNewCustomer(
      @RequestBody Customer customer, UriComponentsBuilder uriComponentsBuilder) {
    Customer savedNewCustomer = customerService.saveNewCustomer(customer);
    URI uri =
        uriComponentsBuilder
            .path("/api/v1/customer/{id}")
            .buildAndExpand(savedNewCustomer.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping
  public List<Customer> listCustomers() {
    return customerService.listCustomers();
  }

  @GetMapping("/{customerId}")
  public Customer getCustomerById(@PathVariable UUID customerId) {
    return customerService.getCustomerById(customerId);
  }
}
