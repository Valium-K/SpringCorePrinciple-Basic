package hello.core2;

import hello.core2.member.Grade;
import hello.core2.member.Member;
import hello.core2.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // POJO - 객체를 직접 찾아옴
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // Spring - 스프링 컨테이너를 통해서 찾아옴
        // 스프링 컨테이너 == ApplicationContext
        // AppConfig의 @Bean에 대해 관리를 할 것이다 - AnnotationConfigApplicationContext 사용
        // AnnotationConfigApplicationContext에 AppConfig 클래스 자체를 넘겨준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 인자 - name: 찾을 @Bean이 달린 메서드 이름, Class: 해당 타입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);



        Member member = new Member(1l, "memberA", Grade.VIP);
        memberService.join(member);

        Member foundMember = memberService.findMember(1L);

        System.out.println("new member: " + member.getName());
        System.out.println("found member: " + foundMember.getName());
    }

}
