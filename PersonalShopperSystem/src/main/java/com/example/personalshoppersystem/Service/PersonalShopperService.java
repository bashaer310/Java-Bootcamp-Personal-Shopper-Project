package com.example.personalshoppersystem.Service;



import com.example.personalshoppersystem.API.ApiException;
import com.example.personalshoppersystem.Model.PersonalShopper;
import com.example.personalshoppersystem.Repository.PersonalShopperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalShopperService {

    private final PersonalShopperRepository personalShopperRepository;

    public List<PersonalShopper> getPersonalShoppers(){

        return personalShopperRepository.findAll();
    }
    public void addPersonalShoppers(PersonalShopper personalShopper){

        personalShopperRepository.save(personalShopper);
    }

    public void updatePersonalShoppers(PersonalShopper personalShopper, Integer id){
        PersonalShopper oldPersonalShopper = personalShopperRepository.findPersonalShopperById(id);
        if(oldPersonalShopper ==null)
            throw new ApiException("Id not found");

        oldPersonalShopper.setCity(personalShopper.getCity());
        oldPersonalShopper.setEmail(personalShopper.getEmail());
        oldPersonalShopper.setName(personalShopper.getName());
        oldPersonalShopper.setUsername(personalShopper.getUsername());
        oldPersonalShopper.setPassword(personalShopper.getPassword());

        personalShopperRepository.save(oldPersonalShopper);

    } public void deletePersonalShoppers(Integer id){
        PersonalShopper personalShopper = personalShopperRepository.findPersonalShopperById(id);
        if(personalShopper ==null)
            throw new ApiException("Id not found");
        personalShopperRepository.delete(personalShopper);

    }

    public List<String> getPersonalShopperByCity(String city){

        List<PersonalShopper> personalShoppers=personalShopperRepository.findPersonalShopperByCity(city);
        List<String> personalShoppersUsername=new ArrayList<>();
        for (int i = 0; i < personalShoppers.size(); i++) {
            personalShoppersUsername.add(personalShoppers.get(i).getUsername());
        }
        if(personalShoppersUsername ==null)
            throw new ApiException("Shoppers not found");
        return personalShoppersUsername;
    }
    public PersonalShopper getPersonalShopperByUsername(String username){

        PersonalShopper personalShopper=personalShopperRepository.findPersonalShopperByUsername(username);
        if(personalShopper ==null)
            throw new ApiException("Username not found");
        return personalShopper;
    }
}
