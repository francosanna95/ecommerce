package com.mindhub.ecommerce.services;

import com.itextpdf.layout.Document;
import com.mindhub.ecommerce.dtos.UserProductDTO;
import com.mindhub.ecommerce.models.User;
import com.mindhub.ecommerce.models.UserProduct;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

public interface PDFService {
    ByteArrayOutputStream generatePDF(HttpServletResponse response, User client, Set<UserProductDTO> sales) throws IOException;
}
