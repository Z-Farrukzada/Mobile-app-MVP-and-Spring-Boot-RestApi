package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.BaseRepository;

import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.DbQueries.AnnouncementQuery;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.AnnouncementWithModelBrandExc;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Domain.CarAnnouncement;
import com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Repositories.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnnouncementRepositoryImpl implements AnnouncementRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CarAnnouncement> getAll() {
        return null;
    }

    @Override
    public CarAnnouncement findById(int id) {
        return null;
    }

    @Override
    public void add(CarAnnouncement entity) {

    }

    @Override
    public void update(CarAnnouncement entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<AnnouncementWithModelBrandExc> getAnnouncementOnlyModelBrandExchange() {
        return jdbcTemplate.query(AnnouncementQuery.SQL_ANNOUNCEMENT_MAIN,withModelBrandExcRowMapper);
    }

     private final RowMapper<AnnouncementWithModelBrandExc> withModelBrandExcRowMapper = (((resultSet, i) -> {
           return new AnnouncementWithModelBrandExc(resultSet.getInt("id"),
                                                    resultSet.getInt("walk"),
                                                    resultSet.getInt("price"),
                                                    resultSet.getDate("carYear"),
                                                    resultSet.getString("model_name"),
                                                    resultSet.getString("brand_name"),
                                                    resultSet.getString("exchange_name"),
                                                    resultSet.getDate("createdAnnouncement"),
                                                    resultSet.getString("imageUrl"));
     }));


}
