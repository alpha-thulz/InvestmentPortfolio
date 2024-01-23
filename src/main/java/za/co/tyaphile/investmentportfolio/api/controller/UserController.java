package za.co.tyaphile.investmentportfolio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.tyaphile.investmentportfolio.api.model.Investor;
import za.co.tyaphile.investmentportfolio.api.respond.ApiResponder;
import za.co.tyaphile.investmentportfolio.service.UserService;

import java.util.Map;

@RestController
public class UserController extends ApiResponder {

    private final UserService userService;

    /**
     * Constructor uses @Autowired to automatically link user service to the controller
     *
     * @param userService - Service used to manage and control resources
     */
    @Autowired
    public  UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * @param investor - body received as JSON automatically handled and converted to Investor
     *
     * @return http response
     */
    @PostMapping("/investor")
    public ResponseEntity<Object> createUser(@RequestBody final Investor investor) {
        String name = investor.getName();
        String address = investor.getAddress();
        String contact = investor.getContact();
        int age = investor.getAge();

        if ((name == null) || (address == null) || (contact == null)) {
            return getResponseError(HttpStatus.BAD_REQUEST, "Failed to create an investor, " +
                    "please provide enough investor information");
        } else if (age < 1) {
            return getResponseError(HttpStatus.BAD_REQUEST, "Age cannot be set to less than 1 year");
        }

        return getResponse(HttpStatus.CREATED, userService.registerInvestor(name, address, contact, age));
    }

    /**
     * Return the investor relating to the investor ID
     *
     * @param id - investor ID
     * @return ResponseEntity
     */
    @GetMapping("/investor/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id){
        Investor investor = userService.getInvestor(id);
        if (investor == null) {
            return getResponseError(HttpStatus.NOT_FOUND, "ID requested does not exist");
        }
        return getResponse(HttpStatus.OK, investor);
    }

    /**
     * Delete the investor profile
     *
     * @param id - investor ID
     * @return boolean
     */
    @DeleteMapping("/investor/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        boolean isDeleted = userService.deleteInvestor(id);
        if (isDeleted) {
            Map<String, Boolean> result = Map.of("success", true);
            return getResponse(HttpStatus.OK, result);
        }
        return getResponseError(HttpStatus.NOT_FOUND, false);
    }

    /**
     * Update investor information
     *
     * @param id - investor ID
     * @param investor - new investor information to be amended
     * @return ResponseEntity
     */
    @PutMapping("/investor/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody Investor investor) {
        String address = investor.getAddress();
        String contact = investor.getContact();

        Investor updatedInvestor = userService.updateInvestor(id, address, contact);
        if (updatedInvestor != null) {
            return getResponse(HttpStatus.ACCEPTED, updatedInvestor);
        }

        return getResponseError(HttpStatus.BAD_REQUEST, false);
    }
}

