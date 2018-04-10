package com.sample.sfms.service.impl;

import com.sample.sfms.entity.Feedback;
import com.sample.sfms.entity.User;
import com.sample.sfms.entity.UserFeedback;
import com.sample.sfms.repository.UserFeedbackRepository;
import com.sample.sfms.service.interf.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("mailService")
public class MailServiceImpl implements MailService {
    private static Logger logger = Logger.getLogger(MailServiceImpl.class.getName());
    private static String SUBJECT = "Nhắc nhở về việc làm feedback";
    @Value("${server.context-path}")
    private String CONTEXT_PATH;
    @Value("${spring.mail.username}")
    private String emailSender;

    private JavaMailSender javaMailSender;

    private UserFeedbackRepository userFeedbackRepository;

    private SpringTemplateEngine templateEngine;

    @Autowired
    public void setUserFeedbackRepository(UserFeedbackRepository userFeedbackRepository) {
        this.userFeedbackRepository = userFeedbackRepository;
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    public void setTemplateEngine(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    @Override
    public List<UserFeedback> getListUserFeedbackNotDoFeedbackYet() {
        java.util.Date today = new java.util.Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, 4);
        java.util.Date fourDayAhead = cal.getTime();
        return userFeedbackRepository.getListUserFeedbackNotDoFeedbackYet(new Date(today.getTime()), new Date(fourDayAhead.getTime()));
    }

    @Override
    public void notifyUser() {
        List<UserFeedback> userFeedbacks = getListUserFeedbackNotDoFeedbackYet();
        sendEmail(userFeedbacks);
    }

    private String[] getListEmailFrom(List<UserFeedback> userFeedbackList) {
        List<String> emailList = new ArrayList<>();
        for (UserFeedback u : userFeedbackList) {
            emailList.add(u.getUserByUserId().getMail());
        }
        return (String[]) emailList.toArray();
    }

    private List<Feedback> getListFeedbackFrom(List<UserFeedback> userFeedbackList, User user) {
        List<Feedback> feedbackList = new ArrayList<>();
        for (UserFeedback u : userFeedbackList) {
            if (u.getUserByUserId().getId() == user.getId()) {
                feedbackList.add(u.getFeedbackByFeedbackId());
            }
        }
        return feedbackList;
    }

    private void sendEmail(List<UserFeedback> userFeedbackList) {
        if (userFeedbackList != null && !userFeedbackList.isEmpty()) {
            for (UserFeedback u : userFeedbackList) {
                try {
                    List<Feedback> feedbackList = getListFeedbackFrom(userFeedbackList, u.getUserByUserId());
                    Context ctx = new Context();
                    ctx.setVariable("name", u.getUserByUserId().getFullname());
                    ctx.setVariable("feedbackList", feedbackList);
                    ctx.setVariable("ctx", CONTEXT_PATH);
                    String emailContent = templateEngine.process("mail/remind-mail", ctx);

                    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    helper.setSubject(SUBJECT);
                    helper.setTo(u.getUserByUserId().getMail());
                    helper.setFrom(emailSender);
                    helper.setText(emailContent, true);

                    javaMailSender.send(mimeMessage);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    logger.log(Level.FINE, e.toString());
                }
            }
        }
    }
}
