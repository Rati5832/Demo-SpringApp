package FirstSpringApplication.DemoSpringApp.Controller;

import FirstSpringApplication.DemoSpringApp.Model.ContactListDTO;
import FirstSpringApplication.DemoSpringApp.Model.ContactRequest;
import FirstSpringApplication.DemoSpringApp.Model.ContactResponseDTO;
import FirstSpringApplication.DemoSpringApp.Model.ContactUpdateRequest;
import FirstSpringApplication.DemoSpringApp.Services.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ContactController.BASE_URL)
public class ContactController {
    public static final String BASE_URL = "/api/v1/contacts";
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @Operation(summary = "Get Contact ", description = "Contact  shesaxeb ")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ContactListDTO getAllContact(){

        return new ContactListDTO
                (contactService.getAllContact());
    }
    @Operation(summary = "Get Contact by id ", description = "Contact id  shesaxeb ")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ContactResponseDTO getContactById(@PathVariable Long id){
        return contactService.getContactsById(id);
    }

    @Operation(summary = "Create Contact  ", description = "Contact sheqmnis  shesaxeb ")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponseDTO createNewContact(@Valid @RequestBody ContactRequest contactRequest){
        return contactService.createNewContact(contactRequest);
    }
    @Operation(summary = "update Contact ", description = "Contact update  shesaxeb ")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ContactResponseDTO updateContact(@PathVariable Long id,@Valid @RequestBody ContactUpdateRequest contactUpdateRequest){

        return contactService.updateContactByDTO(id, contactUpdateRequest);
    }
    @Operation(summary = "Delete Contact by id ", description = "Contact delete  shesaxeb ")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeam(@PathVariable Long id){
        contactService.deleteContactById(id);
    }
}
