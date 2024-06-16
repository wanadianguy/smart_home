package com.insa.smarthomecontroller.api.service;

import com.insa.smarthomecontroller.api.dto.LuminosityDTO;
import com.insa.smarthomecontroller.api.exception.LuminosityException;
import com.insa.smarthomecontroller.api.mapper.LuminosityMapper;
import com.insa.smarthomecontroller.api.mapper.LuminosityMapperImpl;
import com.insa.smarthomecontroller.api.model.Luminosity;
import com.insa.smarthomecontroller.api.repository.LuminosityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LuminosityService {

    private final LuminosityMapper luminosityMapper;
    private final LuminosityRepository luminosityRepository;

    public LuminosityService(LuminosityRepository luminosityRepository) {
        this.luminosityRepository = luminosityRepository;
        luminosityMapper = new LuminosityMapperImpl();
    }

    public LuminosityDTO getCurrentLuminosity(String sensorId) throws LuminosityException {
        Luminosity latestLuminosity = luminosityRepository.findFirstBySensorIdOrderByDateDesc(sensorId);

        if (Objects.isNull(latestLuminosity)) {
            throw new LuminosityException("No luminosity found.");
        }

        return luminosityMapper.mapLuminosityDTO(latestLuminosity);
    }

    public List<LuminosityDTO> getAllLuminositiesOfToday(String sensorId) throws LuminosityException {
        LocalDate today = LocalDate.now();
        List<Luminosity> luminosities = luminosityRepository.findAllBySensorIdAndDateBetween(sensorId, today, today.plusDays(1));

        if (luminosities.isEmpty()) {
            throw new LuminosityException("No luminosity found for today.");
        }

        return luminosities.stream().map(luminosityMapper::mapLuminosityDTO).sorted(Comparator.comparing(LuminosityDTO::getTime)).toList();
    }

    public LuminosityDTO createLuminosity(LuminosityDTO luminosityDTO) throws LuminosityException {
        Luminosity luminosity = luminosityMapper.mapLuminosity(luminosityDTO);
        Luminosity savedLuminosity = luminosityRepository.save(luminosity);

        return luminosityMapper.mapLuminosityDTO(savedLuminosity);
    }

    public LuminosityDTO deleteLuminosity(String id) throws LuminosityException {
        Optional<Luminosity> optionalLuminosity = luminosityRepository.findById(id);

        if (optionalLuminosity.isEmpty()) {
            throw new LuminosityException("Luminosity not found with id: " + id);
        }

        Luminosity luminosityToDelete = optionalLuminosity.get();
        luminosityRepository.delete(luminosityToDelete);
        return luminosityMapper.mapLuminosityDTO(luminosityToDelete);
    }
}
