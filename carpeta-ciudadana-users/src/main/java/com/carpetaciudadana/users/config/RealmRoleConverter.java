package com.carpetaciudadana.users.config;

public class RealmRoleConverter {//implements Converter<Jwt, Collection<GrantedAuthority>> {
//	@Override
//	public Collection<GrantedAuthority> convert(Jwt jwt) {
//
//		// Se extrae el claim
//		final Map<String, List<String>> realmAccess = (Map<String, List<String>>) jwt.getClaims().get("realm_access");
//
//		// Se obtienen los roles
//		return realmAccess.get("roles").stream().map(roleName -> "ROLE_" + roleName) // prefix required by Spring
//																						// Security for roles.
//				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//	}
}