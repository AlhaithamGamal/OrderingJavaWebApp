package net.haitham.otloblidal.entity.annotation;
// Generated Jan 10, 2020 8:49:56 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NamedQuery;

/**
 * Country generated by hbm2java
 */
@Entity
@NamedQuery(
    name = "get_person_by_name",
    query = "select c from Country c where c.nameAr = :nameAr"
)
@Table(name="country"
    ,catalog="online_ordering_db"
    , uniqueConstraints = {@UniqueConstraint(columnNames="name_ar"), @UniqueConstraint(columnNames="name_en")} 
)
public class Country  implements java.io.Serializable {


     private Integer id;
     private String nameAr;
     private String nameEn;
     private Set<City> cities = new HashSet<City>(0);

    public Country() {
    }

	
    public Country(String nameAr, String nameEn) {
        this.nameAr = nameAr;
        this.nameEn = nameEn;
    }
    public Country(String nameAr, String nameEn, Set<City> cities) {
       this.nameAr = nameAr;
       this.nameEn = nameEn;
       this.cities = cities;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public Country setId(Integer id) {
        this.id = id;
        return this;
    }

    
    @Column(name="name_ar", unique=true, nullable=false, length=100)
    public String getNameAr() {
        return this.nameAr;
    }
    
    public Country setNameAr(String nameAr) {
        this.nameAr = nameAr;
        return this;
    }

    
    @Column(name="name_en", unique=true, nullable=false, length=100)
    public String getNameEn() {
        return this.nameEn;
    }
    
    public Country setNameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }

@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="country")
@LazyCollection(LazyCollectionOption.FALSE)
    public Set<City> getCities() {
        return this.cities;
    }
    
    public void setCities(Set<City> cities) {
        this.cities = cities;
    }




}


