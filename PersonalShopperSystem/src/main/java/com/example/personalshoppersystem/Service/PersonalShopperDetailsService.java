package com.example.personalshoppersystem.Service;


import com.example.personalshoppersystem.API.ApiException;
import com.example.personalshoppersystem.DTO.PersonalShopperDetailsDTO;
import com.example.personalshoppersystem.Model.PersonalShopperDetails;
import com.example.personalshoppersystem.Model.PersonalShopper;
import com.example.personalshoppersystem.Repository.PersonalShopperDetailsRepository;
import com.example.personalshoppersystem.Repository.PersonalShopperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PersonalShopperDetailsService {


    private final PersonalShopperDetailsRepository personalShopperDetailsRepository;
    private final PersonalShopperRepository personalShopperRepository;

    public List<PersonalShopperDetails> getPersonalShoppersDetails(){
        return personalShopperDetailsRepository.findAll();
    }
    public void addPersonalShoppersDetails(PersonalShopperDetailsDTO personalShopperDetailsDTO){
        PersonalShopper personalShopper=personalShopperRepository.findPersonalShopperById(personalShopperDetailsDTO.getPersonalShopperId());
        if(personalShopper ==null)
            throw new ApiException("Id not found");

        PersonalShopperDetails personalShopperDetailsModel = new PersonalShopperDetails(null,personalShopperDetailsDTO.getPhoneNumber(),personalShopperDetailsDTO.getBriefDescription(),personalShopperDetailsDTO.getSpecialty(),personalShopperDetailsDTO.getGender(),personalShopperDetailsDTO.getRating(),personalShopperDetailsDTO.getBalance(),personalShopper);
        personalShopperDetailsRepository.save(personalShopperDetailsModel);
    }

    public void updatePersonalShoppersDetails(PersonalShopperDetailsDTO personalShopperDetailsDTO,Integer id){
        PersonalShopperDetails personalShopperDetails=personalShopperDetailsRepository.findPersonalShopperDetailsById(id);
        if(personalShopperDetails ==null)
            throw new ApiException("Details not found");

        personalShopperDetails.setGender(personalShopperDetailsDTO.getGender());
        personalShopperDetails.setBalance(personalShopperDetailsDTO.getBalance());
        personalShopperDetails.setRating(personalShopperDetails.getRating());
        personalShopperDetails.setPhoneNumber(personalShopperDetailsDTO.getPhoneNumber());
        personalShopperDetails.setBriefDescription(personalShopperDetailsDTO.getBriefDescription());
        personalShopperDetails.setSpecialty(personalShopperDetailsDTO.getSpecialty());

        personalShopperDetailsRepository.save(personalShopperDetails);
    }

    public void deletePersonalShoppersDetails(Integer id){

        PersonalShopper personalShopper=personalShopperRepository.findPersonalShopperById(id);
        PersonalShopperDetails personalShopperDetails=personalShopperDetailsRepository.findPersonalShopperDetailsById(id);

        if(personalShopperDetails ==null)
            throw new ApiException("Details not found");

        personalShopper.setPersonalShopperDetails(null);
        personalShopperRepository.save(personalShopper);
        personalShopperDetailsRepository.delete(personalShopperDetails);
    }

    public List<String> getPersonalShopperDetailsBySpecialty(String specialty){
        List<PersonalShopperDetails> personalShopperDetails=personalShopperDetailsRepository.findPersonalShopperDetailsBySpecialty(specialty);
        List<String> personalShoppersUsername=new ArrayList<>();
        for (int i = 0; i < personalShopperDetails.size(); i++) {
            personalShoppersUsername.add(personalShopperRepository.findPersonalShopperById(personalShopperDetails.get(i).getId()).getUsername());
        }
        if(personalShoppersUsername ==null)
            throw new ApiException("Details not found");

        return personalShoppersUsername;
    }

    public List<String> sortPersonalShopperDetailsByRating(){
        List<PersonalShopperDetails> personalShopperDetails=personalShopperDetailsRepository.sortPersonalShopperDetailsByRating();
        List<String> personalShoppersUsername=new ArrayList<>();
        for (int i = 0; i < personalShopperDetails.size(); i++) {
            personalShoppersUsername.add(personalShopperRepository.findPersonalShopperById(personalShopperDetails.get(i).getId()).getUsername());
        }
        if(personalShopperDetails ==null)
            throw new ApiException("Details not found");

        return personalShoppersUsername;
    }


}
