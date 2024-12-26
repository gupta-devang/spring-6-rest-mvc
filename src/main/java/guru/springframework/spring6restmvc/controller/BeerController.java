package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> updateBeerById(@PathVariable UUID uuid,@RequestBody Beer beer) {
        beerService.updateBeerById(uuid, beer);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Void> handleBeer(@RequestBody Beer beer, UriComponentsBuilder ucb) {
        Beer savedBeer = beerService.saveNewBeer(beer);
        URI uri = ucb.path("/api/v1/beer/{id}")
                .buildAndExpand(savedBeer.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    @GetMapping("/{beerId}")
    public Beer getBeerById(@PathVariable UUID beerId) {
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(beerId);
    }
}
