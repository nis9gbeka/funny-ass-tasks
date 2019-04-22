package com.bexultan.arrivals.controller;

import com.bexultan.arrivals.domain.Arrival;
import com.bexultan.arrivals.domain.Views;
import com.bexultan.arrivals.repository.ArrivalRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("arrival")
public class ArrivalController {

    private final ArrivalRepository arrivalRepository;

    @Autowired
    public ArrivalController(ArrivalRepository arrivalRepository) {
        this.arrivalRepository = arrivalRepository;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Arrival> list(){
        return arrivalRepository.findAll();
    }

    @GetMapping("{id}")
    public Arrival getOne(@PathVariable("id") Arrival arrival){
        return arrival;
    }

//    private Map<String, String> getArrival(@PathVariable String id){
//        return arrivals.stream()
//                .filter(arrivals -> arrivals.get("id").equals(id))
//                .findFirst()
//                .orElseThrow(NotFoundException::new );
//    }

    @PostMapping
    public Arrival create(@RequestBody Arrival arrival){
        arrival.setCreationDate(LocalDateTime.now());
        return arrivalRepository.save(arrival);
    }

    @PutMapping("{id}")
    public Arrival update(
            @PathVariable("id") Arrival arrivalFromDb,
            @RequestBody Arrival arrival
    ){
        BeanUtils.copyProperties(arrival, arrivalFromDb, "id");
        return arrivalRepository.save(arrivalFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Arrival arrival){
        arrivalRepository.delete(arrival );
    }
}
