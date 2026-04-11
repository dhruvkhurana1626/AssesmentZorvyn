package com.example.AssesmentZorvyn.service;

import com.example.AssesmentZorvyn.dao.FinancialRecordsDao;
import com.example.AssesmentZorvyn.dao.UserDao;
import com.example.AssesmentZorvyn.dto.request.FinancialRecordRequest;
import com.example.AssesmentZorvyn.dto.response.FinancialRecordResponse;
import com.example.AssesmentZorvyn.enums.Role;
import com.example.AssesmentZorvyn.enums.Type;
import com.example.AssesmentZorvyn.exception.BusinessException;
import com.example.AssesmentZorvyn.models.FinancialRecord;
import com.example.AssesmentZorvyn.models.User;
import com.example.AssesmentZorvyn.transformation.FinancialRecordTransformer;
import com.example.AssesmentZorvyn.utility.Validation;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinancialRecordService {

    private final FinancialRecordsDao financialRecordsDao;
    private final Validation validation;
    private final UserDao userDao;

    // Get logged-in user
    private User getLoggedInUser() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return validation.getUserByEmail(email);

    }

    public FinancialRecordResponse addFinancialRecord(Long userId, FinancialRecordRequest financialRecordRequest) {

        User admin = getLoggedInUser();
        Role role = admin.getRole();
        
        if(role==null || role==Role.VIEWER) throw new BusinessException("You dont have the authority to make this changes");

        User user = validation.checkIfUserExist_byId_ReturnUser(userId);
        validation.checkifUserActive(user.getActive());

        FinancialRecord financialRecord =
                FinancialRecordTransformer.financialRecordRequestToFinancialRecord(financialRecordRequest);

        financialRecord.setUpdatedBy(admin.getName());
        financialRecord.setUser(user);

        FinancialRecord savedFinancialRecord = financialRecordsDao.save(financialRecord);
        return FinancialRecordTransformer.financialRecordToFinancialRecordResponse(savedFinancialRecord);

    }

    public @Nullable List<FinancialRecordResponse> getAllRecords() {

        return financialRecordsDao.findAll()
                .stream()
                .map(FinancialRecordTransformer::financialRecordToFinancialRecordResponse)
                .collect(Collectors.toList());

    }


    public @Nullable String deleteRecord(Long id) {

        User user = getLoggedInUser();

        if (user.getRole()==null || user.getRole() == Role.VIEWER) {
            throw new BusinessException("You Dont have the Authority to make changes");
        }

        FinancialRecord financialRecord = validation.getFinancialRecordById(id);

        financialRecordsDao.delete(financialRecord);
        return "Record deleted successfully";

    }

    public FinancialRecordResponse updateRecord(Long id, FinancialRecordRequest request) {

        User user = getLoggedInUser();

        if (user.getRole() == null || user.getRole() == Role.VIEWER) {
            throw new RuntimeException("You dont have the Authority to make Changes");
        }

        FinancialRecord record = validation.getFinancialRecordById(id);

        if(request.getAmount()!=null)record.setAmount(request.getAmount());
        if(request.getCategory()!=null)record.setCategory(request.getCategory());
        if(request.getType()!=null)record.setType(request.getType());
        if(request.getNote()!=null)record.setNote(request.getNote());

        FinancialRecord savedRecord = financialRecordsDao.save(record);

        return FinancialRecordTransformer.financialRecordToFinancialRecordResponse(savedRecord);
    }

    public @Nullable Object getByType(Type type) {

        return financialRecordsDao.findByType(type)
                .stream()
                .map(FinancialRecordTransformer::financialRecordToFinancialRecordResponse)
                .collect(Collectors.toList());

    }

    public @Nullable Object getByCategory(String category) {

        return financialRecordsDao.findByNoteContainingIgnoreCase(category)
                .stream()
                .map(FinancialRecordTransformer::financialRecordToFinancialRecordResponse)
                .collect(Collectors.toList());

    }
}
