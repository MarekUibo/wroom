package com.example.wroom.services;

import com.example.wroom.exceptions.RentalOfficeNotFoundException;
import com.example.wroom.models.RentalOffice;

import java.util.List;
import java.util.UUID;

/**
 * To handle rental office related operations
 *
 * @author Rigottier Jonathan
 */
public interface RentalOfficeService {
    /**
     * To create a new rental office
     * @param rentalOffice RentalOffice
     */
    void createRentalOffice(RentalOffice rentalOffice);

    /**
     * To find a rental office by its ID
     * @param id id of the branch
     * @return RentalOffice
     */
    RentalOffice findRentalOfficeById(UUID id) throws RentalOfficeNotFoundException;

    /**
     * To find a rental office by its name
     * @param name Name of the rental office
     * @return RentalOffice
     */
    RentalOffice findRentalOfficeByName(String name) throws RentalOfficeNotFoundException;

    /**
     * To find all rental offices
     * @return List of rental offices
     */
    List<RentalOffice> findAllRentalOffices();

    /**
     * To update an existing rental office
     * @param rentalOffice RentalOffice
     */
    void updateRentalOffice(RentalOffice rentalOffice) throws RentalOfficeNotFoundException;

    /**
     * To delete a rental office by its ID
     * @param id id of the rental office
     */
    void deleteRentalOfficeById(UUID id) throws RentalOfficeNotFoundException;

    /**
     * To restore a rental office by its ID
     * @param id
     */
    void restoreRentalOfficeById(UUID id) throws RentalOfficeNotFoundException;
}