package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import repo.entity.MyImage;
import repo.entity.MyPoint;
import repo.repository.ImageRepository;
import repo.repository.PointRepository;
import repo.service.ImageToVectorConverter;
import repo.service.VectorToImageConverter;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by Vlad.M on 18.08.2016.
 */
@Controller
@RequestMapping(value = "/service")
public class MainController {
    @Autowired
    ImageToVectorConverter imageToVectorConverter;
    @Autowired
    VectorToImageConverter vectorToImageConverter;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    PointRepository pointRepository;
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String loadPage(){
        pointRepository.save(new MyPoint(1,1));
        return "addpicture";
    }
    @RequestMapping(value = "/saveimage", method = RequestMethod.POST)
    public String saveImage(@RequestParam("foreignkey") String foreignkey,@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
            BufferedImage loadedImage = (BufferedImage) file;
        java.util.Date date = new java.util.Date();
        MyImage myImage = new MyImage(imageToVectorConverter.convertTo2DWithoutUsingGetRGB(loadedImage), new java.sql.Date(date.getTime()),
                name,loadedImage.getHeight(),loadedImage.getWidth(),Integer.parseInt(foreignkey));
        imageRepository.save(myImage);
//            byte[] bytes = new byte[0];
//            bytes = file.getBytes();
//            BufferedOutputStream stream =
//                    new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
//            stream.write(bytes);
//            stream.close();
            return "addpage";
    }
    @RequestMapping (value = "/loadall", method = RequestMethod.POST)
    public String loadSaved(ModelMap map){
        map.addAttribute("pictures",imageRepository.findAll());
        return "mainpage";
    }

    @RequestMapping(value = "/getImage/{imageId}")
    @ResponseBody
    public byte[] helloWorld(@PathVariable Integer imageId)  {
        BufferedImage image = (BufferedImage) VectorToImageConverter.drawImage(imageRepository.findOne(imageId));
        return( (DataBufferByte)(image.getRaster().getDataBuffer())).getData();
    }
}
