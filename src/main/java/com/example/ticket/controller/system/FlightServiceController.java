package com.example.ticket.controller.system;


import com.example.ticket.model.TimeTable;
import com.example.ticket.service.abst.AdminService;
import com.example.ticket.util.exception.DuplicateException;
import com.example.ticket.util.exception.NotFoundFlightException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/airline")
public class FlightServiceController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/addFlight")
    public ResponseEntity save(@Valid @RequestBody TimeTable flights) throws DuplicateException {
        return ResponseEntity.ok(adminService.save(flights));
    }

    @PutMapping("/updateTimeTable/{id}")
    public ResponseEntity update(@Valid @RequestBody TimeTable flights, @PathVariable int id) throws NotFoundFlightException {
        adminService.update(flights, id);
        return ResponseEntity.ok(flights);
    }

    @DeleteMapping("/eraseFlight/{id}")
    public ResponseEntity erase(@PathVariable int id) throws NotFoundFlightException {
        adminService.delete(id);
        return ResponseEntity.ok("deleted");
    }
}
