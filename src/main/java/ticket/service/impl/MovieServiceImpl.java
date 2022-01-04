package ticket.service.impl;

import ticket.dao.MovieDao;
import ticket.lib.Inject;
import ticket.lib.Service;
import ticket.model.Movie;
import ticket.service.MovieService;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).get();
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
