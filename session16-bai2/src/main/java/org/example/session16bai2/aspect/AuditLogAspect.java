package org.example.session16bai2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditLogAspect {

    @After("execution(* org.example.session16bai2.controller.EmployeeController.getEmployees(..))")
    public void logEmployeeAccess(JoinPoint joinPoint) {

        String methodName =
                joinPoint.getSignature().getName();

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        System.out.println(
                "[AUDIT LOG] Tài khoản '"
                        + username
                        + "' đã gọi hàm '"
                        + methodName
                        + "' vào lúc "
                        + LocalDateTime.now()
        );
    }
}