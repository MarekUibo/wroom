package com.example.wroom.services;

import com.example.wroom.exceptions.RentalEndNotFoundException;
import com.example.wroom.models.Person;
import com.example.wroom.models.RentalEnd;

import java.util.List;
import java.util.UUID;

/**
 * To handle RentalEnd operations
 *
 * @author Rigottier Jonathan
 */
public interface RentalEndService {
    /**
     * To create a new rentalEnd
     * @param rentalEnd RentalEnd
     */
    void createRentalEnd(RentalEnd rentalEnd);

    /**
     * To find a rentalEnd by its ID
     * @param id id of the rentalEnd
     * @return RentalEnd
     */
    RentalEnd findRentalEndById(UUID id) throws RentalEndNotFoundException;

    /**
     * To find a rentalEnd by its customer email
     * @param email Email of the customer
     * @return RentalEnd
     */
    RentalEnd findRentalEndByCustomerEmail(Person email) throws RentalEndNotFoundException;

    /**
     * To find all rentalEnds
     * @return List of rentalEnds
     */
    List<RentalEnd> findAllRentalEnds();

    /**
     * To update an existing rentalEnd
     * @param rentalEnd RentalEnd
     */
    void updateRentalEnd(RentalEnd rentalEnd) throws RentalEndNotFoundException;

    /**
     * To delete a rentalEnd by its ID
     * @param id id of the rentalEnd
     */
    void deleteRentalEndById(UUID id) throws RentalEndNotFoundException;

    /**
     * To restore a rentalEnd by its ID
     * @param id
     */
    void restoreRentalEndById(UUID id) throws RentalEndNotFoundException;
}
