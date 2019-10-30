package com.competitions.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
@Getter
@Setter
public class RequestForEnterPK implements Serializable {

    @Column(name = "captain_id")
    private int captainId;

    @Column(name = "member_id")
    private int memberId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestForEnterPK)) return false;
        RequestForEnterPK that = (RequestForEnterPK) o;
        return getCaptainId() == that.getCaptainId() &&
                getMemberId() == that.getMemberId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCaptainId(), getMemberId());
    }
}
