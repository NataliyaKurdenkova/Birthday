package com.example.birthday.repository;

import com.example.birthday.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface ProcessingDataFromBD extends JpaRepository<Person, Long> {

   List<Person> findPersonByBirthdayEndingWith(String date);


}
