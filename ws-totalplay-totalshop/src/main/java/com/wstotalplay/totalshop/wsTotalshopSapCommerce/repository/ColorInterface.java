package com.wstotalplay.totalshop.wsTotalshopSapCommerce.repository;


import com.wstotalplay.totalshop.wsTotalshopSapCommerce.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ColorInterface extends JpaRepository<ColorEntity,String> {

    @Query(value = "SELECT MCS_ID,MCS_NAME,MCS_TEXT,MCS_GENERIC,MCS_PATH,MCS_LOG FROM MKT_CONFIGURATION_STB WHERE MCS_NAME =:idText", nativeQuery = true)
    ColorEntity getColor(@Param("idText") String idText);

}
