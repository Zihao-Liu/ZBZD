package cn.lzh.zbzd.dao;

import java.util.List;

import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.model.QuestionBelongTag;
import cn.lzh.zbzd.model.Tag;

public interface QuestionBelongTagDao {
    public List<Question> listQuestionByTagId(long tagId);

    public int insertQuestionBelongTag(QuestionBelongTag questionBelongTag);
    
    public List<Question> listQuestionByTagIdOrderByModifiedTime(long tagId);
    
    public List<Question> listQuestionByTagIdOrderByAnswerCount(long tagId);
    
    public Tag getTagIdByQuestionId(long questionId);
}
