package ru.task_manager.services.security;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.task_manager.dto.user.AppUserDTO;
import ru.task_manager.dto.user.UserDetailsImpl;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.EmailNotFoundException;
import ru.task_manager.repositories.UserRepo;
import ru.task_manager.services.UserService;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email));
        return new UserDetailsImpl(
                modelMapper.map(userEntity, AppUserDTO.class)
        );
    }
}
