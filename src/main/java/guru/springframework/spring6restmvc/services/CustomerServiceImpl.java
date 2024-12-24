package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    Map<UUID, Customer> map;

    public CustomerServiceImpl(Map<UUID, Customer> map) {
        this.map = new HashMap<>();
        Customer customer = Customer.builder()
                .customerName("John Thompson")
                .id(UUID.randomUUID())
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .version(1)
                .build();
        Customer customer1 = Customer.builder()
                .customerName("Spring Framework Guru")
                .id(UUID.randomUUID())
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .version(1)
                .build();
        Customer customer2 = Customer.builder()
                .customerName("Devang")
                .id(UUID.randomUUID())
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .version(1)
                .build();
        map.put(customer2.getId(), customer2);
        map.put(customer1.getId(), customer1);
        map.put(customer.getId(), customer);
    }

    @Override
    public Customer getCustomerById(UUID uuid) {
        return map.get(uuid);
    }

    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(map.values());
    }

}
