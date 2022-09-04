package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.BranchNotFoundException;
import com.example.wroom.models.Branch;
import com.example.wroom.repository.BookingRepository;
import com.example.wroom.repository.BranchRepository;
import com.example.wroom.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of Branch Service
 *
 * @author Jonathan Rigottier
 */
@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public void createBranch(Branch branch) {
        branch.setActive(true);
        branchRepository.save(branch);
    }

    @Override
    public Branch findBranchById(UUID id) throws BranchNotFoundException {
        Optional<Branch> optionalBranch = branchRepository.findById(id);

        if(optionalBranch.isEmpty()) {
            throw new BranchNotFoundException(id);
        }
        return optionalBranch.get();
    }

    @Override
    public Branch findBranchByAddress(String address) throws BranchNotFoundException {
        Optional<Branch> optionalBranch = branchRepository.findByAddress(address);

        if(optionalBranch.isEmpty()) {
            throw new BranchNotFoundException(address);
        }
        return optionalBranch.get();
    }

    @Override
    public List<Branch> findAllBranches() {
        return branchRepository.findAll();
    }

    @Override
    public void updateBranch(Branch branch) throws BranchNotFoundException {
        if(findBranchById(branch.getId()) != null) {
            branchRepository.saveAndFlush(branch);
        }
    }

    @Override
    public void deleteBranchById(UUID id) throws BranchNotFoundException {
        Branch branch = findBranchById(id);
        branch.setActive(false);
        branchRepository.saveAndFlush(branch);
    }

    @Override
    public void restoreBranchById(UUID id) throws BranchNotFoundException {
        Branch branch = findBranchById(id);
        branch.setActive(true);
        branchRepository.saveAndFlush(branch);
    }
}
