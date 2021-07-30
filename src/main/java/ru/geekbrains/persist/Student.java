package ru.geekbrains.persist;

import javax.persistence.*;

@Entity
@Table(name = "students")
@NamedQueries({
        @NamedQuery(name = "allStudents", query = "from Student"),
        @NamedQuery(name = "studentByName", query = "from Student s where s.name=:name")
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "mark", length = 5, nullable = false)
    private int mark;

    public Student(){

    }

    public Student(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                '}';
    }
}
