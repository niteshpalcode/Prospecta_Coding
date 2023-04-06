package com.prospecta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prospecta.exception.EntryNotFoundException;
import com.prospecta.model.Entry;
import com.prospecta.model.EntryDTO;

@Service
public interface EntryService {


	public List<EntryDTO> getByCategory(String category) throws EntryNotFoundException;
	
	public Entry saveEntry() throws EntryNotFoundException;
	
}
