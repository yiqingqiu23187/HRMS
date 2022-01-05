package edu.fudan.hrms.service;

import edu.fudan.hrms.controller.req.ReleaseNoticeReq;
import edu.fudan.hrms.entity.Notice;
import edu.fudan.hrms.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<Notice> listNotices() {
        List<Notice> list = noticeRepository.findAll();
        int start = Math.max(0, list.size()-10);
        return list.subList(start,list.size());
    }

    public void releaseNotice(ReleaseNoticeReq req) {
        noticeRepository.save(new Notice(req.notice, req.userID));
    }
}
