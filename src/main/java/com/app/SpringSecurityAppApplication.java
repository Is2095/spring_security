package com.app;

import com.app.persistence.PermissionEntity;
import com.app.persistence.Repository.UserRepository;
import com.app.persistence.RoleEntity;
import com.app.persistence.RoleEnum;
import com.app.persistence.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityAppApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) { /* este método se ejecuta a penas se levanta la aplicación*/
        return args -> {
            /*CREATE PERMISSIONS*/
            PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();
            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();
            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();
            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();
            PermissionEntity refactorPermission = PermissionEntity.builder()
                    .name("REFACTOR")
                    .build();

            /* CREATE ROLES */
            RoleEntity roleAdmin = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();
            RoleEntity roleUser = RoleEntity.builder()
                    .roleEnum(RoleEnum.USER)
                    .permissionList(Set.of(createPermission, readPermission))
                    .build();
            RoleEntity roleInvited = RoleEntity.builder()
                    .roleEnum(RoleEnum.INVITED)
                    .permissionList(Set.of(readPermission))
                    .build();
            RoleEntity roleDeveloper = RoleEntity.builder()
                    .roleEnum(RoleEnum.DEVELOPER)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
                    .build();

            /* CREATE USERS */
            UserEntity userPepe = UserEntity.builder()
                    .username("pepe")
                    .password("$2a$10$/QcD.1pHC0X4X8x5ZnRF6OIj3Ay3az5Pi4vcKDVxxtgImGA1wXQmy")
//                    .password("1234")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleAdmin))
                    .build();
            UserEntity userDaniel = UserEntity.builder()
                    .username("daniel")
                    .password("$2a$10$/QcD.1pHC0X4X8x5ZnRF6OIj3Ay3az5Pi4vcKDVxxtgImGA1wXQmy")
//                    .password("1234")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleUser))
                    .build();
            UserEntity userTito = UserEntity.builder()
                    .username("tito")
                    .password("$2a$10$/QcD.1pHC0X4X8x5ZnRF6OIj3Ay3az5Pi4vcKDVxxtgImGA1wXQmy")
//                    .password("1234")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleInvited))
                    .build();
            UserEntity userAna = UserEntity.builder()
                    .username("ana")
                    .password("$2a$10$/QcD.1pHC0X4X8x5ZnRF6OIj3Ay3az5Pi4vcKDVxxtgImGA1wXQmy")
//                    .password("1234")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleDeveloper))
                    .build();
            userRepository.saveAll(List.of(userPepe, userDaniel, userTito, userAna));
        };
    }
}
