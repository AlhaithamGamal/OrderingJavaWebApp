package net.haitham.otloblidal.entity.annotation;
// Generated Jan 10, 2020 8:49:56 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * ProviderUser generated by hbm2java
 */
@Entity
@Table(name = "provider_user", catalog = "online_ordering_db", uniqueConstraints = @UniqueConstraint(columnNames = "username")
)
public class ProviderUser implements java.io.Serializable {

    private Integer id;
    private Branch branch;
    private Provider provider;
    private String username;
    private String password;
    private String encrypted_password;
    private byte active;
    private String email;

    public ProviderUser() {
    }

    public ProviderUser(Provider provider, String username, String password, byte active) {
        this.provider = provider;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public ProviderUser(Branch branch, Provider provider, String username, String password, byte active) {
        this.branch = branch;
        this.provider = provider;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    public Branch getBranch() {
        return this.branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", nullable = false)
    public Provider getProvider() {
        return this.provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Column(name = "username", unique = true, nullable = false, length = 100)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "active", nullable = false)
    public byte getActive() {
        return this.active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @Column(name = "email", unique = true, nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
@Column(name = "encrypted_password", nullable = false)
    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

}
