package fr.gopartner.auth.domain.authority;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;


@javax.persistence.Entity(name = "authority")
@Data
@SuperBuilder
@NoArgsConstructor
public class Authority implements GrantedAuthority ,Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
