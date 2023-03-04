package kz.bitlab.logistic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
public class Permission extends BaseEntity implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
