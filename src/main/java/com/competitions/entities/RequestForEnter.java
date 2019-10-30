package com.competitions.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "request_for_enter",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"captain_id", "member_id"})})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class RequestForEnter implements Serializable {

    @EmbeddedId
    RequestForEnterPK requestForEnterPK;

    @ManyToOne
    @MapsId(value = "captain_id")
    @JoinColumn(name = "captain_id")
    private Captain captain;

    @ManyToOne
    @MapsId(value = "member_id")
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "request_description")
    private String requestDescription;

    @Column(name = "request_status", nullable = false)
    private boolean requestStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestForEnter)) return false;
        RequestForEnter that = (RequestForEnter) o;
        return isRequestStatus() == that.isRequestStatus() &&
                getRequestForEnterPK().equals(that.getRequestForEnterPK()) &&
                getCaptain().equals(that.getCaptain()) &&
                getMember().equals(that.getMember());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequestForEnterPK(), getCaptain(), getMember(), isRequestStatus());
    }
}
