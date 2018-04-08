package cn.lzh.zbzd.dao;

import java.util.List;

import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.model.QuestionBelongTag;

public interface QuestionBelongTagDao {
    public List<Question> listQuestionByTagId(long tagId);

    public int insertQuestionBelongTag(QuestionBelongTag questionBelongTag);
}
