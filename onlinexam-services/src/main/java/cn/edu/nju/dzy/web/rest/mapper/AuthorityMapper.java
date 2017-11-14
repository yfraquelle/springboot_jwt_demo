package cn.edu.nju.dzy.web.rest.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cn.edu.nju.dzy.domain.Authority;
import cn.edu.nju.dzy.web.rest.dto.AuthorityDTO;

@Mapper(componentModel = "spring", uses = {})
public interface AuthorityMapper {

	AuthorityMapper INSTANCE=Mappers.getMapper(AuthorityMapper.class);
	
	AuthorityDTO authorityToAuthorityDTO(Authority authority);
	
	Authority authorityDTOToAuthority(AuthorityDTO authorityDTO);
	
	Set<AuthorityDTO> authoritysToAuthorityDTOs(Set<Authority> authoritys);
	
	Set<Authority> authorityDTOsToAuthoritys(Set<AuthorityDTO> authorityDTOs);
}
