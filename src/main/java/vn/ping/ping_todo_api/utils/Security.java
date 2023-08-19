package vn.ping.ping_todo_api.utils;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vn.ping.ping_todo_api.models.User;
import vn.ping.ping_todo_api.repositories.UserRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class Security {

    private final UserRepository userRepository;
    public void officialCheck(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserDetails userDetails){
                String phoneNumber = userDetails.getUsername();
                User user = userRepository.findByPhoneNumber(phoneNumber)
                        .orElseThrow(() -> new NullPointerException("User not found"));
                if(!user.getId().equals(userId)){
                    throw  new IllegalArgumentException("User can't implement to other user");
                }
            }
        }
    }

    public Long getIdUserFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserDetails userDetails){
                String phoneNumber = userDetails.getUsername();
                User user = userRepository.findByPhoneNumber(phoneNumber)
                        .orElseThrow(() -> new NoSuchElementException("User not found"));
                return user.getId();
            }
        }
        throw new JwtException("missing jwt");
    }
}
