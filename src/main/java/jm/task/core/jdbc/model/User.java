package jm.task.core.jdbc.model;

//import org.hibernate.annotations.Entity;
import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames={"id", "name", "lastName", "age"})})

public class User {
    @Id
//    @SequenceGenerator(name = "id", sequenceName = "id", allocationSize = 0)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
//    @TableGenerator(name="TABLE_GEN",table="T_GENERATOR",pkColumnName="GEN_KEY",pkColumnValue="TEST",valueColumnName="GEN_VALUE",initialValue=1,allocationSize=1)
//    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    @Column (name = "id")
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "lastName")
    private String lastName;

    @Column (name = "age")
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new String("[" + name + ", " + lastName +", " + age + "]");
    }
}
