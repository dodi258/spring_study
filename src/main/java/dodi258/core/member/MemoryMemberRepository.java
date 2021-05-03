package dodi258.core.member;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> memoryMemberMap = new ConcurrentHashMap<>();

    @Override
    public void add(Member member) {
        Long autoIncrementId = new Long(memoryMemberMap.size() + 1);
        member.setId(autoIncrementId);

        memoryMemberMap.put(autoIncrementId,member);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(memoryMemberMap.get(memberId));
    }
}
