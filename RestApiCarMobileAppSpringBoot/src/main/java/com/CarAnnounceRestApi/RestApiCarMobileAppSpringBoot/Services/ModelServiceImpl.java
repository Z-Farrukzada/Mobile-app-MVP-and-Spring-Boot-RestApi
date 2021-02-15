package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ModelDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarModel;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.ModelRepository;
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
public class ModelServiceImpl implements  ModelService{

    @Autowired
    ModelRepository modelRepository;

    @Override
    public List<ModelDTO> getAll() {
        log.debug("Butun Modeller cagirilir...");
        List<ModelDTO> modelDto = new ArrayList<>();
        for (CarModel model : modelRepository.getAll()) {
            modelDto.add(ModelDTO.builder().id(model.getId()).name(model.getName()).brandId(model.getBrandId()).build());
        }
        log.debug("Butun Modeller  " + modelDto);
        return modelDto;
    }

    @Override
    public ModelDTO findById(int id) {
        log.debug("Model id-ye gore axtarilir...");
        CarModel model = modelRepository.findById(id);
        log.debug("Model " + model);
        return ModelDTO.builder().id(model.getId()).name(model.getName()).brandId(model.getBrandId()).build();
    }

    @Override
    public Map<String,String> add(ModelDTO entity) {
        log.debug("Yeni Model elave olunur...");
        Map<String,String> map = new HashMap<>();
            modelRepository.add(addModel(entity));
            map.put("Message","Yeni Model yaradildi");
        return map;
    }

    @Override
    public Map<String,String> update(ModelDTO entity){
        log.debug("Model deyisdirilir...");
        Map<String,String> map = new HashMap<>();
            modelRepository.update(addModel(entity));
            map.put("Message","Model deyisidirldi");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public  Map<String,String> delete(int id) {
        log.debug("Model silinir...");
        Map<String,String> map = new HashMap<>();
            modelRepository.delete(id);
            map.put("Message","Model silindi");
        log.debug(map.get("Message"));
        return map;
    }

    @Override
    public  List<ModelDTO> FindByModelByBrandId(int id) {
        log.debug("Model Marka ile birlikde cagirilir...");
        List<ModelDTO> modelDto = new ArrayList<>();
        for (CarModel model : modelRepository.FindModelByBrand(id)) {
            modelDto.add(ModelDTO.builder().id(model.getId()).name(model.getName()).brandId(model.getBrandId()).build());
        }
        log.debug("Model Marka ile birlikde " + modelDto);
        return modelDto;
    }

    public CarModel addModel(ModelDTO modelDTO){
        log.debug("ModelDTO CarModel-e elave olunur...");
        CarModel carModel =null;
        if(modelDTO.getName() != null && modelDTO.getBrandId()!= 0){
            carModel = CarModel.builder()
                    .id(modelDTO.getId())
                    .name(modelDTO.getName())
                    .brandId(modelDTO.getBrandId())
                    .build();
        }
        log.debug("ModelDTO CarModel-e elave elave olundu " + carModel);
        return carModel;
    }

}
