package org.mybatis.myservice.service;

import org.mybatis.myservice.dao.MemberDAO;
import org.mybatis.myservice.model.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO memberDAO;

    @Override
    public List<MemberVO> selectAllMembers() {
        return memberDAO.selectAllMembers();
    }

    @Override
    public void insertMember(MemberVO memberVO) {
        memberDAO.insert(memberVO);
    }

    @Override
    public void updateMember(int id, MemberVO memberVO) {
        MemberVO member = memberDAO.selectById(id);
        if (member != null) {
            member.setName(memberVO.getName());
            member.setJob(memberVO.getJob());
        } else {
            throw new IllegalStateException("회원이 존재하지 않는다.");
        }
        memberDAO.update(member);
    }

    @Override
    public void deleteMember(int id) {
        if (memberDAO.selectById(id) != null) {
            memberDAO.delete(id);
        } else {
            throw new IllegalStateException("회원이 존재하지 않는다.");
        }
    }

    @Override
    public MemberVO selectById(int id) {
        return memberDAO.selectById(id);
    }
}
