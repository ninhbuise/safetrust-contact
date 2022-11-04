package com.safetrust.contacts.viewmodel;

import com.safetrust.contacts.model.Contact;

public record ContactVm(
        Long id,
        String name,
        String email,
        String phone,
        String postalAddress
) {
    public static ContactVm fromModel(Contact contact) {
        return new ContactVm(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhone(), contact.getPostalAddress());
    }
}
