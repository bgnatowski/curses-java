package com.amigoscode.springsecurity.auth;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.amigoscode.springsecurity.security.ApplicationUserRole.*;

@RequiredArgsConstructor
@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {
	private final PasswordEncoder passwordEncoder;

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		return getApplicationUsers().stream()
				.filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}

	private List<ApplicationUser> getApplicationUsers(){
		List<ApplicationUser> applicationUsers = Lists.newArrayList(
				new ApplicationUser(
						"annasmith",
						passwordEncoder.encode("password"),
						STUDENT.getGrantedAuthorities(),
						true,
						true,
						true,
						true),
				new ApplicationUser(
						"linda",
						passwordEncoder.encode("password"),
						ADMIN.getGrantedAuthorities(),
						true,
						true,
						true,
						true),
				new ApplicationUser(
						"tom",
						passwordEncoder.encode("password"),
						ADMINTRAINEE.getGrantedAuthorities(),
						true,
						true,
						true,
						true)
		);

		return applicationUsers;
	}
}
