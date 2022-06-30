package com.wstotalplay.totalshop.wsTotalshopSapCommerce.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "MKT_CONFIGURATION_STB")
public class ColorEntity {

    @Id
    @Column(name = "MCS_ID")
    private String mcs_id;

    @Column(name = "MCS_NAME")
    private String mcs_name;

    @Column(name = "MCS_TEXT")
    private String mcs_text;

    @Column(name = "MCS_GENERIC")
    private String mcs_generic;

    @Column(name = "MCS_PATH")
    private String mcs_path;

    @Column(name = "MCS_LOG")
    private String mcs_log;

}
