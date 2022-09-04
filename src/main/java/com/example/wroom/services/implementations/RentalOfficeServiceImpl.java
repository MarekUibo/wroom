package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.RentalOfficeNotFoundException;
import com.example.wroom.models.RentalOffice;
import com.example.wroom.repository.BookingRepository;
import com.example.wroom.repository.RentalOfficeRepository;
import com.example.wroom.services.RentalOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of RentalOffice
 *
 * @author Jonathan Rigottier
 */
@Service
@Transactional
public class RentalOfficeServiceImpl implements RentalOfficeService {

    @Autowired
    private RentalOfficeRepository rentalOfficeRepository;

    @Override
    public void createRentalOffice(RentalOffice rentalOffice) {
        rentalOffice.setActive(true);
        rentalOfficeRepository.save(rentalOffice);
    }

    @Override
    public RentalOffice findRentalOfficeById(UUID id) throws RentalOfficeNotFoundException {
        Optional<RentalOffice> optionalRentalOffice = rentalOfficeRepository.findById(id);

        if(optionalRentalOffice.isEmpty()) {
            throw new RentalOfficeNotFoundException(id);
        }
        return optionalRentalOffice.get();
    }

    @Override
    public RentalOffice findRentalOfficeByName(String name) throws RentalOfficeNotFoundException {
        Optional<RentalOffice> optionalRentalOffice = rentalOfficeRepository.findByName(name);

        if(optionalRentalOffice.isEmpty()) {
            throw new RentalOfficeNotFoundException(name);
        }
        return optionalRentalOffice.get();
    }

    @Override
    public List<RentalOffice> findAllRentalOffices() {
        return rentalOfficeRepository.findAll();
    }

    @Override
    public void updateRentalOffice(RentalOffice rentalOffice) throws RentalOfficeNotFoundException {
        if(findRentalOfficeById(rentalOffice.getId()) != null) {
            rentalOfficeRepository.saveAndFlush(rentalOffice);
        }
    }

    @Override
    public void deleteRentalOfficeById(UUID id) throws RentalOfficeNotFoundException {
        RentalOffice rentalOffice = findRentalOfficeById(id);
        rentalOffice.setActive(false);
        rentalOfficeRepository.saveAndFlush(rentalOffice);
    }

    @Override
    public void restoreRentalOfficeById(UUID id) throws RentalOfficeNotFoundException {
        RentalOffice rentalOffice = findRentalOfficeById(id);
        rentalOffice.setActive(true);
        rentalOfficeRepository.saveAndFlush(rentalOffice);
    }
}
