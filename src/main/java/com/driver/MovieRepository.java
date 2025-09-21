package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        // your code here
        String movieName= movie.getName();
        if(movieMap.containsKey(movieName)){
            return;
        }
        movieMap.put(movieName,movie);
    }

    public void saveDirector(Director director){
        // your code here
        String directorName= director.getName();
        if(directorMap.containsKey(directorName)){
            return;
        }
        directorMap.put(directorName,director);
    }

    public void saveMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            // your code here
            List<String>movieList=new ArrayList<>();
            for (String directorName: directorMovieMapping.keySet()){
                if(directorName.equalsIgnoreCase(director)){
                    movieList=directorMovieMapping.getOrDefault(directorName,new ArrayList<>());
                    for (String movieName: movieList){
                        if(movieName.equalsIgnoreCase(movie)){
                            return;
                        }
                    }
                    movieList.add(movie);
                    return;
                }
            }
            directorMovieMapping.put(director,List.of(movie));
            return ;
        }
        return;
    }

    public Movie findMovie(String movie){
        // your code here
        Movie M=null;
        for (String movieName: movieMap.keySet()){
            if(movieName.equalsIgnoreCase(movie)){
                M= movieMap.get(movie);
            }
        }
        return M;
    }

    public Director findDirector(String director){
        // your code here
        Director D=null;
        for (String directorName : directorMap.keySet()){
            if(directorName.equalsIgnoreCase(director)){
                D= directorMap.get(director);
            }
        }
        return D;
    }

    public List<String> findMoviesFromDirector(String director){
        // your code here
        List<String> movieList= new ArrayList<>();
        for (String directorName:directorMovieMapping.keySet()){
            if(directorName.equals(director)){
                movieList=directorMovieMapping.getOrDefault(director,new ArrayList<>());
            }
        }
        return movieList;

    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){
        // your code here
        if(directorMap.isEmpty()){
            return;
        }
        for (String directorName:directorMap.keySet()){
            if(directorName.equals(director)){
                directorMap.remove(director);
                directorMovieMapping.remove(director);
            }
        }
    }

    public void deleteAllDirector(){
        // your code here
        directorMap.clear();
    }
}