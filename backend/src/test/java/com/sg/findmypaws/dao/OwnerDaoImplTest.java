package com.sg.findmypaws.dao;

import com.sg.findmypaws.TestApplicationConfiguration;
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

    @BeforeEach
    void setUp(){
        List<Owner> owners = ownerDao.getAllOwners();
        for(Owner owner: owners){
            ownerDao.deleteOwnerById(owner.getId());
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

}