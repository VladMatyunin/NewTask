package controller.utils;

/**
 * Created by Vlad.M on 26.08.2016.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import repo.entity.MyImage;
import repo.repository.ImageRepository;
import repo.service.VectorToImageConverter;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@WebServlet(name = "imgcoverter", urlPatterns = {"/getimg"}, initParams = {
        })
public class ConverterServlet extends HttpServlet {
    @Autowired
    VectorToImageConverter vectorToImageConverter;
    @Autowired
    ImageRepository imageRepository;
        private static final long serialVersionUID = 4190636964925948290L;

        public static final String CAPTCHA_KEY = "captcha_key_name";

        protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //Expire response
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Max-Age", 0);
            String id = request.getParameter("id");
//            String type  = request.getParameter("type");
            OutputStream outputStream = response.getOutputStream();
            Integer Image_id = Integer.parseInt(id);
            if (imageRepository.exists(Image_id)){
                PrintWriter out = response.getWriter();
                out.print("There is no picture with that id");
            }
            else{
            MyImage image = imageRepository.findOne(Image_id);
            Image newImage = vectorToImageConverter.drawImage(image);
                ImageIO.write(toBufferedImage(newImage), "png", outputStream);
            }
            HttpSession session = request.getSession(true);







            outputStream.close();
        }

        private BufferedImage toBufferedImage(Image img) {
            if (img instanceof BufferedImage) {
                return (BufferedImage) img;
            }

            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();
            return bimage;
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //response.getWriter().println("Hello World!");
            processRequest(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            processRequest(request, response);
        }

        @Override
        public String getServletInfo() {
            return "Short description";
        } // </editor-fold>
    private WebApplicationContext springContext;
    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
    }


