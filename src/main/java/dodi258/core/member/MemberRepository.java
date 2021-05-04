package dodi258.core.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface MemberRepository {

    void add(Member member);

    Optional<Member> findById(Long memberId);

}
