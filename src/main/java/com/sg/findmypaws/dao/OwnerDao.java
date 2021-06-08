package com.sg.findmypaws.dao;

import com.sg.findmypaws.model.Owner;

import java.util.List;

public interface  OwnerDao {
    public Owner addOwner(Owner owner);
    public Owner getOwnerById(int id);
    public Owner updateOwner(Owner owner);
    public Owner deleteOwnerById(int id);
    public List<Owner> getAllOwners();
    public List<Owner> getAllFilteredOwners(Owner filter);
}
