package hello.core2;

import hello.core2.member.Grade;
import hello.core2.member.Member;
import hello.core2.member.MemberService;
import hello.core2.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1l, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member found = memberService.findMember(1l);
        System.out.println("new: " + memberA);
        System.out.println("found: " + found);

    }
}
