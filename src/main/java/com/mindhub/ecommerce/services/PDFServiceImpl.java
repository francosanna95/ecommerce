package com.mindhub.ecommerce.services;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.mindhub.ecommerce.dtos.UserProductDTO;
import com.mindhub.ecommerce.models.User;
import com.mindhub.ecommerce.models.UserProduct;
import com.mindhub.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

@Service
public class PDFServiceImpl implements PDFService {

    @Autowired
    private UserRepository clientRepo;


    @Override
    public ByteArrayOutputStream generatePDF(HttpServletResponse response, User client, Set<UserProductDTO> sales) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //La linea 56 perimte que una vez creado el pdf se guarde en la response,
        //eso hace que se descargue al encontrar la respuesta
        PdfWriter pdfWriter = new PdfWriter(baos);
        PdfDocument pdfDocument = new PdfDocument((pdfWriter));
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A4);
        String date = LocalDateTime.now().format(ISO_LOCAL_DATE);

        //Concepto similar a grid de css
        float col = 280f;
        float columnWidth[] = {col, col};

        Table table = new Table(columnWidth);
        String imgFile = "https://res.cloudinary.com/melbastrips/image/upload/v1639001581/Profiles/melba-logo-ps_gk4bqz.png";
        ImageData data = ImageDataFactory.create(imgFile);
        Image img = new Image(data);
        img.setMaxHeight(125);
        img.setMarginLeft(10f);

        table.setBackgroundColor(new DeviceRgb(5, 71, 105))
                .setFontColor(new DeviceRgb(255, 255, 255));
        table.addCell(new Cell().add(img).setMarginRight(15f).setVerticalAlignment(VerticalAlignment.MIDDLE).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("MindHub Trips\n#2025 Your experience Factory\n" + date))
                        .setFontSize(20f)
                .setTextAlignment(TextAlignment.RIGHT)
                .setPaddings(30f, 10f, 30f, 0f)
                .setBorder(Border.NO_BORDER));

        float colWidth[] = {80f, 300f, 100f, 80f};
        Table clientTableInfo = new Table(columnWidth);

        clientTableInfo.addCell(new Cell(0, 4)
                .add(new Paragraph("Client Information"))
                .setBold()
                .setBorder(Border.NO_BORDER));

        clientTableInfo.addCell(new Cell()
                .add(new Paragraph("Client Name: "))
                .setBorder(Border.NO_BORDER));
        clientTableInfo.addCell(new Cell()
                .add(new Paragraph(client.getFullName()))
                .setBorder(Border.NO_BORDER));
        clientTableInfo.addCell(new Cell()
                .add(new Paragraph("Account N°: "))
                .setBorder(Border.NO_BORDER));
        clientTableInfo.addCell(new Cell()
                .add(new Paragraph(client.getBankAccountNumber()))
                .setBorder(Border.NO_BORDER));
        clientTableInfo.addCell(new Cell()
                .add(new Paragraph("Date: "))
                .setBorder(Border.NO_BORDER));
        clientTableInfo.addCell(new Cell()
                .add(new Paragraph(date))
                .setBorder(Border.NO_BORDER));


        float itemInfoColWidth[] = {70f, 135f, 225f, 80f, 70f};
        Table dataTable = new Table(itemInfoColWidth);
        dataTable.setFontSize(10f);

        dataTable.addCell(new Cell()
                .setBackgroundColor(new DeviceRgb(5, 71, 105))
                .setFontColor(new DeviceRgb(255, 255, 255))
                .add(new Paragraph("Product Id")).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        dataTable.addCell(new Cell()
                .setBackgroundColor(new DeviceRgb(5, 71, 105))
                .setFontColor(new DeviceRgb(255, 255, 255))
                .add(new Paragraph("Product Type")).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        dataTable.addCell(new Cell()
                .setBackgroundColor(new DeviceRgb(5, 71, 105))
                .setFontColor(new DeviceRgb(255, 255, 255))
                .add(new Paragraph("Product Name")).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        dataTable.addCell(new Cell()
                .setBackgroundColor(new DeviceRgb(5, 71, 105))
                .setFontColor(new DeviceRgb(255, 255, 255))
                .add(new Paragraph("Quantity")).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        dataTable.addCell(new Cell()
                .setBackgroundColor(new DeviceRgb(5, 71, 105))
                .setFontColor(new DeviceRgb(255, 255, 255))
                .add(new Paragraph("Amount")).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));

        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(clientTableInfo);
        document.add(new Paragraph("\n"));
        dataTable.startNewRow();


        for (UserProductDTO sale : sales) {
            dataTable.setFontSize(10f);
            dataTable.addCell(new Cell().add(new Paragraph(String.valueOf(sale.getId()))));
            dataTable.addCell(new Cell().add(new Paragraph(sale.getProductType())));
            dataTable.addCell(new Cell().add(new Paragraph(sale.getProductName())));
            dataTable.addCell(new Cell().add(new Paragraph(String.valueOf(sale.getQuantity()))));
            dataTable.addCell(new Cell().add(new Paragraph("$" + sale.getFinalPrice())));
            dataTable.startNewRow();
        }

        document.add(dataTable);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("PURCHASE DETAILS").setUnderline().setTextAlignment(TextAlignment.CENTER));

        for (UserProductDTO sale : sales){
            Paragraph p = new Paragraph();
            p.add(sale.toString()).setItalic();
            document.add(p);
            document.add(new Paragraph("\n"));
        }
        Table footer = new Table(1).useAllAvailableWidth();


        Cell cell = new Cell().add(new Paragraph("Melba Trips | Your lifetime holidays ").setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER);
        footer.addCell(cell);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        cell = new Cell().add(new Paragraph(LocalDateTime.now().format(formatter)).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER);
        footer.addCell(cell);
        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new TableFooterEventHandler(footer));
        document.close();
        return baos;
    }

    private static class TableFooterEventHandler implements IEventHandler {
        private Table table;

        public TableFooterEventHandler(Table table) {
            this.table = table;
        }

        @Override
        public void handleEvent(Event currentEvent) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) currentEvent;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

            new Canvas(canvas, new Rectangle(36, 20, page.getPageSize().getWidth() - 72, 50))
                    .add(table)
                    .close();
        }
    }

}
