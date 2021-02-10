package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ColorDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ExchangesDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.Exchanges;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.ExchangesRepository;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    ExchangesRepository exchangesRepository;

    @Override
    public List<ExchangesDTO> getAll() {
        List<ExchangesDTO> exchangesDTO = new ArrayList<>();
        for (Exchanges exchanges : exchangesRepository.getAll()) {
            exchangesDTO.add(ExchangesDTO.builder().id(exchanges.getId()).name(exchanges.getName()).build());
        }
        return exchangesDTO;
    }

    @Override
    public ExchangesDTO findById(int id) {
        Exchanges exchanges = exchangesRepository.findById(id);
        return  ExchangesDTO.builder().id(exchanges.getId()).name(exchanges.getName()).build();
    }

    @Override
    public Map<String,String> add(ExchangesDTO entity) throws CustomBadRequest {
        Map<String,String> map = new HashMap<>();
        try {
            exchangesRepository.add(addExchanges(entity));
            map.put("Message","Yeni Valyuta yaradildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Yeni Valyuta yaradilmadi !!!")));
        }
        return map;
    }

    @Override
    public Map<String,String> update(ExchangesDTO entity) throws CustomBadRequest {
        Map<String,String> map = new HashMap<>();
        try {
            exchangesRepository.add(addExchanges(entity));
            map.put("Message","Valyuta deyisdirildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Valyuta deyisdirilmedi !!!")));
        }
        return map;
    }

    @Override
    public Map<String,String> delete(int id) {
        Map<String,String> map = new HashMap<>();
        try{
            exchangesRepository.delete(id);
            map.put("Message","Valyuta silindi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Valyuta silinmedi !!!")));
        }
        return map;
    }

    public Exchanges addExchanges(ExchangesDTO exchangesDTO){
        Exchanges exchanges =null;
        if(exchangesDTO.getName() != null){
            exchanges = Exchanges.builder()
                    .id(exchangesDTO.getId())
                    .name(exchangesDTO.getName())
                    .build();
        }
        return exchanges;
    }
}
