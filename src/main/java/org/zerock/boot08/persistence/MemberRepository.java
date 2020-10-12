package org.zerock.boot08.persistence;

import org.springframework.data.repository.CrudRepository;
import org.zerock.boot08.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
}
