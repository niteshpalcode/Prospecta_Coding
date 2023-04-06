package com.prospecta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prospecta.exception.EntryNotFoundException;
import com.prospecta.model.Entry;
import com.prospecta.model.EntryDTO;
import com.prospecta.model.ListOfEntryDTO;
import com.prospecta.repository.EntryRepository;

@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<EntryDTO> getByCategory(String category) throws EntryNotFoundException {
		String uri="https://api.publicapis.org/entries";
		
		ResponseEntity<ListOfEntryDTO> allEntryDTO=restTemplate.getForEntity(uri, ListOfEntryDTO.class);
		
		List<Entry> listOfEntries=allEntryDTO.getBody().getAllList();
		
		List<EntryDTO> list=listOfEntries.stream()
				.filter(e-> category.equals(e.getCategory()))
				.map(m-> new EntryDTO(m.getTitle(),m.getDescription()))
				.collect(Collectors.toList());
		if(list.isEmpty()) {
			throw new EntryNotFoundException("No Entry found with this category- "+category);
		}
		
		return list;
	}

	@Override
	public Entry saveEntry() throws EntryNotFoundException {
		String uri="https://api.publicapis.org/random";
		
		ResponseEntity<ListOfEntryDTO> allEntryDTO=restTemplate.getForEntity(uri, ListOfEntryDTO.class);
		
		Entry entry= allEntryDTO.getBody().getAllList().get(0);
		
		return entryRepository.save(entry);
	}
	
	
}
