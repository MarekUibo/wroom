package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.RentalStartNotFoundException;
import com.example.wroom.models.Person;
import com.example.wroom.models.RentalStart;
import com.example.wroom.repository.BookingRepository;
import com.example.wroom.repository.RentalStartRepository;
import com.example.wroom.services.RentalStartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of RentalStart Service
 *
 * @author Jonathan Rigottier
 */
@Service
@Transactional
public class RentalStartServiceImpl implements RentalStartService {

    @Autowired
    private RentalStartRepository rentalStartRepository;

    @Override
    public void createRentalStart(RentalStart rentalStart) {
        rentalStart.setActive(true);
        rentalStartRepository.save(rentalStart);
    }

    @Override
    public RentalStart findRentalStartById(UUID id) throws RentalStartNotFoundException {
        Optional<RentalStart> optionalRentalStart = rentalStartRepository.findById(id);

        if(optionalRentalStart.isEmpty()) {
            throw new RentalStartNotFoundException(id);
        }
        return optionalRentalStart.get();
    }

    @Override
    public RentalStart findRentalStartByCustomerEmail(Person email) throws RentalStartNotFoundException {
        Optional<RentalStart> optionalRentalStart = rentalStartRepository.findByCustomerEmail(email);

        if(optionalRentalStart.isEmpty()) {
            throw new RentalStartNotFoundException(email);
        }
        return optionalRentalStart.get();
    }

    @Override
    public List<RentalStart> findAllRentalStarts() {
        return rentalStartRepository.findAll();
    }

    @Override
    public void updateRentalStart(RentalStart rentalStart) throws RentalStartNotFoundException {
        if(findRentalStartById(rentalStart.getId()) != null) {
            rentalStartRepository.saveAndFlush(rentalStart);
        }
    }

    @Override
    public void deleteRentalStartById(UUID id) throws RentalStartNotFoundException {
        RentalStart rentalStart = findRentalStartById(id);
        rentalStart.setActive(false);
        rentalStartRepository.saveAndFlush(rentalStart);
    }

    @Override
    public void restoreRentalStartById(UUID id) throws RentalStartNotFoundException {
        RentalStart rentalStart = findRentalStartById(id);
        rentalStart.setActive(true);
        rentalStartRepository.saveAndFlush(rentalStart);
    }
}
