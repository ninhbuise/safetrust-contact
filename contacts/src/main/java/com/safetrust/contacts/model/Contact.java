package com.safetrust.contacts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String postalAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contact)) {
            return false;
        }
        return id != null && id.equals(((Contact) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
