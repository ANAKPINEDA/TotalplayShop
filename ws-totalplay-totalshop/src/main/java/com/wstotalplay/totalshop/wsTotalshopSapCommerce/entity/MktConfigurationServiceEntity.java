package com.wstotalplay.totalshop.wsTotalshopSapCommerce.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Table(name = "MKT_CONFIGURATION_SERVICE")
public class MktConfigurationServiceEntity {
    @Id
    @Column(name = "NAME")
    private String name;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "LAST_UPDATE")
    private Date lastUpdate;
}
