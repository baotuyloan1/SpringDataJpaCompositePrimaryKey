package com.example.demo;

import com.example.demo.airport.PhoneContact;
import com.example.demo.airport.PhoneContactRepository;
import com.example.demo.airport.PhoneID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class PhoneContactRepositoryTests {

    @Autowired
    private PhoneContactRepository repository;

    @Test
    public void testSaveNew() {
        PhoneID id = new PhoneID(1, "1251231541");
        PhoneContact newContact = new PhoneContact();
        newContact.setId(id);
        newContact.setFirstName("John");
        newContact.setLastName("Kellyson");

        PhoneContact savedPhoneContact = repository.save(newContact);

        assertThat(savedPhoneContact).isNotNull();
        assertThat(savedPhoneContact.getId().getAreaCode()).isEqualTo(1);
        assertThat(savedPhoneContact.getId().getNumber()).isEqualTo("1251231541");
    }

    @Test
    public void testListAll() {
        List<PhoneContact> contacts = repository.findAll();

        assertThat(contacts).isNotNull();
        for (PhoneContact s : contacts) System.out.println(s);
    }

    @Test
    public void testFindById() {
        PhoneID id = new PhoneID(1, "1251231541");
        Optional<PhoneContact> phoneContact = repository.findById(id);
        assertThat(phoneContact).isPresent();
        assertThat(phoneContact.get().getFirstName()).isEqualTo("John");
    }
}
