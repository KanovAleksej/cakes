package com.example.backend.model.dto;

import com.example.backend.model.model.Cake;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CakeDto {

    @Id
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String desc;
    private String image;

    public CakeDto(Cake cake) {
        this.id = cake.getId();
        this.title = cake.getTitle();
        this.desc = cake.getDesc();
        this.image = cake.getImage();
    }

}


