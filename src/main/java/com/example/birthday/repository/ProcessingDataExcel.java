//package com.example.birthday.repository;
//
//import com.example.birthday.domain.Person;
//import domain.Person;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
//public class ProcessingDataExcel implements ProcessingData {
//
//    //чтение файла Excel
//    @Override
//    public List<Person> readFileOrBD() {
//
//        // получаем экземпляр XSSFWorkbook для обработки xlsx файла
//        List<Person> personList = new ArrayList<>();
//
//        XSSFWorkbook myExcelBook = null;
//        try {
//            FileInputStream file = new FileInputStream("src/main/resources/files/date.xlsx");
//            myExcelBook = new XSSFWorkbook(file);
//
//            XSSFSheet myExcelSheet = myExcelBook.getSheet("Sheet1");
//            Iterator<Row> ri = myExcelSheet.rowIterator();
//
//            //читаем файл и выбираем именниников
//            while (ri.hasNext()) {
//                String name = null;
//                String birthdate = null;
//                String photo = null;
//                String position = null;
//                XSSFRow row = (XSSFRow) ri.next();
//                try {
//                    birthdate = row.getCell(0).getStringCellValue();
//                    name = row.getCell(1).getStringCellValue();
//                    position = row.getCell(2).getStringCellValue();
//                    photo = row.getCell(3).getStringCellValue();
//                } catch (NullPointerException e) {
//                    if (position == null) position = " ";
//                    if (photo == null)
//                        photo = "src/main/resources/photo/noPhoto.jpg";
//                }
//                Person person = new Person(birthdate, name, photo, position);
//                personList.add(person);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                myExcelBook.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //выбираем из всех только именинников
//        List<Person> personBirthday = selectingBirthday(personList);
//        return personBirthday;
//    }
//
//    //выбор именниников
//    public List<Person> selectingBirthday(List<Person> personList) {
//        List<Person> personBirthday = new ArrayList<>(); // список именинников
//        //текущая дата
//        Date currentdate = new Date();
//
//        System.out.println("currentData " + currentdate);
//        SimpleDateFormat formatForDayCur = new SimpleDateFormat("dd");
//        String day = formatForDayCur.format(currentdate);
//        System.out.println("day " + day);
//
//        SimpleDateFormat formatForMonthCur = new SimpleDateFormat("MM");
//        String month = formatForMonthCur.format(currentdate);
//        System.out.println("month " + month);
//
//        //дата рождения текущего человека
//        String dayPerson;
//        String monthPerson;
//
//        for (Person p : personList) {
//            String birthdate = p.getBirthday();
//            dayPerson = String.valueOf(birthdate.charAt(0))+String.valueOf(birthdate.charAt(1));
//            monthPerson = String.valueOf(birthdate.charAt(3))+String.valueOf(birthdate.charAt(4));
//
//            if (day.equals(dayPerson) && month.equals(monthPerson)) {
//                Person person = new Person(p.getBirthday(), p.getFio(), p.getPhoto(), p.getPosition());
//                personBirthday.add(person);
//            }
//        }
//
//        return personBirthday;
//    }
//
//}
