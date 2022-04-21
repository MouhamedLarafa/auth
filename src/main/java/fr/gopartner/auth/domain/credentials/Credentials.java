package fr.gopartner.auth.domain.credentials;

import fr.gopartner.auth.domain.authority.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@javax.persistence.Entity
@Data
@SuperBuilder
@Table(name = "credentials")
@NoArgsConstructor
public class Credentials implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    private String name;
    private String password;
    private String lastName;
    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "credentials_authorities",
            joinColumns = {
                    @JoinColumn(name = "credentials_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "authorities_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Authority> authorities = new HashSet<>();
    private Boolean enabled;


}

