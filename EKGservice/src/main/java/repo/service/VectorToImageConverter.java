package repo.service;

import org.springframework.stereotype.Component;
import repo.entity.MyImage;
import repo.entity.MyPoint;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Vlad.M on 18.08.2016.
 */
@Component
public class VectorToImageConverter {
    public static Image drawImage(MyImage myImage){
        BufferedImage drawnImage = new BufferedImage(myImage.getWidth(), myImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics myGraphics = drawnImage.getGraphics();
        MyPoint[] myPoints = myImage.getMyPoints();
        myGraphics.setColor(Color.BLACK);
        for (int i = 1; i < myPoints.length-1; i++) {
            myGraphics.drawLine(i,myPoints[i].getY(),i+1,myPoints[i+1].getY());
        }
        myGraphics.dispose();
        return (Image) drawnImage;
    }
}
