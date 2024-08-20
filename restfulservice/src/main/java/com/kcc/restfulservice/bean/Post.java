package com.kcc.restfulservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
//    @Id
    // 값 증가
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Integer user_id;

    // 기준은 현 클래스로 many인지 one인지 결정
    // FetchType.LAZY: 처음부터 데이터 값을 가져오지 않고 유저를 검색하는 등 필요할 때만 가져옴
//    @ManyToOne(fetch = FetchType.LAZY)
    // 관계를 위한 설정으로 눈에 보이지 않아도 됨
//    @JsonIgnore
//    private User user;

}
