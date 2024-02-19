package com.example.obra.service;

import com.example.obra.dto.request.ObraRequest;
import com.example.obra.exception.ExceptionObra;
import com.example.obra.message.ObraMessage;
import com.example.obra.model.ObraModel;
import com.example.obra.repository.ObraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObraService {

    private final ObraRepository obraRepository;

    public List<ObraModel> getAllObras() {
        return obraRepository.findAll();
    }

    public Optional<ObraModel> getObraById(Long id) {
        return obraRepository.findById(id);
    }

    public ObraModel createObra(ObraRequest obraRequest) {
        try {
            validateObraRequest(obraRequest);
            ObraModel novaObra = ObraModel.builder()
                    .nomeObra(obraRequest.getNomeObra())
                    .descObra(obraRequest.getDescObra())
                    .dataPub(obraRequest.getDataPub())
                    .dataExpo(obraRequest.getDataExpo())
                    .build();

            return obraRepository.save(novaObra);
        } catch (Exception e) {
            throw new ExceptionObra(ObraMessage.CREATED_OBRA);
        }
    }

    public ObraModel updateObra(Long id, ObraRequest obraRequest) {
        try {
            validateObraRequest(obraRequest);
            return obraRepository.findById(id)
                    .map(existingObra -> {
                        existingObra.setNomeObra(obraRequest.getNomeObra());
                        existingObra.setDescObra(obraRequest.getDescObra());
                        existingObra.setDataPub(obraRequest.getDataPub());
                        existingObra.setDataExpo(obraRequest.getDataExpo());
                        return obraRepository.save(existingObra);
                    })
                    .orElseThrow(() -> new IllegalArgumentException(String.format(ObraMessage.OBRA_NOT_FOUND, id)));
        } catch (Exception e) {
            throw new ExceptionObra(ObraMessage.UPDATED_OBRA);
        }
    }

    public void deleteObra(Long id) {
        obraRepository.findById(id).ifPresentOrElse(
                obraRepository::delete,
                () -> {
                    throw new IllegalArgumentException(String.format(ObraMessage.OBRA_NOT_FOUND, id));
                }
        );
    }

    public Optional<ObraModel> getObraModelById(Long id) {
        return obraRepository.findById(id);
    }

    private void validateObraRequest(ObraRequest obraRequest) {
        // Adicionar lógica de validação
    }
}


