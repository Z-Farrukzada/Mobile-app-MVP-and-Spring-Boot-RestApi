package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Services;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ColorDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DTO.ModelDTO;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarColors;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarModel;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Exceptions.CustomBadRequest;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ModelServiceImpl implements  ModelService{

    @Autowired
    ModelRepository modelRepository;

    @Override
    public List<ModelDTO> getAll() {
        List<ModelDTO> modelDto = new ArrayList<>();
        for (CarModel model : modelRepository.getAll()) {
            modelDto.add(ModelDTO.builder().id(model.getId()).name(model.getName()).brandId(model.getBrandId()).build());
        }
        return modelDto;
    }

    @Override
    public ModelDTO findById(int id) {
        CarModel model = modelRepository.findById(id);
        return ModelDTO.builder().id(model.getId()).name(model.getName()).brandId(model.getBrandId()).build();
    }

    @Override
    public Map<String,String> add(ModelDTO entity) throws CustomBadRequest {
        Map<String,String> map = new HashMap<>();
        try {
            modelRepository.add(addModel(entity));
            map.put("Message","Yeni Model yaradildi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Yeni Model yaradilmadi")));
        }
        return map;
    }

    @Override
    public Map<String,String> update(ModelDTO entity)  throws CustomBadRequest{
        Map<String,String> map = new HashMap<>();
        try {
            modelRepository.update(addModel(entity));
            map.put("Message","Model deyisidirldi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Model deyisdirilmedi")));
        }
        return map;
    }

    @Override
    public  Map<String,String> delete(int id) {
        Map<String,String> map = new HashMap<>();
        try{
            modelRepository.delete(id);
            map.put("Message","Model silindi");
        }catch (Exception e){
            map.put("Message", String.valueOf(new CustomBadRequest("Model silinmedi")));
        }
        return map;
    }

    @Override
    public  List<ModelDTO> FindByModelByBrandId(int id) {
        List<ModelDTO> modelDto = new ArrayList<>();
        for (CarModel model : modelRepository.FindModelByBrand(id)) {
            modelDto.add(ModelDTO.builder().id(model.getId()).name(model.getName()).brandId(model.getBrandId()).build());
        }
        return modelDto;
    }

    public CarModel addModel(ModelDTO modelDTO){
        CarModel carModel =null;
        if(modelDTO.getName() != null && modelDTO.getBrandId()!= 0){
            carModel = CarModel.builder()
                    .id(modelDTO.getId())
                    .name(modelDTO.getName())
                    .brandId(modelDTO.getBrandId())
                    .build();
        }
        return carModel;
    }

}
