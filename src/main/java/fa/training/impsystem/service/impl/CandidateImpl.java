package fa.training.impsystem.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import fa.training.impsystem.consts.MessageConst;
import fa.training.impsystem.dto.CandidateDTO;
import fa.training.impsystem.dto.ResponseDTO;
import fa.training.impsystem.entities.Candidates;
import fa.training.impsystem.repository.CandidateRepository;
import fa.training.impsystem.service.CandidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateImpl implements CandidateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateService.class);

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public boolean create(CandidateDTO entity, ResponseDTO responseDTO) {
        LOGGER.info("Start create new candidate");
        boolean isCreatedSuccess = false;
        try {
            entity.setCandidateId(0);
            Candidates candidates = mapper.convertValue(entity, Candidates.class);
            candidates.setCreatedDate(new Date());
            candidateRepository.save(candidates);
            isCreatedSuccess = candidates.getCandidateId() != 0 ? true : false;
        } catch (Throwable t) {
            LOGGER.error("Has error while create new candidate", t);
            responseDTO.setHasError(true);
            responseDTO.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("End create new candidates");
        return isCreatedSuccess;
    }

    @Override
    public CandidateDTO readById(Integer id, ResponseDTO responseDTO) {
        LOGGER.info("Start read candidate info with id {}", id);
        CandidateDTO foundCandidate = null;
        try {
            Optional<Candidates> candidates = candidateRepository.findByCandidateIdAndIsDelete(id, false);
            if (candidates.isPresent()) {
                foundCandidate = mapper.convertValue(candidates.get(), CandidateDTO.class);
            } else {
                LOGGER.error("Candidate with id {} not existed", id);
                responseDTO.setHasError(true);
                responseDTO.setMessage(MessageConst.CANDIDATE_NOT_EXISTED_MESSAGE);
            }
        } catch (Throwable t) {
            LOGGER.error("Has error while read candidate info by id", t);
            responseDTO.setHasError(true);
            responseDTO.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("End read candidate info by id");
        return foundCandidate;
    }

    @Override
    public List<CandidateDTO> readAll(ResponseDTO responseDTO) {
        LOGGER.info("Start read all candidate info");
        List<CandidateDTO> candidateDTOs = new ArrayList<>();
        try {
            List<Candidates> candidates = candidateRepository.findByIsDelete(false);
            for (Candidates candidate : candidates) {
                CandidateDTO candidateDTO = mapper.convertValue(candidate, CandidateDTO.class);
                candidateDTOs.add(candidateDTO);
            }
        } catch (Throwable t) {
            LOGGER.error("Has error while read all candidate info", t);
            responseDTO.setHasError(true);
            responseDTO.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("End read all candidate info");
        return candidateDTOs;
    }

    @Override
    public boolean update(CandidateDTO entity, ResponseDTO responseDTO) {
        LOGGER.info("Start update candidate info");
        boolean isUpdatedSuccess = false;
        try {
            if (candidateRepository.existsByCandidateIdAndIsDelete(entity.getCandidateId(), false)) {
                Candidates candidate = mapper.convertValue(entity, Candidates.class);
                candidate.setUpdatedDate(new Date());
                candidateRepository.save(candidate);
                isUpdatedSuccess = true;
            } else {
                LOGGER.error("Candidate with id {} not existed", entity.getCandidateId());
                responseDTO.setHasError(true);
                responseDTO.setMessage(MessageConst.CANDIDATE_NOT_EXISTED_MESSAGE);
            }
        } catch (Throwable t) {
            LOGGER.error("Has error while update candidate info", t);
            responseDTO.setHasError(true);
            responseDTO.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("End update candidate info");
        return isUpdatedSuccess;
    }

    @Override
    public boolean deleteById(Integer id, ResponseDTO responseDTO) {
        LOGGER.info("Start delete candidate info with id {}", id);
        boolean isDeletedSuccess = false;
        try {
            Optional<Candidates> candidate = candidateRepository.findByCandidateIdAndIsDelete(id, false);
            if (candidate.isPresent()) {
                candidate.get().setIsDelete(true);
                candidateRepository.save(candidate.get());
                isDeletedSuccess = true;
            } else {
                LOGGER.error("Candidate with id {} not existed", id);
                responseDTO.setHasError(true);
                responseDTO.setMessage(MessageConst.CANDIDATE_NOT_EXISTED_MESSAGE);
            }
        } catch (Throwable t) {
            LOGGER.error("Has error while delete candidate info", t);
            responseDTO.setHasError(true);
            responseDTO.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("End delete candidate info with id {}", id);
        return isDeletedSuccess;
    }
}
