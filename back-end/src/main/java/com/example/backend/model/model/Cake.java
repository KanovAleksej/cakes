package com.example.backend.model.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String desc;

    @Column(name = "image")
    private String image;

    public Cake(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }
}
