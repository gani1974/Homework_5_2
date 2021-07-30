package ru.geekbrains;

import ru.geekbrains.persist.AbstractRepository;
import ru.geekbrains.persist.Student;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        AbstractRepository <Student> repository = new AbstractRepository(emFactory);

        Student student = new Student("student1",4);
        repository.findAll().forEach(System.out::println);

//        6.Добавить 1000 записей в таблицу Student.
//        for(int i=1; i<1000; i++){
//            repository.insert(new Student("student"+i,i));
//        }
//        repository.findAll().forEach(System.out::println);

        //7. Проверить работоспособность приложения, выполнить методы по удалению, изменению, добавлению записей, а также выборки одной и всех записей.
        for(int i=1; i<1000; i++){
            repository.delete(i);
        }

//        Student student1 = repository.findById(1000);
//        System.out.println(student1);
//        student1.setName("new Name");
//        repository.update(student1);
//        System.out.println(student1);
//
//        List<Student> studentList = repository.findAll();
//        System.out.println(studentList);

    }
}
