package fr.gopartner.auth.domain.forgetPassword;

import fr.gopartner.auth.domain.forgetPassword.notification.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "lambda",url = "http://localhost:8091")
// hedha y5alik tahki m3a les autres microservices
public interface proxy {
    @PostMapping("/notifications")
    public ResponseEntity<String> forgetPassword(@RequestBody Notification notification);
}
