package FirstSpringApplication.DemoSpringApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

    private Long id;
    private String contacttype;
    private String contactvalue;

}
