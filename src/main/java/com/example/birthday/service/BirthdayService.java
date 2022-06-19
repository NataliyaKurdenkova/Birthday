package com.example.birthday.service;

import com.example.birthday.domain.Person;
import com.example.birthday.repository.ProcessingDataFromBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.birthday.service.DataUtil.*;

@Service
public class BirthdayService {
    private static ProcessingDataFromBD processingDataFromBD;

    @Autowired
    public BirthdayService(ProcessingDataFromBD processingDataFromBD) {
        this.processingDataFromBD = processingDataFromBD;
    }

    //1. выберем с чем будем работать с BD
    public static void createVideo() {

        //2. очистка ранее сгенерированных фото и видео
            // очистка сгенерированных картинок из папки img
        if (deleteAllFilesFolder())
            System.out.println("очищена папка с фото");
        else System.out.println("папка с фото пуста");

            // очистка сгенерированных ранее видео "C:\\video"
        if (deleteVideo())
            System.out.println("очищена папка с видео");
        else System.out.println("папка с видео пуста");

        //3. загрузка фона и данных
        File fileFon = loadPicture();
        List<Person> personList = processingDataFromBD.findPersonByBirthdayEndingWith(getCurrentDayAndMonth());


        //4. накладываем, если имеются данные именинника на картинку-фон
        if (!personList.isEmpty()) {
            combineImageText(fileFon, personList);
            //5. из полученных картинок генерируем видео
            videoFromPicture(personList.size());
        } else
            System.out.println("Сегодня нет именниников");
    }
}
