package com.example.demo.controller;

import com.example.demo.models.dto.DetallePedidoDTO;
import com.example.demo.services.DetallePedidoService;
import com.example.demo.services.OrdenPedidoService;
import com.example.demo.services.ProductoService;
import com.example.demo.services.ProduccionService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/detalles-pedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private OrdenPedidoService ordenPedidoService;

    @Autowired
    private ProduccionService produccionService;

    // Listar Detalles de Pedido
    @GetMapping
    public String listarDetalles(Model model) {
        List<DetallePedidoDTO> detalles = detallePedidoService.findAll();
        model.addAttribute("detalles", detalles);
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("ordenes", ordenPedidoService.findAll());
        model.addAttribute("producciones", produccionService.findAll());
        model.addAttribute("detalle", new DetallePedidoDTO());
        return "detalles-pedido";
    }

    // Generar PDF con los Detalles de Pedido
    @GetMapping("/imprimir-pdf")
    public void imprimirDetallesEnPDF(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=detalles-pedido.pdf");
    
            OutputStream outputStream = response.getOutputStream();
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
    
            document.add(new Paragraph("Detalles de Pedido").setBold().setFontSize(14));
    
            Table table = new Table(new float[]{2, 3, 3, 3});
            table.setWidth(UnitValue.createPercentValue(100));
    
            table.addCell(new Cell().add(new Paragraph("Cantidad").setBold()));
            table.addCell(new Cell().add(new Paragraph("Producto").setBold()));
            table.addCell(new Cell().add(new Paragraph("Orden de Pedido").setBold()));
            table.addCell(new Cell().add(new Paragraph("Producción").setBold()));
    
            List<DetallePedidoDTO> detalles = detallePedidoService.findAll();
            for (DetallePedidoDTO detalle : detalles) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(detalle.getCantidad()))));
                table.addCell(new Cell().add(new Paragraph(
                        detalle.getProducto() != null 
                        ? "Código: " + detalle.getProducto().getCodigo() + " - " + detalle.getProducto().getNombre() 
                        : "Producto no disponible"
                )));
                table.addCell(new Cell().add(new Paragraph(
                        detalle.getOrdenpedido() != null 
                        ? "Número: " + detalle.getOrdenpedido().getNumeroPedido() 
                        : "Orden no disponible"
                )));
                table.addCell(new Cell().add(new Paragraph(
                        detalle.getProduccion() != null 
                        ? "Código: " + detalle.getProduccion().getCodigoProduccion() 
                        : "N/A"
                )));
            }
    
            document.add(table);
            document.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/imprimir-pdf/{id}")
    public void imprimirDetalleEnPDF(@PathVariable Long id, HttpServletResponse response) {
        try {
            // Obtener el detalle del pedido por ID
            DetallePedidoDTO detalle = detallePedidoService.findById(id);
            if (detalle == null) {
                throw new RuntimeException("Detalle no encontrado");
            }

            // Configurar la respuesta HTTP para PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=factura-detalle-" + id + ".pdf");

            // Crear el documento PDF
            OutputStream outputStream = response.getOutputStream();
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Configurar márgenes
            document.setMargins(20, 20, 20, 20);

            // Encabezado del documento
            document.add(new Paragraph("Factura de Pedido")
                    .setBold()
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Empresa Extincor")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Dirección: Calle Principal #123, Ciudad")
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Teléfono: +1 234 567 890, Email: info@empresa.com")
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            // Información del cliente y pedido
            document.add(new Paragraph("Información del Cliente").setBold());
            document.add(new Paragraph("Cliente: " +
                    (detalle.getOrdenpedido() != null && detalle.getOrdenpedido().getCliente() != null
                            ? detalle.getOrdenpedido().getCliente().getNombre()
                            : "No disponible")));
            document.add(new Paragraph("Dirección: " +
                    (detalle.getOrdenpedido() != null && detalle.getOrdenpedido().getCliente() != null
                            ? detalle.getOrdenpedido().getCliente().getDireccion()
                            : "No disponible")));
            document.add(new Paragraph("Teléfono: " +
                    (detalle.getOrdenpedido() != null && detalle.getOrdenpedido().getCliente() != null
                            ? detalle.getOrdenpedido().getCliente().getTelefono()
                            : "No disponible")));
            document.add(new Paragraph("Fecha del Pedido: " +
                    (detalle.getOrdenpedido() != null
                            ? detalle.getOrdenpedido().getFechaPedido()
                            : "No disponible")));
            document.add(new Paragraph("\n"));

            // Tabla con los detalles del pedido
            Table table = new Table(new float[]{2, 5, 3, 3});
            table.setWidth(UnitValue.createPercentValue(100));

            // Encabezados de la tabla
            table.addHeaderCell(new Cell().add(new Paragraph("Cantidad").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Producto").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Orden de Pedido").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Producción").setBold()));

            // Agregar datos a la tabla
            table.addCell(new Cell().add(new Paragraph(String.valueOf(detalle.getCantidad()))));
            table.addCell(new Cell().add(new Paragraph(
                    detalle.getProducto() != null
                            ? "Código: " + detalle.getProducto().getCodigo() + ", Nombre: " + detalle.getProducto().getNombre()
                            : "Producto no disponible"
            )));
            table.addCell(new Cell().add(new Paragraph(
                    detalle.getOrdenpedido() != null
                            ? "Número: " + detalle.getOrdenpedido().getNumeroPedido()
                            : "Orden no disponible"
            )));
            table.addCell(new Cell().add(new Paragraph(
                    detalle.getProduccion() != null
                            ? "Código: " + detalle.getProduccion().getCodigoProduccion()
                            : "Producción no disponible"
            )));

            // Agregar la tabla al documento
            document.add(table);

            // Mensaje de agradecimiento
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Gracias por su compra")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(12));

            // Cerrar el documento
            document.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Guardar o actualizar un detalle de pedido
    @PostMapping("/guardar")
    public String saveDetallePedido(@ModelAttribute("detalle") DetallePedidoDTO detallePedidoDTO, Model model) {
        try {
            detallePedidoService.save(detallePedidoDTO);
            return "redirect:/detalles-pedido";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el detalle de pedido.");
            model.addAttribute("detalles", detallePedidoService.findAll());
            model.addAttribute("productos", productoService.findAll());
            model.addAttribute("ordenes", ordenPedidoService.findAll());
            model.addAttribute("producciones", produccionService.findAll());
            return "detalles-pedido";
        }
    }

    // Editar un detalle de pedido
    @GetMapping("/editar/{id}")
    public String editDetalle(@PathVariable Long id, Model model) {
        model.addAttribute("detalles", detallePedidoService.findAll());
        model.addAttribute("detalle", detallePedidoService.findById(id));
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("ordenes", ordenPedidoService.findAll());
        model.addAttribute("producciones", produccionService.findAll());
        return "detalles-pedido";
    }

    // Eliminar un detalle de pedido
    @GetMapping("/eliminar/{id}")
    public String deleteDetalle(@PathVariable Long id) {
        detallePedidoService.deleteById(id);
        return "redirect:/detalles-pedido";
    }
}
