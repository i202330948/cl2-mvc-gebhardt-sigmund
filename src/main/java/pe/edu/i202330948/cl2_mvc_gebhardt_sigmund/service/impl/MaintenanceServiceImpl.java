package pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.dto.FilmCreateDto;
import pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.dto.FilmDetailDto;
import pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.dto.FilmDto;
import pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.entity.Film;
import pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.repository.FilmRepository;
import pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.repository.LanguageRepository;
import pe.edu.i202330948.cl2_mvc_gebhardt_sigmund.service.MaintenanceService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Override
    public List<FilmDto> findAllFilms() {

        List<FilmDto> films = new ArrayList<FilmDto>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(film.getFilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalDuration(),
                    film.getRentalRate());
            films.add(filmDto);
        });
        return films;

    }

    @Override
    public FilmDetailDto findDetailById(Integer id) {

        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(
                film -> new FilmDetailDto(film.getFilmId(),
                        film.getTitle(),
                        film.getDescription(),
                        film.getReleaseYear(),
                        film.getRentalDuration(),
                        film.getRentalRate(),
                        film.getLength(),
                        film.getReplacementCost(),
                        film.getRating(),
                        film.getSpecialFeatures(),
                        film.getLastUpdate())
        ).orElse(null);

    }

    @Override
    public Boolean updateFilm(FilmDetailDto filmDetailDto) {

        Optional<Film> optional = filmRepository.findById(filmDetailDto.filmId());
        return optional.map(
                film -> {
                    film.setTitle(filmDetailDto.title());
                    film.setDescription(filmDetailDto.description());
                    film.setReleaseYear(filmDetailDto.releaseYear());
                    film.setRentalDuration(filmDetailDto.rentalDuration());
                    film.setRentalRate(filmDetailDto.rentalRate());
                    film.setLength(filmDetailDto.length());
                    film.setReplacementCost(filmDetailDto.replacementCost());
                    film.setRating(filmDetailDto.rating());
                    film.setSpecialFeatures(filmDetailDto.specialFeatures());
                    film.setLastUpdate(new Date());
                    filmRepository.save(film);
                    return true;
                }
        ).orElse(false);
    }

    @Override
    public Boolean deleteFilmById(Integer id) {

        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(
                film -> {
                    filmRepository.delete(film);
                    return true;
                }
        ).orElse(false);
    }

    @Override
    public Boolean createFilm(FilmCreateDto filmCreateDto) {

        Film film = new Film();
        film.setTitle(filmCreateDto.title());
        film.setDescription(filmCreateDto.description());
        film.setReleaseYear(filmCreateDto.releaseYear());
        film.setLanguage(languageRepository.findById(filmCreateDto.languageId()).orElse(null));
        film.setRentalDuration(filmCreateDto.rentalDuration());
        film.setRentalRate(filmCreateDto.rentalRate());
        film.setLength(filmCreateDto.length());
        film.setReplacementCost(filmCreateDto.replacementCost());
        film.setRating(filmCreateDto.rating());
        film.setSpecialFeatures(filmCreateDto.specialFeatures());
        film.setLastUpdate(filmCreateDto.lastUpdate());

        filmRepository.save(film);
        return true;
    }

}
