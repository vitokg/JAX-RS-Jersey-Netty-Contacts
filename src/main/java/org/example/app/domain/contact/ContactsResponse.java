package org.example.app.domain.contact;

import java.util.List;

public class ContactsResponse {

    private final List<Contact> data;

    public ContactsResponse(List<Contact> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return " \"data\" : " + data;
    }
}
