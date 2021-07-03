package com.kmitl.web_programming_2_63_final.service;

import com.kmitl.web_programming_2_63_final.dto.InformationUserDTO;
import com.kmitl.web_programming_2_63_final.entity.InformationEntity;
import com.kmitl.web_programming_2_63_final.repository.InformationUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class WebProgrammingService {

    private final String USERNMAE = "admin";
    private final String PASSWORD = "1234";
    private final InformationUserRepository informationUserRepository;

    public WebProgrammingService(InformationUserRepository informationUserRepository) {
        this.informationUserRepository = informationUserRepository;
    }

    public String checkAdminLogin(String username,String password){
        if(username.equals(USERNMAE) && password.equals(PASSWORD)){
            return "redirect:/adminfindall";
        }else {
            return "redirect:/";
        }
    }

    public Integer saveInformation(InformationUserDTO informationUserDTO , MultipartFile file) throws IOException {
        LocalDate lt = LocalDate.now();
        String docname = file.getOriginalFilename();
        InformationEntity informationEntity = new InformationEntity();
        if(Objects.nonNull(informationUserDTO)){
            informationEntity.setFirstname(informationUserDTO.getFirstname());
            informationEntity.setLastname(informationUserDTO.getLastname());
            informationEntity.setEmail(informationUserDTO.getEmail());
            informationEntity.setPhone(informationUserDTO.getPhone());
            informationEntity.setPlace(informationUserDTO.getPlace());
            informationEntity.setObjectfail(informationUserDTO.getObjectfail());
            informationEntity.setObjectcondition(informationUserDTO.getObjectcondition());
            informationEntity.setDate(lt.toString());
            informationEntity.setDocName(docname);
            informationEntity.setDocType(file.getContentType());
            informationEntity.setPicture(file.getBytes());
            informationEntity.setStatus("Processing");

            try {
                informationUserRepository.save(informationEntity);
                return informationEntity.getId();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return informationEntity.getId();
    }



}
