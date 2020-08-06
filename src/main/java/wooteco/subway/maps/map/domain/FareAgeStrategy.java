package wooteco.subway.maps.map.domain;

import lombok.Builder;
import wooteco.subway.members.favorite.domain.MemberFinder;

public class FareAgeStrategy {
    private int minAge;
    private int maxAge;
    private int basicDiscount;
    private int discountPercentage;
    private MemberFinder memberFinder;

    @Builder
    public FareAgeStrategy(int minAge, int maxAge, int basicDiscount, int discountPercentage) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.basicDiscount = basicDiscount;
        this.discountPercentage = discountPercentage;
        this.memberFinder = new MemberFinder();
    }

    public FareAgeStrategy(int minAge, int maxAge,
                           int basicDiscount, int discountPercentage, MemberFinder memberFinder) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.basicDiscount = basicDiscount;
        this.discountPercentage = discountPercentage;
        this.memberFinder = memberFinder;
    }

    public int discountFare(int fare) {
        if (hasNoDiscountCondition()) {
            return 0;
        }
        return basicDiscount + (fare - basicDiscount) * discountPercentage / 100;
    }

    private boolean hasDiscountCondition() {
        return memberFinder.isLogin() && isBetween(memberFinder.findMemberAge());
    }

    private boolean hasNoDiscountCondition() {
        return !hasDiscountCondition();
    }

    private boolean isBetween(int age) {
        return age >= minAge && age < maxAge;
    }
}
