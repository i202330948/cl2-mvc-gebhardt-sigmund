package pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmCategoryPk {

    private Integer filmId;
    private Integer categoryId;

}

