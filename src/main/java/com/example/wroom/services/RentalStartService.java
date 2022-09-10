package com.example.wroom.services;

import com.example.wroom.exceptions.RentalStartNotFoundException;
import com.example.wroom.models.Person;

import java.util.List;
import java.util.UUID;

/**
 * To handle rentalStart related operations
 *
 * @author Rigottier Jonathan
 */
public interface RentalStartService {
    /**
     * To create a new rentalStart
     * @param rentalStart RentalStart
     */
    void createRentalStart(RentalStart rentalStart);

    /**
     * To find a rentalStart by its ID
     * @param id id of the rentalStart
     * @return RentalStart
     */
    RentalStart findRentalStartById(UUID id) throws RentalStartNotFoundException;

    /**
     * To find a rentalStart by its customer email
     * @param email Email of the customer
     * @return RentalStart
     */
    RentalStart findRentalStartByCustomerEmail(Person email) throws RentalStartNotFoundException;

    /**
     * To find all rentalStarts
     * @return List of rentalStarts
     */
    List<RentalStart> findAllRentalStarts();

    /**
     * To update an existing rentalStart
     * @param rentalStart RentalStart
     */
    void updateRentalStart(RentalStart rentalStart) throws RentalStartNotFoundException;

    /**
     * To delete a rentalStart by its ID
     * @param id id of the rentalStart
     */
    void deleteRentalStartById(UUID id) throws RentalStartNotFoundException;

    /**
     * To restore a rentalStart by its ID
     * @param id
     */
    void restoreRentalStartById(UUID id) throws RentalStartNotFoundException;
}
