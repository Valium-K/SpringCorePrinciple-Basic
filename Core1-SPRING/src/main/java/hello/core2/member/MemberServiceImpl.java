package hello.core2.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 구현체가 하나만 있을때는 구현체뒤에 Impl을 관례상 쓴다.
@Component
public class MemberServiceImpl implements MemberService {

    // MemberServiceImpl이 memberRepository를 의존하지만
    // MemoryMemberRepository를 직접 생성하는 상황 - 사실상 구현체도 의존
    // 그래서 AppConfig가 이를 담당하게함
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 추상화에만 의존
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
