//package com.example.birthday.service;
//
//import domain.Person;
//import repository.ProcessingDataExcel;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//
//public class BirthdayExel {
//
//    // public static void main(String[] args) {
//
//    //1. выберем с чем будем работать с Excel
//    public void start() {
//        // будем работать с Excel файлом
//      //  ProcessingDataExcel processingDataExcel = new ProcessingDataExcel();
//
////2. очистка ранее сгенерированных фото и видео
//        // очистка сгенерированных картинок из папки img
////        if (processingDataExcel.deleteAllFilesFolder("src/main/resources/img"))
////            System.out.println("очищена папка с фото");
////        else System.out.println("папка с фото не очищена");
////
////        // очистка сгенерированных ранее видео "C:\\video"
////        if (processingDataExcel.deleteVideo("C:\\video"))
////            System.out.println("очищена папка с видео");
////        else System.out.println("папка с видео пуста");
//
//        //3. чтение данных
//        File filefon = null;
//        FileInputStream fileExcel = null;
//        try {
//            //fileExcel = new FileInputStream("src/main/resources/files/date.xlsx");
//            filefon = new File("src/main/resources/files/fon.jpg");
//
//            //читаем файл эксель
//       //     List<Person> personList = processingDataExcel.readFileOrBD();
//
////4. накладываем, если имеются данные именинника на картинку-фон
////            if (!personList.isEmpty()) {
////                processingDataExcel.combineImageText(filefon, personList);
//////5. из полученных картинок генерируем видео
////                processingDataExcel.videoFromPicture(personList.size());
////            } else
////                System.out.println("Сегодня нет именниников");
////        } catch (
////                FileNotFoundException e) {
////            e.printStackTrace();
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//}
