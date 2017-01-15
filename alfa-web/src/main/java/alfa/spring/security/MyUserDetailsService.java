package alfa.spring.security;

import alfa.spring.data.AccountEntity;
import alfa.spring.data.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Spring4.1以降でUserDetailsServiceをBean定義していたら自動的に使われる。
 *
 * @author irof
 * @see org.springframework.security.config.annotation.authentication.configuration.InitializeUserDetailsBeanManagerConfigurer
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AccountEntity> account = repository.findByName(username);
        return new MyUserDetails(
                account.orElseThrow(() -> new UsernameNotFoundException(username)),
                AuthorityUtils.createAuthorityList("ROLE_SAMPLE"));
    }
}
