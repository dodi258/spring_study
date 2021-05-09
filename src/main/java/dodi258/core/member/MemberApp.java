package dodi258.core.member;

import dodi258.core.configuration.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member foundMember = memberService.findMemberById(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("found Member = " + foundMember.getName());
    }
}
