package com.cg.sprint.service;

import java.util.List;

import com.cg.sprint.entity.Account;
import com.cg.sprint.entity.Admin;
import com.cg.sprint.entity.City;
import com.cg.sprint.entity.Customer;
import com.cg.sprint.entity.Languages;
import com.cg.sprint.entity.Movies;
import com.cg.sprint.entity.Payments;
import com.cg.sprint.entity.Refund;
import com.cg.sprint.entity.Seats;
import com.cg.sprint.entity.Shows;
import com.cg.sprint.entity.Theatre;

public interface AdminServiceInterface {

	Admin aLogin(String uname, String pass);
	City save(City c);
	Theatre save(Theatre t);
	Movies save(Movies m);
	Shows save(Shows s);
	List<City> getCityList();
	List<Theatre> getTheatreList();
	List<Movies> getMovieList();
	List<Shows> getShowList();
	String updateCity(City c);
	String updateTheatre(Theatre t);
	String updateMovies(Movies m);
	String updateShows(Shows s);
	
    void removeCity(int sno);
    void removeTheatre(int theatre_id);
    void removeMovie(int movie_id);
    void removeShow(int sno);
}
