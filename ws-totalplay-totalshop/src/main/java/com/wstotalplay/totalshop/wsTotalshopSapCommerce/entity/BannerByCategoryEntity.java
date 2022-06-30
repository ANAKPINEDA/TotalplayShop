package com.wstotalplay.totalshop.wsTotalshopSapCommerce.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "MKT_CONFIG_NEWS")
public class BannerByCategoryEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "TITILE")
    private String titile;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TEXT_BUTTON")
    private String text_button;

    @Column(name = "ID_COMMERCE")
    private String id_commerce;

    @Column(name = "DATE_INI")
    private String date_ini;

    @Column(name = "DATE_END")
    private String date_end ;

    @Column(name = "ID_CATEGORY_COMMERCE")
    private String id_category_commerce;

    @Column(name = "HREF")
    private String href;

    @Column(name = "TYPE_CATEGORY")
    private String type_category;

    @Column(name = "CATEGORY_DESCRIPTION")
    private String category_description;

}
