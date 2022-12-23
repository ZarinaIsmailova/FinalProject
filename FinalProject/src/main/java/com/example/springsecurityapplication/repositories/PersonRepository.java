package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    // Получаем запись из БД по логину
    Optional<Person> findByLogin(String login);

    @Modifying
    @Query(value = "UPDATE person SET password = ?2 WHERE id= ?1", nativeQuery = true)
    void updatePersonById(int id, String password);


    // Запрос на получение пользователей по email адресу
    List<Person> findByEmail(String email);
    // select * from user_site where email=""


    // Запрос на получение пользователей по номеру телефона
    List<Person> findByPhoneNumber(String phone_number);

    // Запрос на получение пользователей по фамилии, где первые буквы начинаються с определенной последовательности
    List<Person> findByLastnameStartingWith(String starting_with);

}
