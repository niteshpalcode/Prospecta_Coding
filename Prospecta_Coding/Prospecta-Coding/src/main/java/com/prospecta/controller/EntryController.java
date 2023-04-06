package com.prospecta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospecta.exception.EntryNotFoundException;
import com.prospecta.model.Entry;
import com.prospecta.model.EntryDTO;
import com.prospecta.service.EntryService;

@RestController
public class EntryController {

	
	@Autowired
	private EntryService entryService;
	
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<EntryDTO>> showByCategory(@PathVariable("category") String category) throws EntryNotFoundException{
		
		return new ResponseEntity<List<EntryDTO>>(entryService.getByCategory(category),HttpStatus.OK);
	}
	
	@PostMapping("/entries")
	public ResponseEntity<Entry> saveEntry() throws EntryNotFoundException{
		return new ResponseEntity<Entry>(entryService.saveEntry(),HttpStatus.CREATED);
	}
}
