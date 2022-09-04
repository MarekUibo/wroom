package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.BranchNotFoundException;
import com.example.wroom.models.Branch;
import com.example.wroom.services.BranchService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of Branch Service
 *
 * @author Jonathan Rigottier
 */
@Service
@Transactional
public class BranchServiceImpl implements BranchService {
    @Override
    public void createBranch(Branch branch) {

    }

    @Override
    public Branch findBranchById(UUID id) throws BranchNotFoundException {
        return null;
    }

    @Override
    public Branch findBranchByAddress(String address) throws BranchNotFoundException {
        return null;
    }

    @Override
    public List<Branch> findAllBranches() {
        return null;
    }

    @Override
    public void updateBranch(Branch branch) throws BranchNotFoundException {

    }

    @Override
    public void deleteBranchById(UUID id) throws BranchNotFoundException {

    }

    @Override
    public void restoreBranchById(UUID id) throws BranchNotFoundException {

    }
}
