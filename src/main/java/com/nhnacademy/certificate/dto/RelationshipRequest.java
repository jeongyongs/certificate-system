package com.nhnacademy.certificate.dto;

import com.nhnacademy.certificate.entity.FamilyRelationship;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipRequest {
    private Integer serialNumber;

    @NotNull(message = "가족의 일련번호를 입력하세요.")
    private Integer familySerialNumber;

    @NotNull(message = "가족관계를 입력하세요.")
    private FamilyRelationship.Relationship relationship;
}
