package org.mybatis.myservice.service;

import org.mybatis.myservice.model.MemberVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    public List<MemberVO> selectAllMembers();

    public void insertMember(MemberVO memberVO);

    void updateMember(int id, MemberVO memberVO);

    void deleteMember(int id);

    MemberVO selectById(int id);
}
