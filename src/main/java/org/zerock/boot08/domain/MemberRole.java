package org.zerock.boot08.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tbl_member_roles")
@EqualsAndHashCode(of = "fno")
@ToString
public class MemberRole {
    @Id
    private Long fno;
    private String roleName;
}
