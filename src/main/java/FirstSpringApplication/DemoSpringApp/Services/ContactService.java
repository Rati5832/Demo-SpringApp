package FirstSpringApplication.DemoSpringApp.Services;

import FirstSpringApplication.DemoSpringApp.Model.ContactRequest;
import FirstSpringApplication.DemoSpringApp.Model.ContactResponseDTO;
import FirstSpringApplication.DemoSpringApp.Model.ContactUpdateRequest;

import java.util.List;

public interface ContactService {

    List<ContactResponseDTO> getAllContact();

    ContactResponseDTO getContactsById(Long id);

    ContactResponseDTO createNewContact(ContactRequest contactReq);

    ContactResponseDTO updateContactByDTO(Long id, ContactUpdateRequest contactUpdateReq);

    void deleteContactById(Long id);
}
