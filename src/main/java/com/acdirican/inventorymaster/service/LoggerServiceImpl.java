package com.acdirican.inventorymaster.service;


import java.util.List;
import java.util.Optional;

import com.acdirican.inventorymaster.dao.base.LoggerRepository;
import com.acdirican.inventorymaster.model.Log;
import com.acdirican.inventorymaster.service.base.LoggerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Looger class to keep track inventory updates using Log entity.
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class LoggerServiceImpl implements LoggerService {
	
	@Autowired
	private LoggerRepository loggerRepository;

	public LoggerServiceImpl() {}
	
	public LoggerRepository getLogger() {
		return loggerRepository;
	}
	
	public void setLogger(LoggerRepository logger) {
		this.loggerRepository = logger;
	}
	
	@Override
	public List<Log> list() {
		return loggerRepository.list();
	}

	@Override
	public Optional<Log> add(Log object) {
		return loggerRepository.add(object);
	}

	@Override
	public Optional<Log> getWidthIndex(int index) {
		return loggerRepository.getWidthIndex(index);
	}

	@Override
	public boolean delete(int ID) {
		return loggerRepository.delete(ID);
	}

	@Override
	public boolean delete(Log object) {
		return loggerRepository.delete(object);
	}

	@Override
	public Optional<Log> getWidthID(int ID) {
		return loggerRepository.getWidthID(ID);
	}

	@Override
	public Log update(Log object) {
		return loggerRepository.update(object);
	}

	@Override
	public List<Log> listByProduct(int productID) {
		return loggerRepository.listByProduct(productID);
	}
	

}
