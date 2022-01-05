package edu.fudan.hrms.controller;

import edu.fudan.hrms.controller.req.ReleaseNoticeReq;
import edu.fudan.hrms.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Controller
@CrossOrigin
public class NoticeController {
    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice/list")
    public ResponseEntity<?> getSalary() {
        return ResponseEntity.ok(noticeService.listNotices());
    }

    @PostMapping("/notice/release")
    public ResponseEntity<?> applySalary(@RequestBody ReleaseNoticeReq req) {
        noticeService.releaseNotice(req);
        return ResponseEntity.ok(new HashMap<String, Object>() {
            {
                put("success", true);
            }
        });
    }

}
