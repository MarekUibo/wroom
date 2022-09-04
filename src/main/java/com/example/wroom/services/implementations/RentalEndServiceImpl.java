package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.RentalEndNotFoundException;
import com.example.wroom.models.Person;
import com.example.wroom.models.RentalEnd;
import com.example.wroom.services.RentalEndService;

import java.util.List;
import java.util.UUID;

public class RentalEndServiceImpl implements RentalEndService {
    @Override
    public void createRentalEnd(RentalEnd rentalEnd) {

    }

    @Override
    public RentalEnd findRentalEndById(UUID id) throws RentalEndNotFoundException {
        return null;
    }

    @Override
    public RentalEnd findRentalEndByCustomerEmail(Person email) throws RentalEndNotFoundException {
        return null;
    }

    @Override
    public List<RentalEnd> findAllRentalEnds() {
        return null;
    }

    @Override
    public void updateRentalEnd(RentalEnd rentalEnd) throws RentalEndNotFoundException {

    }

    @Override
    public void deleteRentalEndById(UUID id) throws RentalEndNotFoundException {

    }

    @Override
    public void restoreRentalEndById(UUID id) throws RentalEndNotFoundException {

    }

}
