package com.example.personalshoppersystem.Repository;


import com.example.personalshoppersystem.Model.PersonalShopperDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalShopperDetailsRepository extends JpaRepository<PersonalShopperDetails,Integer> {

    PersonalShopperDetails findPersonalShopperDetailsById(Integer id);
    List<PersonalShopperDetails> findPersonalShopperDetailsBySpecialty(String specialty);

    @Query("select p from PersonalShopperDetails p order by p.rating asc")
    List<PersonalShopperDetails> sortPersonalShopperDetailsByRating();}
