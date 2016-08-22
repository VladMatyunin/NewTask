package repo.service;

import org.springframework.stereotype.Component;
import repo.entity.MyPoint;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by Vlad.M on 18.08.2016.
 */
@Component
public class ImageToVectorConverter {
    public static int[][] fuck(BufferedImage image) {
        int[][] result = convertTo2DWithoutUsingGetRGB(image);
        int[][] finalArray = new int[image.getWidth()][2];
        MyPoint[] points = new MyPoint[image.getWidth()];
        for (int i = 1; i < image.getWidth(); i++){
            int min = image.getHeight();
            int nextY = 0;


                for (int j = 0; j < image.getHeight(); j++) {
                    if (result[j][i] == 0 || result[j][i] == -16777216) {
                        if (Math.abs(finalArray[j - 1][1] - j) < min) {
                            min = Math.abs(finalArray[i - 1][1] - j);
                            nextY = j;
                        }
                    }
                }
            finalArray[i][0] = i;
            finalArray[i][1] = nextY;
            points[i] = new MyPoint(i,nextY);

        }
        return finalArray;
    }
    private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }
}
