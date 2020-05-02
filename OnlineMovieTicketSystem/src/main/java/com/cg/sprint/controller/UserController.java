package com.cg.sprint.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
import com.cg.sprint.exceptions.AccountNotFoundException;
import com.cg.sprint.service.UserServiceInterface;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {
	//controller level exception handling
		@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="controller account is not present")
		@ExceptionHandler({Exception.class})
		public void handleException()
		{
			
		}
		
	@Autowired
	private UserServiceInterface movieservice;
	//Signup Details
	@PostMapping("/signup")
    public Account addUser(@Valid @RequestBody Account a)
    {
   	 return movieservice.save(a);
   	
    }

	//Login Details
	@GetMapping("/validation/{username}/{password}")
	public ResponseEntity<Account> validate(@PathVariable("username") String uname, @PathVariable("password") String pwd) throws AccountNotFoundException{
		Account auth = movieservice.validate(uname, pwd);
		ResponseEntity<Account> response = new ResponseEntity<Account>(auth,HttpStatus.OK);
		return response;
	}
	//displaying cities
	@GetMapping("/cities")
	public List<City> cityNames(){
		List<City> list = movieservice.getCityNames();
		return list;
	}
	//displaying theaters
	@GetMapping("/theatres/{city-name}")
	public List<Theatre> theatreNames(@PathVariable("city-name") String name){
		List<Theatre> list = movieservice.theatreNames(name);
		return list;
	}
	//displaying movies
	@GetMapping("/movies/{theatre-name}")
	public List<Movies> movieNames(@PathVariable("theatre-name") String name){
		List<Movies> list = movieservice.movieNames(name);
		return list;
	}
	//displaying shows
	@GetMapping("/shows")
	public List<Shows> getShows(){
		List<Shows> list = movieservice.getShows();
		return list;
	}
	//displaying languages
	@GetMapping("/languages")
	public List<Languages> getLanguage(){
		List<Languages> list = movieservice.getLanguage();
		return list;
	}
	//displaying seats
	@GetMapping("/seats")
	public List<Seats> getSeats(){
		List<Seats> list = movieservice.getSeats();
		return list;
	}
	//fetching account number
	@GetMapping("/account_data/{account_no}")
	public ResponseEntity<Customer> getAccountData(@PathVariable("account_no") int accno) {
		Customer customer = movieservice.getAccountData(accno);
		ResponseEntity<Customer> response = new ResponseEntity<Customer>(customer,HttpStatus.OK);
		return response;
	}
	//updating seats
	@PutMapping("/update_seats")
	public String updateSeats(@RequestBody Seats seat) {
		String string = movieservice.updateSeats(seat);
		return string;
	}
	//payment details
	@PostMapping("/payments") 
	public String payments(@RequestBody Payments pay) {
		String string = movieservice.payments(pay); 
		return string; 
	}
	/*@PostMapping("/userregister") 
	public String getUser(@RequestBody User us) {
		String string = movieservice.getUser(us); 
		return string; 
	}
*/
	//refund details
	@GetMapping("/refund/{acc_no}/{book_id}")
	public Payments refund(@PathVariable("acc_no") int accno, @PathVariable("book_id") int bookingid) throws AccountNotFoundException {
		return movieservice.refund(accno, bookingid);
	}
	//updating payment
	@PutMapping("/update_payment")
	public String updatePayment(@RequestBody Payments payment) {
		String string = movieservice.updatePayment(payment);
		return string;
	}
	//refund details
	@PostMapping("/refund_details")
	public String refundDetails(@RequestBody Refund refund) {
		String string = movieservice.refundDetails(refund);
		return string;
	}
	//seats information
	@GetMapping("/seat_details/{seat_type}")
	public Seats seatDetails(@PathVariable("seat_type") String seattype) {
		return movieservice.seatDetails(seattype);
	}
	//updating seats
	@PutMapping("/set_seats")
	public String setSeats(@RequestBody Seats seat) {
		String string = movieservice.setSeats(seat);
		return string;
	}
}