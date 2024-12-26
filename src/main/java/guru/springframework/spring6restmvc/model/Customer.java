package guru.springframework.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Customer {
    private String customerName;
    private UUID id;
    private Integer version;
    private Date createdDate;
    private Date lastModifiedDate;

}
