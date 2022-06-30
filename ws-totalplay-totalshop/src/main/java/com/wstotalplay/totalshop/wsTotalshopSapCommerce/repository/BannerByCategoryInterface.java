package com.wstotalplay.totalshop.wsTotalshopSapCommerce.repository;


import com.wstotalplay.totalshop.wsTotalshopSapCommerce.entity.BannerByCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BannerByCategoryInterface extends JpaRepository<BannerByCategoryEntity, String> {
    @Query(value = "SELECT * FROM ( SELECT MCN.MCN_ID AS ID, MCN.MCN_TITLE AS TITILE, MCN.MCN_DESCRIPTION AS DESCRIPTION, MCN.MCN_STATUS AS STATUS, MCN.MCN_BUTTON_TEXT AS TEXT_BUTTON, MCN.MCN_COM_ID AS ID_COMMERCE, MCN.MCN_DATE_INI AS DATE_INI, MCN.MCN_DATE_END AS DATE_END, MCN.MCN_BANNER_CATEGORY AS ID_CATEGORY_COMMERCE, CONCAT(MCS.VALUE,MCN.MCN_IMG_PATH) AS HREF, MCG.MCG_ID AS TYPE_CATEGORY, MCG.MCG_DESCRIPTION AS CATEGORY_DESCRIPTION FROM MKT_CONFIG_NEWS MCN  INNER JOIN MKT_CAT_GENERIC MCG ON MCN.MCG_ID = MCG.MCG_ID INNER JOIN MKT_CONFIGURATION_SERVICE MCS ON MCS.NAME = 'cdn-img' WHERE MCN.MCN_STATUS = 1 AND MCN.MCG_ID = 21 AND MCN.MCN_BANNER_CATEGORY  = LOWER(:idCategory) AND MCN.MCN_DATE_END >= SYSDATE ORDER BY MCN.MCN_ID ASC ) WHERE ROWNUM = 1", nativeQuery = true)
    BannerByCategoryEntity getBannerByCategory(@Param("idCategory") String idCategory);
}
