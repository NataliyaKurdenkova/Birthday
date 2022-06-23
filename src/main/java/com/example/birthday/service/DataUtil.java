package com.example.birthday.service;

import com.example.birthday.domain.Person;
import com.example.birthday.repository.MyFileNameFilter;
import lombok.SneakyThrows;
import org.jcodec.api.awt.AWTSequenceEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.AttributedString;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.time.LocalDate.*;


public class DataUtil {
    public static final String FILES_FON_JPG = "src/main/resources/files/fon.jpg";
    public static final String IMAGE_PATH = "src/main/resources/img";
    public static final String VIDEO_PATH = "C:\\video";
    public static final String IMG_IMAGE = "src/main/resources/img/image";

    // метод наложения текста на фон
    @SneakyThrows
    public static void combineImageText(File file, List<Person> personList) {
        Font font = new Font("Arial", Font.BOLD, 80);
        Font fontPosition = new Font("Arial", Font.BOLD, 50);

        int i = 0;
        for (Person p : personList) {
            BufferedImage image = ImageIO.read(file);
            AttributedString attributedText = new AttributedString(p.getFio());
            AttributedString attributedPosition = new AttributedString(p.getPosition());

            attributedText.addAttribute(TextAttribute.FONT, font);
            attributedPosition.addAttribute(TextAttribute.FONT, fontPosition);
            attributedText.addAttribute(TextAttribute.FOREGROUND, Color.white);
            attributedPosition.addAttribute(TextAttribute.FOREGROUND, Color.white);

            Graphics g = image.getGraphics();
            FontMetrics metrics = g.getFontMetrics(font);

            if (p.getPhoto() != null) {
                BufferedImage imagePerson = ImageIO.read(new File(p.getPhoto()));
                int w = Math.max(image.getWidth(), imagePerson.getWidth());
                int h = Math.max(image.getHeight(), imagePerson.getHeight());
                BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

                g.drawImage(imagePerson, 800, 500, null);
            }

            int positionX = (image.getWidth() - metrics.stringWidth(p.getFio())) / 2;
            int positionY = (image.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent() + 310;
            g.drawString(attributedText.getIterator(), positionX, positionY);

            FontMetrics metricsPosition = g.getFontMetrics(fontPosition);
            int positionXPos = (image.getWidth() - metricsPosition.stringWidth(p.getPosition())) / 2;
            int positionYPos = (image.getHeight() - metricsPosition.getHeight()) / 2 + metricsPosition.getAscent() + 400;
            g.drawString(attributedPosition.getIterator(), positionXPos, positionYPos);


            g.dispose();

            ImageIO.write(image, "jpg", new File(IMG_IMAGE + i + ".jpg"));
            i++;
        }
    }

    //метод генерации видео
    @SneakyThrows
    public static void videoFromPicture(int count) {
        int framesToEncode = 300; // длительность видео

        for (int j = 0; j <= count - 1; j++) {
            System.out.println("начало записи видео №" + j);
            AWTSequenceEncoder encoder = AWTSequenceEncoder.createSequenceEncoder(new File("C://video//video" + j + ".mp4"), 25);
            for (int i = 1; i < framesToEncode / 2; i++) {
                BufferedImage image = ImageIO.read(new File("src/main/resources/img/image" + j + ".jpg"));
                encoder.encodeImage(image);
            }
            encoder.finish();
        }
        System.out.println("запись видео завершена");
    }

    //очистка папки "img"

    public static boolean deleteAllFilesFolder() {
        File myFile = new File(IMAGE_PATH);
        if (!myFile.exists()) {
            System.out.println(IMAGE_PATH + " папка не существует");
            return false;
        }

        if (myFile.listFiles().length == 0) return false;

        for (File f : myFile.listFiles()) {
            if (f.isFile()) f.delete();
        }
        return true;
    }

    //удаление видео из папки video

    public static boolean deleteVideo() {
        String filter = "video";
        File file = new File(VIDEO_PATH);

        if (!file.exists()) {
            System.out.println(VIDEO_PATH + " папка не существует");
            return false;
        }

        File[] listFiles = file.listFiles(new MyFileNameFilter(filter));

        if (listFiles.length == 0) return false;
        else
            for (File myFile : listFiles) {
                myFile.delete();
            }
        return true;
    }

    public static File loadPicture() {
        return new File(FILES_FON_JPG);
    }

    public static String getCurrentDayAndMonth (){
        Date currentdate = new Date();
        SimpleDateFormat formatForDay = new SimpleDateFormat("dd.MM");
        String d=formatForDay.format(currentdate);
        return d;

    }

}
