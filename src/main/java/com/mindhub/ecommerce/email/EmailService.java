package com.mindhub.ecommerce.email;

import com.itextpdf.layout.Document;

public interface EmailService {

    void send(String to, String email, byte[] bytes);

    String createEmail(String name, String lastName);
}
