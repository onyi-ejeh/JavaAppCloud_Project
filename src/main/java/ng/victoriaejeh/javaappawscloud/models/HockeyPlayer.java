package ng.victoriaejeh.javaappawscloud.models;
import jakarta.persistence.*;

@Entity
@Table(name = "HockeyPlayer")
public class HockeyPlayer {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Id")
        private int id;

        @Column(name = "Name")
        private String name;

        @Column(name = "Age")
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
