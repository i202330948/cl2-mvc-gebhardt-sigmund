package pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.dto;

public record FilmDto(Integer filmId,
                      String title,
                      String language,
                      Integer rentalDuration,
                      Double rentalRate) {
}
