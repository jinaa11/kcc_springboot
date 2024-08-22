package com.kcc.security1.auth;

import com.kcc.security1.model.User;
import com.kcc.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // 로그인 버튼을 누르면 호출
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 인증 완료
        User userEntity = userRepository.findByUsername(username);

        if(userEntity != null) {
            return new PrincipalDetail(userEntity);
        }
        return null;
    }
}
