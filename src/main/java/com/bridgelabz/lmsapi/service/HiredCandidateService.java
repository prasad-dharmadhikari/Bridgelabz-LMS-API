package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.HiredCandidateDTO;
import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.model.HiredCandidate;
import com.bridgelabz.lmsapi.repository.HiredCandidateRepository;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Service
public class HiredCandidateService implements IHiredCandidateService {

    @Autowired
    private HiredCandidateRepository hiredCandidateRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Response saveDataInBatchToDatabase(MultipartFile file) {
        HiredCandidateDTO hiredCandidateDTO = new HiredCandidateDTO();
        boolean flag = true;
        try (InputStream fis = file.getInputStream()) {
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            //Iterate through each rows one by one
            Iterator rows = sheet.rowIterator();
            XSSFCell cell;
            //For each row, iterate through all the columns
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                if (!flag) {
                    while (cells.hasNext()) {
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setId((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setFirstName(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setMiddleName(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setLastName(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setEmail(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setHiredCity(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setDegree(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setHiredDate(cell.getDateCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setMobileNumber((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setPermanentPincode((int) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setHiredLab(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setAttitude(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setCommunicationRemark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setKnowledgeRemark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setAggregateRemark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setStatus(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setCreatorStamp(cell.getDateCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setCreatorUser(cell.getStringCellValue());
                        save(hiredCandidateDTO);
                    }
                }
                flag = false;
            }
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
        return new Response(200, "Data Saved Successfully");
    }

    @Override
    public List getHiredCandidateList() {
        return hiredCandidateRepository.findAll();
    }

    @Override
    public void save(HiredCandidateDTO hiredCandidateDTO) throws MessagingException {
        HiredCandidate hiredCandidate = modelMapper.map(hiredCandidateDTO, HiredCandidate.class);
        hiredCandidateRepository.save(hiredCandidate);
        this.sendMail(hiredCandidate);
    }

    @Override
    public HiredCandidate getCandidateProfile(long id) {
        return hiredCandidateRepository.findById(id).get();
    }

    @Override
    public Response updateCandidateStatus(String response, String email) {
        HiredCandidate candidate = hiredCandidateRepository.findByEmail(email);
        candidate.setStatus(response);
        hiredCandidateRepository.save(candidate);
        return new Response(200, "Status Updated Successfully");
    }

    @Override
    public void sendMail(HiredCandidate hiredCandidate) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(hiredCandidate.getEmail());
        helper.setText("Hii, " + hiredCandidate.getFirstName() + " " + hiredCandidate.getLastName() + " " +
                "You have been selected to our Fellowship Program. please click on the following " +
                "link to accept the offer. " + "\n" + "http://localhost:8080/hiredcandidate" +
                "/updatestatus?response=Accepted&email=" + hiredCandidate.getEmail() + "\n\n"
                + "Please click on following link to reject the offer. " + "\n" + "http://localhost:8080/" +
                "hiredcandidate/updatestatus?response=Rejected&email=" + hiredCandidate.getEmail() + "\n\n");
        helper.setSubject("Fellowship Offer From BridgeLabz");
        javaMailSender.send(message);
    }

    @Override
    public Response sendJobOfferNotification() throws MessagingException {
        List<HiredCandidate> acceptedCandidates = hiredCandidateRepository.findByStatus("Accepted");
        for (HiredCandidate candidate : acceptedCandidates) {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(candidate.getEmail());
            helper.setText("Hii, " + candidate.getFirstName() + " " + candidate.getLastName() + " " +
                    "As per your confirmation, You have been officially admitted to BridgeLabz Fellowship" +
                    " Program." + "\n\n" + "We need you to update your personal information, " +
                    "your bank information and your educational information " +
                    " for our records." + "\n\n" + "Click on following links to do the same." +
                    "\n\n" + "For Personal Information : " + "\n\n" +
                    "http://localhost:8080/fellowshipcandidate/jointhecandidate" + "\n\n" +
                    "For Educational information : " + "\n\n" +
                    "http://localhost:8080/candidatequalification/educationalinfo" + "\n\n" +
                    "For Bank Information : " + "\n\n" +
                    "http://localhost:8080/candidatebank/bankinfo"
            );
            helper.setSubject("Fellowship Job from BridgeLabz");
            javaMailSender.send(message);
        }
        return new Response(200, "Mail Sent Successfully");
    }
}
