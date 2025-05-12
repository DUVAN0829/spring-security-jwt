package com.security.app;

import com.security.app.entities.PermissionEntity;
import com.security.app.entities.RoleEntity;
import com.security.app.entities.RoleEnum;
import com.security.app.entities.UserEntity;
import com.security.app.repository.UserRepository;
import com.security.app.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {

        return args -> {

            //todo: Permissions
            PermissionEntity createPermission = new PermissionEntity();
            createPermission.setName("CREATE");

            PermissionEntity readPermission = new PermissionEntity();
            readPermission.setName("READ");

            PermissionEntity updatePermission = new PermissionEntity();
            updatePermission.setName("UPDATE");

            PermissionEntity deletePermission = new PermissionEntity();
            deletePermission.setName("DELETE");

            PermissionEntity refactorPermission = new PermissionEntity();
            refactorPermission.setName("REFACTOR");

            //todo: Roles
            RoleEntity roleDeveloper = new RoleEntity();
            roleDeveloper.setRolesEnum(RoleEnum.DEVELOPER);
            roleDeveloper.setPermissionEntities(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission));

            RoleEntity roleAdmin = new RoleEntity();
            roleAdmin.setRolesEnum(RoleEnum.ADMIN);
            roleAdmin.setPermissionEntities(Set.of(createPermission, readPermission, updatePermission, deletePermission));

            RoleEntity roleUser = new RoleEntity();
            roleUser.setRolesEnum(RoleEnum.USER);
            roleUser.setPermissionEntities(Set.of(createPermission, readPermission));

            RoleEntity roleInvited = new RoleEntity();
            roleInvited.setRolesEnum(RoleEnum.INVITED);
            roleInvited.setPermissionEntities(Set.of(readPermission));

            //todo: Create users
            UserEntity userDuvan = new UserEntity();
            userDuvan.setUsername("Duván");
            userDuvan.setPassword(passwordEncoder.encode("12345"));
            userDuvan.setEnabled(true);
            userDuvan.setAccountNoExpired(true);
            userDuvan.setAccountNoLocked(true);
            userDuvan.setCredentialNoExpired(true);
            userDuvan.setRoles(Set.of(roleDeveloper));

            UserEntity userJames = new UserEntity();
            userJames.setUsername("James");
            userJames.setPassword(passwordEncoder.encode("54321"));
            userJames.setEnabled(true);
            userJames.setAccountNoExpired(true);
            userJames.setAccountNoLocked(true);
            userJames.setCredentialNoExpired(true);
            userJames.setRoles(Set.of(roleAdmin));

            UserEntity userJoe = new UserEntity();
            userJoe.setUsername("Joe");
            userJoe.setPassword(passwordEncoder.encode("56789"));
            userJoe.setEnabled(true);
            userJoe.setAccountNoExpired(true);
            userJoe.setAccountNoLocked(true);
            userJoe.setCredentialNoExpired(true);
            userJoe.setRoles(Set.of(roleUser));

            UserEntity userMarie = new UserEntity();
            userMarie.setUsername("Marie");
            userMarie.setPassword(passwordEncoder.encode("98765"));
            userMarie.setEnabled(true);
            userMarie.setAccountNoExpired(true);
            userMarie.setAccountNoLocked(true);
            userMarie.setCredentialNoExpired(true);
            userMarie.setRoles(Set.of(roleInvited));

            userRepository.saveAll(List.of(userDuvan, userJames, userJoe, userMarie));

        };

    }

}
