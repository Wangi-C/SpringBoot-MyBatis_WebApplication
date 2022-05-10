package org.mybatis.myservice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.myservice.model.MemberVO;

import java.util.List;

@Mapper
public interface MemberDAO {
    public List<MemberVO> selectAllMembers();

    void insert(MemberVO memberVO);

    MemberVO selectById(int id);

    void delete(int id);

    void update(MemberVO member);

    MemberVO selectByName(String name);
}
