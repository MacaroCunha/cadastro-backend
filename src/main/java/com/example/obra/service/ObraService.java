package com.example.obra.service;

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

    public ObraModel createObra(ObraModel obra) {
        return obraRepository.save(obra);
    }

    public ObraModel updateObra(Long id, ObraModel obra) {
        return obraRepository.findById(id)
                .map(existingObra -> {
                    obra.setId(existingObra.getId());
                    return obraRepository.save(obra);
                })
                .orElse(null);
    }
    public void deleteObra(Long id) {
        obraRepository.deleteById(id);
    }
}



