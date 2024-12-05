package pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.service;

import pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.dto.FilmCreateDto;
import pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.dto.FilmDetailDto;
import pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.dto.FilmDto;

import java.util.List;

public interface MaintenanceService {

    List<FilmDto> findAllFilms();

    FilmDetailDto findDetailById(Integer id);

    Boolean updateFilm(FilmDetailDto filmDetailDto);

    Boolean deleteFilmById(Integer id);

    Boolean createFilm(FilmCreateDto filmCreateDto);

}
