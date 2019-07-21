package tacos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    @OneToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @NotBlank(message = "required")
    private String name;

    @NotBlank(message = "required")
    private String street;

    @NotBlank(message = "required")
    private String city;

    @NotBlank(message = "required")
    private String state;

    @NotBlank(message = "required")
    private String zip;

    //@CreditCardNumber(message = "invalid card")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be MM/YY format")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "invalid CVV")
    private String ccCVV;

}