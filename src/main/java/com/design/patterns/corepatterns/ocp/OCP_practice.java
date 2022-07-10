package com.design.patterns.corepatterns.ocp;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class OCP_practice
{
    public static void main(String[] args) {
        Movie m1 = new Movie(1, "John Wick", 18, LocalDate.now().minusYears(2));
        Movie m2 = new Movie(2, "Minions Rise of Gru", 8, LocalDate.now().plusMonths(1));
        Movie m3 = new Movie(3, "Thor: Love and thunder", 12, LocalDate.now().plusMonths(1));
        Movie m4 = new Movie(4, "Jurassic Park", 8, LocalDate.now().minusMonths(1));
        Movie m5 = new Movie(5, "Hangover", 18, LocalDate.now().minusYears(3));

        List<Movie> movieList = List.of(m1, m2, m3, m4, m5);


        MyFilter myFilter = new MyFilter();
        System.out.println("Upcoming movies: ");
        myFilter.filter(movieList, new UpcomingMovieSpecification())
                .forEach(m-> System.out.println(m.getName()));
        System.out.println("Family Movies: ");
        myFilter.filter(movieList, new FamilyMovieSpecification())
                .forEach(m-> System.out.println(m.getName()));

    }
}


class Movie{
    private int id;
    private String name;
    private int minAge;

    private LocalDate releaseDate;

    public Movie(int id, String name, int minAge, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.minAge = minAge;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMinAge() {
        return minAge;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}

interface Specification1<T> {
    boolean isSatisfied(T t);
}

interface FilterMovies<T>{
    Stream<T> filter(List<T> tList, Specification1<T> spec);
}

class FamilyMovieSpecification implements Specification1<Movie>{
    @Override
    public boolean isSatisfied(Movie movie) {
        return movie.getMinAge()<16;
    }
}

class UpcomingMovieSpecification implements Specification1<Movie>{
    @Override
    public boolean isSatisfied(Movie movie) {
        return movie.getReleaseDate().isAfter(LocalDate.now());
    }
}

class MyFilter implements FilterMovies<Movie>{
    @Override
    public Stream<Movie> filter(List<Movie> movies, Specification1<Movie> spec) {
        return movies.stream().filter(m->spec.isSatisfied(m));
    }
}

