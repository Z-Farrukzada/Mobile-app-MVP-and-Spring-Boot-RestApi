package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Exchanges;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.ExchangesRepository;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    ExchangesRepository exchangesRepository;

    @Override
    public List<Exchanges> getAll() {
        return exchangesRepository.getAll();
    }
    @Override
    public Exchanges findById(int id) {
        return exchangesRepository.findById(id);
    }
    @Override
    public void add(Exchanges entity) {
       exchangesRepository.add(entity);
    }
    @Override
    public void update(Exchanges entity) {
      exchangesRepository.update(entity);
    }
    @Override
    public void delete(int id) {
        exchangesRepository.delete(id);
    }
}
