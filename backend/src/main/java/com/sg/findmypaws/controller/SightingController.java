package com.sg.findmypaws.controller;

import com.sg.findmypaws.dao.SightingDaoDB;
import com.sg.findmypaws.model.Sighting;
import com.sg.findmypaws.service.SightingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sighting")
public class SightingController {

private final SightingsService service;
private final SightingDaoDB daoDB;

@Autowired
    public SightingController(SightingsService service, SightingDaoDB daoDB) {
    this.service = service;
    this.daoDB = daoDB;
}

    @PostMapping("/add")
    public ResponseEntity<Sighting> addSighting(@RequestBody Sighting sighting) {
    return new ResponseEntity(daoDB.addSighting(sighting), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sighting> getSightingById(@PathVariable int id) {
        return new ResponseEntity(daoDB.getSightingById(id), HttpStatus.FOUND);
}

getAllSightings() {

}

getAllSightingsWithFilters() {

}

getAllSightingsByLocId() {

}

getAllSightingsByAnimalId() {

}

updateSighting() {

}

deleteSighting() {

}





}

//    private final GameService service;
//
//    @Autowired
//    public GameController(GameService service) {
//        this.service = service;
//    }
//
//    @PostMapping("/begin")
//    public ResponseEntity<Integer> begin() {
//        return new ResponseEntity(service.begin(), HttpStatus.CREATED);
//    }
//
//    @PostMapping("/guess")
//    public Round guess(@RequestBody Guess guess_){
//        return service.guess(guess_.getGameId(), guess_.getGuess());
//    }
//
//    @GetMapping("/games")
//    public List<Game> games() {
//        return service.getGames();
//    }
//
//    @GetMapping("/game/{gameId}")
//    public ResponseEntity<Game> game(@PathVariable int gameId) {
//        Game game_ = service.getGame(gameId);
//        if (game_ == null) {
//            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(game_);
//    }
//
//    @GetMapping("/rounds/{gameId}")
//    public ResponseEntity<List<Round>> rounds(@PathVariable int gameId) {
//        List<Round> rounds_ = service.getRounds(gameId);
//        if (rounds_ == null) {
//            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(rounds_);
//    }