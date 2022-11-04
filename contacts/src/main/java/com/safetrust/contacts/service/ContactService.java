package com.safetrust.contacts.service;

import com.safetrust.contacts.execption.BadRequestException;
import com.safetrust.contacts.execption.NotFoundException;
import com.safetrust.contacts.model.Contact;
import com.safetrust.contacts.repository.ContactRepository;
import com.safetrust.contacts.viewmodel.ContactPostVm;
import com.safetrust.contacts.viewmodel.ContactVm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactVm> getContact() {
        return contactRepository.findAll()
                .stream().map(ContactVm::fromModel)
                .toList();
    }

    public ContactVm getContact(Long id) {
        return ContactVm.fromModel(contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Contact %s is not found", id))));
    }

    public ContactVm createContact(ContactPostVm contactPostVm) {
        if (!contactRepository.findByEmail(contactPostVm.email()).isEmpty())
            throw new BadRequestException(String.format("Already has contact with email: %s", contactPostVm.email()));

        if (!contactRepository.findByPhone(contactPostVm.phone()).isEmpty())
            throw new BadRequestException(String.format("Already has contact with phone: %s", contactPostVm.phone()));

        Contact contact = new Contact();
        contact.setEmail(contactPostVm.email());
        contact.setName(contactPostVm.name());
        contact.setPhone(contactPostVm.phone());
        contact.setPostalAddress(contactPostVm.postalAddress());

        return ContactVm.fromModel(contactRepository.saveAndFlush(contact));
    }

    public ContactVm updateContact(ContactPostVm contactPostVm, Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Contact %s is not found", id)));

        if (!contactRepository.findByEmail(contactPostVm.email()).isEmpty())
            throw new BadRequestException(String.format("Already has contact with email: %s", contactPostVm.email()));

        if (!contactRepository.findByPhone(contactPostVm.phone()).isEmpty())
            throw new BadRequestException(String.format("Already has contact with phone: %s", contactPostVm.phone()));

        contact.setEmail(contactPostVm.email());
        contact.setName(contactPostVm.name());
        contact.setPhone(contactPostVm.phone());
        contact.setPostalAddress(contactPostVm.postalAddress());

        return ContactVm.fromModel(contactRepository.saveAndFlush(contact));
    }

    public void deleteContact(Long id) {
        getContact(id);
        contactRepository.deleteById(id);
    }
}
