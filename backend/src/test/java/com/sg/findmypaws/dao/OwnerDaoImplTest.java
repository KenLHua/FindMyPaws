package com.sg.findmypaws.dao;

import com.sg.findmypaws.TestApplicationConfiguration;
import com.sg.findmypaws.model.Location;
import com.sg.findmypaws.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class OwnerDaoImplTest {

    @Autowired
    OwnerDao ownerDao;

    @Autowired
    LocationDao locationDao;

    @BeforeEach
    void setUp(){
        List<Owner> owners = ownerDao.getAllOwners();
        for(Owner owner: owners){
            ownerDao.deleteOwnerById(owner.getId());
        }

        List<Location> locations =locationDao.getAllLocations();
        for(Location location: locations){
            locationDao.deleteLocationById(location.getId());
        }
    }

    @Test
    void addOwner(){
        Owner owner = new Owner();
        owner.setName("owner");
        owner.setPhone("111-111-111");
        owner.setEmail("owner@gmail.com");
        Owner fromDao = ownerDao.addOwner(owner);
        owner.setId(fromDao.getId());

        assertEquals(fromDao, owner);
    }
    
    @Test
    void getOwnerById(){
        Owner owner1 = new Owner();
        owner1.setName("owner");
        owner1.setPhone("111-111-111");
        owner1.setEmail("owner@gmail.com");
        Owner fromDao = ownerDao.addOwner(owner1);
        owner1.setId(fromDao.getId());


        Owner owner2 = new Owner();
        owner2.setName("owner2");
        owner2.setPhone("222-222-2222");
        owner2.setEmail("owner2@gmail.com");
        Owner fromDao2 = ownerDao.addOwner(owner2);
        owner2.setId(fromDao2.getId());


        Owner fromDao3 = ownerDao.getOwnerById(fromDao.getId());
        assertEquals(owner1, fromDao3);
        assertNotEquals(owner2, fromDao3);


        Owner fromDao4 = ownerDao.getOwnerById(fromDao2.getId());
        assertEquals(owner2, fromDao4);
        assertNotEquals(owner1, fromDao4);
    }

    @Test
    void updateOwner(){
        Owner owner = new Owner();
        owner.setName("owner");
        owner.setPhone("111-111-111");
        owner.setEmail("owner@gmail.com");
        owner = ownerDao.addOwner(owner);

        Owner fromDao = ownerDao.getOwnerById(owner.getId());

        assertEquals(owner, fromDao); //equals
        owner.setName("change ownername"); //change name
        assertNotEquals(owner,fromDao); //different
        fromDao = ownerDao.updateOwner(owner); //update to database
        assertEquals(owner,fromDao); //equals now
    }

    @Test
    void deleleOwnerById(){
        Owner owner = new Owner();
        owner.setName("owner");
        owner.setPhone("111-111-111");
        owner.setEmail("owner@gmail.com");
        owner = ownerDao.addOwner(owner);

        Owner deleteOwner = ownerDao.deleteOwnerById(owner.getId());

        assertEquals(owner,deleteOwner);
        Owner fromDao = ownerDao.getOwnerById(owner.getId());

        assertNull(fromDao);
    }

    @Test
    void getAllOwners(){
        Owner owner1 = new Owner();
        owner1.setName("owner");
        owner1.setPhone("111-111-111");
        owner1.setEmail("owner@gmail.com");
        Owner fromDao = ownerDao.addOwner(owner1);
        owner1.setId(fromDao.getId());


        Owner owner2 = new Owner();
        owner2.setName("owner2");
        owner2.setPhone("222-222-2222");
        owner2.setEmail("owner2@gmail.com");
        Owner fromDao2 = ownerDao.addOwner(owner2);
        owner2.setId(fromDao2.getId());

        List<Owner> owners = ownerDao.getAllOwners();
        assertEquals(2, owners.size());
        assertEquals(true, owners.contains(owner1));
        assertEquals(true, owners.contains(owner2));
    }

}