package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ExchangesDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Exchanges;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.ExchangesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    ExchangesRepository exchangesRepository;

    @Override
    public List<ExchangesDTO> getAll() {
        log.debug("Butun Valyutalar cagirilir...");
        List<ExchangesDTO> exchangesDTO = new ArrayList<>();
        for (Exchanges exchanges : exchangesRepository.getAll()) {
            exchangesDTO.add(ExchangesDTO.builder().id(exchanges.getId()).name(exchanges.getName()).build());
        }
        log.debug("Valyutalar  " + exchangesDTO);
        return exchangesDTO;
    }

    @Override
    public ExchangesDTO findById(int id) {
        log.debug("Valyuta id-ye gore axtarilir...");
        Exchanges exchanges = exchangesRepository.findById(id);
        log.debug("Valyuta  " + exchanges);
        return  ExchangesDTO.builder().id(exchanges.getId()).name(exchanges.getName()).build();
    }

    @Override
    public Map<String,String> add(ExchangesDTO entity) {
        log.debug("Yeni valyuta elave olunur...");
        Map<String,String> map = new HashMap<>();
            exchangesRepository.add(addExchanges(entity));
            map.put("Message","Yeni Valyuta yaradildi");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public Map<String,String> update(ExchangesDTO entity){
        log.debug("Valyuta deyisdirilir...");
        Map<String,String> map = new HashMap<>();
            exchangesRepository.add(addExchanges(entity));
            map.put("Message","Valyuta deyisdirildi");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public Map<String,String> delete(int id) {
        log.debug("Valyuta silinir...");
        Map<String,String> map = new HashMap<>();
            exchangesRepository.delete(id);
            map.put("Message","Valyuta silindi");
        log.debug(map.get("Message"));
        return map;
    }

    public Exchanges addExchanges(ExchangesDTO exchangesDTO){
        log.debug("ExchangesDTO Exchanges-e elave olunur...");
        Exchanges exchanges =null;
        if(exchangesDTO.getName() != null){
            exchanges = Exchanges.builder()
                    .id(exchangesDTO.getId())
                    .name(exchangesDTO.getName())
                    .build();
        }
        log.debug("ExchangesDTO Exchanges-e elave olundu " + exchanges);
        return exchanges;
    }
}
