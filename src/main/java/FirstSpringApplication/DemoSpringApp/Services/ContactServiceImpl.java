package FirstSpringApplication.DemoSpringApp.Services;

import FirstSpringApplication.DemoSpringApp.Domain.Contact;
import FirstSpringApplication.DemoSpringApp.Domain.Student;
import FirstSpringApplication.DemoSpringApp.Mapper.ContactMapper;
import FirstSpringApplication.DemoSpringApp.Mapper.StudentMapper;
import FirstSpringApplication.DemoSpringApp.Model.ContactRequest;
import FirstSpringApplication.DemoSpringApp.Model.ContactResponseDTO;
import FirstSpringApplication.DemoSpringApp.Model.ContactUpdateRequest;
import FirstSpringApplication.DemoSpringApp.Model.StudentDTO;
import FirstSpringApplication.DemoSpringApp.Repositories.ContactRepository;
import FirstSpringApplication.DemoSpringApp.Repositories.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final StudentMapper studentMapper;
    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;
    private final StudentRepository studentRepository;

    public ContactServiceImpl(StudentMapper studentMapper, ContactMapper contactMapper, ContactRepository contactRepository,
                              StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.contactMapper = contactMapper;
        this.contactRepository = contactRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<ContactResponseDTO> getAllContact() {
        return contactRepository
                .findAll()
                .stream()
                .map(contact -> {
                    ContactResponseDTO contactResponseDTO = contactMapper.contactToDto(contact);
                    contactResponseDTO.setContactId(contact.getId());
                    contactResponseDTO.setStudentId(contact.getStudent().getId());
                    return contactResponseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ContactResponseDTO getContactsById(Long id) {
        Contact contact1 = contactRepository.findById(id).get();
        Student student1 = studentRepository.findById(contact1.getStudent().getId()).get();
        StudentDTO studentDTO = studentMapper.studentToDto(student1);
        ContactResponseDTO contactResponse = contactMapper.contactToDto(contact1);
        contactResponse.setStudentId(contact1.getStudent().getId());
        contactResponse.setContactId(contact1.getId());
        return contactResponse;

    }

    @Override
    @Transactional
    public ContactResponseDTO createNewContact(ContactRequest contactReq) {
        Optional<Contact> contactOpt = contactRepository.findByContactvalue(contactReq.getContactvalue());
        if(contactOpt.isPresent()) {
            throw new RuntimeException ("Contactvalue already exists");
        } else {
            switch(contactReq.getContacttype()) {
                case PHONENUMBER : {
                    if(!StringUtils.isNumeric(contactReq.getContactvalue())){
                        throw new IllegalArgumentException("Phonenumber must be numeric");
                    }
                    break;
                }
            }
            Student student = studentRepository.findById(contactReq.getStudentId()).get();
            Contact contact = contactMapper.contactDtoTcontact(contactReq);

            contact.setStudent(student);

            contact = contactRepository.save(contact);

            StudentDTO studentDTO = studentMapper.studentToDto(student);
            ContactResponseDTO contact1 = contactMapper.contactToDto(contact);
            contact1.setStudentId(student.getId());
            contact1.setContactId(contact.getId());

            return contact1;
        }

    }

    @Override
    @Transactional
    public ContactResponseDTO updateContactByDTO(Long id, ContactUpdateRequest contactUpdateReq) {
        Contact contact = contactRepository.findById(id).get();
        Optional<Student> student1 = studentRepository.findById(contact.getStudent().getId());
        Student student = student1.get();
        contact.setContacttype(contactUpdateReq.getContacttype());
        contact.setContactvalue(contactUpdateReq.getContactvalue());
        contact.setStudent(student);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAdress(student.getAdress());
        studentDTO.setFirstname(student.getFirstname());
        studentDTO.setLastname(student.getLastname());
        studentDTO.setIdnumber(student.getIdnumber());
        ContactResponseDTO contactResponse = new ContactResponseDTO();
        contactResponse.setContacttype(contact.getContacttype());
        contactResponse.setContactvalue(contact.getContactvalue());
        contactResponse.setStudent(studentDTO);
        contactResponse.setStudentId(student.getId());
        contactResponse.setContactId(contact.getId());
        return contactResponse;

    }

    @Override
    @Transactional
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }
}
