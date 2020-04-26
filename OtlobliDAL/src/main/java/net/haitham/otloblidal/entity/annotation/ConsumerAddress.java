package net.haitham.otloblidal.entity.annotation;
// Generated Jan 10, 2020 8:49:56 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ConsumerAddress generated by hbm2java
 */
@Entity
@Table(name="consumer_address"
    ,catalog="online_ordering_db"
)
public class ConsumerAddress  implements java.io.Serializable {


     private Integer id;
     private Area area;
     private Consumer consumer;
     private String street;
     private String building;
     private String floor;
     private String flat;
     private String landmark;
     private BigDecimal lat;
     private BigDecimal lng;
     private String label;
     private byte active;
     private Set<Order> orders = new HashSet<Order>(0);

    public ConsumerAddress() {
    }

	
    public ConsumerAddress(Area area, Consumer consumer, String street, String building, String floor, String flat, byte active) {
        this.area = area;
        this.consumer = consumer;
        this.street = street;
        this.building = building;
        this.floor = floor;
        this.flat = flat;
        this.active = active;
    }
    public ConsumerAddress(Area area, Consumer consumer, String street, String building, String floor, String flat, String landmark, BigDecimal lat, BigDecimal lng, String label, byte active, Set<Order> orders) {
       this.area = area;
       this.consumer = consumer;
       this.street = street;
       this.building = building;
       this.floor = floor;
       this.flat = flat;
       this.landmark = landmark;
       this.lat = lat;
       this.lng = lng;
       this.label = label;
       this.active = active;
       this.orders = orders;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="area_id", nullable=false)
    public Area getArea() {
        return this.area;
    }
    
    public void setArea(Area area) {
        this.area = area;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="consumer_id", nullable=false)
    public Consumer getConsumer() {
        return this.consumer;
    }
    
    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    
    @Column(name="street", nullable=false, length=100)
    public String getStreet() {
        return this.street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }

    
    @Column(name="building", nullable=false, length=100)
    public String getBuilding() {
        return this.building;
    }
    
    public void setBuilding(String building) {
        this.building = building;
    }

    
    @Column(name="floor", nullable=false, length=100)
    public String getFloor() {
        return this.floor;
    }
    
    public void setFloor(String floor) {
        this.floor = floor;
    }

    
    @Column(name="flat", nullable=false, length=100)
    public String getFlat() {
        return this.flat;
    }
    
    public void setFlat(String flat) {
        this.flat = flat;
    }

    
    @Column(name="landmark")
    public String getLandmark() {
        return this.landmark;
    }
    
    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    
    @Column(name="lat", precision=10, scale=8)
    public BigDecimal getLat() {
        return this.lat;
    }
    
    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    
    @Column(name="lng", precision=11, scale=8)
    public BigDecimal getLng() {
        return this.lng;
    }
    
    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    
    @Column(name="label", length=100)
    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }

    
    @Column(name="active", nullable=false)
    public byte getActive() {
        return this.active;
    }
    
    public void setActive(byte active) {
        this.active = active;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consumerAddress")
    public Set<Order> getOrders() {
        return this.orders;
    }
    
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }




}


