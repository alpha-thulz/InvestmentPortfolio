package za.co.tyaphile.investmentportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tyaphile.investmentportfolio.api.model.Investor;
import za.co.tyaphile.investmentportfolio.doa.InvestorRepo;

import java.util.*;


/**
 * Investor service used to process investor data
 */
@Service
public class UserService {

    @Autowired
    private InvestorRepo investorRepo;

    public Investor getInvestor(String id) {
        return investorRepo.findById(id).orElse(null);
    }

    public Map<String, String> registerInvestor(String name, String address, String contact, int age) {
        Investor investor = new Investor(name, address, contact, age);
        investorRepo.save(investor);
        return Map.of("name", investor.getName(), "id", investor.getId());
    }

    public boolean deleteInvestor(String id) {
        try {
            Investor investor = getInvestor(id);
            if (investor == null)
                throw new NullPointerException();
            investorRepo.delete(investor);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public Investor updateInvestor(String id, String address, String contact) {
        Investor investor = getInvestor(id);
        if (investor != null && deleteInvestor(id)) {
            if (address != null && !address.isBlank())
                investor.setAddress(address);
            if (contact != null && !contact.isBlank())
                investor.setContact(contact);

            investorRepo.save(new Investor(id, investor.getName(), investor.getAddress(), investor.getContact(), investor.getAge()));
        }

        return investor;
    }
}

