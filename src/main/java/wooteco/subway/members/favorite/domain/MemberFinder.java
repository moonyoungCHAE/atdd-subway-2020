package wooteco.subway.members.favorite.domain;

import wooteco.security.core.context.SecurityContextHolder;

import java.util.LinkedHashMap;
import java.util.Objects;

public class MemberFinder {
    public static boolean isLogin() {
        if (Objects.nonNull(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())) {
            return true;
        }
        return false;
    }

    public static int findMemberAge() {
        try {
            String age = (String) ((LinkedHashMap) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal())
                    .get("age");
            return Integer.parseInt(age);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("사용자의 나이를 찾을 수 없습니다.");
        }
    }
}
