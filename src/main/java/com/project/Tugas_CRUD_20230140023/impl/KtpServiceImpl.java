package com.project.Tugas_CRUD_20230140023.impl;

import com.project.Tugas_CRUD_20230140023.dto.KtpDto;
import com.project.Tugas_CRUD_20230140023.entity.Ktp;
import com.project.Tugas_CRUD_20230140023.mapper.KtpMapper;
import com.project.Tugas_CRUD_20230140023.repository.KtpRepository;
import com.project.Tugas_CRUD_20230140023.service.KtpService;
import com.project.Tugas_CRUD_20230140023.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository ktpRepository;

    @Autowired
    private KtpMapper ktpMapper;

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public KtpDto addKtp(KtpDto request) {
        validationUtil.validate(request);

        if (ktpRepository.findByNomorKtp(request.getNomorKtp()).isPresent()) {
            throw new RuntimeException("Nomor KTP sudah terdaftar!");
        }

        Ktp ktp = ktpMapper.toEntity(request);
        Ktp savedKtp = ktpRepository.save(ktp);
        return ktpMapper.toDto(savedKtp);
    }

    @Override
    public List<KtpDto> getAllKtp() {
        return ktpRepository.findAll().stream()
                .map(ktpMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public KtpDto getKtpById(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan!"));
        return ktpMapper.toDto(ktp);
    }

    @Override
    public KtpDto updateKtp(Integer id, KtpDto request) {
        validationUtil.validate(request);

        Ktp existingKtp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan!"));

        if (!existingKtp.getNomorKtp().equals(request.getNomorKtp()) &&
                ktpRepository.findByNomorKtp(request.getNomorKtp()).isPresent()) {
            throw new RuntimeException("Nomor KTP baru sudah terdaftar milik orang lain!");
        }

        existingKtp.setNomorKtp(request.getNomorKtp());
        existingKtp.setNamaLengkap(request.getNamaLengkap());
        existingKtp.setAlamat(request.getAlamat());
        existingKtp.setTanggalLahir(request.getTanggalLahir());
        existingKtp.setJenisKelamin(request.getJenisKelamin());

        Ktp updatedKtp = ktpRepository.save(existingKtp);
        return ktpMapper.toDto(updatedKtp);
    }

    @Override
    public void deleteKtp(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan!"));
        ktpRepository.delete(ktp);
    }
}
