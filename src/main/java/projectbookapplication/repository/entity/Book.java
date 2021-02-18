package projectbookapplication.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter @Setter
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="id_user")
    private Integer idUser;

    @Column(name="title")
    private String title;

    @Column(name="link_img")
    private String link_img;

    @Column(name="type")
    private String type;

    @Column(name="author")
    private String author;

    @Column(name="year_release")
    private String yearRelease;

    @Column(name="prices")
    private String prices;

    @Column(name="description")
    private String description;

    @Column(name="favorite")
    private boolean favorite;
}
