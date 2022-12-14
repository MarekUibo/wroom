package com.example.wroom.services;

import com.example.wroom.exceptions.BranchNotFoundException;
import com.example.wroom.models.Branch;

import java.util.List;
import java.util.UUID;

/**
 * To handle branch related operations
 *
 * @author Rigottier Jonathan
 */
public interface BranchService {
    /**
     * To create a new branch
     * @param branch Branch
     */
    void createBranch(Branch branch);

    /**
     * To find a branch by its ID
     * @param id id of the branch
     * @return Branch
     */
    Branch findBranchById(UUID id) throws BranchNotFoundException;

    /**
     * To find a branch by its Name
     * @param name id of the branch
     * @return Branch
     */
    Branch findBranchByName(String name) throws BranchNotFoundException;

    /**
     * To find first active branch
     * @return Branch
     */
    Branch findRandomActiveBranch() throws BranchNotFoundException;

    /**
     * To find a branch by its address
     * @param fullAddress fullAddress of the branch
     * @return Branch
     */
    Branch findBranchByFullAddress(String fullAddress) throws BranchNotFoundException;

    /**
     * To find all branches
     * @return List of branches
     */
    List<Branch> findAllBranches();

    /**
     * To update an existing branch
     * @param branch Branch
     */
    void updateBranch(Branch branch) throws BranchNotFoundException;

    /**
     * To delete a branch by its ID
     * @param id id of the branch
     */
    void deleteBranchById(UUID id) throws BranchNotFoundException;

    /**
     * To restore a branch by its ID
     * @param id
     */
    void restoreBranchById(UUID id) throws BranchNotFoundException;
}