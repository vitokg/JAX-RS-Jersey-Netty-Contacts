package org.example.app.domain.contact;

public class ContactResponse {

    private final Contact data;

    public ContactResponse(Contact data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return " \"data\" : " + data;
    }
}
