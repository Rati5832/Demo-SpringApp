package FirstSpringApplication.DemoSpringApp.Mapper;

import FirstSpringApplication.DemoSpringApp.Domain.Contact;
import FirstSpringApplication.DemoSpringApp.Model.ContactDTO;
import FirstSpringApplication.DemoSpringApp.Model.ContactRequest;
import FirstSpringApplication.DemoSpringApp.Model.ContactResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDTO contactToContactDTO(Contact contact);

    Contact contactDtoTcontact(ContactRequest contactReq);

    ContactRequest contactToContactres(Contact contact);

    ContactResponseDTO contactToDto(Contact contact);

}
