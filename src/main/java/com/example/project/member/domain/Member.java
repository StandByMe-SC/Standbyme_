package com.example.project.member.domain;

import com.example.project.board.domain.Board;
import com.example.project.reply.domain.Reply;
import com.google.firebase.auth.FirebaseToken;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberName;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @Column(name = "USER_EMAIL", nullable = false)
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();


    @OneToMany(mappedBy = "member")
    private List<Reply> replies = new ArrayList<>();
//    @OneToMany(mappedBy = "member")
//    @Column(name = "USER_REPLY")
//    private List<Reply> replies = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Builder
    private Member(String memberName, Role role, String email, String name) {
        this.memberName = memberName;
        this.role = role;
        this.email = email;
        this.name = name;

    }

    public void update(FirebaseToken token) { // picture 필드 삭제
        this.memberName = token.getUid();
        this.email = token.getEmail();
        this.name = token.getName();
    }

}
