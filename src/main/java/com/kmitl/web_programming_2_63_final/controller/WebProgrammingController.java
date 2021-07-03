package com.kmitl.web_programming_2_63_final.controller;

import com.kmitl.web_programming_2_63_final.dto.InformationUserDTO;
import com.kmitl.web_programming_2_63_final.dto.LoginDTO;
import com.kmitl.web_programming_2_63_final.entity.InformationEntity;
import com.kmitl.web_programming_2_63_final.service.AdminService;
import com.kmitl.web_programming_2_63_final.service.WebProgrammingService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Controller
public class WebProgrammingController {

    private final WebProgrammingService webProgrammingService;
    private final AdminService adminService;

    public WebProgrammingController(WebProgrammingService webProgrammingService, AdminService adminService) {
        this.webProgrammingService = webProgrammingService;
        this.adminService = adminService;
    }

    @GetMapping("/")
    public String HomeApp(){
        return "index";
    }

    @GetMapping("/login")
    public String getLoginAdmin(Model model){
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("LoginDTO",loginDTO);
        return "login";
    }

    @PostMapping("/login")
    public String postLoginAdmin(@ModelAttribute("LoginDTO") LoginDTO loginDTO){
        String result = "redirect:/";
        if(Objects.nonNull(loginDTO)) {
             result = webProgrammingService.checkAdminLogin(loginDTO.getUsername(), loginDTO.getPassword());
        }
        return result;
    }

    @GetMapping("/informationuser")
    public String getinformationEngine(Model model){
        LocalDate lt = LocalDate.now();
        InformationUserDTO informationUserDTO = new InformationUserDTO();
        model.addAttribute("InformtaionUser",informationUserDTO);
        model.addAttribute("datenow",lt);
        return "informationengine";
    }

    @PostMapping("/informationuser")
    public String postinformationEngine(@RequestParam("files") MultipartFile[] files, @ModelAttribute("InformtaionUser") InformationUserDTO informationUserDTO , Model model) throws IOException {
        Integer result = 0;
        for(MultipartFile file:files){
            if(Objects.nonNull(informationUserDTO)){
            result = webProgrammingService.saveInformation(informationUserDTO,file);
            }
        }
        if(null != result){
            model.addAttribute("id","ID ของคุณ คือ "+result+" สามารถเช็คสถานะได้ที่ Check Status");
            return "success";
        }else{
            return "fail";
        }

    }



    @RequestMapping("/adminfindall")
    public String postfindAllAdmin(@Param("keyword") String keyword, Model model){
        List<InformationEntity> result = adminService.getfindAll(keyword);
        model.addAttribute("ListInformation",result);
        return "adminform";
    }


    @PostMapping("/save")
    public String saveData(@ModelAttribute("InformationUserDTO") InformationEntity informationEntity, Model model) {
        adminService.saveInformation(informationEntity);
        return "redirect:/adminfindall";
    }

    @RequestMapping("/edit/{id}")
    public String editData(@PathVariable(name = "id") Integer id, Model model) {
        Optional<InformationEntity>  informationEntity = adminService.GetByID(id);
        model.addAttribute("data", informationEntity);
        return "editdata";
    }


    @RequestMapping("/delete/{id}")
    public String deleteData(@PathVariable(name = "id") Integer id) {
        adminService.Delete(id);
        return "redirect:/adminfindall";
    }


    @GetMapping("/dowloadFile/{id}")
    public ResponseEntity<ByteArrayResource> dowloadFile(@PathVariable(name = "id") Integer id) {
        Optional<InformationEntity> informationEntity = adminService.GetByID(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(informationEntity.get().getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+informationEntity.get().getDocName()+"\"")
                .body(new ByteArrayResource(informationEntity.get().getPicture()));
    }



    @RequestMapping("/report")
    public String reportSummary(Model model) {
        List<InformationEntity> resultReportSummaryDaily = adminService.reportSummaryDaily();
        List<InformationEntity> resultReportSummaryDailySuccess = adminService.reportSummaryDailySuccess();
        List<InformationEntity> resultReportSummarymounth = adminService.reportSummarymonthly();
        List<InformationEntity> resultReportSummarymounthSuccess = adminService.reportSummarymonthlySuccess();
        model.addAttribute("ListInformationReportDaily",resultReportSummaryDaily);
        model.addAttribute("ListInformationReportMounthly",resultReportSummarymounth);
        model.addAttribute("ListInformationReportDailySuccess",resultReportSummaryDailySuccess);
        model.addAttribute("ListInformationReportMounthlySuccess",resultReportSummarymounthSuccess);
        return "reportsummary";
    }


    @RequestMapping("/send-email-user/{id}")
    public String sendEmailToUser(@PathVariable(name = "id") Integer id,Model model) {
        Optional<InformationEntity>  informationEntity = adminService.GetByID(id);
        if(informationEntity.isPresent()) {
            model.addAttribute("id", informationEntity.get().getId());
            model.addAttribute("name", "Name : " + informationEntity.get().getFirstname() + " " + informationEntity.get().getLastname());
        }
        return "sendemail";
    }

    @RequestMapping("/send-email-user-send/{id}")
    public String sendEmailToUserSave(@RequestParam(name ="text" , required = false) String text,@PathVariable(name = "id") Integer id,Model model) {
        Optional<InformationEntity>  informationEntity = adminService.GetByID(id);
        String result = adminService.sendEmailService(id,text);

        return result;
    }



    @GetMapping ("/checkstatus")
    public String checkstatus(@RequestParam(name = "id" , required = false) Integer id,Model model) {
        if(null != id) {
            Optional<InformationEntity>  informationEntity = adminService.GetByID(id);
            if(informationEntity.isPresent()){
                if(informationEntity.get().getStatus().equals("Processing")){
                    model.addAttribute("status","กำลังดำเนินงานการซ่อมครับ");
                }else{
                   model.addAttribute("status","ซ่อมเสร็จแล้วครับ");
                }

            }else{
                model.addAttribute("status", "ไม่มีการแจ้งซ่อมรหัสสินค้านี้");
            }

            return "checkstatus";
        }else{
            model.addAttribute("status", "โปรดกรอก ID สินค้าของคุณ");
            return "checkstatus";
        }
    }



}
