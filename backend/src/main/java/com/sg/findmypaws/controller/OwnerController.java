package com.sg.findmypaws.controller;

import com.sg.findmypaws.dao.OwnerDao;
import com.sg.findmypaws.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Repository
@RestController
public class OwnerController {

    @Autowired
    OwnerDao ownerDao;

    @PostMapping("/owner")
    public Owner addOwner(@RequestBody Owner owner){
        Owner fromDao = ownerDao.addOwner(owner);
        owner.setId(fromDao.getId());
        return owner;
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable int id){

        Owner owner = ownerDao.getOwnerById(id);

        if(owner == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        ResponseEntity response = new ResponseEntity(owner,HttpStatus.OK);

        return response;
    }

    @PutMapping("/owner/{id}")
    public ResponseEntity<Owner> updateOwnerById(@PathVariable int id, @RequestBody Owner owner){

        owner.setId(id); //set the id of owner to the path variable id
        Owner fromDao = ownerDao.updateOwner(owner);

        if(fromDao == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        ResponseEntity response = new ResponseEntity(fromDao,HttpStatus.OK);

        return response;
    }

    @DeleteMapping("owner/{id}")
    public ResponseEntity<Owner> deleteOwnerById(@PathVariable int id){

        Owner fromDao = ownerDao.deleteOwnerById(id);

        if(fromDao == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        ResponseEntity response = new ResponseEntity(fromDao,HttpStatus.OK);

        return response;
    }

    @GetMapping("owners")
    public ResponseEntity<List<Owner>> getAllOwners(){

        List<Owner> owners = ownerDao.getAllOwners();

        ResponseEntity response = new ResponseEntity(owners, HttpStatus.OK);

        return response;
    }

    @GetMapping("ownerFilter")
    public ResponseEntity<List<Owner>> getAllOwnersFilter(@RequestBody Owner owner) {

        List<Owner> owners = ownerDao.getAllFilteredOwners(owner);

        ResponseEntity response = new ResponseEntity(owners, HttpStatus.OK);

        return response;
    }



}
