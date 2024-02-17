package com.example.obra.service;

import com.example.obra.dto.request.ObraRequest;
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
        ObraModel novaObra = ObraModel.builder()
                .nomeObra(obraRequest.getNomeObra())
                .descObra(obraRequest.getDescObra())
                .dataPub(obraRequest.getDataPub())
                .dataExpo(obraRequest.getDataExpo())
                // Adicione outros campos conforme necessário
                .build();

        return obraRepository.save(novaObra);
    }

    public ObraModel updateObra(Long id, ObraRequest obraRequest) {
        return obraRepository.findById(id)
                .map(existingObra -> {
                    existingObra.setNomeObra(obraRequest.getNomeObra());
                    existingObra.setDescObra(obraRequest.getDescObra());
                    existingObra.setDataPub(obraRequest.getDataPub());
                    existingObra.setDataExpo(obraRequest.getDataExpo());
                    return obraRepository.save(existingObra);
                })
                .orElse(null);
    }

    public void deleteObra(Long id) {
        obraRepository.deleteById(id);
    }
}






