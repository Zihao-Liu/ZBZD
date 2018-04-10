package cn.lzh.zbzd.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lzh.zbzd.dao.QuestionBelongTagDao;
import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.model.QuestionBelongTag;
import cn.lzh.zbzd.model.Tag;
import cn.lzh.zbzd.service.QuestionBelongTagService;

@Service
public class QuestionBelongTagServiceImpl implements QuestionBelongTagService {
    @Autowired
    QuestionBelongTagDao questionBelongTagDao;

    @Autowired
    QuestionBelongTag questionBelongTag;

    @Override
    public int insertQuestionBelongTag(long questionId, long tagId) {
        questionBelongTag.setCreateTime(new Date());
        questionBelongTag.setModifiedTime(questionBelongTag.getCreateTime());
        questionBelongTag.setQuestionId(questionId);
        questionBelongTag.setTagId(tagId);
        return questionBelongTagDao.insertQuestionBelongTag(questionBelongTag);
    }

    @Override
    public List<Question> listQuestionByTagId(long tagId) {
        return questionBelongTagDao.listQuestionByTagId(tagId);
    }

    @Override
    public List<Question> listQuestionByTagIdOrderByModifiedTime(long tagId) {
        return questionBelongTagDao.listQuestionByTagIdOrderByModifiedTime(tagId);
    }

    @Override
    public List<Question> listQuestionByTagIdOrderByAnswerCount(long tagId) {
        return questionBelongTagDao.listQuestionByTagIdOrderByAnswerCount(tagId);
    }
    
    @Override
    public Tag getTagIdByQuestionId(long questionId) {
        return questionBelongTagDao.getTagIdByQuestionId(questionId);
    }

}
