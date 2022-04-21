package fr.gopartner.auth.domain.forgetPassword;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@javax.persistence.Entity
@Table(name = "Token_Reset_Password")
@Data
public class ForgetPassword {
    @Id
    private String uuid;
    private String email;
    private LocalDateTime date;
}
