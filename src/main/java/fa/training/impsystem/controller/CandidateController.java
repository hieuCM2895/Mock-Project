package fa.training.impsystem.controller;

import fa.training.impsystem.consts.CommonConst;
import fa.training.impsystem.consts.MessageConst;
import fa.training.impsystem.dto.CandidateDTO;
import fa.training.impsystem.dto.ResponseDTO;
import fa.training.impsystem.service.CandidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CandidateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    private CandidateService candidateService;

    @GetMapping(CommonConst.HR_CANDIDATE_LIST_URL)
    public String getCandidateList(Model model) {
        LOGGER.info("Start get all candidate info");
        ResponseDTO responseDTO = new ResponseDTO();
        List<CandidateDTO> candidates = candidateService.readAll(responseDTO);
        if (responseDTO.hasError()) {
            model.addAttribute("responseStatus", responseDTO);
        }
        model.addAttribute("candidates", candidates);
        LOGGER.info("End get all candidate info");
        return "hr/candidate-list";
    }

    @GetMapping(CommonConst.HR_CANDIDATE_UPDATE_URL)
    public String getCandidateDetailUpdatePage(@RequestParam(name = "id", required = false) Integer id, Model model) {
        LOGGER.info("Start get candidate detail update page");
        ResponseDTO responseDTO = new ResponseDTO();
        CandidateDTO candidate = null;
        if (id != null) {
            candidate = candidateService.readById(id, responseDTO);
        } else {
            candidate = new CandidateDTO();
        }
        model.addAttribute("candidate", candidate);
        if (responseDTO.hasError()) {
            model.addAttribute("responseStatus", responseDTO);
        }
        LOGGER.info("End get candidate detail update page");
        return "hr/candidate-edit";
    }

    @PostMapping(CommonConst.HR_CANDIDATE_UPDATE_URL)
    public String updateCandidateDetail(@ModelAttribute("candidate") @Valid CandidateDTO candidate,
                                        BindingResult result, Model model) {
        LOGGER.info("Start update candidate detail");
        if (result.hasErrors()) {
            LOGGER.info("Invalid input for candidate from user");
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            if (candidate.getCandidateId() != 0) {
                // Case Update existed candidate
                if (candidateService.update(candidate, responseDTO)) {
                    responseDTO.setMessage(MessageConst.UPDATE_CANDIDATE_SUCCESS_MESSAGE);
                }
            } else {
                // Case Create new candidate
                if (candidateService.create(candidate, responseDTO)) {
                    responseDTO.setMessage(MessageConst.CREATE_CANDIDATE_SUCCESS_MESSAGE);
                }
            }
            model.addAttribute("responseStatus", responseDTO);
        }
        LOGGER.info("End update candidate detail");
        return "hr/candidate-edit";
    }

    @GetMapping(CommonConst.HR_CANDIDATE_DELETE_URL)
    public String deleteCandidateById(@RequestParam("id") Integer id, Model model) {
        LOGGER.info("Start delete candidate by id");
        ResponseDTO responseDTO = new ResponseDTO();
        candidateService.deleteById(id, responseDTO);
        if (responseDTO.hasError()) {
            model.addAttribute("responseStatus", responseDTO);
        }
        LOGGER.info("End delete candidate by id");
        return "redirect:".concat(CommonConst.HR_CANDIDATE_LIST_URL);
    }
}
