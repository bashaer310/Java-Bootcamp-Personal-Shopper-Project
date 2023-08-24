package com.example.personalshoppersystem.Controller;

import com.example.personalshoppersystem.API.ApiResponse;
import com.example.personalshoppersystem.Model.PersonalShopper;
import com.example.personalshoppersystem.Service.PersonalShopperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/personalShopper")
@RequiredArgsConstructor
public class PersonalShopperController {

    private final PersonalShopperService personalShopperService;
    @GetMapping("/get")
    public ResponseEntity getPersonalShoppers(){
        return ResponseEntity.status(200).body(personalShopperService.getPersonalShoppers());
    }
    @PostMapping("/add")
    public ResponseEntity addPersonalShopper(@RequestBody @Valid PersonalShopper personalShopper){
        personalShopperService.addPersonalShoppers(personalShopper);
        return ResponseEntity.status(200).body(new ApiResponse("Shopper added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePersonalShopper(@RequestBody @Valid PersonalShopper personalShopper, @PathVariable Integer id){
        personalShopperService.updatePersonalShoppers(personalShopper,id);
        return ResponseEntity.status(200).body(new ApiResponse("Shopper updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePersonalShopper(@PathVariable Integer id){
        personalShopperService.deletePersonalShoppers(id);
        return ResponseEntity.status(200).body(new ApiResponse("Shopper deleted"));
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity getPersonalShopperByUsername(@PathVariable String username){
        return ResponseEntity.status(200).body(personalShopperService.getPersonalShopperByUsername(username));
    }
    @GetMapping("/getByCity/{city}")
    public ResponseEntity getPersonalShopperByCity(@PathVariable String city){
        return ResponseEntity.status(200).body(personalShopperService.getPersonalShopperByCity(city));
    }

}
