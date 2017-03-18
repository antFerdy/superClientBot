package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TEST")
public class TestEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID_")
    private Integer id;

    @Column(name = "CODE3_")
    private String code3;

    @Column(name = "CODE6_")
    private String code6;

    @Column(name = "NAME_")
    private String name;

    @Column(name = "COUNTRY_")
    private String country;

    @Column(name = "ENABLED_", columnDefinition = "boolean default true")
    private boolean enabled;
    

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode3() {
        return this.code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getCode6() {
        return this.code6;
    }

    public void setCode6(String code6) {
        this.code6 = code6;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}