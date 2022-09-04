package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.RentalEndNotFoundException;
import com.example.wroom.models.Person;
import com.example.wroom.models.RentalEnd;
import com.example.wroom.repository.BookingRepository;
import com.example.wroom.repository.RentalEndRepository;
import com.example.wroom.services.RentalEndService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of RentalEnd Service
 *
 * @author Jonathan Rigottier
 */
@Service
@Transactional
public class RentalEndServiceImpl implements RentalEndService {

    @Autowired
    private RentalEndRepository rentalEndRepository;

    @Override
    public void createRentalEnd(RentalEnd rentalEnd) {
        rentalEnd.setActive(true);
        rentalEndRepository.save(rentalEnd);
    }

    @Override
    public RentalEnd findRentalEndById(UUID id) throws RentalEndNotFoundException {
        Optional<RentalEnd> optionalRentalEnd = rentalEndRepository.findById(id);

        if(optionalRentalEnd.isEmpty()) {
            throw new RentalEndNotFoundException(id);
        }
        return optionalRentalEnd.get();
    }

    @Override
    public RentalEnd findRentalEndByCustomerEmail(Person email) throws RentalEndNotFoundException {
        Optional<RentalEnd> optionalRentalEnd = rentalEndRepository.findByCustomerEmail(email);

        if(optionalRentalEnd.isEmpty()) {
            throw new RentalEndNotFoundException(email);
        }
        return optionalRentalEnd.get();
    }

    @Override
    public List<RentalEnd> findAllRentalEnds() {
        return rentalEndRepository.findAll();
    }

    @Override
    public void updateRentalEnd(RentalEnd rentalEnd) throws RentalEndNotFoundException {
        if(findRentalEndById(rentalEnd.getId()) != null) {
            rentalEndRepository.saveAndFlush(rentalEnd);
        }
    }

    @Override
    public void deleteRentalEndById(UUID id) throws RentalEndNotFoundException {
        RentalEnd rentalEnd = findRentalEndById(id);
        rentalEnd.setActive(false);
        rentalEndRepository.saveAndFlush(rentalEnd);
    }

    @Override
    public void restoreRentalEndById(UUID id) throws RentalEndNotFoundException {
        RentalEnd rentalEnd = findRentalEndById(id);
        rentalEnd.setActive(true);
        rentalEndRepository.saveAndFlush(rentalEnd);
    }

}
