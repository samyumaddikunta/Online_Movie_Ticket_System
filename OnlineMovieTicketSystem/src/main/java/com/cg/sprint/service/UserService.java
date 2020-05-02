package com.cg.sprint.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cap.anurag.entity.User;
import com.cg.sprint.dao.AccountDao;
import com.cg.sprint.dao.CityDao;
import com.cg.sprint.dao.CustomerDao;
import com.cg.sprint.dao.LanguagesDao;
import com.cg.sprint.dao.MoviesDao;
import com.cg.sprint.dao.PaymentDao;
import com.cg.sprint.dao.RefundDao;
import com.cg.sprint.dao.SeatsDao;
import com.cg.sprint.dao.ShowsDao;
import com.cg.sprint.dao.TheatreDao;
import com.cg.sprint.entity.Account;
import com.cg.sprint.entity.City;
import com.cg.sprint.entity.Customer;
import com.cg.sprint.entity.Languages;
import com.cg.sprint.entity.Movies;
import com.cg.sprint.entity.Payments;
import com.cg.sprint.entity.Refund;
import com.cg.sprint.entity.Seats;
import com.cg.sprint.entity.Shows;
import com.cg.sprint.entity.Theatre;

@Service
@Transactional
public class UserService implements UserServiceInterface  {

	@Autowired
	private AccountDao validation;
	@Autowired
	private CityDao city;
	@Autowired
	private TheatreDao theatre;
	@Autowired
	private MoviesDao movie;
	@Autowired
	private ShowsDao shows;
	@Autowired
	private LanguagesDao language;
	@Autowired
	private SeatsDao seats;
	@Autowired
	private CustomerDao account;
	@Autowired 
	private PaymentDao payment;
	@Autowired
	private RefundDao refund;
	//@Autowired
	//private UserDao user;
	//sign up
	@Override
	public Account save(Account a)
    {
   	 return validation.save(a);
    }
	// Validation of an account  
	@Override
	public Account validate(String uname, String pass) {
		return validation.validate(uname, pass);
	}

	//Displaying City Names
	public List<City> getCityNames() {
		return city.getCityNames();
	}
	
	//Displaying theatre names
	
	@Override
	public List<Theatre> theatreNames(String name) {
	List<Theatre> list = theatre.theatreNames(name);
	return list;
	}
	//Displaying movie names
	@Override
	public List<Movies> movieNames(String name) {
		List<Movies> list = movie.movieNames(name);
		return list;
	}
	//Displaying shows list
	public List<Shows> getShows() {
		return shows.getShows();
	
	}
	//Displaying languages
	@Override
	public List<Languages> getLanguage() {
		List<Languages> list = language.findAll();
		return list;
	}
	//Displaying seats
	@Override
	public List<Seats> getSeats() {
		List<Seats> list = seats.findAll();
		return list;
	}
	//Fetching Account number 
	@Override
	public Customer getAccountData(int acc_no) {
		return account.getAccountData(acc_no);
	}
	/*@Override
	public Account getUser(String uName,String uPass) {
		return account.getUser(uName,uPass);
	}*/
	
	//Updating seats
	@Override
	public String updateSeats(Seats seat_obj) {
		boolean bool = seats.existsById(seat_obj.getSno());
		if(bool == true) {
			seats.save(seat_obj);
			return "seats were updated successfully!!";
		}
		else {
			return "sorry, seats were not updated";
		}
	}
	//Payment details
	  @Override public String payments(Payments pay) { 
		  payment.save(pay); 
		  int id = pay.getBooking_id();
		  return "your booking id is : "+id;
	  }
	  //Refund details
	@Override
	public Payments refund(int accountno, int bookingid) {
		return payment.refund(accountno, bookingid);
	}
	//Updating payment table
	@Override
	public String updatePayment(Payments pay) {
		boolean bool = payment.existsById(pay.getBooking_id());
		if(bool == true) {
			payment.save(pay);
			return "payment details updated successfully!!";
		}
		else {
			return "sorry,payment details not updated!!";
		}
	}
	//Refund details
	@Override
	public String refundDetails(Refund ref) {
		refund.save(ref);
		return "inserted the refund details successfully!!";
	}
	//Fetching remaining seats info
	@Override
	public Seats seatDetails(String s_type) {
		return seats.seatDetails(s_type);
	}
	//Updating seats after refund
	@Override
	public String setSeats(Seats seat) {
		boolean bool = seats.existsById(seat.getSno());
		if(bool == true) {
			seats.save(seat);
			return "seats updated successfully!!";
		}
		else {
			return "Sorry!!seats were not updated.";
		}
	}
}