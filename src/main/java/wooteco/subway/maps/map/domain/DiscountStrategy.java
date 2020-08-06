package wooteco.subway.maps.map.domain;

import wooteco.subway.members.favorite.domain.MemberFinder;

public class DiscountStrategy {
    public int discountFare(int fare) {
        if (!MemberFinder.isLogin()) {
            return 0;
        }

        int age = MemberFinder.findMemberAge();
        if (age == 0 || age > 20) {
            return 0;
        }
        if (age > 13) {
            return 350 + (fare - 350) * 20 / 100;
        }
        if (age > 6) {
            return 350 + (fare - 350) * 50 / 100;
        }
        return 0;
    }
}
