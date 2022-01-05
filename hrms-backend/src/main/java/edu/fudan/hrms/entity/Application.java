package edu.fudan.hrms.entity;

import edu.fudan.hrms.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long applicationID;
    public Long userID;
    public int type; // 0调薪申请 1调动申请
    public int status = Constants.STATUS_PROCESSING; //0   // 0待处理 1已通过 2已拒绝
    public Long oldValue; // 旧薪水/旧部门
    public Long newValue; // 新薪水/新部门
    public Long time = System.currentTimeMillis();
    public String reason = "";   // 拒绝申请时的拒绝理由，同意申请传空字符串

    public Application(Long userID, int type, int status, Long oldValue, Long newValue, Long time) {
        this.userID = userID;
        this.type = type;
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.time = time;
    }

    public Application() {

    }
}
