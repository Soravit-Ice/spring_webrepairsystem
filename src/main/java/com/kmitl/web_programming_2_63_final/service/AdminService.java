package com.kmitl.web_programming_2_63_final.service;

import com.kmitl.web_programming_2_63_final.entity.InformationEntity;
import com.kmitl.web_programming_2_63_final.repository.InformationUserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService {

    private final InformationUserRepository informationUserRepository;
    private final String PROCESSING = "Processing";
    private final String SUCCESS = "Success";

    private final JavaMailSender javaMailSender;

    public AdminService(InformationUserRepository informationUserRepository, JavaMailSender javaMailSender) {
        this.informationUserRepository = informationUserRepository;
        this.javaMailSender = javaMailSender;
    }

    public List<InformationEntity> getfindAll(String keyword){
        if(null != keyword) {
            return informationUserRepository.findAllKeyWord(keyword);
        }
        return informationUserRepository.findAll();
    }


    public void saveInformation(InformationEntity informationEntity){
         informationUserRepository.save(informationEntity);
    }

    public Optional<InformationEntity> GetByID(Integer id){
        Optional<InformationEntity> informationEntity = informationUserRepository.findById(id);

        if(informationEntity.isPresent()){
            return informationEntity;
        }else{
            return Optional.empty();
        }

    }

    public void Delete(Integer id) {
        informationUserRepository.deleteById(id);
    }

    public List<InformationEntity> reportSummaryDaily(){
        LocalDate lt = LocalDate.now();
        List<InformationEntity> informationEntities = informationUserRepository.findAllByDateAndStatusOrderByDateAsc(lt.toString(),PROCESSING);
        return informationEntities;
    }

    public List<InformationEntity> reportSummaryDailySuccess(){
        LocalDate lt = LocalDate.now();
        List<InformationEntity> informationEntities = informationUserRepository.findAllByDateAndStatusOrderByDateAsc(lt.toString(),SUCCESS);
        return informationEntities;
    }

    public List<InformationEntity> reportSummarymonthly(){
        LocalDate lt = LocalDate.now();
        String date = lt.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        List<InformationEntity> informationEntities = informationUserRepository.findAllByMounthly(date,PROCESSING);
        return informationEntities;
    }

    public List<InformationEntity> reportSummarymonthlySuccess(){
        LocalDate lt = LocalDate.now();
        String date = lt.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        List<InformationEntity> informationEntities = informationUserRepository.findAllByMounthly(date,SUCCESS);
        return informationEntities;
    }

    public String sendEmailService(Integer id,String text){
        Optional<InformationEntity> informationEntity = GetByID(id);
        if(Objects.nonNull(informationEntity)){
            sendEmail(informationEntity.get().getEmail(),text,"Kmitl repair system");
        }
        return "redirect:/adminfindall";
    }

    public void sendEmail(String email,String body,String subject){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("springboostice@gmail.com");
        message.setTo(email);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);

        System.out.println("Complete to send email to "+email);

    }




}
